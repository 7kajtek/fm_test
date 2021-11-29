package pl.kylos.marczuk.pm_fm_test.repository

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class Data(
    val id: Int,
    val description: String,
    val imageUrl: String,
    private val modificationDate: String,
    val title: String,
    val webUrl: String
) {
    fun getModificationDate(): Date? {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.parse(modificationDate)
        } catch (e: ParseException) {
            return null;
        }
    }
}