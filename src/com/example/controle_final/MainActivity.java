package com.example.controle_final;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.example.controle_final.R;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.bluetooth.BluetoothDevice;

public class MainActivity extends Activity implements SensorEventListener {
	private static final int REQUEST_ENABLE_BT = 0;

    public String message ;
	public BluetoothDevice btdevice;
	public BluetoothAdapter mBluetoothAdapter;
	public BluetoothSocket btsocket;
	private SensorManager sensorManager;
	public ConnectedThread cted;
	private static final UUID MY_UUID =
  	      UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private OutputStream outStream = null;
	byte[] msgBuffer = new byte[1];
	
	TextView xCoor; // declare X axis object
	TextView yCoor; // declare Y axis object
	TextView zCoor; // declare Z axis object
	TextView seta;
	ListView lista;
	ImageView desenho;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
		    // Device does not support Bluetooth
		}
		
		if (!mBluetoothAdapter.isEnabled()) {
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		
		
		List<String> mArrayAdapter = new ArrayList<String>();
		
		/*Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
		        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		    }
		}*/
		
		
		//conecta o celular com o bluetooth (pareado anteriormente);
		btdevice = mBluetoothAdapter.getRemoteDevice("00:12:12:04:08:39");
		
		
		try {
		      btsocket = btdevice.createRfcommSocketToServiceRecord(MY_UUID);
		    } catch (IOException e) {
		    }
		
		
		//mBluetoothAdapter.cancelDiscovery();
		
		try {
		      btsocket.connect();
		      //out.append("\n...Connection established and data link opened...");
		    } catch (IOException e) {
		      try {
		        btsocket.close();
		      } catch (IOException e2) {
		        
		      }
		    }
		
		try {
		      outStream = btsocket.getOutputStream();
		    } catch (IOException e) {
		      
		    }
		
		
		message="ww";

		
		//ConnectThread ct = new ConnectThread(btdevice);
		//ct.run();
		
		//btsocket=ct.getSocket();
		/*try {
			btsocket.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//cted = new ConnectedThread(btsocket);
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
		zCoor=(TextView)findViewById(R.id.zcoor); // create Z axis object
		seta=(TextView)findViewById(R.id.seta);
		desenho=(ImageView)findViewById(R.id.desenho);
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
		
		/*  More sensor speeds (taken from api docs)
		    SENSOR_DELAY_FASTEST get sensor data as fast as possible
		    SENSOR_DELAY_GAME	rate suitable for games
		 	SENSOR_DELAY_NORMAL	rate (default) suitable for screen orientation changes
		*/
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	
	public void onSensorChanged(SensorEvent event){
		
		
	    
	    
	    
	    
	    
		
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
			// assign directions
			float x=event.values[0];
			float y=event.values[1];
			float z=event.values[2];
			
			xCoor.setText("X: "+x);
			yCoor.setText("Y: "+y);
			zCoor.setText("Z: "+z);
			
			if(((int)z==6 && y < 1) || (((int)z==6&&y>-1) )) {
				
				
				if(!message.equals("bb")){
					message="bb";
					msgBuffer= "bb".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra frente");
				desenho.setImageResource(R.drawable.seta2);
			}
			if(((int)z==7 && y < 3) || (((int)z==7&&y>-3) )) {
				
				
				if(!message.equals("cc")){
					message="cc";
					msgBuffer= "cc".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra frente");
				desenho.setImageResource(R.drawable.seta2);
			}
			if(((int)z==8 && (int)y < 3) || (((int)z==8&&(int)y>-3) )) {
				
				
				if(!message.equals("dd")){
					message="dd";
					msgBuffer= "dd".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra frente");
				desenho.setImageResource(R.drawable.seta2);
			}
			if(z<6){
				if(!message.equals("aa")){
					message="aa";
					msgBuffer= "aa".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("parado");
				desenho.setImageResource(R.drawable.parado1);
			}
			if((int)z== 6 && (int)y ==4)
			{
				if(!message.equals("ba")){
					message="ba";
					msgBuffer= "ba".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra direita");
				desenho.setImageResource(R.drawable.seta3);
			}
			if((int)z == 7 && (int)y == 5)
			{
				if(!message.equals("cb")){
					message="cb";
					msgBuffer= "cb".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra direita");
				desenho.setImageResource(R.drawable.seta3);
			}
			if((int)z == 8 && (int)y == 7)
			{
				if(!message.equals("da")){
					message="da";
					msgBuffer= "da".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra direita");
				desenho.setImageResource(R.drawable.seta3);
			}
			if((int)z== 8 && (int)y ==5)
			{
				if(!message.equals("db")){
					message="db";
					msgBuffer= "db".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra direita");
				desenho.setImageResource(R.drawable.seta3);
			}
			if((int)z== 8 && (int)y ==3)
			{
				if(!message.equals("dc")){
					message="dc";
					msgBuffer= "dc".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra direita");
				desenho.setImageResource(R.drawable.seta3);
			}
			if((int)z==6 && (int)y ==-3 )
			{
				if(!message.equals("ab")){
					message="ab";
					msgBuffer= "ab".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra esquerda");
				desenho.setImageResource(R.drawable.seta);
			}
			if((int)z>7 && (int)y <-7 )
			{
				if(!message.equals("ac")){
					message="ac";
					msgBuffer= "ac".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra esquerda");
				desenho.setImageResource(R.drawable.seta);
			}
			if((int)z>7 && (int)y <-5 )
			{
				if(!message.equals("bc")){
					message="bc";
					msgBuffer= "bc".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra esquerda");
				desenho.setImageResource(R.drawable.seta);
			}
			if((int)z>8 && (int)y <-3 )
			{
				if(!message.equals("cd")){
					message="cd";
					msgBuffer= "cd".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra esquerda");
				desenho.setImageResource(R.drawable.seta);
			}
			if((int)z>8 && (int)y <-5 )
			{
				if(!message.equals("bd")){
					message="bd";
					msgBuffer= "bd".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra esquerda");
				desenho.setImageResource(R.drawable.seta);
			}
			if((int)z>8 && (int)y <-7 )
			{
				if(!message.equals("ad")){
					message="ad";
					msgBuffer= "ad".getBytes();
					try {
						outStream.write(msgBuffer);
					} catch (IOException e) {
			      
				    }
				}
				seta.setText("pra esquerda");
				desenho.setImageResource(R.drawable.seta);
			}
			
		}
	}
}
	 