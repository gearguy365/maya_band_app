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

public class LoginActivity extends Activity {
	ProgressBar loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loading = (ProgressBar)findViewById(R.id.progressBar1);
		loading.setVisibility(View.INVISIBLE);
	}

	public void onClickLogin(View v) {
		EditText email_view = (EditText)findViewById(R.id.login_username);
		String email = email_view.getText().toString();
		EditText password_view = (EditText)findViewById(R.id.login_password);
		String password = password_view.getText().toString();
		String uri = "http://54.169.52.143/maya-tests-api/login";
		MyTask task = new MyTask();
		task.execute(uri, email, password);
	}

	public void onClickSignUp(View v) {
		Intent i = new Intent(this, SignupActivity.class);
		startActivity(i);
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

	private class MyTask extends AsyncTask <String, String, String> {

		@Override
		protected void onPreExecute() {
			loading.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {
			String response = ConnectionManager.loginAttempt(params[0], params[1], params[2]);
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
			if (status.equals("success")) {
				Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

				Log.i("data_reply",data);

				Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
				i.putExtra("user_deatils", data);
				startActivity(i);
			}
			else {
				Toast.makeText(getApplicationContext(), "Login Unsuccessful!", Toast.LENGTH_SHORT).show();
			}
		}	
	}
}
