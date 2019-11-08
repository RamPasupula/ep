package com.uob.edag.edagportal.controller;

import com.uob.edag.edagportal.dataload.CacheData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(maxAge = 3600L)
@RequestMapping({"/frr"})
@RestController
public class FRRController
{
  private static final Logger logger = LogManager.getLogger(FRRController.class);
  @RequestMapping({"/daily/t20"})
  public String getFRRDailyT20() {
    logger.info("inside getFRRDailyT20");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_DAILY_T20");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
         return loadStatus.toString();
    } 
    
    return null;
  }
  @RequestMapping({"/daily/t30"})
  public String getFRRDailyT30() {
    logger.info("inside getFRRDailyT30");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedFRR("FRR_DAILY_T30");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  

 @RequestMapping({"/monthly/t20"})
  public String getFRRMonthlyT20() {
    logger.info("inside getFRRMonthlyT20");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_MONTHLY_T20");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
    // System.out.println("# " + loadStatus);
      return loadStatus.toString();
    } 
    
    return null;
  }
  @RequestMapping({"/monthly/t30"})
  public String getFRRMonthlyT30() {
    logger.info("inside getFRRMonthlyT30");
    Object loadStatus = "";
    try {
      loadStatus = CacheData.getCachedFRR("FRR_MONTHLY_T30");
    } catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus)
      return loadStatus.toString(); 
    return null;
  }
  
  @RequestMapping({"/daily2/t20"})
  public String getFRRDaily2T20() {
    logger.info("inside getFRRDaily2T20");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_DAILY2_T20");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
   
      return loadStatus.toString();
    } 
    
    return null;
  }

  @RequestMapping({"/daily2/t30"})
  public String getFRRDaily2T30() {
    logger.info("inside getFRRDaily2T30");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_DAILY2_T30");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
    
      return loadStatus.toString();
    } 
    
    return null;
  }
  
  
  @RequestMapping({"/daily/t"})
  public String getFRRDailyT() {
    logger.info("inside getFRRDailyT");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_DAILY_T");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
     
      return loadStatus.toString();
    } 
    
    return null;
  }


  @RequestMapping(value = "/daily/frrasiaareamap", produces = "text/plain")

  public String getFRRAsiaAreaMapData() {
    logger.info("inside getFRRAsiaAreaMapData");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_ASIA_AREAMAP");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
    	
  //   System.out.println(loadStatus);
     return loadStatus.toString();
    
    } 
    
    return null;
  }
  
  @RequestMapping({"/daily/frrwesternareamap"})
  public String getFRRWesternAreaMapData() {
    logger.info("inside getFRRWesternAreaMapData");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_WESTERN_AREAMAP");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
     
      return loadStatus.toString();
    } 
    
    return null;
  }
  
  @RequestMapping({"/daily/frrasiaheatmap"})
  public String getFRRAsiaHeatMapData() {
    logger.info("inside getFRRAsiaHeatMapData");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_ASIA_HEATMAP");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
     
      return loadStatus.toString();
    } 
    
    return null;
  }
  
  @RequestMapping({"/daily/frrwesternheatmap"})
  public String getFRRWesternHeatMapData() {
    logger.info("inside getFRRWesternHeatMapData");
    Object loadStatus = "";
    
    try {
      loadStatus = CacheData.getCachedFRR("FRR_WESTERN_HEATMAP");
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      System.err.println(e);
    } 
    if (null != loadStatus) {
  //   System.out.println(loadStatus);
      return loadStatus.toString();
    } 
    
    return null;
  }  
}
