package com.uob.edag.dl;

public class FRRCountry
{
  private String name;
 
  private int Completed;
  private int failed;
  private int Running;
  private int Pending;
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof FRRCountry)) {
      return false;
    }
    
    FRRCountry account = (FRRCountry)o;
    
    return account.name.equals(this.name);
  }


  
  public int hashCode() {
   int result = 17;
    return 31 * result + this.name.hashCode();
  }




  
  public String getName() { return this.name; }



  
  public void setName(String name) { this.name = name; }


  



/**
 * @return the completed
 */
public int getCompleted() {
	return Completed;
}



/**
 * @param completed the completed to set
 */
public void setCompleted(int completed) {
	Completed = completed;
}



/**
 * @return the failed
 */
public int getFailed() {
	return failed;
}



/**
 * @param failed the failed to set
 */
public void setFailed(int failed) {
	this.failed = failed;
}



/**
 * @return the running
 */
public int getRunning() {
	return Running;
}



/**
 * @param running the running to set
 */
public void setRunning(int running) {
	Running = running;
}



/**
 * @return the pending
 */
public int getPending() {
	return Pending;
}



/**
 * @param pending the pending to set
 */
public void setPending(int pending) {
	Pending = pending;
}
}
