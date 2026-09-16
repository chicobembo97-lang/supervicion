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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.SampleData
import com.example.ui.theme.AmberAlert
import com.example.ui.theme.AmberDark
import com.example.ui.theme.BrandBlueBorder
import com.example.ui.theme.BrandBlueInteractive
import com.example.ui.theme.BrandBlueLight
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.EmeraldBorder
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldTarget
import com.example.ui.theme.RedDeficit
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
fun ConfigScreen(
    state: UiState,
    onNavigate: (AppNavTab) -> Unit,
    onSaveOperativeData: () -> Unit,
    onAddNewGestor: (String, String, String, Double) -> Unit,
    onDeleteGestor: (String) -> Unit,
    onUpdateSupervisor: (String, String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var supName by remember { mutableStateOf(state.supervisor.name) }
    var supId by remember { mutableStateOf(state.supervisor.employeeId) }
    var supPhone by remember { mutableStateOf(state.supervisor.phone) }
    var selectedShift by remember { mutableStateOf(state.supervisor.assignedShift) }

    // Gerencia fields
    var gerenciaPlaza by remember { mutableStateOf(state.gerencia.plaza) }
    var gerenciaDivision by remember { mutableStateOf(state.gerencia.division) }
    var centroCostos by remember { mutableStateOf(state.gerencia.costCenter) }
    var gerenteZona by remember { mutableStateOf(state.gerencia.zoneManager) }

    // Quick add fields
    var newGestorName by remember { mutableStateOf("Roberto Gómez Pérez") }
    var newGestorId by remember { mutableStateOf("GIC-03") }
    var newGestorRoute by remember { mutableStateOf("Ruta 22 - Corredor Fin.") }
    var newGestorMeta by remember { mutableStateOf("16200") }
    var showQuickAdd by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceCanvas)
    ) {
        // Top Info Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "MÓDULO OPERATIVO MAESTRO",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandBlueInteractive,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Registro de Datos Operativos",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepNavy
                )
                Text(
                    text = "Captura de Líder, Gerencia y Alta de Gestores de campo",
                    fontSize = 11.sp,
                    color = TextSecondary
                )
            }

            IconButton(
                onClick = onSaveOperativeData,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(BrandBlueInteractive)
                    .testTag("save_config_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Guardar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Status Badges Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Badge 1
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(EmeraldLight)
                            .border(1.dp, EmeraldBorder, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Estado: ● Sincronizado",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDark
                        )
                    }

                    // Badge 2
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(BrandBlueLight)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Turno: $selectedShift",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandBlueInteractive
                        )
                    }

                    // Badge 3
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceSubtle)
                            .border(1.dp, SurfaceBorder, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Corte: 18:00 hrs",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary
                        )
                    }
                }
            }

            // Hero Banner Image
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_hero_banner),
                        contentDescription = "Protocolo Operativo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color(0xDD0B1C30))
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(BrandBlueInteractive)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "PROTOCOLO OPERATIVO 2025",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Validación de Cuadrilla y Rutas",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            // 1. Datos del Líder / Supervisor
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "1. Datos del Líder / Supervisor",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(BrandBlueLight)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Obligatorio",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BrandBlueInteractive
                                )
                            }
                        }

                        OutlinedTextField(
                            value = supName,
                            onValueChange = {
                                supName = it
                                onUpdateSupervisor(supName, supId, supPhone, selectedShift)
                            },
                            label = { Text("Nombre completo del Líder / Supervisor", fontSize = 12.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlueInteractive,
                                unfocusedBorderColor = SurfaceBorder
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedTextField(
                                value = supId,
                                onValueChange = {
                                    supId = it
                                    onUpdateSupervisor(supName, supId, supPhone, selectedShift)
                                },
                                label = { Text("No. Empleado", fontSize = 12.sp) },
                                modifier = Modifier.weight(1f),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlueInteractive,
                                    unfocusedBorderColor = SurfaceBorder
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )

                            OutlinedTextField(
                                value = supPhone,
                                onValueChange = {
                                    supPhone = it
                                    onUpdateSupervisor(supName, supId, supPhone, selectedShift)
                                },
                                label = { Text("Teléfono de contacto", fontSize = 12.sp) },
                                modifier = Modifier.weight(1.3f),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlueInteractive,
                                    unfocusedBorderColor = SurfaceBorder
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                        }

                        // Shift Pills
                        Column {
                            Text(
                                text = "Turno asignado",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextSecondary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val shifts = listOf("Matutino", "Vesp.", "Mixto")
                                shifts.forEach { shift ->
                                    val isSelected = selectedShift == shift
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (isSelected) BrandBlueInteractive else SurfaceSubtle)
                                            .border(
                                                1.dp,
                                                if (isSelected) BrandBlueInteractive else SurfaceBorder,
                                                RoundedCornerShape(8.dp)
                                            )
                                            .clickable {
                                                selectedShift = shift
                                                onUpdateSupervisor(supName, supId, supPhone, selectedShift)
                                            }
                                            .padding(vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = shift,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) Color.White else DeepNavy
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 2. Gerencia / GCC Regional
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
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "2. Gerencia / GCC Regional",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = DeepNavy
                        )

                        OutlinedTextField(
                            value = gerenciaDivision,
                            onValueChange = { gerenciaDivision = it },
                            label = { Text("Gerencia Regional / División", fontSize = 12.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlueInteractive,
                                unfocusedBorderColor = SurfaceBorder
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )

                        OutlinedTextField(
                            value = gerenciaPlaza,
                            onValueChange = { gerenciaPlaza = it },
                            label = { Text("Nombre de GCC / Plaza Operativa", fontSize = 12.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = BrandBlueInteractive,
                                unfocusedBorderColor = SurfaceBorder
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedTextField(
                                value = centroCostos,
                                onValueChange = { centroCostos = it },
                                label = { Text("Centro Costos", fontSize = 12.sp) },
                                modifier = Modifier.weight(1f),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlueInteractive,
                                    unfocusedBorderColor = SurfaceBorder
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )

                            OutlinedTextField(
                                value = gerenteZona,
                                onValueChange = { gerenteZona = it },
                                label = { Text("Gerente de Zona", fontSize = 12.sp) },
                                modifier = Modifier.weight(1.4f),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlueInteractive,
                                    unfocusedBorderColor = SurfaceBorder
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                        }
                    }
                }
            }

            // 3. Gestores (GIC) de Cuadrilla
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "3. Gestores (GIC) de Cuadrilla",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )
                            Text(
                                text = "Rutas asignadas y metas operativas",
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(BrandBlueInteractive)
                                .clickable { showQuickAdd = !showQuickAdd }
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "Agregar",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    // Blue banner bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(BrandBlueInteractive)
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "CUADRILLA: ${state.gestores.size} Gestores",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "META ACUMULADA: ${SampleData.formatCurrency(state.totalTarget)} MXN",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Gestor Cards inside Config
            items(state.gestores.size) { index ->
                val gestor = state.gestores[index]
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
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
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(BrandBlueLight)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = gestor.id,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BrandBlueInteractive
                                    )
                                }
                                Text(
                                    text = gestor.fullName,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DeepNavy
                                )
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar",
                                    tint = TextSecondary,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .clickable { }
                                )
                                Icon(
                                    imageVector = Icons.Default.DeleteOutline,
                                    contentDescription = "Eliminar",
                                    tint = RedDeficit,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .clickable { onDeleteGestor(gestor.id) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Ruta: ${gestor.routeName}",
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                            Text(
                                text = "Meta: ${SampleData.formatCurrency(gestor.targetAmount)}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DeepNavy
                            )
                        }

                        Text(
                            text = "Tel: ${gestor.phone} • Último: ${gestor.lastReportTime}",
                            fontSize = 10.sp,
                            color = TextTertiary
                        )
                    }
                }
            }

            // Alta Rápida de Gestor Subcard
            if (showQuickAdd) {
                item {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = BrandBlueLight),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BrandBlueBorder),
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(BrandBlueInteractive),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "+",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                    Text(
                                        text = "Alta Rápida de Gestor",
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
                                    Text(
                                        text = "EN REGISTRO",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldDark
                                    )
                                }
                            }

                            OutlinedTextField(
                                value = newGestorName,
                                onValueChange = { newGestorName = it },
                                label = { Text("Nombre Completo del Gestor", fontSize = 11.sp) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlueInteractive,
                                    unfocusedBorderColor = Color.White,
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedTextField(
                                    value = newGestorId,
                                    onValueChange = { newGestorId = it },
                                    label = { Text("ID GIC", fontSize = 11.sp) },
                                    modifier = Modifier.weight(1f),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = BrandBlueInteractive,
                                        unfocusedBorderColor = Color.White,
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                )

                                OutlinedTextField(
                                    value = newGestorRoute,
                                    onValueChange = { newGestorRoute = it },
                                    label = { Text("Ruta Asignada", fontSize = 11.sp) },
                                    modifier = Modifier.weight(1.5f),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = BrandBlueInteractive,
                                        unfocusedBorderColor = Color.White,
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                )
                            }

                            OutlinedTextField(
                                value = newGestorMeta,
                                onValueChange = { newGestorMeta = it },
                                label = { Text("Meta Diaria (MXN)", fontSize = 11.sp) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = BrandBlueInteractive,
                                    unfocusedBorderColor = Color.White,
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { showQuickAdd = false },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color.White,
                                        contentColor = TextSecondary
                                    )
                                ) {
                                    Text("Descartar", fontSize = 12.sp)
                                }

                                Button(
                                    onClick = {
                                        val metaValue = newGestorMeta.toDoubleOrNull() ?: 12000.0
                                        onAddNewGestor(newGestorName, newGestorId, newGestorRoute, metaValue)
                                        newGestorName = ""
                                        newGestorRoute = ""
                                    },
                                    modifier = Modifier.weight(1.4f),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = BrandBlueInteractive,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("Confirmar Gestor", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            // Security note
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceSubtle)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = BrandBlueInteractive,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Al guardar, los datos quedan cifrados localmente y sincronizados con el servidor central GOC para cálculo de comisiones y auditorías en tiempo real.",
                        fontSize = 11.sp,
                        color = TextSecondary,
                        lineHeight = 16.sp
                    )
                }
            }

            // Primary buttons
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = onSaveOperativeData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("guardar_datos_operativos_btn"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandBlueInteractive,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Guardar y Vincular Datos Operativos",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = SurfaceSubtle,
                            contentColor = DeepNavy
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SurfaceBorder)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.FileDownload,
                                contentDescription = null,
                                tint = TextSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Importar desde Catálogo Central / Excel",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = DeepNavy
                            )
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
