package com.uob.edag.edagportal.dataload;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import com.uob.edag.access.ImpalaConnectionAccess;
import com.uob.edag.access.OracleConnection;
import com.uob.edag.access.TeradataConnection;
import com.uob.edag.dl.DLJsonConverter;
import com.uob.edag.dl.FRRCountry;
import com.uob.edag.dl.FRRDROP2Converter;
import com.uob.edag.dl.FRRDROP2Country;
import com.uob.edag.dl.FRRJsonConverter;
import com.uob.edag.dl.Region;
import com.uob.edag.edagportal.Converter;
import com.uob.edag.util.FRRHeatMapMetrics;
import com.uob.edag.util.FRRHeatMetricConverter;
import com.uob.edag.util.FRRMetricConverter;
import com.uob.edag.util.FRRMetrics;
import com.uob.edag.util.PropertyLoadUtil;

public class CacheData {
	private static final Logger logger = LogManager.getLogger(CacheData.class);

	

	private static CacheData cachedData = null;
	private static CacheData cachedData2 = null;
	private static CacheData cachedDataImpala = null;
	private static CacheData cachedSummary = null;
	private static CacheData cachedV1 = null;
	private static CacheData cachedFRR = null;
	public HashMap<String, Object> jsonMap;
	public HashMap<String, Object> jsonMapOps;
	public HashMap<String, Object> jsonMapImpala;
	public HashMap<String, Object> jsonMapSummary;
	public HashMap<String, Object> jsonMapV1;
	public HashMap<String, Object> jsonMapFiles;
	public HashMap<String, Object> jsonMapFRR;
	private final String hiveEnvironment;
	private static CacheData cachedFile = null;
	private final String teradataEnvironment;
	private final String EDAG_SOURCE_SYSTEMS_QUERY;
	private final String EDAG_FILE_DETAILS_QUERY;
	private final String DLT1LOAD_QUERY;
	private final String EDAG_DL_T1_LOADSTAUS_QUERY;
	private final String EDAG_DL_FRR_T1_LOADSTAUS_QUERY;
	private final String EDAG_DL_T14_LOADSTAUS_QUERY;
	private final String EDAG_DL_T15_LOADSTAUS_QUERY;
	private final String EDAG_DL_T2_LOADSTAUS_QUERY;
	private final String EDAG_DL_T3_LOADSTAUS_QUERY;
	private final String EDAG_DL_T1_SUMMARY_QUERY;
	private final String EDAG_DL_FRR_T1_SUMMARY_QUERY;
	private final String EDAG_DL_T14_SUMMARY_QUERY;
	private final String EDAG_DL_T15_SUMMARY_QUERY;
	private final String EDAG_DL_T2_SUMMARY_QUERY;
	private final String EDAG_DL_T3_SUMMARY_QUERY;
	private final String CUSTOMER_REPORT_QUERY;
	private final String ACCOUNT_REPORT_QUERY;
	private final String EDAG_EDW_CLUSTER_MEM_QUERY;
	private final String EDAG_EDW_CLUSTER_CPU_QUERY;
	private final String EDAG_EDW_CLUSTER_IO_QUERY;
	private final String FRR_DAILY_T20_QUERY;
	private final String FRR_DAILY_T30_QUERY;
	private final String FRR_MONTHLY_T20_QUERY;
	private final String FRR_MONTHLY_T30_QUERY;
	private final String FRR_DAILY2_T20_QUERY;
	private final String FRR_DAILY2_T30_QUERY;
	private final String FRR_DAILY_T_QUERY;
	private final String FRR_ASIA_HEATMAP_QUERY;
	private final String FRR_WESTERN_HEATMAP_QUERY;
	private final String FRR_ASIA_AREAMAP_QUERY;
	private final String FRR_WESTERN_AREAMAP_QUERY;	
	
