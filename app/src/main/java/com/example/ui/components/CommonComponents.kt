package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.SampleData
import com.example.ui.theme.AmberAlert
import com.example.ui.theme.AmberBorder
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
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceSubtle
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun GocTopBar(
    title: String,
    onSyncClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onDateClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "SUPERVISIÓN GOC",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BrandBlueInteractive,
                letterSpacing = 0.8.sp
            )
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DeepNavy
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Day pill
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(BrandBlueLight)
                    .border(1.dp, BrandBlueBorder, RoundedCornerShape(20.dp))
                    .clickable { onDateClick() }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Calendario",
                    tint = BrandBlueInteractive,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Hoy",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = DeepNavy
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expandir",
                    tint = BrandBlueInteractive,
                    modifier = Modifier.size(16.dp)
                )
            }

            // Sync action
            IconButton(
                onClick = onSyncClick,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SurfaceSubtle)
                    .testTag("sync_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Sync,
                    contentDescription = "Sincronizar",
                    tint = EmeraldDark,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Profile action
            IconButton(
                onClick = onProfileClick,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(BrandBlueInteractive)
                    .testTag("profile_button")
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun MetricTripleRow(
    targetAmount: Double,
    currentAmount: Double,
    deficitAmount: Double,
    modifier: Modifier = Modifier,
    targetLabel: String = "Meta",
    currentLabel: String = "Hoy voy por",
    deficitLabel: String = "Me falta",
    percentage: Int? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Meta Total / Target
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceSubtle)
                .border(1.dp, SurfaceBorder, RoundedCornerShape(10.dp))
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Column {
                Text(
                    text = targetLabel,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = SampleData.formatCurrency(targetAmount),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepNavy
                )
            }
        }

        // Hoy voy por / Current
        Box(
            modifier = Modifier
                .weight(1.15f)
                .clip(RoundedCornerShape(10.dp))
                .background(EmeraldLight)
                .border(1.dp, EmeraldBorder, RoundedCornerShape(10.dp))
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Column {
                Text(
                    text = currentLabel,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = EmeraldDark
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = SampleData.formatCurrency(currentAmount),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldDark
                    )
                    if (percentage != null) {
                        Text(
                            text = "$percentage%",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldDark,
                            modifier = Modifier.padding(bottom = 1.dp)
                        )
                    }
                }
            }
        }

        // Me falta / Deficit
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(RedLight)
                .border(1.dp, RedBorder, RoundedCornerShape(10.dp))
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Column {
                Text(
                    text = deficitLabel,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = RedDeficit
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = SampleData.formatCurrency(deficitAmount),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = RedDeficit
                )
            }
        }
    }
}

@Composable
fun CustomLinearProgress(
    progress: Float,
    color: Color,
    trackColor: Color = SurfaceSubtle,
    height: Int = 8,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(height.dp))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .height(height.dp)
                .clip(RoundedCornerShape(height.dp))
                .background(color)
        )
    }
}

@Composable
fun DigitalSignatureBox(
    title: String,
    personName: String,
    timestamp: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceCard)
            .border(1.dp, SurfaceBorder, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = TextTertiary,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(6.dp))

            // Canvas drawing simulating a realistic handwritten digital signature curve
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
            ) {
                val path = Path().apply {
                    val w = size.width
                    val h = size.height
                    moveTo(w * 0.15f, h * 0.65f)
                    cubicTo(w * 0.3f, h * 0.3f, w * 0.4f, h * 0.9f, w * 0.55f, h * 0.45f)
                    cubicTo(w * 0.65f, h * 0.2f, w * 0.75f, h * 0.7f, w * 0.85f, h * 0.55f)
                }
                drawPath(
                    path = path,
                    color = BrandBlueInteractive,
                    style = Stroke(width = 2.5f, cap = StrokeCap.Round)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = personName,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = DeepNavy
            )
            Text(
                text = timestamp,
                fontSize = 10.sp,
                color = TextSecondary
            )
        }
    }
}

@Composable
fun StepToggleRow(
    number: Int,
    title: String,
    isCumple: Boolean,
    onToggle: () -> Unit,
    badgeText: String = if (isCumple) "Cumple" else "No cumple",
    isWarningBadge: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
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
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(BrandBlueLight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$number",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandBlueInteractive
                    )
                }
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = DeepNavy
                )
            }

            // Status chip
            val chipBg = if (isWarningBadge) AmberLight else if (isCumple) EmeraldLight else RedLight
            val chipBorder = if (isWarningBadge) AmberBorder else if (isCumple) EmeraldBorder else RedBorder
            val chipColor = if (isWarningBadge) AmberDark else if (isCumple) EmeraldDark else RedDeficit

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(chipBg)
                    .border(1.dp, chipBorder, RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = badgeText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = chipColor
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Toggle buttons (Cumple / No cumple)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(SurfaceSubtle)
                .padding(2.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Cumple button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (isCumple) Color.White else Color.Transparent)
                    .then(
                        if (isCumple) Modifier.border(1.dp, BrandBlueInteractive, RoundedCornerShape(6.dp))
                        else Modifier
                    )
                    .clickable { onToggle() }
                    .padding(vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (isCumple) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = BrandBlueInteractive,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    Text(
                        text = "Cumple",
                        fontSize = 12.sp,
                        fontWeight = if (isCumple) FontWeight.Bold else FontWeight.Normal,
                        color = if (isCumple) BrandBlueInteractive else TextSecondary
                    )
                }
            }

            // No cumple button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (!isCumple) Color.White else Color.Transparent)
                    .then(
                        if (!isCumple) Modifier.border(1.dp, RedDeficit, RoundedCornerShape(6.dp))
                        else Modifier
                    )
                    .clickable { onToggle() }
                    .padding(vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No cumple",
                    fontSize = 12.sp,
                    fontWeight = if (!isCumple) FontWeight.Bold else FontWeight.Normal,
                    color = if (!isCumple) RedDeficit else TextSecondary
                )
            }
        }
    }
}
