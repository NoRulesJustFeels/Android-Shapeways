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
 * Models class for Jackson JSON library data binding of Shapeways API responses
 * @see http://wiki.fasterxml.com/JacksonDocumentation
 * @see http://developers.shapeways.com/docs?li=d_gettingStarted
 * @see com.entertailion.android.shapeways.api.Base
 * 
 * @author leon_nicholls
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Models extends Base {
	private static final String LOG_TAG = "Models";
	
	// {"result":"success","models":[{"modelId":"1002632","modelVersion":"0","modelTitle":"Original-52936"}],"nextActionSuggestions":[]}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Model {
		private String modelId, modelTitle;
		private int modelVersion;
		
		@JsonProperty("modelId")
		public String getModelId() {
			return modelId;
		}

		@JsonProperty("modelId")
		public void setModelId(String modelId) {
			this.modelId = modelId;
		}

		@JsonProperty("modelTitle")
		public String getModelTitle() {
			return modelTitle;
		}

		@JsonProperty("modelTitle")
		public void setModelTitle(String modelTitle) {
			this.modelTitle = modelTitle;
		}

		@JsonProperty("modelVersion")
		public int getModelVersion() {
			return modelVersion;
		}

		@JsonProperty("modelVersion")
		public void setModelVersion(int modelVersion) {
			this.modelVersion = modelVersion;
		}
		
		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			Log.i(LOG_TAG, "Model.handleUnknown: " + key + "=" + value);
		}
	}
	
	private List<Model> models;
	
	@JsonProperty("models")
	public List<Model> getModels() {
		return models;
	}

	@JsonProperty("models")
	public void setModels(List<Model> models) {
		this.models = models;
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		Log.i(LOG_TAG, "Models.handleUnknown: " + key + "=" + value);
	}
}
