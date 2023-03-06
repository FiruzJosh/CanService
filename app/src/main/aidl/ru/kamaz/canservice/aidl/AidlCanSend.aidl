// AidlCanSend.aidl
package ru.kamaz.canservice.aidl;

// Declare any non-default types here with import statements

interface AidlCanSend {
    void sendCan(int id, int startByte, int startBit, int factor, int offset,float data);
}