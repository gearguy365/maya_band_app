package com.example.bandapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DashboardActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		Bundle extras = getIntent().getExtras();
		String userdetail = extras.getString("user_deatils");
		JSONObject object = null;
		String name = "";
		String mail = "";
		int id = 0;
		try {
			object = new JSONObject(userdetail);
			name = object.getString("name");
			mail = object.getString("email");
			id = object.getInt("id");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		TextView welcometext = (TextView)findViewById(R.id.welcome_text);
		welcometext.setText("Welcome "+name+" !");

		TextView user_data = (TextView)findViewById(R.id.user_id);
		user_data.setText("Profile Details\n"+"Name: "+name+"\n"+"email: "+mail+"\n"+"Assigned ID: "+id);
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
}
