/**
 * 
 */
package com.uob.edag.util;

/**
 * @author vencp9
 *
 */
public class FRRMetrics {
	
	/**
	 * 
	 */
	public FRRMetrics() {
		super();
	}




	/**
	 * @param hour
	 * @param foundation
	 * @param finance
	 * @param products
	 * @param risk
	 */
	public FRRMetrics(String date, int hour, int foundation, int finance, int products, int risk) {
		super();
		this.date = date;
		this.hour = hour;
		this.foundation = foundation;
		this.finance = finance;
		this.products = products;
		this.risk = risk;
	}

	

	private int hour;
	private int foundation;
	private int finance;
	private int products;
	private int risk;
	private String date;
	

	
	
	
	
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}




	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}



	/**
	 * @return the foundation
	 */
	public int getFoundation() {
		return foundation;
	}




	/**
	 * @param foundation the foundation to set
	 */
	public void setFoundation(int foundation) {
		this.foundation = foundation;
	}




	/**
	 * @return the finance
	 */
	public int getFinance() {
		return finance;
	}




	/**
	 * @param finance the finance to set
	 */
	public void setFinance(int finance) {
		this.finance = finance;
	}




	/**
	 * @return the products
	 */
	public int getProducts() {
		return products;
	}




	/**
	 * @param products the products to set
	 */
	public void setProducts(int products) {
		this.products = products;
	}




	/**
	 * @return the risk
	 */
	public int getRisk() {
		return risk;
	}




	/**
	 * @param risk the risk to set
	 */
	public void setRisk(int risk) {
		this.risk = risk;
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







	@Override
	public boolean equals(Object o) {

	    if (o == this) return true;
	    if (!(o instanceof FRRMetrics)) {
	        return false;
	    }

	    FRRMetrics account = (FRRMetrics) o;

	    return account.date.equalsIgnoreCase(date) ;
	}

	//Idea from effective Java : Item 9
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + date.hashCode()  ;

		return result;
	}

	@Override
	public String toString() {
		  StringBuilder sb = new StringBuilder();
		
		    	
		 //  sb.append("[ ").append(" new Date(").append(0).append(",").append(0).append(",").append(0).append(",").append(this.hour).append(") ,");
		  sb.append("[ ").append(this.date).append(",").append(this.hour).append(",");	
	        sb.append(this.foundation).append(",");
	        sb.append(this.finance).append(" ,");
	   
	        sb.append(this.products).append(",");
	        sb.append(this.risk);
	       
	        sb.append("]");
	        return sb.toString();
	}

}
