package com.uob.edag.dl;

public class FileSystem {
	private String date;
	private String name;
	private String arrivalTime;
	private String recordsCount;
	private Long fileSize;
	private String fileName;
	private String isCompleted = "green"; 
	private long rowInput = 0;
	private String loadingStatus ="F";
	private String failReason;


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof FileSystem)) {
			return false;
		}

		FileSystem account = (FileSystem) o;

		return account.name.equals(this.name);
	}

	public int hashCode() {
		int result = 17;
		return 31 * result + this.name.hashCode();
	}

	public String getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getRecordsCount() {
		return this.recordsCount;
	}

	public void setRecordsCount(String recordsCount) {
		this.recordsCount = recordsCount;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String tableName) {
		this.fileName = tableName;
	}

	/**
	 * @return the isCompleted
	 */
	public String getIsCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}

	public long getRowInput() {
		return rowInput;
	}

	public void setRowInput(long rowInput) {
		this.rowInput = rowInput;
	}

	public String getLoadingStatus() {
		return loadingStatus;
	}

	public void setLoadingStatus(String loadingStatus) {
		this.loadingStatus = loadingStatus;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
}
