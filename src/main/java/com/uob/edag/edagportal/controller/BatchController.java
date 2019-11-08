package com.uob.edag.edagportal.controller;

import com.uob.edag.edagportal.dataload.CacheData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(maxAge = 3600L)
@RequestMapping({"/batch"})
@RestController
public class BatchController
{
  @RequestMapping({"/dlJobs"})
  public String getDLJobDetails() {
    Object jobs = "";
    
    try {
      jobs = CacheData.getCachedData("EDAG_DL_JOBS");
    } catch (Exception e) {
      System.err.println(e);
    } 
    return jobs.toString();
  }
  
  @RequestMapping({"/edwJobs"})
  public String getEDWJobDetails() {
    Object jobs = "";
    
    try {
      jobs = CacheData.getCachedData("EDAG_EDW_JOBS");
    } catch (Exception e) {
      System.err.println(e);
    } 
    return jobs.toString();
  }
}
