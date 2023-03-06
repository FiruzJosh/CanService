// AidleForCan.aidl
package ru.kamaz.canservice.aidl;

import ru.kamaz.canservice.aidl.DataCan;
import ru.kamaz.canservice.aidl.CanDataInfo;

interface AidleForCan {
    DataCan getCan(int id, int startByte, int startBit,int lenght, int factor, int offset);
    void sendCan(in CanDataInfo[] data);
    void sendAllCan(int id,in byte[] data);

    void setVin();
    String getVin();

}