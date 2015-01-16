package com.example.funapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.funapp.weather.JSONParser;

public class WeatherActivity extends Activity {
	String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=Bucharest&units=metric";
	TextView tvWeather;
	JSONParse asyncTask = new JSONParse();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		tvWeather = (TextView) findViewById(R.id.tvWeather);
		asyncTask.execute(baseURL);
	}

	private class JSONParse extends AsyncTask<String, String, JSONObject> {
		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(WeatherActivity.this);
			pDialog.setMessage("Getting data...");
			pDialog.show();
		}

		@Override
		protected JSONObject doInBackground(String... args) {
			JSONParser jParser = new JSONParser();
			// Getting JSON from URL
			JSONObject json = jParser.getJSONFromUrl(baseURL);
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			pDialog.dismiss();
			try {
				JSONArray array = new JSONArray();
				json.toJSONArray(array);
				JSONObject x = json.getJSONObject("main");
				tvWeather.setText("Current Degrees: " + x.getString("temp")
						+ "°C");
			} catch (JSONException e) {
				tvWeather.setText("Current Degrees: Not Available");
				e.printStackTrace();
			}
		}
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
