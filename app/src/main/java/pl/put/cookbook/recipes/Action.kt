package pl.put.cookbook.recipes

import android.icu.text.DecimalFormat
import pl.put.cookbook.App
import pl.put.cookbook.R
import kotlin.math.floor

class Action (private val text: String, private val seconds: Int? = null) {

    fun getText(): String {
        return this.text
    }

    fun getTimeInSeconds(): Int? {
        return this.seconds
    }

    fun getHumanReadableTime(): String? {
        if (seconds == null) return null

        val res = App.appResources
        if (seconds <= 90 && seconds != 60) {
            return seconds.toString() + " " + res.getString(R.string.unit_sec)
        }

        val nf = DecimalFormat.getInstance()
        nf.minimumFractionDigits = 0
        nf.maximumFractionDigits = 1

        var minutes = seconds / 60.0
        if (minutes <= 90.0 && minutes != 60.0) {
            if (minutes >= 10) minutes = floor(minutes)
            return nf.format(minutes) + " " + res.getString(R.string.unit_min)
        }

        val hours = minutes / 60.0
        return nf.format(hours) + " " + res.getString(R.string.unit_hrs)
    }
}