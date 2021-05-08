package com.emreozturk.harcamatakip.database

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "harcama_tablo")
data class HarcamaTablo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var icon: String,
    var tur: String,
    var fiyat: Int,
    var durum: Int = -1
) : Parcelable
