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
package com.entertailion.android.shapeways.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.entertailion.android.shapeways.ShapewaysApplication;

/**
 * OAuth 1.0 client for Shapeways API.
 * 
 * @see http://developers.shapeways.com/getting-started?li=dh_gs
 * 
 * @author leon_nicholls
 * 
 */
public class ShapewaysClient {

	private static final String LOG_TAG = "ShapewaysClient";

	// http://developers.shapeways.com/docs?li=d_gettingStarted
	public static final String API_URL_BASE = "http://api.shapeways.com";
	public static final String REQUEST_TOKEN_PATH = "/oauth1/request_token/v1";
	public static final String ACCESS_TOKEN_PATH = "/oauth1/access_token/v1";
	public static final String API_PATH = "/api/v1/";
	public static final String ORDERS_PATH = "/orders/cart/v1";
	public static final String MATERIALS_PATH = "/materials/v1";
	public static final String MATERIAL_PATH = "/materials/%s/v1"; // /materials/{materialId}/v1
	public static final String MODELS_PATH = "/models/v1/";
	public static final String MODEL_PATH = "/models/%s/v1"; // /models/{modelId}/v1
	public static final String MODEL_INFO_PATH = "/models/%s/v1"; // /models/{modelId}/info/v1
	public static final String MODEL_FILES_PATH = "/models/%s/files/v1/"; // /models/{modelId}/files/v1/
	public static final String MODEL_FILES_VERSION_PATH = "/models/%s/files/%s/v1"; // /models/{modelId}/files/{fileVersion}/v1
	public static final String MODEL_PHOTOS_PATH = "/models/%s/photos/v1"; // /models/{modelId}/photos/v1
	public static final String PRINTERS_PATH = "/printers/v1";
	public static final String PRINTER_PATH = "/printers/%s/v1"; // /printers/{printerId}/v1
	public static final String PRICES_PATH = "/price/v1";

	public static final String MODEL_ID_PARAMETER = "modelId";
	public static final String MATERIAL_ID_PARAMETER = "materialId";
	public static final String QUANTITY_ID_PARAMETER = "quantity";

	private static final String POST = "POST";
	private static final String GET = "GET";
	public static final String QUESTION_MARK = "?";
	public static final String AMPERSAND = "&";
	public static final String EQUALS = "=";
	public static final String ENCODING = "UTF-8";

	public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
	public static final String AUTHENTICATION_URL = "authentication_url";
	public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
	public static final String OAUTH_CALLBACK = "oauth_callback";
	public static final String OAUTH_TOKEN = "oauth_token";
	public static final String OAUTH_VERIFIER = "oauth_verifier";

	public static final String OAUTH_TIMESTAMP = "oauth_timestamp";
	public static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
	public static final String OAUTH_VERSION = "oauth_version";
	public static final String OAUTH_NONCE = "oauth_nonce";
	public static final String OAUTH_VERSION_1_0 = "1.0";
	public static final String OAUTH_SIGNATURE_METHOD_HMAC_SHA1 = "HMAC-SHA1";

	public static final String OAUTH_SIGNATURE = "oauth_signature";

	private Context context;
	private final String consumerKey;
	private final String consumerSecret;
	private String oauthToken;
	private String oauthTokenSecret;
	private OAuthConsumer consumer; // https://code.google.com/p/oauth-signpost/

	/**
	 * Register your app to get a consumer key and secret
	 * 
	 * @see http://developers.shapeways.com/manage-apps
	 * 
	 * @param consumerKey
	 * @param consumerSecret
	 */
	public ShapewaysClient(Context context, String consumerKey, String consumerSecret) {
		this.context = context;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		// check if OAuth token obtained before
		oauthToken = preferences.getString(ShapewaysClient.OAUTH_TOKEN, null);
		oauthTokenSecret = preferences.getString(ShapewaysClient.OAUTH_TOKEN_SECRET, null);

		// https://code.google.com/p/oauth-signpost/wiki/GettingStarted
		consumer = new CommonsHttpOAuthConsumer(ShapewaysApplication.CONSUMER_KEY, ShapewaysApplication.CONSUMER_SECRET);
		consumer.setTokenWithSecret(oauthToken, oauthTokenSecret);
	}

	/**
	 * Get the request token
	 * 
	 * @param callbackUrl
	 *            HTTP callback URL for handling the user authorization
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getRequestToken(String callbackUrl) throws Exception {
		Log.d(LOG_TAG, "getRequestToken");
		URLConnection urlConnection = getUrlConnection(API_URL_BASE + REQUEST_TOKEN_PATH, true);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());

		Request request = new Request(POST);
		request.addParameter(OAUTH_CONSUMER_KEY, consumerKey);
		request.addParameter(OAUTH_CALLBACK, callbackUrl);
		request.sign(API_URL_BASE + REQUEST_TOKEN_PATH, consumerSecret, null);
		outputStreamWriter.write(request.toString());
		outputStreamWriter.close();

		return readParams(urlConnection.getInputStream());
	}

	/**
	 * Get the access token
	 * 
	 * @param requestToken
	 * @param requestTokenSecret
	 * @param requestTokenVerifier
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getAccessToken(String requestToken, String requestTokenSecret, String requestTokenVerifier) throws Exception {
		Log.d(LOG_TAG, "getAccessToken");
		URLConnection urlConnection = getUrlConnection(API_URL_BASE + ACCESS_TOKEN_PATH, true);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());

		Request request = new Request(POST);
		request.addParameter(OAUTH_CONSUMER_KEY, consumerKey);
		request.addParameter(OAUTH_TOKEN, requestToken);
		request.addParameter(OAUTH_VERIFIER, requestTokenVerifier);
		request.sign(API_URL_BASE + ACCESS_TOKEN_PATH, consumerSecret, requestTokenSecret);
		outputStreamWriter.write(request.toString());
		outputStreamWriter.close();

		return readParams(urlConnection.getInputStream());
	}

	/**
	 * Call a Shapeways API with GET
	 * 
	 * @param apiUrl
	 * @param requestToken
	 * @param requestTokenSecret
	 * @return
	 * @throws Exception
	 */
	private String getResponseOld(String apiUrl) throws Exception {
		Log.d(LOG_TAG, "getResponse: url=" + apiUrl);
		Request request = new Request(GET);
		request.addParameter(OAUTH_CONSUMER_KEY, consumerKey);
		request.addParameter(OAUTH_TOKEN, oauthToken);
		request.sign(apiUrl, consumerSecret, oauthTokenSecret);

		URLConnection urlConnection = getUrlConnection(apiUrl + QUESTION_MARK + request.toString(), false);

		return readResponse(urlConnection.getInputStream());
	}

