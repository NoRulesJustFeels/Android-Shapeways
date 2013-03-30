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

import java.util.List;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Prices class for Jackson JSON library data binding of Shapeways API responses
 * @see http://wiki.fasterxml.com/JacksonDocumentation
 * @see http://developers.shapeways.com/docs?li=d_gettingStarted
 * @see com.entertailion.android.shapeways.api.Base
 * 
 * @author leon_nicholls
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prices extends Base {
	private static final String LOG_TAG = "Prices";
	
	// {"result":"success","models":[],"nextActionSuggestions":[]}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Price {
		
		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			Log.i(LOG_TAG, "Price.handleUnknown: " + key + "=" + value);
		}
	}
	
	private List<Price> prices;
	
	@JsonProperty("prices")
	public List<Price> getPrices() {
		return prices;
	}

	@JsonProperty("prices")
	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		Log.i(LOG_TAG, "Prices.handleUnknown: " + key + "=" + value);
	}
}
