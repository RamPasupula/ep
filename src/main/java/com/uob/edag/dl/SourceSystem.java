package com.uob.edag.dl;

import java.util.ArrayList;
import java.util.List;








public class SourceSystem
{
  private String name = "";

  
  private List<FileSystem> children;

  private String isCompleted; 

  
  public String getName() { return this.name; }


  
  public void setName(String name) { this.name = name; }

  
  public List<FileSystem> getChildren() {
    if (this.children == null) {
      this.children = new ArrayList();
    }
    return this.children;
  }

  
  public void setChildren(List<FileSystem> children) { this.children = children; }



  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof SourceSystem)) {
      return false;
    }
    
    SourceSystem account = (SourceSystem)o;
    
    return account.name.equals(this.name);
  }

  
  public int hashCode() {
    int result = 17;
    if (null != this.name) {
      result = 31 * result + this.name.hashCode();
    }
    return result;
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


}