	/**
	 * Call a Shapeways API with GET
	 * 
	 * @param apiUrl
	 * @param requestToken
	 * @param requestTokenSecret
	 * @return
	 * @throws Exception
	 */
	public String getResponse(String apiUrl) throws Exception {
		Log.d(LOG_TAG, "getResponse: url=" + apiUrl);
		String response = null;
		try {
			// http://hc.apache.org/httpcomponents-client-ga/tutorial/html/fundamentals.html#d5e68
			HttpGet request = new HttpGet(apiUrl);
			consumer.sign(request);

			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(request);
			Log.d(LOG_TAG, "status=" + httpResponse.getStatusLine());
			response = EntityUtils.toString(httpResponse.getEntity());
			Log.d(LOG_TAG, "response=" + response);
		} catch (Exception e) {
			Log.e(LOG_TAG, "getResponse", e);
		}
		return response;
	}

	/**
	 * Call a Shapeways API with POST
	 * 
	 * @param apiUrl
	 * @return
	 * @throws Exception
	 */
	private String postResponseOld(String apiUrl, Map<String, String> parameters) throws Exception {
		Log.d(LOG_TAG, "postResponse: url=" + apiUrl);
		URLConnection urlConnection = getUrlConnection(apiUrl, true);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());

		Request request = new Request(POST);
		request.addParameter(OAUTH_CONSUMER_KEY, consumerKey);
		request.addParameter(OAUTH_TOKEN, oauthToken);

		for (String key : parameters.keySet()) {
			request.addParameter(key, parameters.get(key));
		}

		request.sign(apiUrl, consumerSecret, oauthTokenSecret);
		outputStreamWriter.write(request.toString());
		outputStreamWriter.close();

		return readResponse(urlConnection.getInputStream());
	}

	/**
	 * Call a Shapeways API with POST
	 * 
	 * @param apiUrl
	 * @return
	 * @throws Exception
	 */
	public String postResponse(String apiUrl, Map<String, String> parameters) throws Exception {
		Log.d(LOG_TAG, "postResponse: url=" + apiUrl);
		String response = null;
		try {
			// http://hc.apache.org/httpcomponents-client-ga/tutorial/html/fundamentals.html#d5e68
			HttpPost request = new HttpPost(apiUrl);
			for (String key : parameters.keySet()) {
				request.getParams().setParameter(key, parameters.get(key));
			}
			consumer.sign(request);

			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(request);
			Log.d(LOG_TAG, "status=" + httpResponse.getStatusLine());
			response = EntityUtils.toString(httpResponse.getEntity());
			Log.d(LOG_TAG, "response=" + response);
		} catch (Exception e) {
			Log.e(LOG_TAG, "postResponse", e);
		}
		return response;
	}

	/**
	 * Utility method to create a URL connection
	 * 
	 * @param urlValue
	 * @param doPost
	 *            is this for a HTTP POST
	 * @return
	 * @throws Exception
	 */
	private static URLConnection getUrlConnection(String urlValue, boolean doPost) throws Exception {
		URL url = new URL(urlValue);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setConnectTimeout(0);
		urlConnection.setReadTimeout(0);
		if (doPost) {
			urlConnection.setDoOutput(true);
		}
		return urlConnection;
	}

	/**
	 * @param inputStream
	 * @return map of parameter key/value pairs
	 * @throws Exception
	 */
	private static Map<String, String> readParams(InputStream inputStream) throws Exception {
		String reponse = readResponse(inputStream);
		String keyValues[] = reponse.split(AMPERSAND);
		Map<String, String> parameters = new HashMap<String, String>();
		for (int i = 0; i < keyValues.length; i++) {
			int pos = keyValues[i].indexOf(EQUALS);
			if (pos != -1) {
				String name = keyValues[i].substring(0, pos);
				String value = keyValues[i].substring(pos + 1);

				parameters.put(decode(name), decode(value));
			}
		}

		return parameters;
	}

	/**
	 * Utility method to convert response to a string
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	private static String readResponse(InputStream inputStream) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		String line;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		bufferedReader.close();
		return stringBuilder.toString();
	}

	public final static String decode(String value) throws Exception {
		if (value == null) {
			return "";
		}
		return URLDecoder.decode(value, ENCODING);
	}

	// ////////////////////////////////////////

	public void setOauthToken(String oauthToken, String oauthTokenSecret) {
		this.oauthToken = oauthToken;
		this.oauthTokenSecret = oauthTokenSecret;

		consumer.setTokenWithSecret(oauthToken, oauthTokenSecret);
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public String getOauthTokenSecret() {
		return oauthTokenSecret;
	}

}