package com.uob.edag.dl;

import java.util.HashSet;
import java.util.Set;



public class Country
{
  private String name;
  private Set<SourceSystem> children;
  private String isCompleted; 
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Country)) {
      return false;
    }
    
    Country account = (Country)o;
    
    return account.name.equals(this.name);
  }


  
  public int hashCode() {
   int result = 17;
    return 31 * result + this.name.hashCode();
  }




  
  public String getName() { return this.name; }



  
  public void setName(String name) { this.name = name; }


  
  public Set<SourceSystem> getChildren() {
    if (null == this.children)
      this.children = new HashSet(); 
    return this.children;
  }


  
  public void setChildren(Set<SourceSystem> children) { this.children = children; }



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
