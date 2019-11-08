/**
 * 
 */
package com.uob.edag.util;

/**
 * @author vencp9
 *
 */
public class ErrorMessage {
	
	private boolean isError = false;
	private boolean isValid = true;
	private String errorCode;
	
	private String errorReason;

	/**
	 * @return the isError
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * @param isError the isError to set
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorReason
	 */
	public String getErrorReason() {
		return errorReason;
	}

	/**
	 * @param errorReason the errorReason to set
	 */
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	
}
