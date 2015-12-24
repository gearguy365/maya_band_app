package com.example.bandapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.util.Log;

public class ConnectionManager {

	public static String loginAttempt(String uri, String email, String password) {

		String data = "";
		try {
			data = URLEncoder.encode("email", "UTF-8") 
					+ "=" + URLEncoder.encode(email, "UTF-8");
			data += "&" + URLEncoder.encode("password", "UTF-8") + "="
					+ URLEncoder.encode(password, "UTF-8"); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		Log.i("enocoded","encoded data is:"+data);
		String text = "";
		BufferedReader reader=null;

		try { 
			URL url = new URL(uri);
			URLConnection conn = url.openConnection(); 
			conn.setDoOutput(true); 
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
			wr.write( data ); 
			wr.flush(); 
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;

			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			text = sb.toString();

		} catch(Exception ex) {

			ex.printStackTrace();
			Log.i("customexception","it occured when trying to do my thing...");

		} finally {
			try {
				reader.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return text;
	}

	public static String signupAttempt(String uri, String name, String email, String password) {

		String data = "";

		try {

			data = URLEncoder.encode("name", "UTF-8") 
					+ "=" + URLEncoder.encode(name, "UTF-8");
			data += "&" + URLEncoder.encode("email", "UTF-8") + "="
					+ URLEncoder.encode(email, "UTF-8"); 
			data += "&" + URLEncoder.encode("password", "UTF-8") + "="
					+ URLEncoder.encode(password, "UTF-8"); 

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 

		Log.i("enocoded","encoded data is:"+data);

		String text = "";
		BufferedReader reader=null;

		try	{ 

			URL url = new URL(uri);
			URLConnection conn = url.openConnection(); 
			conn.setDoOutput(true); 
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
			wr.write( data ); 
			wr.flush(); 

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;

			while((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			text = sb.toString();

		} catch(Exception ex) {

			ex.printStackTrace();
			Log.i("customexception","it occured when trying to do my thing...");
		} finally {

			try {
				reader.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return text;
	}
}
