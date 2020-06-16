package su.leff.smartcounter.util

import android.os.Build

fun isProbablyAnEmulator() = Build.FINGERPRINT.startsWith("generic")
        || Build.FINGERPRINT.startsWith("unknown")
        || Build.MODEL.contains("google_sdk")
        || Build.MODEL.contains("Emulator")
        || Build.MODEL.contains("Android SDK built for x86")
        || Build.BOARD == "QC_Reference_Phone" //bluestacks
        || Build.MANUFACTURER.contains("Genymotion")
        || Build.HOST.startsWith("Build") //MSI App Player
        || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
        || "google_sdk" == Build.PRODUCT


fun getMealName(mealType: Long): String{
    return when (mealType) {
        1L -> {
            "Завтрак"
        }
        2L -> {

            "Обед"
        }
        3L -> {

            "Завтрак"
        }
        4L -> {

            "Завтрак"
        }
        5L -> {

            "Ланч"
        }
        6L -> {

            "Перекус"
        }
        8L -> {

            "Ужин"
        }
        else -> "Ужин"
    }
}