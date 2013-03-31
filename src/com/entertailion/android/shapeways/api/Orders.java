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
 * Orders class for Jackson JSON library data binding of Shapeways API responses
 * 
 * @see http://wiki.fasterxml.com/JacksonDocumentation
 * @see http://developers.shapeways.com/docs?li=d_gettingStarted
 * @see com.entertailion.android.shapeways.api.Base
 * 
 * @author leon_nicholls
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders extends Base {
	private static final String LOG_TAG = "Orders";

	// {"result":"success","itemCount":0,"items":[],"nextActionSuggestions":[]}
	// {"result":"success","itemCount":1,"items":[{"cartKey":"1002632-6","modelId":"1002632","materialId":"6","quantity":"1"}],"nextActionSuggestions":[]}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Item {
		private String cartKey, quantity;
		private int modelId, materialId;

		@JsonProperty("cartKey")
		public String getCartKey() {
			return cartKey;
		}

		@JsonProperty("cartKey")
		public void setCartKey(String cartKey) {
			this.cartKey = cartKey;
		}

		@JsonProperty("modelId")
		public int getModelId() {
			return modelId;
		}

		@JsonProperty("modelId")
		public void setModelId(int modelId) {
			this.modelId = modelId;
		}

		@JsonProperty("quantity")
		public String getQuantity() {
			return quantity;
		}

		@JsonProperty("quantity")
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		@JsonProperty("materialId")
		public int getMaterialId() {
			return materialId;
		}

		@JsonProperty("materialId")
		public void setMaterialId(int materialId) {
			this.materialId = materialId;
		}

		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			Log.i(LOG_TAG, "Item.handleUnknown: " + key + "=" + value);
		}
	}

	private int itemCount;
	private List<Item> items;

	@JsonProperty("items")
	public List<Item> getItems() {
		return items;
	}

	@JsonProperty("items")
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@JsonProperty("itemCount")
	public int getItemCount() {
		return itemCount;
	}

	@JsonProperty("itemCount")
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		Log.i(LOG_TAG, "Orders.handleUnknown: " + key + "=" + value);
	}
}
