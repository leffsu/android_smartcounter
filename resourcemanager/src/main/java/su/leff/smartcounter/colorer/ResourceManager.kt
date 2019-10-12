package su.leff.smartcounter.colorer

import android.content.Context
import androidx.core.content.ContextCompat

object ResourceManager {

    fun getBackgroundColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.background)
    }

    fun getBackgroundColor(): Int {
        return R.color.background
    }

    fun getOrangeAccentColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.orangeAccent)
    }

    fun getPinkAccentColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.pinkAccent)
    }

    fun getGreenAccentColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.greenAccent)
    }

    fun getUsualTextColorColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.usualTextColor)
    }

    fun getUsualTextColorColor(): Int {
        return R.color.usualTextColor
    }

    fun getItemBackgroundColor(): Int {
        return R.color.itemBackground
    }

    fun getItemBackgroundColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.itemBackground)
    }

    fun getBottomAppBarColor(): Int {
        return R.color.bottomAppBarColor
    }

    fun getBottomAppBarColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.bottomAppBarColor)
    }

    fun getButtonBackgroundResource(): Int {
        return R.drawable.ic_button
    }

    fun getScanButtonResource(): Int {
        return R.drawable.ic_qr_scan
    }
}