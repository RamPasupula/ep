/**
 * 
 */
package com.uob.edag.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author vencp9
 *
 */
public class ClusterMetrics {
	
	private String time;
	private float min;
	private float max;
	private float mean;
//	private int count;
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the min
	 */
	public float getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(float min) {
		this.min = min;
	}
	/**
	 * @return the max
	 */
	public float getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(float max) {
		this.max = max;
	}
	/**
	 * @return the mean
	 */
	public float getMean() {
		return mean;
	}
	/**
	 * @param mean the mean to set
	 */
	public void setMean(float mean) {
		this.mean = mean;
	}
	
	@Override
	public String toString() {
		  StringBuilder sb = new StringBuilder();
		   DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Calendar now = null;
		    try {
		    	now= Calendar.getInstance(); 
		    	now.setTime(sdf.parse(this.time));
		    	int year = now.get(Calendar.YEAR);
		    	int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		    	int day = now.get(Calendar.DAY_OF_MONTH);
		    	int hour = now.get(Calendar.HOUR_OF_DAY);
		    	int minute = now.get(Calendar.MINUTE);
		    	//int second = now.get(Calendar.SECOND);
		    	
				sb.append("[ ").append(" new Date(").append(year).append(",").append(month).append(",").append(day).append(",").append(hour).append(",").append(minute).append(") ,");
				
			} catch (ParseException e) {
			
			}
	        sb.append(this.min).append(",");
	        sb.append("\"").append(this.min).append(" \",");
	      //  sb.append("\"").append(this.min).append(" \",");
	        sb.append(this.mean).append(",");
	        sb.append("\"").append(this.mean).append(" \",");
	       
	        sb.append(this.max).append("]");
	        return sb.toString();
	}

}
