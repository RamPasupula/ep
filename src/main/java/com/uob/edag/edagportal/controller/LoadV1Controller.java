package com.uob.edag.edagportal.controller;

import com.uob.edag.edagportal.dataload.CacheData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(maxAge = 3600L)
@RequestMapping({"/load"})
@RestController
public class LoadV1Controller
{
  private static final Logger logger = LogManager.getLogger(LoadV1Controller.class);
  @RequestMapping({"/v1"})
  public String getDLT1LoadStatus() {
    logger.info("inside getDLV1LoadStatus");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedV1Ops("EDAG_DL_V1_SUMMARY");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
      StringBuilder sb = new StringBuilder("{ \"name\":\"DataLake\", \"children\": ");

      
      sb.append(loadStatus.toString()).append(" } ");
   //   System.out.println("HERE::  " + sb);
      return sb.toString();
     
    } 
    
    return null;
  }
  @RequestMapping({"/v2"})
  public String getEDWT1LoadStatus() {
    logger.info("inside getDLTv2LoadStatus");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataSummary("EDAG_DL_T1_SUMMARY");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
}
