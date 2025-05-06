package com.example.blenotification;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    public static BluetoothGatt bluetoothGatt;
    public static BluetoothGattCharacteristic notifyChar;
    public static final UUID SERVICE_UUID = UUID.fromString("6e40fc00-b5a3-f393-e0a9-e50e24dcca9e");
    public static final UUID CHARACTERISTIC_UUID = UUID.fromString("6e40fc20-b5a3-f393-e0a9-e50e24dcca9e");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice("41:42:79:8A:91:4B"); // Replace with your watch MAC
        bluetoothGatt = device.connectGatt(this, false, new BluetoothGattCallback() {
            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                BluetoothGattService service = gatt.getService(SERVICE_UUID);
                notifyChar = service.getCharacteristic(CHARACTERISTIC_UUID);
            }
        });
        bluetoothGatt.connect();
    }
}
