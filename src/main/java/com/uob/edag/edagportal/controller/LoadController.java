package com.uob.edag.edagportal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uob.edag.edagportal.dataload.CacheData;

@CrossOrigin(maxAge = 3600L)
@RequestMapping({"/operation"})
@RestController
public class LoadController
{
  private static final Logger logger = LogManager.getLogger(LoadController.class);
  @RequestMapping({"/t1"})
  public String getDLT1LoadStatus() {
    logger.info("inside getDLT1LoadStatus");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedDataOps("EDAG_DL_T1_LOADSTAUS");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    return loadStatus.toString();
  }
  
  @RequestMapping({"/frrt1"})
  public String getDLFRRT1LoadStatus() {
    logger.info("inside getDLFRRT1LoadStatus");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedDataOps("EDAG_DL_FRR_T1_LOADSTAUS");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    return loadStatus.toString();
  }
  @RequestMapping({"/t14"})
  public String getEDWT14LoadStatus() {
    logger.info("inside getDLT14LoadStatus");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedDataOps("EDAG_DL_T14_LOADSTAUS");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    return loadStatus.toString();
  }
  @RequestMapping({"/t15"})
  public String getEDWT15LoadStatus() {
    logger.info("inside getDLT5LoadStatus");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedDataOps("EDAG_DL_T15_LOADSTAUS");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    return loadStatus.toString();
  }

  
  @RequestMapping({"/t2"})
  public String getEDWT2LoadStatus() {
    logger.info("inside getDLT2LoadStatus");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataOps("EDAG_DL_T2_LOADSTAUS");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  @RequestMapping({"/t3"})
  public String getFRRDetails() {
    logger.info("inside getDLT3LoadStatus");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataOps("EDAG_DL_T3_LOADSTAUS");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  @RequestMapping({"/t1summary"})
  public String getT1SummaryDetails() {
    logger.info("inside getT1SummaryDetails");
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
  @RequestMapping({"/frrt1summary"})
  public String getFRRT1SummaryDetails() {
    logger.info("inside getFRRT1SummaryDetails");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataSummary("EDAG_DL_FRR_T1_SUMMARY");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  
  @RequestMapping({"/t14summary"})
  public String getT14SummaryDetails() {
    logger.info("inside getT14SummaryDetails");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataSummary("EDAG_DL_T14_SUMMARY");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  @RequestMapping({"/t15summary"})
  public String getT15SummaryDetails() {
    logger.info("inside getT15SummaryDetails");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataSummary("EDAG_DL_T15_SUMMARY");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  @RequestMapping({"/t2summary"})
  public String getT2SummaryDetails() {
    logger.info("inside getT2SummaryDetails");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataSummary("EDAG_DL_T2_SUMMARY");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  @RequestMapping({"/t3summary"})
  public String getT3SummaryDetails() {
    logger.info("inside getT3SummaryDetails");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedDataSummary("EDAG_DL_T3_SUMMARY");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
}