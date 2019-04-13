package ir.siatech.kotlinmvvm.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
@Entity
data class Source(
    @SerializedName("articleId")
    val id: Any?,
    @SerializedName("name")
    val name: String
)