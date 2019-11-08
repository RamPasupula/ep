package com.uob.edag.dl;

public class FRRDROP2Country {

	private String name;

	private String tier;

	private int t14Completed;

	private int t14Pending;

	private int t15Completed;

	private int t15Pending;
	private int t20Completed;

	private int t20Pending;
	private int t30Completed;

	private int t30Pending;
	private int t14Total;
	private int t15Total;
	private int t20Total;
	private int t30Total;

	/**
	 * @return the t14Total
	 */
	public int getT14Total() {
		return t14Total;
	}

	/**
	 * @param t14Total the t14Total to set
	 */
	public void setT14Total(int t14Total) {
		this.t14Total = t14Total;
	}

	/**
	 * @return the t15Total
	 */
	public int getT15Total() {
		return t15Total;
	}

	/**
	 * @param t15Total the t15Total to set
	 */
	public void setT15Total(int t15Total) {
		this.t15Total = t15Total;
	}

	/**
	 * @return the t20Total
	 */
	public int getT20Total() {
		return t20Total;
	}

	/**
	 * @param t20Total the t20Total to set
	 */
	public void setT20Total(int t20Total) {
		this.t20Total = t20Total;
	}

	/**
	 * @return the t30Total
	 */
	public int getT30Total() {
		return t30Total;
	}

	/**
	 * @param t30Total the t30Total to set
	 */
	public void setT30Total(int t30Total) {
		this.t30Total = t30Total;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof FRRDROP2Country)) {
			return false;
		}

		FRRDROP2Country account = (FRRDROP2Country) o;

		return account.name.equals(this.name);
	}

	public int hashCode() {
		int result = 17;
		return 31 * result + this.name.hashCode();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the t14Completed
	 */
	public int getT14Completed() {
		return t14Completed;
	}

	/**
	 * @param t14Completed the t14Completed to set
	 */
	public void setT14Completed(int t14Completed) {
		this.t14Completed = t14Completed;
	}

	/**
	 * @return the t14Pending
	 */
	public int getT14Pending() {
		return t14Pending;
	}

	/**
	 * @param t14Pending the t14Pending to set
	 */
	public void setT14Pending(int t14Pending) {
		this.t14Pending = t14Pending;
	}

	/**
	 * @return the t15Completed
	 */
	public int getT15Completed() {
		return t15Completed;
	}

	/**
	 * @param t15Completed the t15Completed to set
	 */
	public void setT15Completed(int t15Completed) {
		this.t15Completed = t15Completed;
	}

	/**
	 * @return the t15Pending
	 */
	public int getT15Pending() {
		return t15Pending;
	}

	/**
	 * @param t15Pending the t15Pending to set
	 */
	public void setT15Pending(int t15Pending) {
		this.t15Pending = t15Pending;
	}

	/**
	 * @return the t20Completed
	 */
	public int getT20Completed() {
		return t20Completed;
	}

	/**
	 * @param t20Completed the t20Completed to set
	 */
	public void setT20Completed(int t20Completed) {
		this.t20Completed = t20Completed;
	}

	/**
	 * @return the t20Pending
	 */
	public int getT20Pending() {
		return t20Pending;
	}

	/**
	 * @param t20Pending the t20Pending to set
	 */
	public void setT20Pending(int t20Pending) {
		this.t20Pending = t20Pending;
	}

	/**
	 * @return the t30Completed
	 */
	public int getT30Completed() {
		return t30Completed;
	}

	/**
	 * @param t30Completed the t30Completed to set
	 */
	public void setT30Completed(int t30Completed) {
		this.t30Completed = t30Completed;
	}

	/**
	 * @return the t30Pending
	 */
	public int getT30Pending() {
		return t30Pending;
	}

	/**
	 * @param t30Pending the t30Pending to set
	 */
	public void setT30Pending(int t30Pending) {
		this.t30Pending = t30Pending;
	}

	/**
	 * @return the tier
	 */
	public String getTier() {
		return tier;
	}

	/**
	 * @param tier the tier to set
	 */
	public void setTier(String tier) {
		this.tier = tier;
	}

}
