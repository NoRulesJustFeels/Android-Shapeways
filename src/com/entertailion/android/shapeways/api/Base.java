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

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base class for Jackson JSON library data binding of Shapeways API responses
 * 
 * @see http://wiki.fasterxml.com/JacksonDocumentation
 * @see http
 *      ://programmerbruce.blogspot.co.uk/2011/05/deserialize-json-with-jackson
 *      -into.html
 * @see http://developers.shapeways.com/docs?li=d_gettingStarted
 * 
 * @author leon_nicholls
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Base {
	private static final String LOG_TAG = "Base";

	// {result=success, rateLimit_OkToGo_InSeconds=0,
	// rateLimit_Retry_InSeconds=0, rateLimit_Window_InMinutes=5,
	// rateLimit_Limit=150, rateLimit_Remaining=149,
	// rateLimit_History={201303281557=1}, rateLimit={retryInSeconds=0,
	// retryTimestamp=1364486251, windowInSeconds=300, limit=150, remaining=149,
	// history={201303281557=1}}, nextActionSuggestions={discovery={method=GET,
	// restUrl=http://api.shapeways.com/, link=/, dependencies=Access the
	// discovery system without OAuth1 keys}}}

	public enum Result {
		// {"result":"success","itemCount":0,"items":[],"nextActionSuggestions":[]}
		// {"result":"failure","reason":"\n Field <modelId> is required, but missing."}
		success, failure
	};

	private Result result;
	private String reason; // failure reason
	private RateLimit rateLimit;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class RateLimit {
		private int retryInSeconds, retryTimestamp, windowInSeconds, limit, remaining;

		@JsonProperty("retryInSeconds")
		public int getRetryInSeconds() {
			return retryInSeconds;
		}

		@JsonProperty("retryInSeconds")
		public void setRetryInSeconds(int retryInSeconds) {
			this.retryInSeconds = retryInSeconds;
		}

		@JsonProperty("retryTimestamp")
		public int getRetryTimestamp() {
			return retryTimestamp;
		}

		@JsonProperty("retryTimestamp")
		public void setRetryTimestamp(int retryTimestamp) {
			this.retryTimestamp = retryTimestamp;
		}

		@JsonProperty("windowInSeconds")
		public int getWindowInSeconds() {
			return windowInSeconds;
		}

		@JsonProperty("windowInSeconds")
		public void setWindowInSeconds(int windowInSeconds) {
			this.windowInSeconds = windowInSeconds;
		}

		@JsonProperty("limit")
		public int getLimit() {
			return limit;
		}

		@JsonProperty("limit")
		public void setLimit(int limit) {
			this.limit = limit;
		}

		@JsonProperty("remaining")
		public int getRemaining() {
			return remaining;
		}

		@JsonProperty("remaining")
		public void setRemaining(int remaining) {
			this.remaining = remaining;
		}

		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			Log.i(LOG_TAG, "RateLimit.handleUnknown: " + key + "=" + value);
		}
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		Log.i(LOG_TAG, "Base.handleUnknown: " + key + "=" + value);
	}

	@JsonProperty("result")
	public Result getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(Result result) {
		this.result = result;
	}

	@JsonProperty("reason")
	public String getReason() {
		return reason;
	}

	@JsonProperty("reason")
	public void setReason(String reason) {
		this.reason = reason;
	}

	@JsonProperty("rateLimit")
	public RateLimit getRateLimit() {
		return rateLimit;
	}

	@JsonProperty("rateLimit")
	public void setRateLimit(RateLimit rateLimit) {
		this.rateLimit = rateLimit;
	}

}
