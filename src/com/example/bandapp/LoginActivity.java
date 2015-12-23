package com.example.bandapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
		attemptLogin(uri, email, password);
		
		
	}
	
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
	

	private void attemptLogin(String uri, String email, String password) {
		
		RequestPackage p = new RequestPackage();
		p.setMethod("POST");
		p.setUri(uri);
		p.setParam("email", email);
		p.setParam("password", password);
		
		MyTask task = new MyTask();
		task.execute(p);
	}
	
	private class MyTask extends AsyncTask<RequestPackage, String, String> {

		@Override
		protected void onPreExecute() {
			loading.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected String doInBackground(RequestPackage... params) {
			String content = HttpManager.getData(params[0]);
			return content;
		}
		
		@Override
		protected void onPostExecute(String result) {
			/*
			try {
				JSONObject reader = new JSONObject(result);
				if(reader.getString("status").equals("success")) {
					Toast.makeText(getApplicationContext(), "the attempt seems to be successful!", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getApplicationContext(), "this is invalid", Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			*/
			TextView log = (TextView)findViewById(R.id.log);
			log.setText(result);
			
		}
		
	}
}
