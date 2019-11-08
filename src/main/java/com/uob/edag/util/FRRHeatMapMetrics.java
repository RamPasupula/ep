/**
 * 
 */
package com.uob.edag.util;

import org.json.JSONPropertyName;

/**
 * @author vencp9
 *
 */
public class FRRHeatMapMetrics {
	
	/**
	 * 
	 */
	public FRRHeatMapMetrics() {
		super();
	}




	/**
	 * @param hour
	 * @param foundation
	 * @param finance
	 * @param products
	 * @param risk
	 */
	public FRRHeatMapMetrics(String date,int hour, int per, String category, String subjectArea) {
		super();
		this.date =date;
		this.hour = hour;
		this.per = per;
		this.category = category;
		this.subjectArea = subjectArea;
	}

	

	private int hour;
	private int per;
	
	private String date;

	/**
	 * @return the per
	 */
	public int getPer() {
		return per;
	}




	/**
	 * @param per the per to set
	 */
	public void setPer(int per) {
		this.per = per;
	}



	private String category;
	

	private String subjectArea;
	
	/**
	 * @return the subjectArea
	 */
	//@JSONPropertyIgnore
	@JSONPropertyName("Subject Area")
	public String getSubjectArea() {
		return subjectArea;
	}




	/**
	 * @param subjectArea the subjectArea to set
	 */
	public void setSubjectArea(String subjectArea) {
		this.subjectArea = subjectArea;
	}



	public static int foundationTot;
	public static  int financeTot;
	public static  int productsTot;
	public static  int riskTot;
	
	
	
	
	/**
	 * @return the hour
	 */
	@JSONPropertyName("Hour")
	public int getHour() {
		return hour;
	}




	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}





	@Override
	public boolean equals(Object o) {

	    if (o == this) return true;
	    if (!(o instanceof FRRHeatMapMetrics)) {
	        return false;
	    }

	    FRRHeatMapMetrics account = (FRRHeatMapMetrics) o;

	    return  account.date.equalsIgnoreCase(date) &&  account.category.equalsIgnoreCase(category) && account.subjectArea.equalsIgnoreCase(this.subjectArea);
	}

	//Idea from effective Java : Item 9
	@Override
	public int hashCode() {
	    int result = 17;
	    result = 31 * result + date.hashCode() + result + category.hashCode() +subjectArea.hashCode() ;
	    
	  
	    return result;
	}

	@Override
	public String toString() {
		  StringBuilder sb = new StringBuilder();
		
		    	
		 //  sb.append("[ ").append(" new Date(").append(0).append(",").append(0).append(",").append(0).append(",").append(this.hour).append(") ,");
		    sb.append("[ ").append(this.date).append(",").append(this.hour).append(",");	
	        sb.append(this.per).append(",");
	        sb.append(this.category).append(" ,");
	        sb.append(this.subjectArea);
	       
	        sb.append("]");
	        return sb.toString();
	}




	/**
	 * @return the category
	 */
	@JSONPropertyName("Category")
	public String getCategory() {
		return category;
	}




	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}




	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}




	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
