/**
 * 
 */
package com.uob.edag.edagportal.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uob.edag.edagportal.dataload.CacheData;

/**
 * @author vencp9
 *
 */



@CrossOrigin(maxAge = 3600L)
@RequestMapping({"/customer"})
@RestController
public class ReportController
{
  @RequestMapping({"/customer"})
  public String getCustomeryDetails() {
    Object memory = "";

    
    try {
      memory = CacheData.getCachedDataImpala("EDAG_EDW_CUSTOMER");
    }
    catch (Exception e) {
      System.err.println(e);
    } 
    return memory.toString();
  }
  
  @RequestMapping({"/account"})
  public String getAccountDetails() {
    Object account = "";
    
    try {
      account = CacheData.getCachedDataImpala("EDAG_EDW_CUSTOMER_DATA");
    }
    catch (Exception e) {
      System.err.println(e);
    } 
    return account.toString();
  }
}
