package com.rejangtoindo.kamusbahasarejang.model

import android.content.Intent
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class kamus(val indo: String, val rejang: String,val kelas_kata:String){
    companion object {
        const val TABLE: String = "kamus"
        const val id: String = "id"
        const val indo: String = "indo"
        const val rejang: String = "rejang"
        const val kelas_kata: String = "kelas_kata"
    }
}
