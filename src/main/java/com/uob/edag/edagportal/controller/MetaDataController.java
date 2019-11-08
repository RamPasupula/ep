package com.uob.edag.edagportal.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uob.edag.edagportal.dataload.CacheData;



@CrossOrigin(maxAge = 3600L)
@RequestMapping({"/metadata"})
@RestController
public class MetaDataController
{
  @RequestMapping({"/systems"})
  public String getSystemDetails() {
    Object sourceSystems = "";
    
    try {
      sourceSystems = CacheData.getCachedData("EDAG_SOURCE_SYSTEMS");





    
    }
    catch (Exception e) {
      System.err.println(e);
    } 
    return sourceSystems.toString();
  }
  
  @RequestMapping({"/files"})
  public String getFileDetails() {
    Object files = "";
    
    try {
      files = CacheData.getCachedFiles("EDAG_FILE_DETAILS");
    
    }
    catch (Exception e) {
      System.err.println(e);
    } 
    return files.toString();
  }
}
