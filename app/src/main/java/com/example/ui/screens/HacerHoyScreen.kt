package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.GroupWork
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Gestor
import com.example.data.GestorStatus
import com.example.ui.components.CustomLinearProgress
import com.example.ui.components.GocTopBar
import com.example.ui.components.MetricTripleRow
import com.example.ui.theme.BrandBlueBorder
import com.example.ui.theme.BrandBlueInteractive
import com.example.ui.theme.BrandBlueLight
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.EmeraldBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldTarget
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceCanvas
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceSubtle
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.viewmodel.AppNavTab
import com.example.ui.viewmodel.UiState

@Composable
fun HacerHoyScreen(
    state: UiState,
    onNavigate: (AppNavTab) -> Unit,
    onRealizarClick: (Gestor) -> Unit,
    onCierreSupervisionClick: () -> Unit,
    onToggleDatos: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
    ) {
        // App Top Bar
        GocTopBar(
            title = "Hacer Hoy",
            onSyncClick = { /* ViewModel sync */ },
            onProfileClick = { onNavigate(AppNavTab.PORTAL) }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Filter Pills Row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Pill 1: Hoy, 24 Oct
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(BrandBlueLight)
                            .border(1.dp, BrandBlueBorder, RoundedCornerShape(20.dp))
                            .clickable { }
                            .padding(horizontal = 12.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Hoy, 24 Oct",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Pill 2: DATOS
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (state.isFilterDatosActive) EmeraldLight else SurfaceSubtle)
                            .border(
                                1.dp,
                                if (state.isFilterDatosActive) EmeraldBorder else SurfaceBorder,
                                RoundedCornerShape(20.dp)
                            )
                            .clickable { onToggleDatos() }
                            .padding(horizontal = 12.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = if (state.isFilterDatosActive) EmeraldDark else TextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "DATOS",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (state.isFilterDatosActive) DeepNavy else TextSecondary,
                            letterSpacing = 0.5.sp
                        )
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(if (state.isFilterDatosActive) EmeraldTarget else TextTertiary)
                        )
                    }
                }
            }

            // Card: Avance Global del Día
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TrendingUp,
                                    contentDescription = null,
                                    tint = BrandBlueInteractive,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "Avance Global del Día",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(BrandBlueLight)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "${state.globalPercentage}% Cuota",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // 3 boxes
                        MetricTripleRow(
                            targetAmount = state.totalTarget,
                            currentAmount = state.currentAmountTotal,
                            deficitAmount = state.deficitAmountTotal,
                            targetLabel = "Meta Total",
                            currentLabel = "Hoy voy por",
                            deficitLabel = "Me falta"
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        CustomLinearProgress(
                            progress = state.globalPercentage / 100f,
                            color = BrandBlueInteractive,
                            height = 7
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${state.activeGestoresCount} de ${state.gestores.size} Gestores en campo",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSecondary
                            )
                            Text(
                                text = "Ritmo: +14% vs ayer",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSecondary
                            )
                        }
                    }
                }
            }

            // Section Header: Gestores en Ruta
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.GroupWork,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Gestores en Ruta",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )
                    }

                    Text(
                        text = "HOY: ${state.gestores.size} ASIGNADOS",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextTertiary,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            // Individual Gestor Cards
            items(state.gestores) { gestor ->
                GestorRouteCard(
                    gestor = gestor,
                    onRealizarClick = { onRealizarClick(gestor) }
                )
            }

            // Bottom Banner Card: Cierre de Supervisión
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = BrandBlueLight),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCierreSupervisionClick() }
                        .testTag("cierre_supervision_card")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(BrandBlueInteractive),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Assignment,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "Cierre de Supervisión",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                                Text(
                                    text = "Generar reporte consolidado del turno",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = BrandBlueInteractive,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun GestorRouteCard(
    gestor: Gestor,
    onRealizarClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Header Row: Avatar, Info, Action Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    // Avatar image with status dot
                    Box {
                        if (gestor.avatarResId != null) {
                            Image(
                                painter = painterResource(id = gestor.avatarResId),
                                contentDescription = gestor.fullName,
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SurfaceSubtle),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = gestor.shortName.take(2).uppercase(),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                            }
                        }

                        // Status dot
                        val dotColor = when (gestor.status) {
                            GestorStatus.EN_RUTA -> EmeraldTarget
                            GestorStatus.SUPERVISANDO -> BrandBlueInteractive
                            GestorStatus.PENDIENTE -> TextTertiary
                            GestorStatus.ACTIVO -> EmeraldTarget
                        }

                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(dotColor)
                                .border(2.dp, Color.White, CircleShape)
                                .align(Alignment.BottomEnd)
                        )
                    }

                    // Text Info
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(BrandBlueLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "GESTOR ${gestor.number}",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                val statusColor = when (gestor.status) {
                                    GestorStatus.EN_RUTA -> EmeraldDark
                                    GestorStatus.SUPERVISANDO -> BrandBlueInteractive
                                    GestorStatus.PENDIENTE -> TextSecondary
                                    GestorStatus.ACTIVO -> EmeraldDark
                                }
                                Box(
                                    modifier = Modifier
                                        .size(5.dp)
                                        .clip(CircleShape)
                                        .background(statusColor)
                                )
                                Text(
                                    text = gestor.status.label,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = statusColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = gestor.shortName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )

                        Text(
                            text = "GOC ${gestor.number} • ${gestor.zone}",
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                }

                // CTA Button: Realizar / En Curso
                if (gestor.status == GestorStatus.SUPERVISANDO) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(BrandBlueLight)
                            .border(1.dp, BrandBlueBorder, RoundedCornerShape(10.dp))
                            .clickable { onRealizarClick() }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = BrandBlueInteractive,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "En Curso",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = BrandBlueInteractive
                            )
                        }
                    }
                } else {
                    Button(
                        onClick = onRealizarClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandBlueInteractive,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        ),
                        modifier = Modifier.testTag("realizar_button_${gestor.id}")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Realizar",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 3-Metric Block
            MetricTripleRow(
                targetAmount = gestor.targetAmount,
                currentAmount = gestor.currentAmount,
                deficitAmount = gestor.remainingAmount,
                percentage = gestor.progressPercent
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Progress bar
            val progressColor = when {
                gestor.progressPercent >= 75 -> EmeraldTarget
                gestor.progressPercent >= 50 -> BrandBlueInteractive
                else -> Color(0xFF64748B)
            }

            CustomLinearProgress(
                progress = gestor.progressPercent / 100f,
                color = progressColor,
                height = 5
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Footer info: Location / Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (gestor.status == GestorStatus.PENDIENTE) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(14.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Text(
                        text = "${gestor.visitedCount} / ${gestor.totalVisits} comercios visitados",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                }

                Text(
                    text = if (gestor.status == GestorStatus.PENDIENTE) "Inicio: ${gestor.startTime}"
                    else "Último: ${gestor.lastReportTime}",
                    fontSize = 11.sp,
                    color = TextTertiary
                )
            }
        }
    }
}
