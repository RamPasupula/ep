/**
 * 
 */
package com.uob.edag.util;

/**
 * @author vencp9
 *
 */
public class Account {
	

private String date;
 
private String type ;
 
public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

private int valueI;

private int valueC;

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}



public int getValueI() {
	return valueI;
}

public void setValueI(int valueI) {
	this.valueI = valueI;
}

public int getValueC() {
	return valueC;
}

public void setValueC(int valueC) {
	this.valueC = valueC;
}
 



@Override
public boolean equals(Object o) {

    if (o == this) return true;
    if (!(o instanceof Account)) {
        return false;
    }

    Account account = (Account) o;

    return account.date.equals(date);
}

//Idea from effective Java : Item 9
@Override
public int hashCode() {
    int result = 17;
    result = 31 * result + date.hashCode();
  
    return result;
}

}
