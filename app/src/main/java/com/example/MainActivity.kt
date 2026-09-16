package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.ConfigScreen
import com.example.ui.screens.HacerHoyScreen
import com.example.ui.screens.PortalScreen
import com.example.ui.screens.SemanalScreen
import com.example.ui.screens.SupervisionScreen
import com.example.ui.theme.BrandBlueInteractive
import com.example.ui.theme.BrandBlueLight
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCanvas
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.AppNavTab
import com.example.ui.viewmodel.SupervisionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

data class NavItem(
    val tab: AppNavTab,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun MainApp(viewModel: SupervisionViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showCierreDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(
                message = msg,
                duration = SnackbarDuration.Short
            )
            viewModel.clearSnackbar()
        }
    }

    val navItems = listOf(
        NavItem(
            tab = AppNavTab.PORTAL,
            label = "Inicio",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavItem(
            tab = AppNavTab.HACER_HOY,
            label = "Hacer Hoy",
            selectedIcon = Icons.Filled.Assignment,
            unselectedIcon = Icons.Outlined.Assignment
        ),
        NavItem(
            tab = AppNavTab.SEMANAL,
            label = "Semanal",
            selectedIcon = Icons.Filled.CalendarMonth,
            unselectedIcon = Icons.Outlined.CalendarMonth
        ),
        NavItem(
            tab = AppNavTab.SUPERVISION,
            label = "Supervisión",
            selectedIcon = Icons.Filled.AssignmentTurnedIn,
            unselectedIcon = Icons.Outlined.AssignmentTurnedIn
        ),
        NavItem(
            tab = AppNavTab.CONFIG,
            label = "Config",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = SurfaceCanvas,
        contentWindowInsets = WindowInsets.navigationBars,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar(
                containerColor = SurfaceCard,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp)
                    .border(1.dp, SurfaceBorder)
                    .testTag("bottom_nav_bar")
            ) {
                navItems.forEach { item ->
                    val isSelected = uiState.currentTab == item.tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.navigateTo(item.tab) },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label,
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = BrandBlueInteractive,
                            selectedTextColor = BrandBlueInteractive,
                            indicatorColor = BrandBlueLight,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        ),
                        modifier = Modifier.testTag("nav_${item.tab.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = uiState.currentTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { targetTab ->
                when (targetTab) {
                    AppNavTab.PORTAL -> {
                        PortalScreen(
                            state = uiState,
                            onNavigate = { viewModel.navigateTo(it) }
                        )
                    }

                    AppNavTab.HACER_HOY -> {
                        HacerHoyScreen(
                            state = uiState,
                            onNavigate = { viewModel.navigateTo(it) },
                            onRealizarClick = { gestor -> viewModel.openAuditForGestor(gestor) },
                            onCierreSupervisionClick = { showCierreDialog = true },
                            onToggleDatos = { viewModel.toggleDatosFilter() }
                        )
                    }

                    AppNavTab.SEMANAL -> {
                        SemanalScreen(
                            state = uiState,
                            onNavigate = { viewModel.navigateTo(it) },
                            onDaySelect = { day -> viewModel.selectDay(day) },
                            onToggleGestorAssignment = { id -> viewModel.toggleGestorAssignment(id) },
                            onSavePlanning = { viewModel.saveDayPlanning() }
                        )
                    }

                    AppNavTab.SUPERVISION -> {
                        SupervisionScreen(
                            state = uiState,
                            onNavigate = { viewModel.navigateTo(it) },
                            onToggleSectionA = { viewModel.toggleAuditSectionAConoce() },
                            onTogglePaso = { step -> viewModel.toggleAuditPaso(step) },
                            onToggleViccAplica = { viewModel.toggleViccAplica() },
                            onToggleSecurity = { idx -> viewModel.toggleSecurityItem(idx) },
                            onToggleTools = { idx -> viewModel.toggleToolItem(idx) },
                            onSealAndSave = { viewModel.sealAndSaveAudit() }
                        )
                    }

                    AppNavTab.CONFIG -> {
                        ConfigScreen(
                            state = uiState,
                            onNavigate = { viewModel.navigateTo(it) },
                            onSaveOperativeData = { viewModel.saveOperativeData() },
                            onAddNewGestor = { name, id, route, meta -> viewModel.addNewGestor(name, id, route, meta) },
                            onDeleteGestor = { id -> viewModel.deleteGestor(id) },
                            onUpdateSupervisor = { n, id, p, s -> viewModel.updateSupervisorInfo(n, id, p, s) }
                        )
                    }
                }
            }
        }

        // Cierre de Supervisión Modal Dialog
        if (showCierreDialog) {
            AlertDialog(
                onDismissRequest = { showCierreDialog = false },
                title = {
                    Text(
                        text = "Cierre de Supervisión del Turno",
                        fontWeight = FontWeight.Bold,
                        color = DeepNavy,
                        fontSize = 18.sp
                    )
                },
                text = {
                    Text(
                        text = "Se consolidarán los avances de los ${uiState.gestores.size} gestores en campo de Zacualtipán. Recaudación actual: $${(uiState.currentAmountTotal).toInt()} MXN (Meta: $${(uiState.totalTarget).toInt()} MXN).\n\n¿Desea generar el reporte consolidado y sellar el turno?",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        lineHeight = 18.sp
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showCierreDialog = false
                            viewModel.sealAndSaveAudit()
                        }
                    ) {
                        Text(
                            text = "Confirmar Cierre",
                            fontWeight = FontWeight.Bold,
                            color = BrandBlueInteractive
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showCierreDialog = false }) {
                        Text(text = "Cancelar", color = TextSecondary)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                containerColor = SurfaceCard
            )
        }
    }
}
