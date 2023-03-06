package ru.kamaz.canservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CanDataInfo(val id:Int, val startByte :Int, val startBit:Int,val lenght:Int, val factor:Int, val offset:Int, val data:Float) : Parcelable