package com.uob.edag.edagportal.dataload;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//private static  String SCHEDULE_TIME = PropertyLoadUtil.getProperty("SCHEDULE_TIME");
	
	
  
   
	// in milliseconds
	// @Scheduled(fixedRate = 3600000) // 1 hour
	/*
	 * public void reloadData() {
	 * log.info("reloadData() called: The time is now {}", dateFormat.format(new
	 * Date())); try { CacheData cacheData = CacheData.getInstance();
	 * cacheData.loadJsonMap(); } catch (Exception e) { System.err.println(e); }
	 * log.info("reloadData() call completed: The time is now {}",
	 * dateFormat.format(new Date())); }
	 */
	 @Scheduled(cron = "${schedule.sysdata}")
	public void reloadSystemsData() {
		log.info("reloadSystemsData() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance();
			cacheData.getSystems();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("reloadSystemsData() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	 @Scheduled(cron = "${schedule.reloadfiledata}")
	public void reloadFileData() {
		log.info("reloadSystemsData() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getFileInstance();
			cacheData.getFileDetails();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("reloadSystemsData() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	// getServers();
	/**
	 * @Scheduled(fixedRate = 3600000) public void reloadServersData() {
	 *                      log.info("reloadSystemsData() called: The time is now
	 *                      {}", dateFormat.format(new Date())); try { CacheData
	 *                      cacheData = CacheData.getInstance(); HashMap<String,
	 *                      Object> jsonMap = cacheData.getServers();;
	 *                      cacheData.setJsonMap(jsonMap); } catch (Exception e) {
	 *                      System.err.println(e); } log.info("reloadSystemsData()
	 *                      call completed: The time is now {}",
	 *                      dateFormat.format(new Date())); }
	 * 
	 * 
	 * @Scheduled(fixedRate = 3600000) public void reloadDLJobsData() {
	 *                      log.info("reloadDLJobsData() called: The time is now
	 *                      {}", dateFormat.format(new Date())); try { CacheData
	 *                      cacheData = CacheData.getInstance(); HashMap<String,
	 *                      Object> jsonMap = cacheData.getDLJobs();;
	 *                      cacheData.setJsonMap(jsonMap); } catch (Exception e) {
	 *                      System.err.println(e); } log.info("reloadDLJobsData()
	 *                      call completed: The time is now {}",
	 *                      dateFormat.format(new Date())) }
	 * 
	 * 
	 * @Scheduled(fixedRate = 3600000) public void reloadEDAGJobsData() {
	 *                      log.info("reloadEDAGJobsData() called: The time is now
	 *                      {}", dateFormat.format(new Date())); try { CacheData
	 *                      cacheData = CacheData.getInstance(); HashMap<String,
	 *                      Object> jsonMap = cacheData.getEDAGJobs();
	 *                      cacheData.setJsonMap(jsonMap); } catch (Exception e) {
	 *                      System.err.println(e); } log.info("reloadEDAGJobsData()
	 *                      call completed: The time is now {}",
	 *                      dateFormat.format(new Date())); }
	 **/


	 @Scheduled(cron = "${schedule.dlt1loadstatus}")
	public void getDLT1LoadStatus() {
		log.info("getDLLoadStatus() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance2();
			cacheData.getDLT1LoadStatus();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLLoadStatus() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	 @Scheduled(cron = "${schedule.dlfrrt1loadstatus}")
	public void getDLFRRT1LoadStatus() {
		log.info("getDLFRRT1LoadStatus() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance2();
			cacheData.getDLFRRT1LoadStatus();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLFRRT1LoadStatus() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	 @Scheduled(cron = "${schedule.edwt14loadstatus}")
	public void getEDWT14LoadStatus() {
		log.info("getEDWT14LoadStatus() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance2();
			cacheData.getEDWT14LoadStatus();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getEDWT14LoadStatus() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	 @Scheduled(cron = "${schedule.edwt15loadstatus}")
	public void getEDWT15LoadStatus() {
		log.info("getEDWT15LoadStatus() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance2();
			cacheData.getEDWT15LoadStatus();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getEDWT15LoadStatus() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	 @Scheduled(cron = "${schedule.edwt2loadstatus}")
	public void getEDWT2LoadStatus() {
		log.info("getEDWT2LoadStatus() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance2();
			cacheData.getEDWT2LoadStatus();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getEDWT2LoadStatus() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	 @Scheduled(cron = "${schedule.edwt3loadstatus}")
	public void getEDWT3LoadStatus() {
		log.info("getEDWT3LoadStatus() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstance2();
			cacheData.getEDWT3LoadStatus();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getEDWT3LoadStatus() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	
	 /*
	  *  @Scheduled(cron = "${schedule.dlT1loadsummary}")
	 public void getDLT1LoadSummary() {
		log.info("getDLT1LoadSummary() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceSummary();
			cacheData.getDLT1Summary();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLT1LoadSummary() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	
	 * @Scheduled(fixedRate = 900000L)
	public void getDLFRRT1Summary() {
		log.info("getDLFRRT1Summary() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceSummary();
			cacheData.getDLFRRT1Summary();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLFRRT1Summary() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(fixedRate = 900000L)
	public void getDLT14LoadSummary() {
		log.info("getDLT14LoadSummary() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceSummary();
			cacheData.getDLT14Summary();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLT14LoadSummary() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(fixedRate = 900000L)
	public void getDLT15LoadSummary() {
		log.info("getDLT15LoadSummary() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceSummary();
			cacheData.getDLT15Summary();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLT15LoadSummary() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(fixedRate = 900000L)
	public void getDLT2LoadSummary() {
		log.info("getDLT2LoadSummary() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceSummary();
			cacheData.getDLT2Summary();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLT2LoadSummary() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(fixedRate = 900000L)
	public void getDLT3LoadSummary() {
		log.info("getDLT3LoadSummary() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceSummary();
			cacheData.getDLT3Summary();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getDLT3LoadSummary() call completed: The time is now {}", dateFormat.format(new Date()));
	}
*/
	/*
	 * @Scheduled(cron = "${schedule.dlv1loadsummary}") public void
	 * getDLV1LoadSummary() {
	 * log.info("getDLT1LoadSummary() called: The time is now {}",
	 * dateFormat.format(new Date())); try { CacheData cacheData =
	 * CacheData.getInstanceSummary(); cacheData.getDLV1Summary(); } catch
	 * (Exception e) { System.err.println(e); }
	 * log.info("getDLT1LoadSummary() call completed: The time is now {}",
	 * dateFormat.format(new Date())); }
	 */
	/**
	 * 
	 * @Scheduled(fixedRate = 3600000) public void reloadClusterUtilisationData() {
	 *                      log.info("reloadClusterUtilisationData() called: The
	 *                      time is now {}", dateFormat.format(new Date())); try {
	 *                      CacheData cacheData = CacheData.getInstance();
	 *                      HashMap<String, Object> jsonMap =
	 *                      cacheData.getClusterUtilisation();
	 *                      cacheData.setJsonMap(jsonMap); } catch (Exception e) {
	 *                      System.err.println(e); }
	 *                      log.info("reloadClusterUtilisationData() call completed:
	 *                      The time is now {}", dateFormat.format(new Date())); }
	 * 
	 */

/*	@Scheduled(fixedRate = 3600000L)
	public void reloadCusromerAccountDetailsData() {
		log.info("reloadCusromerAccountDetailsData() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceImpala();
			cacheData.getCusromerAccountDetails();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("reloadCusromerAccountDetailsData() call completed: The time is now {}",
				dateFormat.format(new Date()));
	}
	
	
	


	@Scheduled(fixedRate = 900000L)
	public void getFRRDailyT20() {
		log.info("getFRRDailyT20() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRDailyT20();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRDailyT20() call completed: The time is now {}", dateFormat.format(new Date()));
	}

	@Scheduled(fixedRate = 900000L)
	public void getFRRMonthlyT20() {
		log.info("getFRRMonthlyT20() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRMonthlyT20();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRMonthlyT20() call completed: The time is now {}", dateFormat.format(new Date()));
	}
	*/

	//@Scheduled(fixedRate = 900000L)
	 @Scheduled(cron = "${schedule.frrdaily2t20}")
	public void getFRRDaily2T20() {
		log.info("getFRRDaily2T20() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRDaily2T20();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRDaily2T20() call completed: The time is now {}", dateFormat.format(new Date()));
	}
	
	 @Scheduled(cron = "${schedule.frrdaily2t30}")
	public void getFRRDaily2T30() {
		log.info("getFRRDaily2T30() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRDaily2T30();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRDaily2T30() call completed: The time is now {}", dateFormat.format(new Date()));
	}
	
	
	 @Scheduled(cron = "${schedule.frrdailyt}")
	public void getFRRDailyT() {
		log.info("getFRRDaily2T30() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRDailyT();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRDaily2T30() call completed: The time is now {}", dateFormat.format(new Date()));
	}
	 
	@Scheduled(cron = "${schedule.frrasiaareamap}")
	public void getFRRAsiaAreaMapData() {
		log.info("getFRRAsiaAreaMapData() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRAsiaAreaMapData();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRAsiaAreaMapData() call completed: The time is now {}", dateFormat.format(new Date()));
	}
	
	@Scheduled(cron = "${schedule.frrwesternareamap}")
	public void getFRRWesternAreaMapData() {
		log.info("getFRRWesternAreaMapData() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRWesternAreaMapData();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRWesternAreaMapData() call completed: The time is now {}", dateFormat.format(new Date()));
	}
	
	@Scheduled(cron = "${schedule.frrasiaheatmap}")
	public void getFRRAsiaHeatMapData() {
		log.info("getFRRAsiaHeatMapData() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRAsiaHeatMapData();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRAsiaHeatMapData() call completed: The time is now {}", dateFormat.format(new Date()));
	}
	
	@Scheduled(cron = "${schedule.frrwesternheatmap}")
	public void getFRRWesternHeatMapData() {
		log.info("getFRRDaily2T30() called: The time is now {}", dateFormat.format(new Date()));
		try {
			CacheData cacheData = CacheData.getInstanceFRR();
			cacheData.getFRRWesternHeatMapData();
		} catch (Exception e) {
			System.err.println(e);
		}
		log.info("getFRRWesternHeatMapData() call completed: The time is now {}", dateFormat.format(new Date()));
	}
}
