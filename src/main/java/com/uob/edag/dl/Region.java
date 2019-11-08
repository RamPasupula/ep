package com.uob.edag.dl;

import java.util.HashSet;
import java.util.Set;



public class Region
{
  private String name;
  /**
 * 
 */
private String isCompleted; 



private Set<Country> children;
  
  public boolean equals(Object o) {
    if (o == this) return true; 
    if (!(o instanceof Region)) {
      return false;
    }
    
    Region account = (Region)o;
    
    return account.name.equals(this.name);
  }

  
  public String getName() { return this.name; }


  
  public void setName(String name) { this.name = name; }

  
  public Set<Country> getChildren() {
    if (null == this.children)
      this.children = new HashSet<Country>(); 
    return this.children;
  }

  
  public void setChildren(Set<Country> children) { this.children = children; }


  
  public int hashCode() {
    int result = 17;
    return 31 * result + this.name.hashCode();
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
