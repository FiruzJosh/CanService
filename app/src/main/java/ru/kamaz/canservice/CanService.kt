package ru.kamaz.canservice

import android.app.Service
import android.content.*
import android.database.Cursor
import android.net.Uri
import android.os.IBinder
import android.util.Log
import bw.car.Car
import bw.car.CarNotConnectedException
import bw.car.hardware.CarPropertyValue
import bw.car.hardware.property.CarPropertyManager
import bw.car.hardware.vehicle.CarVehiclePropertyIds
import bw.car.settings.CarSettingManager
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*
import kotlin.experimental.and
import kotlin.math.pow


class CanService : Service() {

    private var mCar: Car? = null
    private var carManager: CarSettingManager? = null
    private var carPropertyManager: CarPropertyManager? = null

    override fun onCreate() {
        super.onCreate()
        mCar = Car.createCar(this, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                Log.e(TAG, "onServiceConnected : ")
                initCarSettingManager()
                setCanIdConfig()

            }
            override fun onServiceDisconnected(name: ComponentName) {}
        })
        mCar!!.connect()

    }

    private fun initCarSettingManager() {
        try {
            carManager = mCar!!.getCarManager(Car.CAR_SETTING_SERVICE) as CarSettingManager
            carPropertyManager = mCar!!.getCarManager(Car.PROPERTY_SERVICE) as CarPropertyManager
            //Register canid for data listening
            carPropertyManager!!.registerListener(carPropertyEventListener,
                CarVehiclePropertyIds.VEHICLE_CAN_DATA, 0f)
        } catch (e: CarNotConnectedException) {
            e.printStackTrace()
        }
    }

    private fun setCanIdConfig() {
        try {
            val canid: MutableList<String> = ArrayList()
            canid.add("18FEEF00")
            canid.add("18FEEE00")
            canid.add("18FEF500")
            canid.add("18FEE500")
            canid.add("18FEF600")
            canid.add("0CF00400")
            canid.add("18FD0900")
            canid.add("18FEF100")
            canid.add("0CF00300")
            canid.add("18FEAE30")
            canid.add("18FEEA2F")
            canid.add("18FE592F")
            canid.add("0CFE5A2F")
            canid.add("0CE7FF34")
            canid.add("0CFE6CEE")
            canid.add("18FEE6EE")
            canid.add("18FEC1EE")
            canid.add("18FFC4EE")
            canid.add("18FEECEE")
            canid.add("18ECFFEE")
            canid.add("18EBFFEE")
            canid.add("18FF2817")
            canid.add("18FF2F17")
            canid.add("18FF3117")
            canid.add("18FF6017")
            canid.add("1CDEEE17")
            canid.add("18EAEE17")
            canid.add("18FEEA17")
            canid.add("18A5014B")
            canid.add("18FF281C")
            canid.add("18FDE119")
            canid.add("18FE6D44")
            canid.add("18FF0244")
            canid.add("18FE6D45")
            canid.add("18FF0245")
            canid.add("18FEF433")
            canid.add("18FF7E2B")
            canid.add("18FE782B")
            canid.add("18FE7A2B")
            canid.add("18FEF42B")
            canid.add("18F00029")
            canid.add("1CFEAC0B")
            canid.add("18FEF40B")
            canid.add("18F0010B")
            canid.add("18FEC4C8")
            canid.add("18FE5CC8")
            canid.add("18FE8C1E")
            canid.add("18D0001E")
            canid.add("18FF211E")
            canid.add("18FF221E")
            canid.add("18FF231E")
            canid.add("18FED91E")
            canid.add("18FE401E")
            canid.add("18F0061E")
            canid.add("18FEFC1E")
            canid.add("1CFEC31E")
            canid.add("18FEEF1E")
            canid.add("1CFEAC1E")
            canid.add("18F00503")
            canid.add("18FF8503")
            canid.add("18FE4A03")
            canid.add("0CEFFF50")
            canid.add("18FECA44")
            canid.add("18FECA45")
            canid.add("18DA28F1")
            canid.add("18F0F62A")
            canid.add("0CF02FA0")
            canid.add("18FF172A")
            canid.add("10F007E8")
            canid.add("10FF352A")
            carManager!!.setCanidConfig(canid)
            Log.d(TAG, "config cinid finish!")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun setTime() {
        canTime[0x18FEEF00] = 0
        canTime[0x18FEEE00] = 0
        canTime[0x18FEF500] = 0
        canTime[0x18FEE500] = 0
        canTime[0x18FEF600] = 0
        canTime[0x0CF00400] = 0
        canTime[0x18FD0900] = 0
        canTime[0x18FEF100] = 0
        canTime[0x0CF00300] = 0
        canTime[0x18FEAE30] = 0
        canTime[0x18FEEA2F] = 0
        canTime[0x18FE592F] = 0
        canTime[0x0CFE5A2F] = 0
        canTime[0x0CE7FF34] = 0
        canTime[0x0CFE6CEE] = 0
        canTime[0x18FEE6EE] = 0
        canTime[0x18FEC1EE] = 0
        canTime[0x18FFC4EE] = 0
        canTime[0x18FEECEE] = 0
        canTime[0x18ECFFEE] = 0
        canTime[0x18EBFFEE] = 0
        canTime[0x18FF2817] = 0
        canTime[0x18FF2F17] = 0
        canTime[0x18FF3117] = 0
        canTime[0x18FF6017] = 0
        canTime[0x1CDEEE17] = 0
        canTime[0x18EAEE17] = 0
        canTime[0x18FEEA17] = 0
        canTime[0x18A5014B] = 0
        canTime[0x18FF281C] = 0
        canTime[0x18FDE119] = 0
        canTime[0x18FE6D44] = 0
        canTime[0x18FF0244] = 0
        canTime[0x18FE6D45] = 0
        canTime[0x18FF0245] = 0
        canTime[0x18FEF433] = 0
        canTime[0x18FF7E2B] = 0
        canTime[0x18FE782B] = 0
        canTime[0x18FE7A2B] = 0
        canTime[0x18FEF42B] = 0
        canTime[0x18F00029] = 0
        canTime[0x1CFEAC0B] = 0
        canTime[0x18FEF40B] = 0
        canTime[0x18F0010B] = 0
        canTime[0x18FEC4C8] = 0
        canTime[0x18FE5CC8] = 0
        canTime[0x18FE8C1E] = 0
        canTime[0x18D0001E] = 0
        canTime[0x18FF211E] = 0
        canTime[0x18FF221E] = 0
        canTime[0x18FF231E] = 0
        canTime[0x18FED91E] = 0
        canTime[0x18FE401E] = 0
        canTime[0x18F0061E] = 0
        canTime[0x18FEFC1E] = 0
        canTime[0x1CFEC31E] = 0
        canTime[0x18FEEF1E] = 0
        canTime[0x1CFEAC1E] = 0
        canTime[0x18F00503] = 0
        canTime[0x18FF8503] = 0
        canTime[0x18FE4A03] = 0
        canTime[0x0CEFFF50] = 0
        canTime[0x18FECA44] = 0
        canTime[0x18FECA45] = 0
        canTime[0x18DA28F1] = 0
        canTime[0x18F0F62A] = 0
        canTime[0x0CF02FA0] = 0
        canTime[0x18FF172A] = 0
        canTime[0x10F007E8] = 0
        canTime[0x10FF352A] = 0
    }


    private fun convertToCanData(bytes: ByteArray, byteOrder: ByteOrder): CanData {
        val buffer = ByteBuffer.allocate(bytes.size).order(byteOrder).put(bytes)
        buffer.position(0)

        val canId = buffer.int
        val conf = buffer.get()
        val dataSize = (conf and 0xF).toInt()

        val data = ByteArray(dataSize)
        buffer.get(data)

        return CanData(canId, data)
    }

    private val carPropertyEventListener: CarPropertyManager.CarPropertyEventListener =
        object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(value: CarPropertyValue<*>) {
                val propertyId = value.propertyId
                if (propertyId == CarVehiclePropertyIds.VEHICLE_CAN_DATA) {
                    canTime[value.areaId] = Calendar.getInstance().timeInMillis
                    if(value.areaId == 0x18EBFFEE){
                        var data = value.value as ByteArray
                        Log.i("vinus", data.toString())
                        vinNumCount(data)
                    }
                    if(value.areaId == 0x18FE6D44){
                        var data = value.value as ByteArray
                        Log.i("vinus", data[0].toString()+" "+data[1].toString()+" "+data[2].toString()+" "+data[3].toString()+" "+data[4].toString()+" "+data[5].toString()+" "+data[6].toString()+" "+data[7].toString())
                        Log.i("vinus",(CanDataConverter.convertToFloat(data,6,4,4,1f,0f,ByteOrder.LITTLE_ENDIAN)!!).toString())
                    }

                }

            }
            override fun onErrorEvent(propId: Int, zone: Int) {}
        }

    fun vinNumCount(data:ByteArray){
        var text = ""
        var stop = false

        for(i in 1 until 7){
            if(data[i]==0x2A.toByte()){break}
            text += String((data[i].toChar().toString()).toByteArray(charset("windows-1252")), charset("windows-1251"))

        }

        when(data[0]){
            1.toByte()->{
                vin0[0] = text
            }
            2.toByte()->{
                vin0[1] = text
            }
            3.toByte()->{
                vin0[2] = text
            }
        }
        if(vin0[0]!="" && vin0[1]!="" && vin0[2]!=""){
            vinq = vin0[0]+ vin0[1]+vin0[2]

            vin0 = mutableListOf("","","")
            Log.i("vinus", vinq)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        val can = object :ru.kamaz.canservice.aidl.AidleForCan.Stub(){

            override fun getCan(id: Int, startByte: Int, startBit: Int, lenght: Int, factor: Int, offset: Int): DataCan {
                Log.i("data_can","1")
                var idCan = -1
                var dataCan = -1f
                var time: Long = -1
                Log.i("data_can","2")
                val data = carPropertyManager?.getProperty<ByteArray>(CarVehiclePropertyIds.VEHICLE_CAN_DATA,id)
                Log.i("data_can","3")
                val d = data?.value?: ByteArray(0)
                Log.i("cantq",d[0].toString()+" "+d[1].toString()+" "+d[2].toString()+" "+d[3].toString()+" "+d[4].toString()+" "+
                        d[5].toString()+" "+d[6].toString()+" "+d[7].toString()+" "+d[8].toString()+" "+d[9].toString()+" "+
                        d[10].toString()+" "+d[11].toString()+" "+d[12].toString())

                dataCan = CanDataConverter.convertToFloat(d,startByte,startBit,lenght,factor.toFloat(),offset.toFloat(),ByteOrder.LITTLE_ENDIAN)!!


                idCan = data?.areaId ?: 0
                time = data?.timestamp ?: 0

                Log.i("cantq","$idCan -id" + canTime[idCan] + "-time")
                return DataCan(idCan,dataCan,((canTime[idCan] ?: 0) /1000).toInt())
            }

            var count = 0
            override fun sendCan(data: Array<out CanDataInfo>?) {
                Log.i("cant", "2-е")
                var dataByte = ByteArray(8){0xff.toByte();0xff.toByte();0xff.toByte();0xff.toByte();0xff.toByte();0xff.toByte();0xff.toByte();0xff.toByte()}
                var idByte = ByteArray(4)
                var moreData = (0).toULong()
                var idM = if(data != null){data?.get(0)?.id!!}else{0}

                if(data != null){

                    for (i in data.indices){
                        moreData = ((data[i].data.toFloat() - data[i].offset.toFloat()) / data[i].factor).toULong() shl data[i].startBit
                        val array = UByteArray(data[i].lenght/8+if(data[i].startBit>0){1}else{0})
                        for (j in 0 until  array.size) {
                            array[array.size - 1 - j] = (moreData / (2.0.pow(8 * (array.size - 1 - j))).toUInt()).toUInt().toUByte()
                            moreData -= array[array.size - 1 - j]* (2.0.pow(8 * (array.size - 1 - j))).toULong()
                        }
                        for (j in data[i].startByte.. (data[i].lenght/8)+data[i].startByte){
                            dataByte[j] = (dataByte[j] + array[j-data[i].startByte].toByte()).toByte()
                        }

                    }

                    for(i in 0..3){
                        idByte[i] = (idM/ 2.0.pow((24-(8*i)).toDouble())).toInt().toByte()
                        idM -= idByte[i].toInt()*2.0.pow((24-(8*i)).toDouble()).toInt()
                    }
                    carPropertyManager!!.setProperty(ByteArray::class.java, CarVehiclePropertyIds.VEHICLE_CAN_DATA, data[0].id, byteArrayOf(
                        idByte[3], idByte[2], idByte[1], idByte[0], dataByte[0], dataByte[1], dataByte[2], dataByte[3], dataByte[4], dataByte[5], dataByte[6], dataByte[7]
                    ))
                }
            }

            override fun sendAllCan(id: Int, data: ByteArray?) {
                var idByte = ByteArray(4)
                var dataByte = data ?: ByteArray(8)
                var idM = id

                for(i in 0..3){
                    idByte[i] = (idM/ 2.0.pow((24-(8*i)).toDouble())).toInt().toByte()
                    idM -= idByte[i].toInt()*2.0.pow((24-(8*i)).toDouble()).toInt()
                }
                carPropertyManager!!.setProperty(ByteArray::class.java, CarVehiclePropertyIds.VEHICLE_CAN_DATA, id, byteArrayOf(
                    idByte[3], idByte[2], idByte[1], idByte[0], dataByte[0], dataByte[1], dataByte[2], dataByte[3], dataByte[4], dataByte[5], dataByte[6], dataByte[7]
                ))
            }

            override fun setVin() {
                carPropertyManager!!.setProperty(ByteArray::class.java, CarVehiclePropertyIds.VEHICLE_CAN_DATA, 0x18EAEEFE, byteArrayOf(
                    0xFE.toByte(), 0xEE.toByte(), 0xEA.toByte(), 0x18.toByte(), 0xEC.toByte(), 0xFE.toByte(), 0, 0, 0, 0,0, 0))
            }

            override fun getVin(): String {
                return vinq
            }
        }
        return can
        //TODO("Return the communication channel to the service.")
    }


    companion object {
        private val TAG = "data_can"
        var canTime: MutableMap<Int,Long> = mutableMapOf()
        var vin0  = mutableListOf("","","")
        var vinq = ""
    }

}