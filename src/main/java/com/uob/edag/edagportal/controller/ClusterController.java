
package com.uob.edag.edagportal.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.JsonPath;
import com.uob.edag.edagportal.dataload.CacheData;
import com.uob.edag.util.ClusterMetrics;
import com.uob.edag.util.PropertyLoadUtil;

/**
 * @author vencp9
 *
 */

@CrossOrigin(maxAge = 3600L)
@RequestMapping({ "/cluster" })
@RestController
public class ClusterController {
	private static final Logger logger = LogManager.getLogger(ClusterController.class);
    private final String  COMMAND_QUERY = PropertyLoadUtil.getQueryProperty("EDAG_COMMAND_QUERY");
    private final String PROCESS_BUILD_DIRECTORY = PropertyLoadUtil.getQueryProperty("PROCESS_BUILD_DIRECTORY");
	@RequestMapping({ "/memory" })
	public String getMemoryDetails() {
		String formatedMetrics = "";

		try {
			String command = "curl -k -X GET -u admin:Hadoop@123 -i https://apledatsg99.sg.uobnet.com:7183/api/v16/timeseries?query=select+cpu_percent_across_hosts+where+category+%3D+CLUSTER&contentType=application/json";
			
			 
			ProcessBuilder processBuilder = new ProcessBuilder(COMMAND_QUERY.split(" "));
			System.out.println(COMMAND_QUERY);
			//"/prodlib/EDA/edf/appl/lib/"
			processBuilder.directory(new File(PROCESS_BUILD_DIRECTORY));
			Process process = processBuilder.start();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			// Read the output from the command
			// System.out.println("Here is the standard output of the command:\n");
			String s1 = null;
			StringBuilder sb = new StringBuilder();
			while ((s1 = stdInput.readLine()) != null) {
				sb.append(s1);
			}
			System.out.println(sb.toString());
			// Read any errors from the attempted command
			while ((s1 = stdError.readLine()) != null) {
				System.out.println(s1);
			}
			int exitCode = process.exitValue();
			System.out.println(exitCode);
			formatedMetrics = getFormatedMetrix(sb.toString());
			System.out.println(formatedMetrics);

			// memory = CacheData.getCachedData("EDAG_EDW_CLUSTER_MEM");
		} catch (Exception e) {
			System.err.println(e);
		}
		if (null != formatedMetrics)
			return formatedMetrics.toString();
		return "{}";
	}

	private String getFormatedMetrix(String str) {
		
		 List<String> dataList = JsonPath.read(str, "$.items[0].timeSeries[0].data[*].aggregateStatistics");
		 System.out.println(dataList);	
		 String tokensString= null;
		 ClusterMetrics clusterMetrics = null;
		 List<ClusterMetrics> clusterMetricsList = new ArrayList<ClusterMetrics>();
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	     Date date = null;
	     DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 for(Object obj : dataList) {
		
			tokensString = obj.toString().substring(1,obj.toString().indexOf("crossEntityMetadata")-1);
			StringTokenizer tokens = new StringTokenizer(tokensString, ",");
			clusterMetrics = new ClusterMetrics();
			while (tokens.hasMoreElements()) {
				StringTokenizer keyList = new StringTokenizer(tokens.nextToken(), "=");
				
				if (keyList.hasMoreElements()) {
					String key = keyList.nextElement().toString();
					if(("sampleTime").equalsIgnoreCase(key.trim())) {
						String value = keyList.nextElement().toString();  
						String res = null;
						try {
							date = df.parse((value));
							 res = sdf.format(date);
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						clusterMetrics.setTime(res);
					}
					/*
					 * if(("count").equalsIgnoreCase(key.trim())) { String value =
					 * keyList.nextElement().toString();
					 * 
					 * clusterMetrics.setCount(Integer.valueOf(value.trim())); }
					 */
					if(("min").equalsIgnoreCase(key.trim())) {
						String value = keyList.nextElement().toString();
					
						clusterMetrics.setMin(Float.valueOf(value.trim()));;
					}
					if(("max").equalsIgnoreCase(key.trim())) {
						String value = keyList.nextElement().toString();
					
						clusterMetrics.setMax(Float.valueOf(value.trim()));
					}
					if(("mean").equalsIgnoreCase(key.trim())) {
						String value = keyList.nextElement().toString();
					
						clusterMetrics.setMean(Float.valueOf(value.trim()));;
					}
				
				
				}
				
			}
			
		 }
		
		return clusterMetricsList.toString();
	}

	@RequestMapping({ "/cpu" })
	public String getCPUDetails() {
		Object cpu = "";

		try {
			cpu = CacheData.getCachedData("EDAG_EDW_CLUSTER_CPU");
		} catch (Exception e) {
			System.err.println(e);
		}
		if (null != cpu)
			return cpu.toString();
		return null;
	}

	@RequestMapping({ "/io" })
	public String getIODetails() {
		Object clusterIO = "";

		try {
			clusterIO = CacheData.getCachedData("EDAG_EDW_CLUSTER_IO");
		} catch (Exception e) {
			System.err.println(e);
		}
		if (null != clusterIO)
			return clusterIO.toString();
		return null;
	}
}