	private CacheData() {
		this.jsonMap = null;
		this.jsonMapOps = null;
		this.jsonMapImpala = null;
		this.jsonMapSummary = null;

		this.hiveEnvironment = PropertyLoadUtil.getProperty("HIVE_ENV");
		this.teradataEnvironment = PropertyLoadUtil.getProperty("TERADATA_ENV");
		this.EDAG_SOURCE_SYSTEMS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_SOURCE_SYSTEMS_QUERY");
		this.EDAG_FILE_DETAILS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_FILE_DETAILS_QUERY");
		this.DLT1LOAD_QUERY = PropertyLoadUtil.getQueryProperty("DLT1Load_QUERY");
		this.EDAG_DL_T1_LOADSTAUS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T1_LOADSTAUS_QUERY");
		this.EDAG_DL_FRR_T1_LOADSTAUS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_FRR_T1_LOADSTAUS_QUERY");
		this.EDAG_DL_T14_LOADSTAUS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T14_LOADSTAUS_QUERY");
		this.EDAG_DL_T15_LOADSTAUS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T15_LOADSTAUS_QUERY");
		this.EDAG_DL_T2_LOADSTAUS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T2_LOADSTAUS_QUERY");
		this.EDAG_DL_T3_LOADSTAUS_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T3_LOADSTAUS_QUERY");

		this.EDAG_DL_T1_SUMMARY_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T1_SUMMARY_QUERY");
		this.EDAG_DL_FRR_T1_SUMMARY_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_FRR_T1_SUMMARY_QUERY");
		this.EDAG_DL_T14_SUMMARY_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T14_SUMMARY_QUERY");
		this.EDAG_DL_T15_SUMMARY_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T15_SUMMARY_QUERY");
		this.EDAG_DL_T2_SUMMARY_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T2_SUMMARY_QUERY");
		this.EDAG_DL_T3_SUMMARY_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_DL_T3_SUMMARY_QUERY");

		this.CUSTOMER_REPORT_QUERY = PropertyLoadUtil.getQueryProperty("CUSTOMER_REPORT_QUERY");
		this.ACCOUNT_REPORT_QUERY = PropertyLoadUtil.getQueryProperty("ACCOUNT_REPORT_QUERY");
		this.EDAG_EDW_CLUSTER_MEM_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_EDW_CLUSTER_MEM_QUERY");
		this.EDAG_EDW_CLUSTER_CPU_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_EDW_CLUSTER_CPU_QUERY");
		this.EDAG_EDW_CLUSTER_IO_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_EDW_CLUSTER_IO_QUERY");
		this.FRR_DAILY_T20_QUERY = PropertyLoadUtil.getQueryProperty("FRR_DAILY_T20_QUERY");
		this.FRR_DAILY_T30_QUERY = PropertyLoadUtil.getQueryProperty("FRR_DAILY_T30_QUERY");
		this.FRR_MONTHLY_T20_QUERY = PropertyLoadUtil.getQueryProperty("FRR_MONTHLY_T20_QUERY");
		this.FRR_MONTHLY_T30_QUERY = PropertyLoadUtil.getQueryProperty("FRR_MONTHLY_T30_QUERY");
		this.FRR_DAILY2_T20_QUERY = PropertyLoadUtil.getQueryProperty("FRR_DAILY2_T20_QUERY");
		this.FRR_DAILY2_T30_QUERY = PropertyLoadUtil.getQueryProperty("FRR_DAILY2_T30_QUERY");
		this.FRR_DAILY_T_QUERY = PropertyLoadUtil.getQueryProperty("FRR_DAILY_T_QUERY");
		
		this.FRR_ASIA_HEATMAP_QUERY = PropertyLoadUtil.getQueryProperty("FRR_ASIA_HEATMAP_QUERY");
		this.FRR_WESTERN_HEATMAP_QUERY = PropertyLoadUtil.getQueryProperty("FRR_WESTERN_HEATMAP_QUERY");
		this.FRR_ASIA_AREAMAP_QUERY = PropertyLoadUtil.getQueryProperty("FRR_ASIA_AREAMAP_QUERY");
		this.FRR_WESTERN_AREAMAP_QUERY = PropertyLoadUtil.getQueryProperty("FRR_WESTERN_AREAMAP_QUERY");
		
		this.jsonMap = new HashMap<String, Object>();
		this.jsonMapOps = new HashMap<String, Object>();
		this.jsonMapImpala = new HashMap<String, Object>();
		this.jsonMapSummary = new HashMap<String, Object>();
		this.jsonMapV1 = new HashMap<String, Object>();
		this.jsonMapFiles = new HashMap<String, Object>();
		this.jsonMapFRR = new HashMap<String, Object>();
	}

