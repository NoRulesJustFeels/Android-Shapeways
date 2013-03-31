/*
 * Copyright (C) 2013 ENTERTAILION LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.entertailion.android.shapeways;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.entertailion.android.shapeways.api.Base;
import com.entertailion.android.shapeways.api.Base.Result;
import com.entertailion.android.shapeways.api.Materials;
import com.entertailion.android.shapeways.api.Materials.Material;
import com.entertailion.android.shapeways.api.Models;
import com.entertailion.android.shapeways.api.Orders;
import com.entertailion.android.shapeways.api.Prices;
import com.entertailion.android.shapeways.api.Printers;
import com.entertailion.android.shapeways.api.ShapewaysClient;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainActivity extends Activity {
	private static final String LOG_TAG = "MainActivity";

	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		if (((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getOauthToken() == null) {
			// Get the OAuth token; launch activity to show webview for user
			// authorization
			new Thread(new Runnable() {
				public void run() {
					try {
						Intent intent = new Intent(MainActivity.this, ShapewaysActivity.class);
						startActivity(intent);
					} catch (Exception e) {
						Log.e(LOG_TAG, "run", e);
					}
				}
			}).start();
		}

		((Button) findViewById(R.id.button_api)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						getApi();
						//getOrders();
						// getPrinters();
						// getMaterials();
						// postOrder();
						// getModels();
						// postPrices();
					}
				}).start();
			}
		});
	}

	private void getApi() {
		Log.d(LOG_TAG, "getApi");
		try {
			String response = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getResponse(
					ShapewaysClient.API_URL_BASE + ShapewaysClient.API_PATH);
			Log.i(LOG_TAG, "response=" + response);

			// http://wiki.fasterxml.com/JacksonInFiveMinutes
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(response, Map.class);
			Log.i(LOG_TAG, "map=" + map);

			Base base = mapper.readValue(response, Base.class);
			Log.i(LOG_TAG, "result=" + base.getResult());
			Log.i(LOG_TAG, "rateLimit.getRemaining=" + base.getRateLimit().getRemaining());
		} catch (Exception e) {
			Log.e(LOG_TAG, "getApi", e);
		}
	}

	private void getOrders() {
		Log.d(LOG_TAG, "getOrders");
		try {
			String response = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getResponse(
					ShapewaysClient.API_URL_BASE + ShapewaysClient.ORDERS_PATH);
			Log.i(LOG_TAG, "response=" + response);

			// http://wiki.fasterxml.com/JacksonInFiveMinutes
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(response, Map.class);
			Log.i(LOG_TAG, "map=" + map);

			Orders orders = mapper.readValue(response, Orders.class);
			Log.i(LOG_TAG, "result=" + orders.getResult());
			Log.i(LOG_TAG, "getItemCount=" + orders.getItemCount());
		} catch (Exception e) {
			Log.e(LOG_TAG, "getOrders", e);
		}
	}

	private void postOrder() {
		Log.d(LOG_TAG, "postOrder");
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put(ShapewaysClient.MODEL_ID_PARAMETER, "1002632");
			parameters.put(ShapewaysClient.MATERIAL_ID_PARAMETER, "6");
			parameters.put(ShapewaysClient.QUANTITY_ID_PARAMETER, "1");
			String response = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().postResponse(
					ShapewaysClient.API_URL_BASE + ShapewaysClient.ORDERS_PATH, parameters);
			Log.i(LOG_TAG, "response=" + response);

			// http://wiki.fasterxml.com/JacksonInFiveMinutes
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(response, Map.class);
			Log.i(LOG_TAG, "map=" + map);

			Orders orders = mapper.readValue(response, Orders.class);
			// Log.i(LOG_TAG, "result=" + orders.getResult());
			Log.i(LOG_TAG, "reason=" + orders.getReason());
			// Log.i(LOG_TAG, "getItemCount=" + orders.getItemCount());
		} catch (Exception e) {
			Log.e(LOG_TAG, "postOrder", e);
		}
	}

	private void getPrinters() {
		Log.d(LOG_TAG, "getPrinters");
		try {
			String response = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getResponse(
					ShapewaysClient.API_URL_BASE + ShapewaysClient.PRINTERS_PATH);
			Log.i(LOG_TAG, "response=" + response);

			// http://wiki.fasterxml.com/JacksonInFiveMinutes
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(response, Map.class);
			Log.i(LOG_TAG, "map=" + map);

			Printers printers = mapper.readValue(response, Printers.class);
			Log.i(LOG_TAG, "result=" + printers.getResult());
			Log.i(LOG_TAG, "printers=" + printers.getPrinters().size());
		} catch (Exception e) {
			Log.e(LOG_TAG, "getPrinters", e);
		}
	}

	private void getMaterials() {
		Log.d(LOG_TAG, "getMaterials");
		try {
			String response = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getResponse(
					ShapewaysClient.API_URL_BASE + ShapewaysClient.MATERIALS_PATH);
			Log.i(LOG_TAG, "response=" + response);

			// http://wiki.fasterxml.com/JacksonInFiveMinutes
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(response, Map.class);
			Log.i(LOG_TAG, "map=" + map);

			// The response does not use array[] notation for the list of
			// materials.
			// So, have to manually parse the JSON to get the list data.
			MappingJsonFactory f = new MappingJsonFactory();
			JsonParser jp = f.createJsonParser(response);
			Materials materials = new Materials();
			jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				String fieldname = jp.getCurrentName();
				Log.d(LOG_TAG, "fieldname=" + fieldname);
				jp.nextToken(); // move to value
				if ("materials".equals(fieldname)) { // contains an object
					// Material material = jp.readValueAs(Material.class);
					boolean end = false;
					do {
						Material material = new Material();
						while (jp.nextToken() != JsonToken.END_OBJECT) {
							String namefield = jp.getCurrentName();
							Log.d(LOG_TAG, "namefield=" + namefield);
							jp.nextToken(); // move to value
							if ("materialId".equals(namefield)) {
								material.setMaterialId(jp.getText());
							} else if ("title".equals(namefield)) {
								material.setTitle(jp.getText());
							} else if ("supportsColorFiles".equals(namefield)) {
								material.setSupportsColorFiles(Integer.parseInt(jp.getText()));
							} else if ("printerId".equals(namefield)) {
								material.setPrinterId(jp.getText());
							} else if ("swatch".equals(namefield)) {
								material.setSwatch(jp.getText());
							} else {
								Log.w(LOG_TAG, "Unrecognized material field: " + namefield);
							}
						}
						materials.getMaterials().add(material);
						JsonToken token = jp.nextToken();
						Log.d(LOG_TAG, "token=" + token);
						if (token == JsonToken.FIELD_NAME) {
							try {
								Integer.parseInt(jp.getCurrentName());
								jp.nextToken();
							} catch (Exception ex) {
								end = true;
							}
						} else if (token == JsonToken.END_OBJECT) {
							end = true;
						}
					} while (!end);

				} else if ("result".equals(fieldname)) {
					if (jp.getText().equals(Result.success.name())) {
						materials.setResult(Result.success);
					} else if (jp.getText().equals(Result.failure.name())) {
						materials.setResult(Result.failure);
					}
				} else {
					Log.w(LOG_TAG, "Unrecognized materials field: " + fieldname);
				}
			}
			jp.close(); // ensure resources get cleaned up timely and properly

			Log.i(LOG_TAG, "materials=" + materials.getMaterials().size());
			Log.i(LOG_TAG, "material: id=" + materials.getMaterials().get(0).getMaterialId());
			Log.i(LOG_TAG, "material: title=" + materials.getMaterials().get(0).getTitle());
			Log.i(LOG_TAG, "material: swatch=" + materials.getMaterials().get(0).getSwatch());
		} catch (Exception e) {
			Log.e(LOG_TAG, "getMaterials", e);
		}
	}

	private void getModels() {
		Log.d(LOG_TAG, "getModels");
		try {
			String response = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getResponse(
					ShapewaysClient.API_URL_BASE + ShapewaysClient.MODELS_PATH);
			Log.i(LOG_TAG, "response=" + response);

			// http://wiki.fasterxml.com/JacksonInFiveMinutes
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(response, Map.class);
			Log.i(LOG_TAG, "map=" + map);

			Models models = mapper.readValue(response, Models.class);
			Log.i(LOG_TAG, "result=" + models.getResult());
			Log.i(LOG_TAG, "models=" + models.getModels().size());
		} catch (Exception e) {
			Log.e(LOG_TAG, "getModels", e);
		}
	}

	private void postPrices() {
		Log.d(LOG_TAG, "postPrices");
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			// parameters.put(ShapewaysClient.MODEL_ID_PARAMETER, "-1");
			// parameters.put(ShapewaysClient.MATERIAL_ID_PARAMETER, "-1");
			// parameters.put(ShapewaysClient.QUANTITY_ID_PARAMETER, "-1");
			String response = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().postResponse(
					ShapewaysClient.API_URL_BASE + ShapewaysClient.PRICES_PATH, parameters);
			Log.i(LOG_TAG, "response=" + response);

			// http://wiki.fasterxml.com/JacksonInFiveMinutes
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(response, Map.class);
			Log.i(LOG_TAG, "map=" + map);

			Prices prices = mapper.readValue(response, Prices.class);
			Log.i(LOG_TAG, "result=" + prices.getResult());
			Log.i(LOG_TAG, "prices=" + prices.getPrices().size());
		} catch (Exception e) {
			Log.e(LOG_TAG, "postPrices", e);
		}
	}

	/////////////////// Testing

	public void doOAuth() {
		try {
			OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ShapewaysApplication.CONSUMER_KEY, ShapewaysApplication.CONSUMER_SECRET);

			consumer.setTokenWithSecret(((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getOauthToken(),
					((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getOauthTokenSecret());

			// http://hc.apache.org/httpcomponents-client-ga/tutorial/html/fundamentals.html#d5e68
			// create an HTTP request to a protected resource
			HttpGet request = new HttpGet(ShapewaysClient.API_URL_BASE + ShapewaysClient.API_PATH);

			// sign the request
			consumer.sign(request);

			// send the request
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			Log.d(LOG_TAG, "response=" + response.getStatusLine());
			Log.d(LOG_TAG, "response=" + EntityUtils.toString(response.getEntity()));

		} catch (Exception e) {
			Log.e(LOG_TAG, "doOAuth", e);
		}
	}

	public void doOAuth1() {
		try {
			OAuthConsumer consumer = new CommonsHttpOAuthConsumer(ShapewaysApplication.CONSUMER_KEY, ShapewaysApplication.CONSUMER_SECRET);

			consumer.setTokenWithSecret(((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getOauthToken(),
					((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getOauthTokenSecret());

			// http://hc.apache.org/httpcomponents-client-ga/tutorial/html/fundamentals.html#d5e68
			String bodyString = ShapewaysClient.MODEL_ID_PARAMETER + "=" + URLEncoder.encode("1002632", "UTF-8") + "&" + ShapewaysClient.MATERIAL_ID_PARAMETER
					+ "=" + URLEncoder.encode("6", "UTF-8") + "&" + ShapewaysClient.QUANTITY_ID_PARAMETER + "=" + URLEncoder.encode("1", "UTF-8");
			HttpPost request = new HttpPost(ShapewaysClient.API_URL_BASE + ShapewaysClient.PRICES_PATH);
			Log.d(LOG_TAG, "body=" + bodyString);
			StringEntity body = new StringEntity(bodyString);
			// request.setEntity(body);
			// request.getParams().setParameter(ShapewaysClient.MODEL_ID_PARAMETER,
			// "1002632");
			// request.getParams().setParameter(ShapewaysClient.MATERIAL_ID_PARAMETER,
			// "6");
			// request.getParams().setParameter(ShapewaysClient.QUANTITY_ID_PARAMETER,
			// "1");

			consumer.sign(request);

			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);

			Log.d(LOG_TAG, "response=" + response.getStatusLine());
			Log.d(LOG_TAG, "response=" + EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			Log.e(LOG_TAG, "doOAuth", e);
		}
	}

}
