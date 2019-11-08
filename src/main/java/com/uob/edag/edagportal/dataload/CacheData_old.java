package com.uob.edag.edagportal.dataload;

import java.sql.ResultSet;
import java.util.HashMap;

import org.json.JSONArray;

import com.uob.edag.access.ImpalaConnectionAccess;
import com.uob.edag.access.OracleConnection;
import com.uob.edag.edagportal.Converter;
import com.uob.edag.util.Constants;

public class CacheData_old {

	private static CacheData_old cachedData = null;
	public HashMap<String, Object> jsonMap = null;
	
	private CacheData_old() {
		jsonMap = new HashMap<String, Object>();
	}
	 
	public static CacheData_old getInstance() {
		
		if (cachedData == null) {
			cachedData = new CacheData_old();
		}
		
		return cachedData;
	}
	
	public HashMap<String, Object> getJsonMap() {
		return jsonMap;
	}
	
	public void setJsonMap(HashMap<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}


	public static Object getCachedData(String jsonDataKey) {
		CacheData_old cachedData = CacheData_old.getInstance();
		return cachedData.getJsonMap().get(jsonDataKey); 
	}

	public void loadJsonMap() throws Exception {
		try {
			//read the queries from properties file and fire the query and populate the json data in the map
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			String jsonDataKey = Constants.EDAG_SOURCE_SYSTEMS;
			String query = "select SRC_SYS_CD as SourceCode, SRC_SYS_NM SourceName from EDAG_SOURCE_SYSTEM";
					
			OracleConnection connection = new OracleConnection();
			ResultSet rs = connection.execute(query);
			
			Converter converter = new Converter();
			JSONArray jarray = converter.convertToJSON(rs);
			
			System.out.println("EDAG_SOURCE_SYSTEMS jarray.length() " + jarray.length());
		
			jsonMap.put(jsonDataKey, jarray);
			
			rs.close();
			connection.close();
			
			connection = new OracleConnection();
			jsonDataKey = Constants.EDAG_FILE_DETAILS;
			query = "select a.PROC_ID ProcID, B.SRC_SYS_CD SourceCode, a.FILE_NM FileName, B.PROC_DESC Description, B.PROC_FREQ_CD Frequency, a.FILE_TYPE_CD FileType, a.CTRL_INFO_CD ControlInfo, C.TGT_TBL_NM HiveTableName from EDAG_FILE_DETAIL a, EDAG_PROCESS_MASTER B, EDAG_LOAD_PROCESS C where B.PROC_ID = a.PROC_ID and C.PROC_ID = B.PROC_ID and a.proc_id not like '%_H0%' order by SourceCode, FileName";
					
			rs = connection.execute(query);

			converter = new Converter(); 
			jarray = converter.convertToJSON(rs);
			
			jsonMap.put(jsonDataKey, jarray);
	
			rs.close();
			connection.close();
			
		
			/*
			connection = new OracleConnection();
			jsonDataKey = Constants.EDAG_SERVERS;
			query = "SELECT ENV AS ENV, \"TYPE\" AS TYPE1, HOST_NAME AS HOSTNAME, IP_ADDRESS AS IPADDRESS, MEMORY AS MEMORY, CPU AS CPU, \"STORAGE\" AS STORAGE1, OS AS OS, SOFTWARE_INSTALLED AS SOFTWAREINSTALLED, SOFTWARE_VERSION AS SOFTWAREVERSION, SPECIAL_HANDLING AS SPECIALHANDLING, REMARKS AS REMARKS FROM EDAG_SERVERS";
					
			rs = connection.execute(query);

			converter = new Converter(); 
			jarray = converter.convertToJSON(rs);
			
			jsonMap.put(jsonDataKey, jarray);
	
			rs.close();
			connection.close();
			*/
			

			  /**
			//BATCH DL JOBS(T11)
			connection = new OracleConnection();
			jsonDataKey = Constants.EDAG_DL_JOBS;
			//query = "SELECT JOB_STATUS, NODE_ID, TRUNC((END_DATE-TRUNC(SYSDATE, 'dd'))*86400/600) AS INTERVAL_NO, COUNT(1) AS JOB_COUNT\r\n" + 
			//		"FROM GDW.TGDW_RUNHISTORY\r\n" + 
			//		"WHERE (JOB_NAME LIKE 'EDA%I' OR JOB_NAME LIKE 'EDA%L') AND END_DATE BETWEEN TRUNC(SYSDATE, 'dd') AND SYSDATE\r\n" + 
			//		"GROUP BY JOB_STATUS, NODE_ID, TRUNC((END_DATE-TRUNC(SYSDATE, 'dd'))*86400/600)";
			query = "SELECT JOB_NAME, NODE_ID, JOB_STATUS, START_DATE, END_DATE, ELAPSED_TIME, APP_CODE, LOAD_CTRY, ODATE\r\n" + 
					"FROM GDW.TGDW_RUNHISTORY\r\n" + 
					"WHERE (JOB_NAME LIKE 'EDA%I' OR JOB_NAME LIKE 'EDA%L') AND END_DATE BETWEEN TRUNC(SYSDATE, 'dd') AND SYSDATE";
					
			rs = connection.execute(query, "jdbc:oracle:thin:@gdwtsg98:1521:gdws", "uncpgdw", "uncpgdw");

			converter = new Converter(); 
			jarray = converter.convertToJSON(rs);
			
			jsonMap.put(jsonDataKey, jarray);
	
			rs.close();
			connection.close();
          
			//BATCH EDW JOBS(T14-T30)
			TeradataConnection teradataConnection = new TeradataConnection();
			jsonDataKey = Constants.EDAG_EDW_JOBS;
			query = "SELECT CASE WHEN LEFT(PROCESS_NAME,3)='LD_' THEN 'T14' ELSE LEFT(PROCESS_NAME,3) END AS TIER, PROCESS_NAME, SITE_ID, SRC_SYS_CD, FREQ, BIZ_DT, JOB_START_DATETIME, JOB_END_DATETIME, PROCESS_FLG\r\n" + 
					"FROM U03_EDW_UTL_V.UOB_OPERATIONAL_JOB_STATUS\r\n" + 
					"WHERE JOB_END_DATETIME BETWEEN CURRENT_DATE AND CURRENT_TIMESTAMP;";
					
			rs = teradataConnection.execute("jdbc:teradata://apledausg15/USER=U01_EDW_DEP_FRR,PASSWORD=DeployCodeUat2018", query);

			converter = new Converter(); 
			jarray = converter.convertToJSON(rs);
			
			jsonMap.put(jsonDataKey, jarray);
	
			rs.close();
			teradataConnection.close();
			**/
			
			
			// -loadStatus
			connection = new OracleConnection();
			jsonDataKey = Constants.EDAG_DL_T1_LOADSTAUS;
			//query = "SELECT * FROM (SELECT BIZ_DT, TO_CHAR(TO_DATE(LOAD_TIME, 'DD-MON-YYYY HH24:MI:SS'),'HH24:MI') AS LOAD_TIME, COUNTRY_CODE FROM V_BUSINESS_USER_REPORT WHERE LOADING_STATUS='S' AND BIZ_DT >= SYSDATE - 60) PIVOT (MAX(LOAD_TIME) FOR COUNTRY_CODE IN ('GD','SG','CN','ID','MY','TH','AU','BN','CA','GB','HK','IN','JP','KR','MM','M2','PH','TW','U1','U2','VN','M1')) ORDER BY BIZ_DT DESC";
			query = "SELECT * FROM (SELECT to_char(BIZ_DT,'YYYY-MM-DD') AS BIZ_DT, TO_CHAR(TO_DATE(LOAD_TIME, 'DD-MON-YYYY HH24:MI:SS'),'HH24:MI') AS LOAD_TIME, COUNTRY_CODE FROM V_BUSINESS_USER_REPORT WHERE LOADING_STATUS='S' AND BIZ_DT >= SYSDATE - 60) PIVOT (MAX(LOAD_TIME) FOR COUNTRY_CODE IN ('GD','SG','CN','ID','MY','TH','AU','BN','CA','GB','HK','IN','JP','KR','MM','M2','PH','TW','U1','U2','VN','M1')) ORDER BY BIZ_DT DESC";		
			rs = connection.execute(query);
			
			converter = new Converter(); 
			
			jarray = converter.convertToJSON(rs);
			jsonMap.put(jsonDataKey, jarray);
	
			rs.close();
			connection.close();

		

		/**

			// Added for Hadoop cluster utilization
			ImpalaConnectionAccess conn = new ImpalaConnectionAccess();
			jsonDataKey = Constants.EDAG_EDW_CLUSTER_MEM;

			//connection = new OracleConnection();

			query = "SELECT   AVG(CAST(value AS  decimal(9,2))) AS value , rundate FROM DEFAULT.CLUSTER_REPORT_MEM WHERE to_date(rundate) >= date_sub(from_timestamp(now(), 'yyyy-MM-dd'),14) AND to_date(rundate) <= from_timestamp(now(), 'yyyy-MM-dd') GROUP BY rundate ORDER BY rundate";

			rs = conn.execute(query);

			converter = new Converter();
			jarray = converter.convertToJSON(rs);

			jsonMap.put(jsonDataKey, jarray);
			

			//rs.close();
			//conn.close();

			jsonDataKey = Constants.EDAG_EDW_CLUSTER_CPU;
		
			query = "SELECT  AVG(CAST(value AS  decimal(9,2))) AS value , rundate FROM DEFAULT.CLUSTER_REPORT_CPU WHERE to_date(rundate) >= date_sub(from_timestamp(now(), 'yyyy-MM-dd'),14) AND to_date(rundate) <= from_timestamp(now(), 'yyyy-MM-dd') GROUP BY rundate ORDER BY rundate";

			rs = conn.execute(query);

			converter = new Converter();
			jarray = converter.convertToJSON(rs);

			jsonMap.put(jsonDataKey, jarray);

			
			jsonDataKey = Constants.EDAG_EDW_CLUSTER_IO;
			
			query = "SELECT AVG(CAST(value AS  decimal(9,2))) AS value, rundate FROM DEFAULT.CLUSTER_REPORT_IO WHERE to_date(rundate) >= date_sub(from_timestamp(now(), 'yyyy-MM-dd'),14) AND to_date(rundate) <= from_timestamp(now(), 'yyyy-MM-dd') GROUP BY rundate ORDER BY rundate";

			rs = conn.execute(query);

			converter = new Converter();
			jarray = converter.convertToJSON(rs);

			jsonMap.put(jsonDataKey, jarray);
			conn.release();
			**/

			
			
			// Customer-Account REport
			
			jsonDataKey = Constants.EDAG_EDW_CUASTOMER_REPORT;


			//HiveDBConnection hiveConn = new HiveDBConnection();
			ImpalaConnectionAccess conn = new ImpalaConnectionAccess();
			
			//query = "SELECT AVG(cust_count) value,  curr_biz_dt as date, cust_type as type FROM S01_OPS_REP.EDA_CUSTOMER_REPORT_D Where cust_type in('Individual','Corporate') GROUP BY curr_biz_dt, cust_type  ORDER BY curr_biz_dt limit 10";
          //  query = "SELECT AVG(cust_count) value,  curr_biz_dt as date, cust_type as type FROM S01_OPS_REP.EDA_CUSTOMER_REPORT_D WHERE cust_type IN('Individual','Corporate') AND  to_date(curr_biz_dt) >= date_sub(CURRENT_DATE,240) GROUP BY curr_biz_dt, cust_type  ORDER BY curr_biz_dt";
			query = "SELECT AVG(CAST(cust_count AS  decimal(9,0))) AS value,  curr_biz_dt as bizdate, cust_type as type, site_id FROM S01_OPS_REP.EDA_CUSTOMER_REPORT_D WHERE cust_type IN('Individual','Corporate') AND  to_date(curr_biz_dt) >= date_sub(from_timestamp(now(), 'yyyy-MM-dd'),240) GROUP BY curr_biz_dt, cust_type,site_id  ORDER BY curr_biz_dt";
			rs = conn.execute(query);

			//converter = new Converter();
			//AccountJSONconverter accountJSONconverter = new AccountJSONconverter();

			//List<Account> list = accountJSONconverter.getAccount(rs);
			jarray = converter.convertToJSON(rs);
			// jarray = accountJSONconverter.convertToJSON(list);
			jsonMap.put(jsonDataKey, jarray);
			
			jsonDataKey = Constants.EDAG_EDW_CUASTOMER_REPORT_DATA;
			//SELECT AVG(acct_count) value,  curr_biz_dt  as date, acct_type  as type,site_id  FROM S01_OPS_REP.EDA_ACCOUNT_REPORT_D   
			//WHERE to_date(curr_biz_dt) >= date_sub(CURRENT_DATE,400) GROUP BY curr_biz_dt, acct_type ,site_id  ORDER BY curr_biz_dt;
			// SELECT AVG(acct_count) value,  curr_biz_dt  as bizdate, acct_type  as type, site_id  FROM S01_OPS_REP.EDA_ACCOUNT_REPORT_D   
			//	WHERE to_date(curr_biz_dt) >= date_sub(CURRENT_DATE,400) GROUP BY curr_biz_dt, acct_type ,site_id  ORDER BY curr_biz_dt;
			//query = "SELECT AVG(CAST(cust_count AS  decimal(9,0))) AS value,  curr_biz_dt as bizdate, cust_type as type, site_id FROM S01_OPS_REP.EDA_ACCOUNT_REPORT_D WHERE cust_type IN('Individual','Corporate') AND  to_date(curr_biz_dt) >= date_sub(from_timestamp(now(), 'yyyy-MM-dd'),240) GROUP BY curr_biz_dt, cust_type,site_id  ORDER BY curr_biz_dt";
			
			rs = conn.execute(query);
			jarray = converter.convertToJSON(rs);
				jsonMap.put(jsonDataKey, jarray);
					
				conn.release();
			
			
		

			CacheData_old cacheData = CacheData_old.getInstance();
			cacheData.setJsonMap(jsonMap);
		
		//	conn.release();
		 

		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}
		

	}
		
}
