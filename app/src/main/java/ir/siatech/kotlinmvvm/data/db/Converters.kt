package ir.siatech.kotlinmvvm.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.siatech.kotlinmvvm.data.model.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source?): String? {
        return if (source == null) null else Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(value: String?): Source? {
        return if (value == null)
            null
        else
            Gson().fromJson<Source>(value, object : TypeToken<Source>() {}.type)
    }

}
