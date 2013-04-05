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

import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.entertailion.android.shapeways.api.ShapewaysClient;

/**
 * Activity for user authorization using web-based login
 * 
 * @author leon_nicholls
 * 
 */
public class ShapewaysActivity extends Activity {
	private static final String LOG_TAG = "ShapewaysActivity";

	private static final String CALLBACK_URL = "shapeways-api://callback";

	private WebView webView;
	private SharedPreferences preferences;
	protected ProgressDialog dialog;

	private String oauthToken;
	private String oauthTokenSecret;
	private String oauthVerifier;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shapeways);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		webView = (WebView) findViewById(R.id.shapeways_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.clearView();

		CookieSyncManager.createInstance(this);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();

		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.d(LOG_TAG, "shouldOverrideUrlLoading: url=" + url);
				try {
					Uri uri = Uri.parse(url);
					oauthVerifier = uri.getQueryParameter(ShapewaysClient.OAUTH_VERIFIER);
					if (null != oauthVerifier) {
						oauthToken = uri.getQueryParameter(ShapewaysClient.OAUTH_TOKEN);
						new Thread(new Runnable() {
							public void run() {
								getAccessToken();
								finish();
							}
						}).start();
						return true;
					}
				} catch (Exception e) {
					Log.e(LOG_TAG, "shouldOverrideUrlLoading", e);
				}
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Log.d(LOG_TAG, "onReceivedError");
				super.onReceivedError(view, errorCode, description, failingUrl);
				try {
					AlertDialog alertDialog = new AlertDialog.Builder(ShapewaysActivity.this).create();
					alertDialog.setTitle(ShapewaysActivity.this.getString(R.string.shapeways_web_error_title));
					alertDialog.setMessage(ShapewaysActivity.this.getString(R.string.shapeways_web_error, description));
					alertDialog.setButton(Dialog.BUTTON_POSITIVE, ShapewaysActivity.this.getString(R.string.shapeways_dialog_reload),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									webView.reload();
								}
							});
					alertDialog.setButton(Dialog.BUTTON_NEGATIVE, ShapewaysActivity.this.getString(R.string.shapeways_dialog_exit),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									finish();
								}
							});
					alertDialog.show();
				} catch (Exception e) {
					Log.e(LOG_TAG, "onReceivedError", e);
				}
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				Log.d(LOG_TAG, "onPageFinished");
				try {
					if (null != dialog) {
						dialog.cancel();
						dialog = null;
					}
				} catch (Throwable e) {
					Log.e(LOG_TAG, "onPageFinished", e);
				}
				super.onPageFinished(view, url);
			}

		});

	}

	@Override
	protected void onResume() {
		Log.d(LOG_TAG, "onResume");
		super.onResume();

		if (null == dialog)
			dialog = ProgressDialog.show(ShapewaysActivity.this, "", ShapewaysActivity.this.getString(R.string.shapeways_loading), true);
		new ShapewaysAsyncTask().execute();
	}

	private void getAccessToken() {
		Log.d(LOG_TAG, "getAccessToken");
		try {
			Map<String, String> accessTokenData = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getAccessToken(oauthToken,
					oauthTokenSecret, oauthVerifier);
			Log.d(LOG_TAG, "Access Token Data=" + accessTokenData);

			oauthTokenSecret = accessTokenData.get(ShapewaysClient.OAUTH_TOKEN_SECRET);
			oauthToken = accessTokenData.get(ShapewaysClient.OAUTH_TOKEN);

			final Editor edit = preferences.edit();
			edit.putString(ShapewaysClient.OAUTH_TOKEN, oauthToken);
			edit.putString(ShapewaysClient.OAUTH_TOKEN_SECRET, oauthTokenSecret);
			edit.commit();
			Log.i(LOG_TAG, "Access Token Retrieved");

			((ShapewaysApplication) getApplicationContext()).getShapewaysClient().setOauthToken(oauthToken, oauthTokenSecret);
		} catch (Exception e) {
			Log.e(LOG_TAG, "getAccessToken", e);
		}
	}

	private class ShapewaysAsyncTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			Log.d(LOG_TAG, "doInBackground");
			try {
				return getAuthenticationUrl();
			} catch (Exception e) {
				Log.e(LOG_TAG, "doInBackground", e);
			}
			return null;
		}

		@Override
		protected void onPostExecute(String url) {
			Log.d(LOG_TAG, "onPostExecute: url=" + url);
			try {
				if (null != url) {
					webView.loadUrl(url);
				}
			} catch (Exception e) {
				Log.e(LOG_TAG, "onPostExecute: " + url, e);
			}
		}
	}

	private String getAuthenticationUrl() throws Exception {
		Log.d(LOG_TAG, "getAuthenticationUrl");
		Map<String, String> requestTokenData = ((ShapewaysApplication) getApplicationContext()).getShapewaysClient().getRequestToken(CALLBACK_URL);
		Log.d(LOG_TAG, "Request Token Data=" + requestTokenData);

		oauthTokenSecret = requestTokenData.get(ShapewaysClient.OAUTH_TOKEN_SECRET);

		return requestTokenData.get(ShapewaysClient.AUTHENTICATION_URL);
	}
}
