package com.example.funapp;

	import com.parse.Parse;

	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.Button;

	public class WelcomeActivity extends Activity {

		/** Called when the activity is first created. */
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.welcome);
			
			Parse.initialize(this, "BfPiWiWgG5Z9QYS6nU6GZxRhLUkgRpIHBqqOu4i6", "QngYzKIUsugkta3WJTcCWf7FWLkXr0rCODhekP4o");
			getIntent();

			
			
			Button signUp = (Button)findViewById(R.id.SignUp);
			signUp.setOnClickListener(new OnClickListener() {
			     public void onClick(View v) {
			         Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
			         startActivity(i);
			     }		
			 });
			
			Button logIn = (Button)findViewById(R.id.LogIn);
			logIn.setOnClickListener(new OnClickListener() {
			     public void onClick(View v) {
			         Intent j = new Intent(getApplicationContext(), MainActivity.class);
			         startActivity(j);			     }
 });}}


	
