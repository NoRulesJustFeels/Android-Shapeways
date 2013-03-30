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

import java.net.URL;
import java.net.URLEncoder;
import java.util.TreeSet;

import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

import android.util.Log;

/**
 * Handle the OAuth request parameters
 * 
 * @author leon_nicholls
 * 
 */
public class Request {
	private static final String LOG_TAG = "Request";

	private final TreeSet<String> parameters; // sorted
	private final String httpMethod;

	public Request(String httpMethod) {
		this.httpMethod = httpMethod;
		parameters = new TreeSet<String>();
	}

	/**
	 * Add an encoded key/value pair to the request
	 * 
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void addParameter(String key, String value) throws Exception {
		Log.d(LOG_TAG, "addParameter: " + key + "=" + value);
		parameters.add(encode(key) + "=" + encode(value));
	}

	/**
	 * Sign the request based on OAuth 1.0
	 * 
	 * @param url
	 * @param consumerSecret
	 * @param tokenSecret
	 * @throws Exception
	 */
	public void sign(String url, String consumerSecret, String tokenSecret) throws Exception {
		Log.d(LOG_TAG, "sign: " + url);
		addParameter(ShapewaysClient.OAUTH_SIGNATURE_METHOD, ShapewaysClient.OAUTH_SIGNATURE_METHOD_HMAC_SHA1);
		addParameter(ShapewaysClient.OAUTH_VERSION, ShapewaysClient.OAUTH_VERSION_1_0);
		addParameter(ShapewaysClient.OAUTH_NONCE, RandomStringUtils.randomAlphanumeric(32));
		addParameter(ShapewaysClient.OAUTH_TIMESTAMP, Long.toString(System.currentTimeMillis() / 1000));

		StringBuffer stringBuffer = new StringBuffer();
		for (String parameter : parameters) {
			if (stringBuffer.length() != 0) {
				stringBuffer.append(ShapewaysClient.AMPERSAND);
			}
			stringBuffer.append(parameter);
		}

		String baseString = String.format("%s&%s&%s", encode(httpMethod), encode(normalize(url)), encode(stringBuffer.toString()));
		Log.d(LOG_TAG, "base=" + baseString);

		addParameter(ShapewaysClient.OAUTH_SIGNATURE, generateHmac(encode(consumerSecret) + ShapewaysClient.AMPERSAND + encode(tokenSecret), baseString));
	}

	/**
	 * Normalize the URL parameters
	 * 
	 * @param urlValue
	 * @return
	 * @throws Exception
	 */
	private final String normalize(String urlValue) throws Exception {
		Log.d(LOG_TAG, "normalize: " + urlValue);
		URL url = new URL(urlValue);
		if (url.getQuery() != null) {
			String[] parameters = url.getQuery().split(ShapewaysClient.AMPERSAND);

			for (String parameter : parameters) {
				if (parameter != null) {
					String[] keyValue = parameter.split(ShapewaysClient.EQUALS);
					if (keyValue != null) {
						addParameter(keyValue[0], keyValue[1]);
					}
				}
			}
		}

		return urlValue;
	}

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		for (String parameter : parameters) {
			if (stringBuffer.length() != 0) {
				stringBuffer.append(ShapewaysClient.AMPERSAND);
			}
			stringBuffer.append(parameter);
		}
		return stringBuffer.toString();
	}

	private final static String encode(String value) throws Exception {
		Log.d(LOG_TAG, "encode: " + value);
		if (value == null) {
			return "";
		}
		return URLEncoder.encode(value, ShapewaysClient.ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
	}

	/**
	 * Generate HMAC-SHA1 for message
	 * 
	 * @param key
	 * @param message
	 * @return
	 * @throws Exception
	 */
	private static String generateHmac(String key, String message) throws Exception {
		Log.d(LOG_TAG, "generateHmac: " + key + "=" + message);
		byte[] keyBytes = key.getBytes(ShapewaysClient.ENCODING);
		byte[] data = message.getBytes(ShapewaysClient.ENCODING);

		HMac macProvider = new HMac(new SHA1Digest());
		macProvider.init(new KeyParameter(keyBytes));
		macProvider.reset();

		macProvider.update(data, 0, data.length);
		byte[] output = new byte[macProvider.getMacSize()];
		macProvider.doFinal(output, 0);

		byte[] hmac = Base64.encode(output);
		return new String(hmac).replaceAll("\r\n", "");
	}

}
