package ru.kamaz.canservice

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*
import kotlin.experimental.and
import kotlin.math.ceil

internal object CanDataConverter {
    private val short = 0xFFFF

    fun convertToFloat(bytes: ByteArray, startByte: Int, startBit: Int, countBit: Int, factor: Float, offset: Float, byteOrder: ByteOrder): Float? {
        val size = ceil((startBit + countBit) / 8.0).toInt()
        val bitSet = getBitSet(bytes, startByte, startBit, countBit)
        val byteArray = Arrays.copyOf(bitSet.toByteArray(), size)
        val byteBuffer = ByteBuffer.allocate(size).order(byteOrder)

        byteBuffer.put(byteArray)
        byteBuffer.position(0)

        return when (size) {
            1 -> byteBuffer.get().toCheckedInt() * factor + offset
            2 -> byteBuffer.getCheckedShort() * factor + offset
            3 -> byteBuffer.getThreeByte() * factor + offset
            4 -> byteBuffer.int * factor + offset
            //8 -> byteBuffer.long * factor + offset
            else -> null
        }
    }

    private fun getBitSet(array: ByteArray, fromByte: Int, fromBit: Int, count: Int): BitSet {
        val bitSet = BitSet.valueOf(array)
        val startIndex = fromByte * 8 + fromBit

        return bitSet.get(startIndex, startIndex + count)
    }

    private fun ByteBuffer.getThreeByte(): Int {
        val b1 = this.get()
        val b2 = this.get()
        val b3 = this.get()

        return b1.toCheckedInt() or (b2.toCheckedInt() shl 8) or (b3.toCheckedInt() shl 16) // Reverse or not?
    }

    private fun Byte.toCheckedInt(): Int {
        return this.toInt() and 0xFF
    }

    private fun ByteBuffer.getCheckedShort(): Int {
        return this.short.toInt() and 0xFFFF
    }
}