package com.example.funapp;

import android.app.Application;

import com.parse.Parse;

public class MainApp extends Application {
	@Override
	public void onCreate() {
		Parse.initialize(this, "6oTPKYBssJI34nXxsEuny4LW24jnbc8HaQXxSCvn",
				"JvmAwaBpB3xv8mZl2hhbBW7TIqVwmpgrtIZYKcpJ");
	}
}