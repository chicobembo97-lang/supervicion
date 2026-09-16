package com.example.data

enum class GestorStatus(val label: String) {
    EN_RUTA("En Ruta"),
    SUPERVISANDO("Supervisando"),
    PENDIENTE("Pendiente"),
    ACTIVO("Activo")
}

data class Gestor(
    val id: String,
    val number: Int,
    val fullName: String,
    val shortName: String,
    val routeName: String,
    val zone: String,
    val phone: String,
    val targetAmount: Double,
    val currentAmount: Double,
    val status: GestorStatus,
    val avatarResId: Int? = null,
    val avatarUrl: String? = null,
    val visitedCount: Int = 16,
    val totalVisits: Int = 20,
    val lastReportTime: String = "Hace 8 min",
    val startTime: String = "09:15 AM",
    val isAssignedToday: Boolean = true,
    val confirmationStatus: String = "Confirmado"
) {
    val remainingAmount: Double
        get() = (targetAmount - currentAmount).coerceAtLeast(0.0)

    val progressPercent: Int
        get() = if (targetAmount > 0) ((currentAmount / targetAmount) * 100).toInt().coerceIn(0, 100) else 0
}

data class DayPerformance(
    val dayKey: String,
    val dayName: String,
    val dayNumber: Int,
    val amountAchieved: Double,
    val isTargetMet: Boolean,
    val isPastOrToday: Boolean
)

data class WeeklySummary(
    val weekNumber: Int = 42,
    val weeklyTarget: Double = 185000.0,
    val currentAmount: Double = 112400.0,
    val deficitAmount: Double = 72600.0,
    val percentAchieved: Float = 60.8f,
    val expectedPacing: String = "+4.2% vs esperado",
    val remainingWorkingDays: Int = 5,
    val dailyAverage: String = "$26.4k/día prom.",
    val dailyTarget: Double = 26400.0,
    val dailyPerformances: List<DayPerformance> = listOf(
        DayPerformance("LUN", "Lun", 14, 32000.0, true, true),
        DayPerformance("MAR", "Mar", 15, 28000.0, true, true),
        DayPerformance("MIE", "Mié", 16, 25000.0, false, true),
        DayPerformance("JUE", "Jue", 17, 0.0, false, false),
        DayPerformance("VIE", "Vie", 18, 0.0, false, false),
        DayPerformance("SAB", "Sáb", 19, 0.0, false, false)
    )
)

data class SupervisorInfo(
    val name: String = "Lic. Roberto Aguilar Morales",
    val titularName: String = "Ing. Roberto Morales C.",
    val employeeId: String = "SUP-0428",
    val phone: String = "55 4920 3184",
    val assignedShift: String = "Matutino"
)

data class GerenciaInfo(
    val division: String = "Gerencia Regional Hidalgo Norte",
    val plaza: String = "GCC Zacualtipán - Sierra Alta",
    val costCenter: String = "GER-ZN-104",
    val zoneManager: String = "Ing. Laura Patricia Trejo"
)

data class AuditoriaAudit(
    val folio: String = "Folio: #SUP-2024-089",
    val date: String = "24 Oct 2024",
    val time: String = "11:30 AM",
    val plaza: String = "Zacualtipan",
    val modalidad: String = "Acompañamiento",
    val supervisorName: String = "Roberto Aguilar M.",
    val gestorName: String = "Carlos Mendoza R.",
    val gestorSubtitle: String = "GIC 1 • Ruta 14 (Barrio Arriba)",
    val targetAmount: Double = 12000.0,
    val verifiedAmount: Double = 9600.0,
    val verifiedCount: Int = 16,
    val targetVisits: Int = 20,
    val visitedCount: Int = 16,
    
    // Sección A
    val conoceEsquema: Boolean = true,
    val sueldoBase: Boolean = true,
    val variableCobranza: Boolean = true,
    val participacionBolsa: Boolean = true,
    val variableInvestigaciones: Boolean = true,

    // Sección B
    val paso1Saludo: Boolean = true,
    val paso2Adeudo: Boolean = true,
    val paso3Motivo: Boolean = true,
    val paso4Soluciones: Boolean = true,
    val paso5ObjecionesObservacion: Boolean = true,
    val paso6Cierre: Boolean = true,
    val paso7Despedida: Boolean = true,

    // Sección C
    val conoceViccAplica: Boolean = true,
    val dominioConceptual: String = "Excelente",
    val ejecucionCampo: String = "100% Alineada",

    // Sección D
    val equipoSeguridad: Boolean = true,
    val licenciaVigente: Boolean = true,
    val tarjetaCirculacionVigente: Boolean = true,
    val estadoUnidad: Boolean = true,

    // Sección E
    val terminalPax: Boolean = true,
    val sinPendientes: Boolean = true,

    // Observaciones
    val hallazgosNotas: String = "\"El gestor Carlos Mendoza demuestra excelente dominio del esquema de compensación y la sistemática VICC en Barrio Arriba. Cuenta con equipo de protección en regla y terminal PAX operando al 100%. Se sugiere reforzar el paso 5 (Manejo de objeciones) en acuerdos con clientes ausentes.\"",
    val compromiso: String = "Completar las 4 visitas pendientes de cobranza antes de las 16:30 hrs y reportar el depósito de $2,400 en plaza Zacualtipan.",
    
    // Firmas
    val supervisorFirmaTime: String = "11:32:15 AM",
    val gestorFirmaTime: String = "11:33:04 AM",
    val isSealed: Boolean = true
)
