package pl.kylos.marczuk.pm_fm_test.repository.net

import com.google.gson.annotations.SerializedName

data class DataNet(
    @SerializedName("description")
    val description: String,

    @SerializedName("image_url")
    val image_url: String,

    @SerializedName("modificationDate")
    val modificationDate: String,

    @SerializedName("orderId")
    val orderId: Int,

    @SerializedName("title")
    val title: String,
)