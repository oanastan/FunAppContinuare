package com.example.funapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class NextActivity extends Activity {

	RelativeLayout mainRl;
	Button changeBg;
	Button ceva, btnWeather;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);

		mainRl = (RelativeLayout) findViewById(R.id.mainR1);
		changeBg = (Button) findViewById(R.id.changeBgBtn);
		changeBg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mainRl.setBackgroundColor(Color.RED);

			}
		});

		addListenerOnButton();

		btnWeather = (Button) findViewById(R.id.weatherBtn);
		btnWeather.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(NextActivity.this,
						WeatherActivity.class));

			}
		});
	}

	public void logout(View view) {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();

	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub

		image = (ImageView) findViewById(R.id.img);
		ImageView image1 = (ImageView) findViewById(R.id.img);
		ceva = (Button) findViewById(R.id.ceva);
		ceva.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				image.setImageResource(R.drawable.pets_launcher);

			}

		});

	}
}
