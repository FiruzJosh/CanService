package ru.kamaz.canservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataCan(val id:Int, var data:Float,var time:Int): Parcelable