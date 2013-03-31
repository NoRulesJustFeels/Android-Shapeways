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

import com.entertailion.android.shapeways.api.ShapewaysClient;

import android.app.Application;

/**
 * Application shared data.
 * 
 * @author leon_nicholls
 * 
 */
public class ShapewaysApplication extends Application {

	private static final String LOG_TAG = "ShapewaysApplication";
	
	// http://developers.shapeways.com/manage-apps
	public static final String CONSUMER_KEY = "";
	public static final String CONSUMER_SECRET = "";
	
	private ShapewaysClient shapewaysClient;

	@Override
	public void onCreate() {
		super.onCreate();
		
		java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
		java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);

		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
		
		shapewaysClient = new ShapewaysClient(this, CONSUMER_KEY, CONSUMER_SECRET);
	}
	
	public ShapewaysClient getShapewaysClient() {
		return shapewaysClient;
	}

}
