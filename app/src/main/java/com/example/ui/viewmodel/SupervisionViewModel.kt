package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.AuditoriaAudit
import com.example.data.GerenciaInfo
import com.example.data.Gestor
import com.example.data.GestorStatus
import com.example.data.SampleData
import com.example.data.SupervisorInfo
import com.example.data.WeeklySummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppNavTab(val title: String) {
    PORTAL("Inicio"),
    HACER_HOY("Hacer Hoy"),
    SEMANAL("Semanal"),
    SUPERVISION("Supervisión"),
    CONFIG("Config")
}

data class UiState(
    val currentTab: AppNavTab = AppNavTab.PORTAL,
    val selectedDayNumber: Int = 14,
    val gestores: List<Gestor> = SampleData.initialGestores,
    val supervisor: SupervisorInfo = SupervisorInfo(),
    val gerencia: GerenciaInfo = GerenciaInfo(),
    val weeklySummary: WeeklySummary = WeeklySummary(),
    val currentAudit: AuditoriaAudit = AuditoriaAudit(),
    val snackbarMessage: String? = null,
    val isFilterDatosActive: Boolean = true,
    val isSyncing: Boolean = false
) {
    val totalTarget: Double
        get() = gestores.sumOf { it.targetAmount }

    val currentAmountTotal: Double
        get() = gestores.sumOf { it.currentAmount }

    val deficitAmountTotal: Double
        get() = (totalTarget - currentAmountTotal).coerceAtLeast(0.0)

    val globalPercentage: Int
        get() = if (totalTarget > 0) ((currentAmountTotal / totalTarget) * 100).toInt() else 0

    val activeGestoresCount: Int
        get() = gestores.count { it.isAssignedToday }
}

class SupervisionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun navigateTo(tab: AppNavTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun selectDay(dayNumber: Int) {
        _uiState.update { it.copy(selectedDayNumber = dayNumber) }
    }

    fun toggleDatosFilter() {
        _uiState.update { it.copy(isFilterDatosActive = !it.isFilterDatosActive) }
    }

    fun toggleGestorAssignment(gestorId: String) {
        _uiState.update { state ->
            val updated = state.gestores.map { g ->
                if (g.id == gestorId) {
                    val nextAssigned = !g.isAssignedToday
                    val nextStatus = if (nextAssigned) "Confirmado" else "Por confirmar"
                    g.copy(isAssignedToday = nextAssigned, confirmationStatus = nextStatus)
                } else g
            }
            state.copy(gestores = updated)
        }
    }

    fun openAuditForGestor(gestor: Gestor) {
        _uiState.update { state ->
            val updatedAudit = state.currentAudit.copy(
                gestorName = gestor.fullName,
                gestorSubtitle = "${gestor.id} • ${gestor.routeName}",
                targetAmount = gestor.targetAmount,
                verifiedAmount = gestor.currentAmount,
                verifiedCount = gestor.visitedCount,
                targetVisits = gestor.totalVisits
            )
            state.copy(
                currentAudit = updatedAudit,
                currentTab = AppNavTab.SUPERVISION
            )
        }
    }

    fun toggleAuditSectionAConoce() {
        _uiState.update { it.copy(currentAudit = it.currentAudit.copy(conoceEsquema = !it.currentAudit.conoceEsquema)) }
    }

    fun toggleAuditPaso(stepIndex: Int) {
        _uiState.update { state ->
            val a = state.currentAudit
            val updated = when (stepIndex) {
                1 -> a.copy(paso1Saludo = !a.paso1Saludo)
                2 -> a.copy(paso2Adeudo = !a.paso2Adeudo)
                3 -> a.copy(paso3Motivo = !a.paso3Motivo)
                4 -> a.copy(paso4Soluciones = !a.paso4Soluciones)
                5 -> a.copy(paso5ObjecionesObservacion = !a.paso5ObjecionesObservacion)
                6 -> a.copy(paso6Cierre = !a.paso6Cierre)
                7 -> a.copy(paso7Despedida = !a.paso7Despedida)
                else -> a
            }
            state.copy(currentAudit = updated)
        }
    }

    fun toggleViccAplica() {
        _uiState.update { state ->
            state.copy(currentAudit = state.currentAudit.copy(conoceViccAplica = !state.currentAudit.conoceViccAplica))
        }
    }

    fun toggleSecurityItem(itemIndex: Int) {
        _uiState.update { state ->
            val a = state.currentAudit
            val updated = when (itemIndex) {
                1 -> a.copy(equipoSeguridad = !a.equipoSeguridad)
                2 -> a.copy(licenciaVigente = !a.licenciaVigente)
                3 -> a.copy(tarjetaCirculacionVigente = !a.tarjetaCirculacionVigente)
                4 -> a.copy(estadoUnidad = !a.estadoUnidad)
                else -> a
            }
            state.copy(currentAudit = updated)
        }
    }

    fun toggleToolItem(itemIndex: Int) {
        _uiState.update { state ->
            val a = state.currentAudit
            val updated = when (itemIndex) {
                1 -> a.copy(terminalPax = !a.terminalPax)
                2 -> a.copy(sinPendientes = !a.sinPendientes)
                else -> a
            }
            state.copy(currentAudit = updated)
        }
    }

    fun updateSupervisorInfo(name: String, employeeId: String, phone: String, shift: String) {
        _uiState.update {
            it.copy(
                supervisor = it.supervisor.copy(
                    name = name,
                    employeeId = employeeId,
                    phone = phone,
                    assignedShift = shift
                )
            )
        }
    }

    fun addNewGestor(name: String, id: String, route: String, targetAmount: Double) {
        val newGestor = Gestor(
            id = id.ifBlank { "GIC-0${_uiState.value.gestores.size + 1}" },
            number = _uiState.value.gestores.size + 1,
            fullName = name.ifBlank { "Nuevo Gestor" },
            shortName = name.split(" ").firstOrNull() ?: "Gestor",
            routeName = route.ifBlank { "Ruta General" },
            zone = "Zona Operativa",
            phone = "771 000 0000",
            targetAmount = if (targetAmount > 0) targetAmount else 12000.0,
            currentAmount = 0.0,
            status = GestorStatus.ACTIVO,
            visitedCount = 0,
            totalVisits = 18,
            lastReportTime = "Recién integrado",
            isAssignedToday = true,
            confirmationStatus = "Confirmado"
        )
        _uiState.update {
            it.copy(
                gestores = it.gestores + newGestor,
                snackbarMessage = "Gestor ${newGestor.fullName} agregado a la cuadrilla"
            )
        }
    }

    fun deleteGestor(id: String) {
        _uiState.update { state ->
            state.copy(
                gestores = state.gestores.filterNot { it.id == id },
                snackbarMessage = "Gestor eliminado de la cuadrilla"
            )
        }
    }

    fun sealAndSaveAudit() {
        _uiState.update {
            it.copy(
                currentAudit = it.currentAudit.copy(isSealed = true),
                snackbarMessage = "Auditoría sellada y guardada exitosamente con firma digital"
            )
        }
    }

    fun saveDayPlanning() {
        _uiState.update {
            it.copy(snackbarMessage = "Planificación del día guardada exitosamente en Zacualtipán")
        }
    }

    fun saveOperativeData() {
        _uiState.update {
            it.copy(snackbarMessage = "Datos operativos cifrados y sincronizados con el servidor GOC")
        }
    }

    fun clearSnackbar() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }
}