	public static CacheData getInstance() {
		if (cachedData == null) {
			cachedData = new CacheData();
		}

		return cachedData;
	}

	public static CacheData getInstance2() {
		if (cachedData2 == null) {
			cachedData2 = new CacheData();
		}

		return cachedData2;
	}

	public static CacheData getInstanceImpala() {
		if (cachedDataImpala == null) {
			cachedDataImpala = new CacheData();
		}

		return cachedDataImpala;
	}

	public static CacheData getInstanceV1() {
		if (cachedV1 == null) {
			cachedV1 = new CacheData();
		}

		return cachedV1;
	}

	public static CacheData getInstanceSummary() {
		if (cachedSummary == null) {
			cachedSummary = new CacheData();
		}

		return cachedSummary;
	}

	public static CacheData getFileInstance() {
		if (cachedFile == null) {
			cachedFile = new CacheData();
		}

		return cachedFile;
	}

	public HashMap<String, Object> getJsonMapSummary() {
		return this.jsonMapSummary;
	}

	public void setJsonMapSummary(HashMap<String, Object> jsonMapSummary) {
		this.jsonMapSummary = jsonMapSummary;
	}

	public HashMap<String, Object> getJsonMap() {
		return this.jsonMap;
	}

	public void setJsonMap(HashMap<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public HashMap<String, Object> getJsonMapOps() {
		return this.jsonMapOps;
	}

	public void setJsonMapOps(HashMap<String, Object> jsonMapOps) {
		this.jsonMapOps = jsonMapOps;
	}

	public HashMap<String, Object> getJsonMapImpala() {
		return this.jsonMapImpala;
	}

	public void setJsonMapImpala(HashMap<String, Object> jsonMapImpala) {
		this.jsonMapImpala = jsonMapImpala;
	}

	public HashMap<String, Object> getJsonMapV1() {
		return this.jsonMapV1;
	}

	public void setJsonMapV1(HashMap<String, Object> jsonMapV1) {
		this.jsonMapV1 = jsonMapV1;
	}

	public HashMap<String, Object> getJsonMapFiles() {
		return this.jsonMapFiles;
	}

	public void setJsonMapFiles(HashMap<String, Object> jsonMapFiles) {
		this.jsonMapFiles = jsonMapFiles;
	}

	/**
	 * @return the jsonMapFRR
	 */
	public HashMap<String, Object> getJsonMapFRR() {
		return jsonMapFRR;
	}

	/**
	 * @param jsonMapFRR the jsonMapFRR to set
	 */
	public void setJsonMapFRR(HashMap<String, Object> jsonMapFRR) {
		this.jsonMapFRR = jsonMapFRR;
	}

	public static CacheData getInstanceFRR() {
		if (cachedFRR == null) {
			cachedFRR = new CacheData();
		}
		return cachedFRR;
	}

	public static Object getCachedData(String jsonDataKey) {
		CacheData cachedData = getInstance();
		return cachedData.getJsonMap().get(jsonDataKey);
	}

	public static Object getCachedDataOps(String jsonDataKey) {
		CacheData cachedData2 = getInstance2();
		return cachedData2.getJsonMapOps().get(jsonDataKey);
	}

	public static Object getCachedDataImpala(String jsonDataKey) {
		CacheData cachedDataImpala = getInstanceImpala();
		return cachedDataImpala.getJsonMapImpala().get(jsonDataKey);
	}

	public static Object getCachedDataSummary(String jsonDataKey) {
		CacheData cache = getInstanceSummary();
		return cache.getJsonMapSummary().get(jsonDataKey);
	}

	public static Object getCachedV1Ops(String jsonDataKey) {
		CacheData cache = getInstanceV1();
		return cache.getJsonMapV1().get(jsonDataKey);
	}

	public static Object getCachedFiles(String jsonDataKey) {
		CacheData cache = getFileInstance();
		return cache.getJsonMapFiles().get(jsonDataKey);
	}

	public static Object getCachedFRR(String jsonDataKey) {
		CacheData cache = getInstanceFRR();
		return cache.getJsonMapFRR().get(jsonDataKey);
	}

	public void loadJsonMap() {
		logger.info(" loadJsonMap start ");

		try {
			cachedData = getInstance();
			cachedData2 = getInstance2();
			cachedDataImpala = getInstanceImpala();
			cachedSummary = getInstanceSummary();
			cachedV1 = getInstanceV1();
			cachedFile = getFileInstance();
			cachedFRR = getInstanceFRR();

						
			try {
				getSystems();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				getFileDetails();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			
			/*
			 * try {
			 * 
			 * getDLV1Summary();
			 * 
			 * } catch (Exception e) { e.printStackTrace(); }
			 * 
			 */

			try {
				getDLT1LoadStatus();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				getDLFRRT1LoadStatus();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				getEDWT14LoadStatus();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				getEDWT15LoadStatus();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				getEDWT2LoadStatus();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				getEDWT3LoadStatus();
				System.out.println("getEDWT3LoadStatus Done!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			/*
			 * try { getDLT1Summary(); } catch (Exception e) { e.printStackTrace(); }
			 * 
			 * try { getDLFRRT1Summary(); } catch (Exception e) { e.printStackTrace(); }
			 * 
			 * try { getDLT14Summary(); } catch (Exception e) { e.printStackTrace(); } try {
			 * getDLT15Summary(); } catch (Exception e) { e.printStackTrace(); } try {
			 * getDLT2Summary(); } catch (Exception e) { e.printStackTrace(); } try {
			 * getDLT3Summary();
			 * 
			 * } catch (Exception e) { e.printStackTrace(); }

			
			

			/*
			 * try { getCusromerAccountDetails(); } catch (Exception e) {
			 * e.printStackTrace(); }
			 * /
			try {
				getFRRDailyT20();
			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * try { getFRRDailyT30(); } catch (Exception e) { e.printStackTrace(); }
			 * /
			try {
				getFRRMonthlyT20();
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
			
			try {
				getFRRDaily2T20();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				getFRRDaily2T30();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				getFRRDailyT();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			/*
			 * try { getFRRMonthlyT30(); } catch (Exception e) { e.printStackTrace(); }
			 */
			
			try {
				getFRRAsiaAreaMapData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {

				getFRRAsiaHeatMapData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				getFRRWesternAreaMapData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				getFRRWesternHeatMapData();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}
		logger.info(" loadJsonMap end ");
	}

	public void getCusromerAccountDetails() {
		String jsonDataKey = "EDAG_EDW_CUSTOMER";

		ImpalaConnectionAccess conn = new ImpalaConnectionAccess();

		String query = this.CUSTOMER_REPORT_QUERY;
		query = StringUtils.replace(query, "[HIVE_ENV]", this.hiveEnvironment);
		Converter converter = new Converter();

		try {
			ResultSet rs = conn.execute(query);
			JSONArray jarray = converter.convertToJSON(rs);
			this.jsonMapImpala.put(jsonDataKey, jarray);
		} catch (Exception e) {
			e.printStackTrace();
		}

		jsonDataKey = "EDAG_EDW_CUSTOMER_DATA";

		query = this.ACCOUNT_REPORT_QUERY;
		query = StringUtils.replace(query, "[HIVE_ENV]", this.hiveEnvironment);
		try {
			ResultSet rs = conn.execute(query);

			JSONArray jarray = converter.convertToJSON(rs);
			this.jsonMapImpala.put(jsonDataKey, jarray);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cachedDataImpala = getInstanceImpala();
		cachedDataImpala.setJsonMapImpala(this.jsonMapImpala);
		conn.release();
	}

	public void getClusterUtilisation() throws Exception {
		ImpalaConnectionAccess conn = new ImpalaConnectionAccess();
		String jsonDataKey = "EDAG_EDW_CLUSTER_MEM";
		logger.info("Inside cluster utilisation");

		String query = this.EDAG_EDW_CLUSTER_MEM_QUERY;
		ResultSet rs = conn.execute(query);

		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);

		this.jsonMap.put(jsonDataKey, jarray);

		jsonDataKey = "EDAG_EDW_CLUSTER_CPU";

		query = this.EDAG_EDW_CLUSTER_CPU_QUERY;

		rs = conn.execute(query);

		converter = new Converter();
		jarray = converter.convertToJSON(rs);

		this.jsonMap.put(jsonDataKey, jarray);

		jsonDataKey = "EDAG_EDW_CLUSTER_IO";

		query = this.EDAG_EDW_CLUSTER_IO_QUERY;
		rs = conn.execute(query);

		converter = new Converter();
		jarray = converter.convertToJSON(rs);

		this.jsonMap.put(jsonDataKey, jarray);
		cachedData.setJsonMap(this.jsonMap);
		logger.info("End of Inside cluster utilisation");
		conn.release();
	}

	public void getDLT1LoadStatus() throws Exception {
		OracleConnection connection = new OracleConnection();
		String jsonDataKey = "EDAG_DL_T1_LOADSTAUS";
		String query = this.EDAG_DL_T1_LOADSTAUS_QUERY;
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapOps.put(jsonDataKey, jarray);
		cachedData2.setJsonMapOps(this.jsonMapOps);
		rs.close();
		connection.close();
	}

	public void getDLFRRT1LoadStatus() throws Exception {
		OracleConnection connection = new OracleConnection();
		String jsonDataKey = "EDAG_DL_FRR_T1_LOADSTAUS";
		String query = this.EDAG_DL_FRR_T1_LOADSTAUS_QUERY;
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapOps.put(jsonDataKey, jarray);
		cachedData2.setJsonMapOps(this.jsonMapOps);
		rs.close();
		connection.close();
	}

	public void getEDWT14LoadStatus() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T14_LOADSTAUS";
		String query = this.EDAG_DL_T14_LOADSTAUS_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);

		ResultSet rs = connection.execute(query);

		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapOps.put(jsonDataKey, jarray);
		cachedData2.setJsonMapOps(this.jsonMapOps);
		rs.close();
		connection.close();
	}

	public void getEDWT15LoadStatus() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T15_LOADSTAUS";
		String query = this.EDAG_DL_T15_LOADSTAUS_QUERY;

		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapOps.put(jsonDataKey, jarray);
		cachedData2.setJsonMapOps(this.jsonMapOps);
		rs.close();
		connection.close();
	}

	public void getEDWT2LoadStatus() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T2_LOADSTAUS";
		String query = this.EDAG_DL_T2_LOADSTAUS_QUERY;

		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapOps.put(jsonDataKey, jarray);
		cachedData2.setJsonMapOps(this.jsonMapOps);
		rs.close();
		connection.close();
	}

	public void getEDWT3LoadStatus() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T3_LOADSTAUS";
		String query = this.EDAG_DL_T3_LOADSTAUS_QUERY;

		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapOps.put(jsonDataKey, jarray);
		cachedData2.setJsonMapOps(this.jsonMapOps);
		rs.close();
		connection.close();
	}

	public void getDLT1Summary() throws Exception {
		OracleConnection connection = new OracleConnection();
		String jsonDataKey = "EDAG_DL_T1_SUMMARY";
		String query = this.EDAG_DL_T1_SUMMARY_QUERY;

		ResultSet rs = connection.execute(query);

		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapSummary.put(jsonDataKey, jarray);
		cachedSummary.setJsonMapSummary(this.jsonMapSummary);
		rs.close();
		connection.close();
	}

	public void getDLFRRT1Summary() throws Exception {
		OracleConnection connection = new OracleConnection();
		String jsonDataKey = "EDAG_DL_FRR_T1_SUMMARY";
		String query = this.EDAG_DL_FRR_T1_SUMMARY_QUERY;

		ResultSet rs = connection.execute(query);

		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapSummary.put(jsonDataKey, jarray);
		cachedSummary.setJsonMapSummary(this.jsonMapSummary);
		rs.close();
		connection.close();
	}

	public void getDLV1Summary() throws Exception {
		OracleConnection connection = new OracleConnection();
		String jsonDataKey = "EDAG_DL_V1_SUMMARY";
		String query = this.EDAG_DL_T1_SUMMARY_QUERY;

		ResultSet rs = connection.execute(query);

		List<Region> regoins = (new DLJsonConverter()).getJson(rs);

		JSONArray jsonArray = (new DLJsonConverter()).convertToJSON(regoins);

		this.jsonMapV1.put(jsonDataKey, jsonArray);
	
		cachedV1.setJsonMapV1(this.jsonMapV1);
		
		rs.close();
		connection.close();
	}

	public void getDLT14Summary() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T14_SUMMARY";
		String query = this.EDAG_DL_T14_SUMMARY_QUERY;

		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);

		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapSummary.put(jsonDataKey, jarray);
		cachedSummary.setJsonMapSummary(this.jsonMapSummary);
		rs.close();
		connection.close();
	}

	public void getDLT15Summary() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T15_SUMMARY";

		String query = this.EDAG_DL_T15_SUMMARY_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapSummary.put(jsonDataKey, jarray);
		cachedSummary.setJsonMapSummary(this.jsonMapSummary);
		rs.close();
		connection.close();
	}

	public void getDLT2Summary() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T2_SUMMARY";
		String query = this.EDAG_DL_T2_SUMMARY_QUERY;

		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);

		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapSummary.put(jsonDataKey, jarray);
		cachedSummary.setJsonMapSummary(this.jsonMapSummary);
		rs.close();
		connection.close();
	}

	public void getDLT3Summary() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "EDAG_DL_T3_SUMMARY";
		String query = this.EDAG_DL_T3_SUMMARY_QUERY;

		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();

		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapSummary.put(jsonDataKey, jarray);

		cachedSummary.setJsonMapSummary(this.jsonMapSummary);
		rs.close();
		connection.close();
	}

	public void getEDAGJobs() throws Exception {
		TeradataConnection teradataConnection = new TeradataConnection();
		String jsonDataKey = "EDAG_EDW_JOBS";
		String query = "SELECT CASE WHEN LEFT(PROCESS_NAME,3)='LD_' THEN 'T14' ELSE LEFT(PROCESS_NAME,3) END AS TIER, PROCESS_NAME, SITE_ID, SRC_SYS_CD, FREQ, BIZ_DT, JOB_START_DATETIME, JOB_END_DATETIME, PROCESS_FLG\r\nFROM [TERADATA_ENV]_EDW_UTL_V.UOB_OPERATIONAL_JOB_STATUS\r\nWHERE JOB_END_DATETIME BETWEEN CURRENT_DATE AND CURRENT_TIMESTAMP;";

		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = teradataConnection.execute(query);
		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);

		this.jsonMap.put(jsonDataKey, jarray);
		cachedData.setJsonMap(this.jsonMap);

		rs.close();
		teradataConnection.close();
	}

	public void getDLJobs() throws Exception {
		OracleConnection connection = new OracleConnection();
		String jsonDataKey = "EDAG_DL_JOBS";

		String query = "SELECT JOB_NAME, NODE_ID, JOB_STATUS, START_DATE, END_DATE, ELAPSED_TIME, APP_CODE, LOAD_CTRY, ODATE\r\nFROM GDW.TGDW_RUNHISTORY\r\nWHERE (JOB_NAME LIKE 'EDA%I' OR JOB_NAME LIKE 'EDA%L') AND END_DATE BETWEEN TRUNC(SYSDATE, 'dd') AND SYSDATE";

		ResultSet rs = connection.execute(query, "jdbc:oracle:thin:@gdwtsg98:1521:gdws", "uncpgdw", "uncpgdw");

		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);

		this.jsonMap.put(jsonDataKey, jarray);
		cachedData.setJsonMap(this.jsonMap);
		rs.close();
		connection.close();
	}

	public void getServers() throws Exception {
		OracleConnection connection = new OracleConnection();
		String jsonDataKey = "EDAG_SERVERS";
		String query = "SELECT ENV AS ENV, \"TYPE\" AS TYPE1, HOST_NAME AS HOSTNAME, IP_ADDRESS AS IPADDRESS, MEMORY AS MEMORY, CPU AS CPU, \"STORAGE\" AS STORAGE1, OS AS OS, SOFTWARE_INSTALLED AS SOFTWAREINSTALLED, SOFTWARE_VERSION AS SOFTWAREVERSION, SPECIAL_HANDLING AS SPECIALHANDLING, REMARKS AS REMARKS FROM EDAG_SERVERS";
		ResultSet rs = connection.execute(query);

		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);

		this.jsonMap.put(jsonDataKey, jarray);
		cachedData.setJsonMap(this.jsonMap);
		rs.close();
		connection.close();
	}

	public void getFileDetails() throws Exception {
		OracleConnection connection = null;
		ResultSet rs = null;

		try {
			connection = new OracleConnection();
			String jsonDataKey = "EDAG_FILE_DETAILS";
			String query = this.EDAG_FILE_DETAILS_QUERY;

			rs = connection.execute(query);

			Converter converter = new Converter();
			JSONArray jarray = converter.convertToJSON(rs);

			this.jsonMapFiles.put(jsonDataKey, jarray);
			cachedFile.setJsonMapFiles(this.jsonMapFiles);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			rs.close();
			connection.close();
		}
	}

	public void getSystems() throws Exception {
		OracleConnection connection = null;
		ResultSet rs = null;

		try {
			this.jsonMap = new HashMap<String, Object>();
			String jsonDataKey = "EDAG_SOURCE_SYSTEMS";
			String query = this.EDAG_SOURCE_SYSTEMS_QUERY;

			connection = new OracleConnection();
			rs = connection.execute(query);

			Converter converter = new Converter();
			JSONArray jarray = converter.convertToJSON(rs);

			logger.info("EDAG_SOURCE_SYSTEMS jarray.length() " + jarray.length());

			this.jsonMap.put(jsonDataKey, jarray);
			cachedData.setJsonMap(this.jsonMap);
		} catch (Exception e) {
			logger.error("EDAG_SOURCE_SYSTEMS " + e.getMessage());
			System.err.println(e);
		} finally {
			rs.close();
			connection.close();
		}
	}

	void getFRRDailyT20() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_DAILY_T20";
		String query = this.FRR_DAILY_T20_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
	
		ResultSet rs = connection.execute(query);
		//Converter converter = new Converter();

		//JSONArray jarray = converter.convertToJSON(rs);
	//	this.jsonMapFRR.put(jsonDataKey, jarray);
	//	cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		
		List<FRRCountry> regoins = (new FRRJsonConverter()).getJson(rs);

		JSONArray jsonArray = (new FRRJsonConverter()).convertToJSON(regoins);
       
		this.jsonMapFRR.put(jsonDataKey, jsonArray);
		cachedFRR.setJsonMapV1(this.jsonMapFRR);
				
		rs.close();
		connection.close();
	}

	 void getFRRDailyT30() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_DAILY_T30";
		String query = this.FRR_DAILY_T30_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}

	void getFRRMonthlyT20() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_MONTHLY_T20";
		String query = this.FRR_MONTHLY_T20_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		List<FRRCountry> regoins = (new FRRJsonConverter()).getJson(rs);
		JSONArray jarray = (new FRRJsonConverter()).convertToJSON(regoins);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}

	 void getFRRMonthlyT30() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_MONTHLY_T30";
		String query = this.FRR_MONTHLY_T30_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}

	
	void getFRRDaily2T20() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_DAILY2_T20";
		String query = this.FRR_DAILY2_T20_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}
	
	void getFRRDaily2T30() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_DAILY2_T30";
		String query = this.FRR_DAILY2_T30_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
		ResultSet rs = connection.execute(query);
		Converter converter = new Converter();
		JSONArray jarray = converter.convertToJSON(rs);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}
	
	
	void getFRRDailyT() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_DAILY_T";
		String query = this.FRR_DAILY_T_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
	
		ResultSet rs = connection.execute(query);
		
		List<FRRDROP2Country> regoins = (new FRRDROP2Converter()).getJson(rs);
		JSONArray jarray = (new FRRDROP2Converter()).convertToJSON(regoins);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}
	
	void getFRRAsiaAreaMapData() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_ASIA_AREAMAP";
		String query = this.FRR_ASIA_AREAMAP_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
	
		ResultSet rs = connection.execute(query);
		
		FRRMetricConverter converter = new FRRMetricConverter();
		List<FRRMetrics>  metrics = converter.getJson(rs);
	 	  JSONArray jarray = converter.convertToJSON(metrics);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}
	
	void getFRRWesternAreaMapData() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_WESTERN_AREAMAP";
		String query = this.FRR_WESTERN_AREAMAP_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
	
		ResultSet rs = connection.execute(query);
		
		FRRMetricConverter converter = new FRRMetricConverter();
		List<FRRMetrics>  metrics = converter.getJson(rs);
		 JSONArray jarray = converter.convertToJSON(metrics);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}
	
	void getFRRAsiaHeatMapData() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_ASIA_HEATMAP";
		String query = this.FRR_ASIA_HEATMAP_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
	
		ResultSet rs = connection.execute(query);
		
		FRRHeatMetricConverter converter = new FRRHeatMetricConverter();
		List<FRRHeatMapMetrics> metrics = converter.getJson(rs);
		 JSONArray jarray = converter.convertToJSON(metrics);
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}
	
	void getFRRWesternHeatMapData() throws Exception {
		TeradataConnection connection = new TeradataConnection();
		String jsonDataKey = "FRR_WESTERN_HEATMAP";
		String query = this.FRR_WESTERN_HEATMAP_QUERY;
		query = StringUtils.replace(query, "[TERADATA_ENV]", this.teradataEnvironment);
	
		ResultSet rs = connection.execute(query);
		
		FRRHeatMetricConverter converter = new FRRHeatMetricConverter();
		List<FRRHeatMapMetrics> metrics = converter.getJson(rs);
		JSONArray jarray = converter.convertToJSON(metrics);
			
		this.jsonMapFRR.put(jsonDataKey, jarray);
		cachedFRR.setJsonMapFRR(this.jsonMapFRR);
		rs.close();
		connection.close();
	}
}
