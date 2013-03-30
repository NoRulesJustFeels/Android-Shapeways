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

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Materials class for Jackson JSON library data binding of Shapeways API responses
 * @see http://wiki.fasterxml.com/JacksonDocumentation
 * @see http://developers.shapeways.com/docs?li=d_gettingStarted
 * @see com.entertailion.android.shapeways.api.Base
 * 
 * @author leon_nicholls
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Materials extends Base {
	private static final String LOG_TAG = "Materials";
	
	// {"result":"success","materials":{"6":{"materialId":"6","title":"White Strong & Flexible","supportsColorFiles":"0","printerId":"5","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/plastic_wsf_white.jpg"},"62":{"materialId":"62","title":"White Strong & Flexible Polished","supportsColorFiles":"0","printerId":"20","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/wsfp_thumb.jpg"},"25":{"materialId":"25","title":"Black Strong & Flexible","supportsColorFiles":"0","printerId":"8","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/plastic_wsf_black.jpg"},"76":{"materialId":"76","title":"Coral Red Strong & Flexible Polished","supportsColorFiles":"0","printerId":"20","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/swatch-coral-red.jpg"},"77":{"materialId":"77","title":"Hot Pink Strong & Flexible Polished","supportsColorFiles":"0","printerId":"20","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/swatch-hot-pink.jpg"},"78":{"materialId":"78","title":"Royal Blue Strong & Flexible Polished","supportsColorFiles":"0","printerId":"20","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/swatch-royal-blue.jpg"},"75":{"materialId":"75","title":"Violet Purple Strong & Flexible Polished","supportsColorFiles":"0","printerId":"20","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/swatch-violet-purple.jpg"},"61":{"materialId":"61","title":"Frosted Ultra Detail","supportsColorFiles":"0","printerId":"19","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/fud_thumb.jpg"},"60":{"materialId":"60","title":"Frosted Detail","supportsColorFiles":"0","printerId":"18","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/fd_thumb.jpg"},"5":{"materialId":"5","title":"White Detail","supportsColorFiles":"0","printerId":"4","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/plastic_detail_white.jpg"},"7":{"materialId":"7","title":"Black Detail","supportsColorFiles":"0","printerId":"4","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/plastic_detail_black.jpg"},"4":{"materialId":"4","title":"Transparent Detail","supportsColorFiles":"0","printerId":"4","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/plastic_detail_transparent.jpg"},"26":{"materialId":"26","title":"Full Color Sandstone","supportsColorFiles":"1","printerId":"11","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/sandstone_full_color.jpg"},"27":{"materialId":"27","title":"Sandstone","supportsColorFiles":"0","printerId":"11","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/sandstone_no_color.jpg"},"72":{"materialId":"72","title":"Eggshell Blue Ceramics","supportsColorFiles":"0","printerId":"21","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/th_ebc.jpg"},"70":{"materialId":"70","title":"Satin Black Ceramics","supportsColorFiles":"0","printerId":"21","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/th_sbc.jpg"},"74":{"materialId":"74","title":"Pastel Yellow Ceramics","supportsColorFiles":"0","printerId":"21","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/th_pyc.jpg"},"64":{"materialId":"64","title":"Gloss Black Ceramics","supportsColorFiles":"0","printerId":"21","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/blackceramics_thumbnail.jpg"},"73":{"materialId":"73","title":"Avocado Green Ceramics","supportsColorFiles":"0","printerId":"21","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/th_agc.jpg"},"63":{"materialId":"63","title":"Glazed Ceramics","supportsColorFiles":"0","printerId":"21","swatch":"http:\/\/static2.shapeways.netdna-cdn.com\/rrstatic\/img\/materials\/ceramics-thumb.jpg"},"81":{"materialId":"8

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Material {
		private String materialId, title, printerId, swatch;
		private int supportsColorFiles;
		
		@JsonProperty("materialId")
		public String getMaterialId() {
			return materialId;
		}
		@JsonProperty("materialId")
		public void setMaterialId(String materialId) {
			this.materialId = materialId;
		}
		@JsonProperty("title")
		public String getTitle() {
			return title;
		}
		@JsonProperty("title")
		public void setTitle(String title) {
			this.title = title;
		}
		@JsonProperty("printerId")
		public String getPrinterId() {
			return printerId;
		}
		@JsonProperty("printerId")
		public void setPrinterId(String printerId) {
			this.printerId = printerId;
		}
		@JsonProperty("swatch")
		public String getSwatch() {
			return swatch;
		}
		@JsonProperty("swatch")
		public void setSwatch(String swatch) {
			this.swatch = swatch;
		}
		@JsonProperty("supportsColorFiles")
		public int getSupportsColorFiles() {
			return supportsColorFiles;
		}
		@JsonProperty("supportsColorFiles")
		public void setSupportsColorFiles(int supportsColorFiles) {
			this.supportsColorFiles = supportsColorFiles;
		}
		
		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			Log.i(LOG_TAG, "Material.handleUnknown: " + key + "=" + value);
		}
	}
	
	private List<Material> materials;
	
	public Materials() {
		materials = new ArrayList<Material>();
	}
	
	@JsonProperty("materials")
	public List<Material> getMaterials() {
		return materials;
	}

	@JsonProperty("materials")
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		Log.i(LOG_TAG, "Materials.handleUnknown: " + key + "=" + value);
	}
}
