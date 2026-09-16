package com.example.data

import com.example.R
import java.text.NumberFormat
import java.util.Locale

object SampleData {
    fun formatCurrency(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.maximumFractionDigits = 0
        return format.format(amount)
    }

    val initialGestores = listOf(
        Gestor(
            id = "GIC-01",
            number = 1,
            fullName = "Carlos Mendoza Rivas",
            shortName = "Carlos Mendoza",
            routeName = "Ruta 14 - Barrio Arriba",
            zone = "Centro Histórico",
            phone = "771 289 4410",
            targetAmount = 12000.0,
            currentAmount = 9600.0,
            status = GestorStatus.EN_RUTA,
            avatarResId = R.drawable.img_worker_carlos,
            visitedCount = 16,
            totalVisits = 20,
            lastReportTime = "Hace 8 min",
            startTime = "09:15 AM",
            isAssignedToday = true,
            confirmationStatus = "Confirmado"
        ),
        Gestor(
            id = "GIC-02",
            number = 2,
            fullName = "Andrea Salinas Mejía",
            shortName = "Andrea Salinas",
            routeName = "Ruta 08 - Centro Norte",
            zone = "Zona Industrial...",
            phone = "771 930 1125",
            targetAmount = 10500.0,
            currentAmount = 6300.0,
            status = GestorStatus.SUPERVISANDO,
            avatarResId = R.drawable.img_worker_andrea,
            visitedCount = 12,
            totalVisits = 18,
            lastReportTime = "Hace 3 min",
            startTime = "08:52 AM",
            isAssignedToday = true,
            confirmationStatus = "En preparación"
        ),
        Gestor(
            id = "GIC-03",
            number = 3,
            fullName = "Roberto Gómez Pérez",
            shortName = "Roberto Gómez",
            routeName = "Corredor Financiero",
            zone = "Corredor Financ...",
            phone = "771 450 7820",
            targetAmount = 9000.0,
            currentAmount = 4500.0,
            status = GestorStatus.PENDIENTE,
            avatarResId = R.drawable.img_worker_roberto,
            visitedCount = 8,
            totalVisits = 15,
            lastReportTime = "Hace 15 min",
            startTime = "10:15 AM",
            isAssignedToday = false,
            confirmationStatus = "Por confirmar"
        )
    )
}
