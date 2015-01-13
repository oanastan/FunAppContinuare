package com.example.funapp;

import com.example.funapp.NextActivity;
import com.example.funapp.MainActivity;
import com.example.funapp.R;
import com.example.funapp.RegisterActivity;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static final int PROGRESS = 0x1;

	private Handler myHandler = new Handler();
	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	private TextView attempts;
	int counter = 3;
	private EditText username;
	private EditText password;
	private Button login;
	Button register;
	String usernametxt;
	String passwordtxt;
	private TextView ErrorField;
		

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);
		username = (EditText)findViewById(R.id.editText1);
	    password = (EditText)findViewById(R.id.editText2);
	    login = (Button)findViewById(R.id.loginButton);
	    register = (Button) findViewById(R.id.button1);

		mProgress = (ProgressBar) findViewById(R.id.progressBar1);
		attempts = (TextView)findViewById(R.id.textView1);
	    attempts.setText(Integer.toString(counter));
	    
	    
	    usernametxt = username.getText().toString();
        passwordtxt = password.getText().toString();
        ErrorField = (TextView) findViewById(R.id.error_messages);
	    
	    
	    Parse.enableLocalDatastore(this);
	    Parse.initialize(this, "BfPiWiWgG5Z9QYS6nU6GZxRhLUkgRpIHBqqOu4i6", "QngYzKIUsugkta3WJTcCWf7FWLkXr0rCODhekP4o");
	        
	}
	    
	    public void login(View view){
	    	
	        if(username.getText().toString().equals("admin") && 
	        		password.getText().toString().equals("admin")){
	        	    	  		Intent i = new Intent(this, NextActivity.class);
	        	    	  		startActivity(i);
	        	   }	
	        	   else{
	        	      Toast.makeText(getApplicationContext(), "Wrong Credentials",
	        	      Toast.LENGTH_SHORT).show();
	        	      attempts.setBackgroundColor(Color.RED);	
	        	      counter--;
	        	      attempts.setText(Integer.toString(counter));
	        	      if(counter==0){
	        	         login.setEnabled(false);
	        	      }

	        	   }
	    
	    
		Button loginBtn = (Button) findViewById(R.id.loginButton);
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(getApplicationContext(),
						NextActivity.class);
				myHandler.post(r);
}
 }); }
		 
	    public void signIn(final View v){
			v.setEnabled(false);
			ParseUser.logInInBackground(usernametxt, passwordtxt, new LogInCallback() {
				@Override
				public void done(ParseUser user, ParseException e) {
					if (user != null) {
    
						Intent intent = new Intent(MainActivity.this, NextActivity.class);
						startActivity(intent);
						finish();
					} else {
						// Sign up failed. Look at the ParseException to see what happened.
						switch(e.getCode()){
						case ParseException.USERNAME_TAKEN:
							ErrorField.setText("Sorry, this username has already been taken.");
							break;
						case ParseException.USERNAME_MISSING:
							ErrorField.setText("Sorry, you must supply a username to register.");
							break;
						case ParseException.PASSWORD_MISSING:
							ErrorField.setText("Sorry, you must supply a password to register.");
							break;
						case ParseException.OBJECT_NOT_FOUND:
							ErrorField.setText("Sorry, those credentials were invalid.");
							break;
						default:
							ErrorField.setText(e.getLocalizedMessage());
							break;
						}
						v.setEnabled(true);
					}
				}
			});
		}

		public void showRegistration(View v) {
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			finish();
		}
	

		/*Button changepasswordBtn = (Button) findViewById(R.id.changepasswordButton);
		changepasswordBtn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent j = new Intent(getApplicationContext(),
							OtherActivity.class);
				myHandler.post(x);
			}
			
		});*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	Runnable r = new Runnable() {

		@Override
		public void run() {
			if (mProgressStatus == 100) {

				startActivity(new Intent(getApplicationContext(),
						NextActivity.class));
			} else {
				mProgress.setProgress(mProgressStatus);
				mProgressStatus += 10;
				myHandler.postDelayed(r, 250);
			}
		}
	};
	
	Runnable x = new Runnable() {

		@Override
		public void run() {
			if (mProgressStatus == 100) {

				startActivity(new Intent(getApplicationContext(),
						OtherActivity.class));
			} else {
				mProgress.setProgress(mProgressStatus);
				mProgressStatus += 10;
				myHandler.postDelayed(x, 250);
			}
		}
	};
}
