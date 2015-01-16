package com.example.funapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {

	private EditText mEmailField;
	private EditText usernametxt;
	private EditText passwordtxt;
	private TextView ErrorField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		mEmailField = (EditText) findViewById(R.id.register_email);
		usernametxt = (EditText) findViewById(R.id.register_username);
		passwordtxt = (EditText) findViewById(R.id.register_password);
		ErrorField = (TextView) findViewById(R.id.error_messages);
	}

	public void register(final View v) {
		if (mEmailField.getText().length() == 0
				|| usernametxt.getText().length() == 0
				|| passwordtxt.getText().length() == 0)
			return;

		v.setEnabled(false);
		ParseUser user = new ParseUser();
		user.setEmail(mEmailField.getText().toString());
		user.setUsername(usernametxt.getText().toString());
		user.setPassword(passwordtxt.getText().toString());

		ErrorField.setText("");

		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {
					Intent intent = new Intent(RegisterActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					// Sign up didn't succeed. Look at the ParseException
					// to figure out what went wrong
					switch (e.getCode()) {
					case ParseException.EMAIL_TAKEN:
						ErrorField
								.setText("Sorry, this e-mail address has already been taken.");
					case ParseException.EMAIL_MISSING:
						ErrorField
								.setText("Sorry, you must supply an e-mail address to register.");
						break;
					case ParseException.INVALID_EMAIL_ADDRESS:
						ErrorField
								.setText("Sorry, you must supply a valid e-mail address to register.");
						break;
					case ParseException.USERNAME_TAKEN:
						ErrorField
								.setText("Sorry, this username has already been taken.");
						break;
					case ParseException.USERNAME_MISSING:
						ErrorField
								.setText("Sorry, you must supply a username to register.");
						break;
					case ParseException.PASSWORD_MISSING:
						ErrorField
								.setText("Sorry, you must supply a password to register.");
						break;
					default:
						ErrorField.setText(e.getLocalizedMessage());
					}
					v.setEnabled(true);
				}
			}
		});
	}

	public void showLogin(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
