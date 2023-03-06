package ru.kamaz.canservice

internal data class CanData(
    private val canId: Int,
    private val data: ByteArray
) {
    fun getCanId() = canId
    fun getData() = data

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CanData

        if (canId != other.canId) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = canId
        result = 31 * result + data.contentHashCode()
        return result
    }
}