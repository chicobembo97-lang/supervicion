package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SampleData
import com.example.ui.components.CustomLinearProgress
import com.example.ui.components.DigitalSignatureBox
import com.example.ui.components.StepToggleRow
import com.example.ui.theme.AmberAlert
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberLight
import com.example.ui.theme.BrandBlueBorder
import com.example.ui.theme.BrandBlueInteractive
import com.example.ui.theme.BrandBlueLight
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.EmeraldBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldTarget
import com.example.ui.theme.RedBorder
import com.example.ui.theme.RedDeficit
import com.example.ui.theme.RedLight
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
fun SupervisionScreen(
    state: UiState,
    onNavigate: (AppNavTab) -> Unit,
    onToggleSectionA: () -> Unit,
    onTogglePaso: (Int) -> Unit,
    onToggleViccAplica: () -> Unit,
    onToggleSecurity: (Int) -> Unit,
    onToggleTools: (Int) -> Unit,
    onSealAndSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    val audit = state.currentAudit

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = BrandBlueInteractive,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "SUPERVISIÓN EN CAMPO",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandBlueInteractive,
                        letterSpacing = 0.8.sp
                    )
                }

                Text(
                    text = "Formato Oficial de Auditoría",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepNavy
                )

                Text(
                    text = audit.folio,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Time badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(SurfaceSubtle)
                        .border(1.dp, SurfaceBorder, RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = audit.time,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )
                    }
                }

                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(SurfaceSubtle)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartir",
                        tint = DeepNavy,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // General Info Card
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "GCC / PLAZA",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextTertiary
                                )
                                Text(
                                    text = audit.plaza,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "MODALIDAD",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextTertiary
                                )
                                Text(
                                    text = audit.modalidad,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }
                        }

                        // Líder supervisor
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(BrandBlueLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "RA",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }
                            Column {
                                Text(
                                    text = "LÍDER SUPERVISOR",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextTertiary
                                )
                                Text(
                                    text = audit.supervisorName,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                            }
                        }

                        // GIC Evaluado
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(EmeraldLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "CM",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark
                                )
                            }
                            Column {
                                Text(
                                    text = "GIC / GESTOR EVALUADO",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextTertiary
                                )
                                Text(
                                    text = audit.gestorName,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                                Text(
                                    text = audit.gestorSubtitle,
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }
                        }
                    }
                }
            }

            // Metas del Turno Evaluado Card
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Metas del Turno Evaluado",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(EmeraldLight)
                                    .border(1.dp, EmeraldBorder, RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "80% Cumplido",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Column 1
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SurfaceSubtle)
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text("META HOY", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextTertiary)
                                    Text(SampleData.formatCurrency(audit.targetAmount), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DeepNavy)
                                    Text("Cobro esperado", fontSize = 9.sp, color = TextSecondary)
                                }
                            }

                            // Column 2
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(EmeraldLight)
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text("VERIFICADO", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
                                    Text(SampleData.formatCurrency(audit.verifiedAmount), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
                                    Text("${audit.verifiedCount} cobros", fontSize = 9.sp, color = EmeraldDark)
                                }
                            }

                            // Column 3
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(RedLight)
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text("FALTANTE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = RedDeficit)
                                    Text(SampleData.formatCurrency(audit.targetAmount - audit.verifiedAmount), fontSize = 13.sp, fontWeight = FontWeight.Bold, color = RedDeficit)
                                    Text("4 restantes", fontSize = 9.sp, color = RedDeficit)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        CustomLinearProgress(progress = 0.8f, color = EmeraldTarget, height = 5)

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("16 comercios atendidos", fontSize = 10.sp, color = TextSecondary)
                            Text("Meta: 20 comercios", fontSize = 10.sp, color = TextSecondary)
                        }
                    }
                }
            }

            // Sección A: Esquema de Compensación
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "A. Esquema de Compensación",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                                Text(
                                    text = "Evaluación de conocimiento del GIC",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(EmeraldLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Dominio 4/4",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark
                                )
                            }
                        }

                        Text(
                            text = "¿Conoce su esquema de compensación?",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = DeepNavy
                        )

                        // Dual choice button: Conoce / Desconoce
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceSubtle)
                                .padding(2.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (audit.conoceEsquema) Color.White else Color.Transparent)
                                    .clickable { onToggleSectionA() }
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = BrandBlueInteractive,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "Conoce",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BrandBlueInteractive
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (!audit.conoceEsquema) Color.White else Color.Transparent)
                                    .clickable { onToggleSectionA() }
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Desconoce",
                                    fontSize = 12.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        // Sub-items
                        val subItems = listOf(
                            "Sueldo base (Importe fijo quincenal)",
                            "Variable individual por cobranza",
                            "Participación en bolsa",
                            "Variable ind. de investigaciones"
                        )

                        subItems.forEach { label ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = label, fontSize = 11.sp, color = DeepNavy)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(EmeraldLight)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text("✓ Dominado", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
                                }
                            }
                        }
                    }
                }
            }

            // Sección B: Camino para Cobrar Mejor (7 Pasos)
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "B. Camino para Cobrar Mejor",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                                Text(
                                    text = "Pasos del proceso de cobranza en campo",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(BrandBlueLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "7 Pasos",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }
                        }

                        // 7 Steps
                        StepToggleRow(1, "Saludo y presentación", audit.paso1Saludo, { onTogglePaso(1) })
                        StepToggleRow(2, "Informó del adeudo", audit.paso2Adeudo, { onTogglePaso(2) })
                        StepToggleRow(3, "Indaga motivo del atraso", audit.paso3Motivo, { onTogglePaso(3) })
                        StepToggleRow(4, "Aplica cadena soluciones", audit.paso4Soluciones, { onTogglePaso(4) })
                        StepToggleRow(
                            number = 5,
                            title = "Manejo de objeciones",
                            isCumple = audit.paso5ObjecionesObservacion,
                            onToggle = { onTogglePaso(5) },
                            badgeText = "En Observación",
                            isWarningBadge = true
                        )
                        StepToggleRow(6, "Cierre", audit.paso6Cierre, { onTogglePaso(6) })
                        StepToggleRow(7, "Despedida por nombre", audit.paso7Despedida, { onTogglePaso(7) })
                    }
                }
            }

            // Sección C: Sistemática Operativa VICC
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "C. Sistemática Operativa VICC",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                                Text(
                                    text = "Visitas, Itinerario, Cobranza y Cierre",
                                    fontSize = 11.sp,
                                    color = TextSecondary
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(EmeraldLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Validado",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark
                                )
                            }
                        }

                        Text(
                            text = "¿Sabe qué es y cómo aplicar la Sistemática Operativa VICC?",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = DeepNavy
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceSubtle)
                                .padding(2.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (audit.conoceViccAplica) Color.White else Color.Transparent)
                                    .clickable { onToggleViccAplica() }
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Conoce y Aplica",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (!audit.conoceViccAplica) Color.White else Color.Transparent)
                                    .clickable { onToggleViccAplica() }
                                    .padding(vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Desconoce",
                                    fontSize = 12.sp,
                                    color = TextSecondary
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Dominio conceptual: ${audit.dominioConceptual}", fontSize = 10.sp, color = TextSecondary)
                            Text("Ejecución: ${audit.ejecucionCampo}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
                        }
                    }
                }
            }

            // Sección D: Seguridad Vial y Motocicleta
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.TwoWheeler,
                                    contentDescription = null,
                                    tint = BrandBlueInteractive,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "D. Seguridad Vial y Motocicleta",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(EmeraldLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("4 Puntos", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
                            }
                        }

                        StepToggleRow(1, "Equipo de seguridad completo (DOT/ECE)", audit.equipoSeguridad, { onToggleSecurity(1) })
                        StepToggleRow(2, "Licencia de conducir vigente", audit.licenciaVigente, { onToggleSecurity(2) }, "Vigente")
                        StepToggleRow(3, "Tarjeta de circulación vigente", audit.tarjetaCirculacionVigente, { onToggleSecurity(3) }, "Vigente")
                        StepToggleRow(4, "Estado y cuidado de la unidad", audit.estadoUnidad, { onToggleSecurity(4) }, "Buen estado")
                    }
                }
            }

            // Sección E: Herramientas de Trabajo
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "E. Herramientas de Trabajo",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(EmeraldLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("2 Criterios", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
                            }
                        }

                        StepToggleRow(1, "Terminal PAX / Impresora portátil", audit.terminalPax, { onToggleTools(1) }, "Buen estado")
                        StepToggleRow(2, "¿Tiene pendientes de herramientas?", audit.sinPendientes, { onToggleTools(2) }, "Sin pendientes")
                    }
                }
            }

            // Hallazgos y Observaciones
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Hallazgos y Observaciones",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceSubtle)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = audit.hallazgosNotas,
                                fontSize = 11.sp,
                                color = TextPrimary,
                                lineHeight = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Compromiso Acordado para el Cierre de Turno",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(BrandBlueLight)
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Handshake,
                                contentDescription = null,
                                tint = BrandBlueInteractive,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = audit.compromiso,
                                fontSize = 11.sp,
                                color = BrandBlueInteractive,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            // Conformidad de Firmas Digitales
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Conformidad de Firmas",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(EmeraldLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Sellado Digital",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldDark
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            DigitalSignatureBox(
                                title = "SUPERVISOR",
                                personName = audit.supervisorName,
                                timestamp = audit.supervisorFirmaTime,
                                modifier = Modifier.weight(1f)
                            )

                            DigitalSignatureBox(
                                title = "GESTOR EVALUADO",
                                personName = audit.gestorName,
                                timestamp = audit.gestorFirmaTime,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Bottom Buttons: Imprimir PDF & Guardar y Sellar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = SurfaceSubtle,
                            contentColor = DeepNavy
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Print,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = DeepNavy
                            )
                            Text("Imprimir PDF", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    Button(
                        onClick = onSealAndSave,
                        modifier = Modifier
                            .weight(1.3f)
                            .height(48.dp)
                            .testTag("guardar_sellar_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandBlueInteractive,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Text("Guardar y Sellar", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
