package com.example.abluetest;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import com.example.abluetest.util.Converter;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView temperature = (TextView) findViewById(R.id.temperatureMessageF);
		Resources res = getResources();
		temperature.setText(""
				+ Converter.CelsiusToFahrenheit(getTemperature()) + " "
				+ res.getString(R.string.grade_symbol));
		
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
//        TODO check if device was previously paired
//        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//        if (pairedDevices.size() > 0) {
//            for (BluetoothDevice device : pairedDevices) {
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
//            }
//        }
	}

	private double getTemperature() {
		/* ToDo: Get from Bluetooth device */
		return 24.0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
     
        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;
     
            try {
            	//APP UUID
                tmp = device.createRfcommSocketToServiceRecord(new UUID(1l, 1l));
            } catch (IOException e) { }
            mmSocket = tmp;
        }
     
        public void run() {
            mBluetoothAdapter.cancelDiscovery();
     
            try {
                mmSocket.connect();
            } catch (IOException connectException) {
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }
     
            // Do work to manage the connection (in a separate thread)
//            manageConnectedSocket(mmSocket);
//            Get the InputStream and OutputStream that handle transmissions through the socket, 
//            via getInputStream() and getOutputStream(), respectively.
//            Read and write data to the streams with read(byte[]) and write(byte[]).
        }
     
        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

}
