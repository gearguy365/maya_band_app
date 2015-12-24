package com.example.bandapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class SignupActivity extends Activity {
	ProgressBar loading;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		loading = (ProgressBar)findViewById(R.id.progressBar1);
		loading.setVisibility(View.INVISIBLE);
	}
	
	public void onClickRegister(View v) {
		EditText namefield = (EditText)findViewById(R.id.register_name);
		EditText emailfield = (EditText)findViewById(R.id.register_email);
		EditText passwordfield = (EditText)findViewById(R.id.register_password);
		EditText passwordagainfield = (EditText)findViewById(R.id.register_passwordAgain);
		
		String name = namefield.getText().toString();
		String email = emailfield.getText().toString();
		String password = passwordfield.getText().toString();
		String passwordagain = passwordagainfield.getText().toString();
		
		if(password.equals(passwordagain)) {
			String uri = "http://54.169.52.143/maya-tests-api/users";
			String [] parameter = {uri,name,email,password};
			MyTask task = new MyTask();
			task.execute(parameter);
		}
		else {
			Toast.makeText(getApplicationContext(), "Enter passwords correctly", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class MyTask extends AsyncTask <String[], String, String> {

		@Override
		protected void onPreExecute() {
			loading.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected String doInBackground(String[]... params) {
			String response = ConnectionManager.signupAttempt(params[0][0], params[0][1], params[0][2], params[0][3]);
			return response;
		}
		
		@Override
		protected void onPostExecute(String result) {
			Log.i("response", "the response is: "+result);
			loading.setVisibility(View.INVISIBLE);
			String status = "";
			String data = "";
			JSONObject object = null;
			try {
				object = new JSONObject(result);
				status = object.getString("status");
				data = object.getString("data");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (status.equals("success")){
				Toast.makeText(getApplicationContext(), "Account created! Login & get started", Toast.LENGTH_LONG).show();
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(i);
			}
			else {
				Toast.makeText(getApplicationContext(), "Account creation failed", Toast.LENGTH_SHORT).show();
			}
		}	
	}
}
