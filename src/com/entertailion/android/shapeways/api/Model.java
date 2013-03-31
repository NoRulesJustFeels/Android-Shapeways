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
 * Model class for Jackson JSON library data binding of Shapeways API
 * responses
 * 
 * @see http://wiki.fasterxml.com/JacksonDocumentation
 * @see http://developers.shapeways.com/docs?li=d_gettingStarted
 * @see com.entertailion.android.shapeways.api.Base
 * 
 * @author leon_nicholls
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Model extends Base {
	private static final String LOG_TAG = "Model";

	// {result=success, modelId=1002632, modelVersion=0, title=Original-52936,
	// fileName=original-52936.stl, contentLength=7212984,
	// fileMd5Checksum=b16b3a5a17888076d3094472d48c5ba2, fileData=null,
	// description=null, isPublic=0, isForSale=0, materials={6={materialId=6,
	// markup=0.000000, isActive=1}, 62={materialId=62, markup=0.000000,
	// isActive=1}, 25={materialId=25, markup=0.000000, isActive=1},
	// 76={materialId=76, markup=0.000000, isActive=1}, 77={materialId=77,
	// markup=0.000000, isActive=1}, 78={materialId=78, markup=0.000000,
	// isActive=1}, 75={materialId=75, markup=0.000000, isActive=1},
	// 61={materialId=61, markup=0.000000, isActive=1}, 60={materialId=60,
	// markup=0.000000, isActive=1}, 5={materialId=5, markup=0.000000,
	// isActive=1}, 7={materialId=7, markup=0.000000, isActive=1},
	// 4={materialId=4, markup=0.000000, isActive=1}, 27={materialId=27,
	// markup=0.000000, isActive=1}, 81={materialId=81, markup=0.000000,
	// isActive=1}, 54={materialId=54, markup=0.000000, isActive=1},
	// 53={materialId=53, markup=0.000000, isActive=1}, 23={materialId=23,
	// markup=0.000000, isActive=1}, 31={materialId=31, markup=0.000000,
	// isActive=1}, 39={materialId=39, markup=0.000000, isActive=1},
	// 37={materialId=37, markup=0.000000, isActive=1}, 38={materialId=38,
	// markup=0.000000, isActive=1}, 66={materialId=66, markup=0.000000,
	// isActive=1}, 28={materialId=28, markup=0.000000, isActive=1},
	// 26={materialId=26, markup=0.000000, isActive=1}, 72={materialId=72,
	// markup=0.000000, isActive=1}, 64={materialId=64, markup=0.000000,
	// isActive=1}, 63={materialId=63, markup=0.000000, isActive=1},
	// 74={materialId=74, markup=0.000000, isActive=1}, 73={materialId=73,
	// markup=0.000000, isActive=1}, 70={materialId=70, markup=0.000000,
	// isActive=1}}, printable=yes,
	// nextActionSuggestions={addModelToShoppingCart={method=POST,
	// restUrl=http://api.shapeways.com/orders/cart/v1, link=/orders/cart/v1,
	// parameters={modelId=1002632, materialId=int, count=int}},
	// downloadModel={method=GET,
	// restUrl=http://api.shapeways.com/models/1002632/files/0/v1?file=1,
	// link=/models/1002632/files/0/v1?file=1}, updateModelDetails={method=PUT,
	// restUrl=http://api.shapeways.com/models/1002632/info/v1,
	// link=/models/1002632/info/v1}, updateModelFile={method=POST,
	// restUrl=http://api.shapeways.com/models/1002632/files/v1,
	// link=/models/1002632/files/v1}, deleteModel={method=DELETE,
	// restUrl=http://api.shapeways.com/models/1002632/v1,
	// link=/models/1002632/v1}, addModelPhoto={method=POST,
	// restUrl=http://api.shapeways.com/models/1002632/photos/v1,
	// link=/models/1002632/photos/v1}}}

	private int modelId, modelVersion, contentLength, isPublic, isForSale;
	private String title, fileName, fileData, description, fileMd5Checksum;

	@JsonProperty("modelId")
	public int getModelId() {
		return modelId;
	}

	@JsonProperty("modelId")
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	@JsonProperty("modelVersion")
	public int getModelVersion() {
		return modelVersion;
	}

	@JsonProperty("modelVersion")
	public void setModelVersion(int modelVersion) {
		this.modelVersion = modelVersion;
	}

	@JsonProperty("contentLength")
	public int getContentLength() {
		return contentLength;
	}

	@JsonProperty("contentLength")
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	@JsonProperty("fileMd5Checksum")
	public String getFileMd5Checksum() {
		return fileMd5Checksum;
	}

	@JsonProperty("fileMd5Checksum")
	public void setFileMd5Checksum(String fileMd5Checksum) {
		this.fileMd5Checksum = fileMd5Checksum;
	}

	@JsonProperty("isPublic")
	public int getIsPublic() {
		return isPublic;
	}

	@JsonProperty("isPublic")
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}

	@JsonProperty("isForSale")
	public int getIsForSale() {
		return isForSale;
	}

	@JsonProperty("isForSale")
	public void setIsForSale(int isForSale) {
		this.isForSale = isForSale;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("fileName")
	public String getFileName() {
		return fileName;
	}

	@JsonProperty("fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonProperty("fileData")
	public String getFileData() {
		return fileData;
	}

	@JsonProperty("fileData")
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		Log.i(LOG_TAG, "Model.handleUnknown: " + key + "=" + value);
	}
}
