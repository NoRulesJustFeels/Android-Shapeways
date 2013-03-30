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
	private static final String CONSUMER_KEY = "";
	private static final String CONSUMER_SECRET = "";
	
	private ShapewaysClient shapewaysClient;

	@Override
	public void onCreate() {
		super.onCreate();
		
		shapewaysClient = new ShapewaysClient(this, CONSUMER_KEY, CONSUMER_SECRET);
	}
	
	public ShapewaysClient getShapewaysClient() {
		return shapewaysClient;
	}

}
