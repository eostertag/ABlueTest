package com.example.abluetest;

import com.example.abluetest.util.Converter;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView temperature = (TextView) findViewById(R.id.temperatureMessageF);
		Resources res = getResources();
		temperature.setText(""
				+ Converter.CelsiusToFahrenheit(getTemperature()) + " "
				+ res.getString(R.string.grade_symbol));
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

}
