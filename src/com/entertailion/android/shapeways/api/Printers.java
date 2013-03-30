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
 * Printers class for Jackson JSON library data binding of Shapeways API responses
 * @see http://wiki.fasterxml.com/JacksonDocumentation
 * @see http://developers.shapeways.com/docs?li=d_gettingStarted
 * @see com.entertailion.android.shapeways.api.Base
 * 
 * @author leon_nicholls
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Printers extends Base {
	private static final String LOG_TAG = "Printers";
	
	// {"result":"success","printers":[{"printerId":"1","title":"Somatech FDM","xBoundMin":"0.2500","xBoundMax":"25.0000","yBoundMin":"0.2500","yBoundMax":"25.0000","zBoundMin":"0.1000","zBoundMax":"30.0000"},{"printerId":"4","title":"Somatech Objet 720","xBoundMin":"0.2390","xBoundMax":"25.0000","yBoundMin":"0.2390","yBoundMax":"25.0000","zBoundMin":"0.1000","zBoundMax":"20.0000"},{"printerId":"5","title":"SLS Printer","xBoundMin":"0.0700","xBoundMax":"66.0000","yBoundMin":"0.0700","yBoundMax":"35.0000","zBoundMin":"0.0700","zBoundMax":"55.0000"},{"printerId":"6","title":"Metal Printer matt","xBoundMin":"0.2500","xBoundMax":"100.0000","yBoundMin":"0.2500","yBoundMax":"45.0000","zBoundMin":"0.3000","zBoundMax":"25.0000"},{"printerId":"8","title":"SLS Color Printer","xBoundMin":"0.0700","xBoundMax":"18.0000","yBoundMin":"0.0700","yBoundMax":"23.0000","zBoundMin":"0.0700","zBoundMax":"32.0000"},{"printerId":"9","title":"Silver Printer","xBoundMin":"0.2390","xBoundMax":"15.0000","yBoundMin":"0.2390","yBoundMax":"15.0000","zBoundMin":"0.0600","zBoundMax":"3.0000"},{"printerId":"10","title":"White Glaze FDM","xBoundMin":"0.2500","xBoundMax":"0.2500","yBoundMin":"0.2500","yBoundMax":"0.2500","zBoundMin":"0.2500","zBoundMax":"0.2500"},{"printerId":"11","title":"ZPrinter 650","xBoundMin":"0.2500","xBoundMax":"25.4000","yBoundMin":"0.2500","yBoundMax":"38.1000","zBoundMin":"0.3000","zBoundMax":"20.3000"},{"printerId":"12","title":"SLS Alumide","xBoundMin":"0.0800","xBoundMax":"18.4110","yBoundMin":"0.0800","yBoundMax":"23.2560","zBoundMin":"0.0800","zBoundMax":"31.7000"},{"printerId":"13","title":"Glass Printer","xBoundMin":"0.2499","xBoundMax":"7.5000","yBoundMin":"0.2499","yBoundMax":"7.5000","zBoundMin":"0.3000","zBoundMax":"7.5000"},{"printerId":"14","title":"Metal printer Gold","xBoundMin":"0.2490","xBoundMax":"100.0000","yBoundMin":"0.2490","yBoundMax":"45.0000","zBoundMin":"0.3000","zBoundMax":"25.0000"},{"printerId":"15","title":"SLS Color Printer New","xBoundMin":"0.0700","xBoundMax":"18.0000","yBoundMin":"0.0700","yBoundMax":"23.0000","zBoundMin":"0.0700","zBoundMax":"32.0000"},{"printerId":"16","title":"Shapeways products","xBoundMin":"0.2500","xBoundMax":"35.0000","yBoundMin":"0.2500","yBoundMax":"35.0000","zBoundMin":"0.2500","zBoundMax":"35.0000"},{"printerId":"18","title":"HD printer","xBoundMin":"0.2490","xBoundMax":"29.8000","yBoundMin":"0.2490","yBoundMax":"18.5000","zBoundMin":"0.0500","zBoundMax":"20.3000"},{"printerId":"19","title":"UHD printer","xBoundMin":"0.2490","xBoundMax":"12.7000","yBoundMin":"0.2490","yBoundMax":"17.8000","zBoundMin":"0.0300","zBoundMax":"15.8000"},{"printerId":"20","title":"SLS Printer polished","xBoundMin":"0.2490","xBoundMax":"15.0000","yBoundMin":"0.2490","yBoundMax":"15.0000","zBoundMin":"0.1000","zBoundMax":"15.0000"},{"printerId":"21","title":"Ceramics printer","xBoundMin":"0.2490","xBoundMax":"34.0000","yBoundMin":"0.2490","yBoundMax":"24.0000","zBoundMin":"0.2490","zBoundMax":"17.0000"},{"printerId":"22","title":"SLS Polished Alumide","xBoundMin":"0.1000","xBoundMax":"15.0000","yBoundMin":"0.1000","yBoundMax":"15.0000","zBoundMin":"0.1000","zBoundMax":"15.0000"},{"printerId":"23","title":"Sinterstation Flex","xBoundMin":"0.7000","xBoundMax":"26.0000","yBoundMin":"0.7000","yBoundMax":"21.0000","zBoundMin":"0.1500","zBoundMax":"17.0000"},{"printerId":"24","title":"High Definition Stainless Steel","xBoundMin":"0.3000","xBoundMax":"4.0000","yBoundMin":"0.3000","yBoundMax":"4.0000","zBoundMin":"0.3000","zBoundMax":"3.5000"},{"printerId":"25","title":"Super Silver Printer","xBoundMin":"0.2400","xBoundMax":"10.0000","yBoundMin":"0.2400","yBoundMax":"10.0000","zBoundMin":"0.0600","zBoundMax":"3.0000"}]}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Printer {
		private String printerId, title;
		private float xBoundMin, xBoundMax, yBoundMin, yBoundMax, zBoundMin, zBoundMax;
		
		@JsonProperty("printerId")
		public String getPrinterId() {
			return printerId;
		}
		@JsonProperty("printerId")
		public void setPrinterId(String printerId) {
			this.printerId = printerId;
		}
		@JsonProperty("title")
		public String getTitle() {
			return title;
		}
		@JsonProperty("title")
		public void setTitle(String title) {
			this.title = title;
		}
		@JsonProperty("xBoundMin")
		public float getXBoundMin() {
			return xBoundMin;
		}
		@JsonProperty("xBoundMin")
		public void setXBoundMin(float xBoundMin) {
			this.xBoundMin = xBoundMin;
		}
		@JsonProperty("xBoundMax")
		public float getXBoundMax() {
			return xBoundMax;
		}
		@JsonProperty("xBoundMax")
		public void setXBoundMax(float xBoundMax) {
			this.xBoundMax = xBoundMax;
		}
		@JsonProperty("yBoundMin")
		public float getYBoundMin() {
			return yBoundMin;
		}
		@JsonProperty("yBoundMin")
		public void setYBoundMin(float yBoundMin) {
			this.yBoundMin = yBoundMin;
		}
		@JsonProperty("yBoundMax")
		public float getYBoundMax() {
			return yBoundMax;
		}
		@JsonProperty("yBoundMax")
		public void setYBoundMax(float yBoundMax) {
			this.yBoundMax = yBoundMax;
		}
		@JsonProperty("zBoundMin")
		public float getZBoundMin() {
			return zBoundMin;
		}
		@JsonProperty("zBoundMin")
		public void setZBoundMin(float zBoundMin) {
			this.zBoundMin = zBoundMin;
		}
		@JsonProperty("zBoundMax")
		public float getZBoundMax() {
			return zBoundMax;
		}
		@JsonProperty("zBoundMax")
		public void setZBoundMax(float zBoundMax) {
			this.zBoundMax = zBoundMax;
		}
		
		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			Log.i(LOG_TAG, "Printer.handleUnknown: " + key + "=" + value);
		}
	}
	
	private List<Printer> printers;
	
	@JsonProperty("printers")
	public List<Printer> getPrinters() {
		return printers;
	}

	@JsonProperty("printers")
	public void setPrinters(List<Printer> printers) {
		this.printers = printers;
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		Log.i(LOG_TAG, "Printers.handleUnknown: " + key + "=" + value);
	}
}
