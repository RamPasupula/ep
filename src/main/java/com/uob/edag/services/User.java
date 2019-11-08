/**
 * 
 */
package com.uob.edag.services;

import com.uob.edag.util.ErrorMessage;

/**
 * @author vencp9
 *
 */
public class User {
	private String userid;

	private String passwd;

	private String ip;
	
	private ErrorMessage ErrorMessage;
	
	private String domain;




	/**
	 * @return the ErrorMessage
	 */
	public ErrorMessage getErrorMessage() {
		return ErrorMessage;
	}

	/**
	 * @param ErrorMessage the ErrorMessage to set
	 */
	public void setErrorMessage(ErrorMessage ErrorMessage) {
		this.ErrorMessage = ErrorMessage;
	}

	public User(String userid, String passwd, String ip,String domain ) {
		this.userid = userid;
		this.passwd = passwd;
		this.ip = ip;
		this.domain = domain;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}


}
