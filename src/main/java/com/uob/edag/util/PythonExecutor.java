/**
 * 
 */
package com.uob.edag.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author vencp9
 *
 */
public class PythonExecutor {
	static String execute() {

		// run the Unix "ps -ef" command
		// using the Runtime exec method:

		// curl -k -X GET -u admin:Hadoop@123 -i
		// 'https://"$HOST_NAME".sg.uobnet.com:7183/api/v16/timeseries?query=select+cpu_percent_across_hosts+where+category+%3D+CLUSTER&contentType=text/csv&from="$FRM_DT"T"$HOUR"%3A"$MIN"%3A00.000Z&to="$TO_DT"T"$HOUR"%3A"$MIN"%3A09.062Z'

		// String x ="curl -X GET -u admin:Hadoop@123 -i
		// https://apledatsg99.sg.uobnet.com:7183/api/v16/timeseries?query=select+cpu_percent_across_hosts+where+category+%3D+CLUSTER&contentType=application/json";
		// curl -v -k -X GET -u admin:Hadoop@123
		// 'https://apledatsg99.sg.uobnet.com:7183/api/v16/timeseries?query=select+cpu_percent_across_hosts+where+category+%3D+CLUSTER&contentType=application/json'
		String x = "curl -k -X GET -u admin:Hadoop@123 -i 'https://apledatsg99.sg.uobnet.com:7183/api/v16/timeseries?query=select+cpu_percent_across_hosts+where+category+%3D+CLUSTER&contentType=application/json'";

		try {
			System.out.println(" Iam here $$$ ");
			// System.out.println(x);

			String command = "curl -k -X GET -u admin:Hadoop@123 -i https://apledatsg99.sg.uobnet.com:7183/api/v16/timeseries?query=select+cpu_percent_across_hosts+where+category+%3D+CLUSTER&contentType=application/json";
			ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
			System.out.println(command);
			processBuilder.directory(new File("/prodlib/EDA/edf/appl/lib/"));
			Process process = processBuilder.start();

			InputStream inputStream = process.getInputStream();

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			
			// Read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s1 = null;
			StringBuilder sb = new StringBuilder();

			while ((s1 = stdInput.readLine()) != null) {
				sb.append(s1);
			}
			List<String> srr = getValuesForGivenKey(sb.toString(), "data");
			
		
			System.out.println(srr.toString());
		
			// Read any errors from the attempted command

			while ((s1 = stdError.readLine()) != null) {
				System.out.println(s1);
			}

			int exitCode = process.exitValue();

			/*
			 * Process proc = Runtime.getRuntime().exec(x); System.out.println("Oh great ");
			 * 
			 * BufferedReader stdInput = new BufferedReader(new
			 * InputStreamReader(proc.getInputStream()));
			 * 
			 * BufferedReader stdError = new BufferedReader(new
			 * InputStreamReader(proc.getErrorStream()));
			 * 
			 * // Read the output from the command
			 * System.out.println("Here is the standard output of the command:\n"); String
			 * s1 = null; while ((s1 = stdInput.readLine()) != null) {
			 * System.out.println(s1); }
			 * 
			 * // Read any errors from the attempted command
			 * System.out.println("Here is the standard error of the command (if any):\n");
			 * while ((s1 = stdError.readLine()) != null) { System.out.println(s1); }
			 * System.out.println("<nonno>");
			 */

		} catch (Exception e) {
			System.err.println(e);

		}

		return null;
	}

	public static void main(String[] args) {
		//execute();
		String sb = "[{\r\n" + 
				"   \"items\":[\r\n" + 
				"      {\r\n" + 
				"         \"timeSeries\":[\r\n" + 
				"            {\r\n" + 
				"               \"metadata\":{\r\n" + 
				"                  \"metricName\":\"cpu_percent_across_hosts\",\r\n" + 
				"                  \"entityName\":\"UOBCDHDEV\",\r\n" + 
				"                  \"startTime\":\"2019-09-12T02:50:18.388Z\",\r\n" + 
				"                  \"endTime\":\"2019-09-12T02:55:18.388Z\",\r\n" + 
				"                  \"attributes\":{\r\n" + 
				"                     \"active\":\"true\",\r\n" + 
				"                     \"clusterDisplayName\":\"UOBCDHDEV\",\r\n" + 
				"                     \"category\":\"CLUSTER\",\r\n" + 
				"                     \"version\":\"CDH 5.14.4\",\r\n" + 
				"                     \"entityName\":\"1\",\r\n" + 
				"                     \"clusterName\":\"UOBCDHDEV\"\r\n" + 
				"                  },\r\n" + 
				"                  \"unitNumerators\":[\r\n" + 
				"                     \"percent\"\r\n" + 
				"                  ],\r\n" + 
				"                  \"unitDenominators\":[\r\n" + 
				"\r\n" + 
				"                  ],\r\n" + 
				"                  \"expression\":\"SELECT cpu_percent_across_hosts WHERE entityName = \\\"1\\\" AND category = CLUSTER\",\r\n" + 
				"                  \"metricCollectionFrequencyMs\":60000,\r\n" + 
				"                  \"rollupUsed\":\"RAW\"\r\n" + 
				"               },\r\n" + 
				"               \"data\":[\r\n" + 
				"                  {\r\n" + 
				"                     \"timestamp\":\"2019-09-12T02:51:04.406Z\",\r\n" + 
				"                     \"value\":2.3,\r\n" + 
				"                     \"type\":\"SAMPLE\",\r\n" + 
				"                     \"aggregateStatistics\":{\r\n" + 
				"                        \"sampleTime\":\"2019-09-12T02:50:21.000Z\",\r\n" + 
				"                        \"sampleValue\":10.7,\r\n" + 
				"                        \"count\":8,\r\n" + 
				"                        \"min\":0.0,\r\n" + 
				"                        \"minTime\":\"2019-09-12T02:50:24.000Z\",\r\n" + 
				"                        \"max\":10.7,\r\n" + 
				"                        \"maxTime\":\"2019-09-12T02:50:21.000Z\",\r\n" + 
				"                        \"mean\":2.3,\r\n" + 
				"                        \"stdDev\":3.492849839314596,\r\n" + 
				"                        \"crossEntityMetadata\":{\r\n" + 
				"                           \"maxEntityDisplayName\":\"apledatsg93.sg.uobnet.com\",\r\n" + 
				"                           \"maxEntityName\":\"05a70b8b-1672-42ee-96b3-8e6699776f73\",\r\n" + 
				"                           \"minEntityDisplayName\":\"apledatsg100.sg.uobnet.com\",\r\n" + 
				"                           \"minEntityName\":\"3bf5dfe2-369f-487a-af42-3fccd1f9651e\",\r\n" + 
				"                           \"numEntities\":8.0\r\n" + 
				"                        }\r\n" + 
				"                     }\r\n" + 
				"                  },\r\n" + 
				"                  {\r\n" + 
				"                     \"timestamp\":\"2019-09-12T02:52:04.407Z\",\r\n" + 
				"                     \"value\":2.75,\r\n" + 
				"                     \"type\":\"SAMPLE\",\r\n" + 
				"                     \"aggregateStatistics\":{\r\n" + 
				"                        \"sampleTime\":\"2019-09-12T02:51:21.000Z\",\r\n" + 
				"                        \"sampleValue\":10.9,\r\n" + 
				"                        \"count\":8,\r\n" + 
				"                        \"min\":0.0,\r\n" + 
				"                        \"minTime\":\"2019-09-12T02:51:24.000Z\",\r\n" + 
				"                        \"max\":10.9,\r\n" + 
				"                        \"maxTime\":\"2019-09-12T02:51:21.000Z\",\r\n" + 
				"                        \"mean\":2.75,\r\n" + 
				"                        \"stdDev\":3.854496446637726,\r\n" + 
				"                        \"crossEntityMetadata\":{\r\n" + 
				"                           \"maxEntityDisplayName\":\"apledatsg93.sg.uobnet.com\",\r\n" + 
				"                           \"maxEntityName\":\"05a70b8b-1672-42ee-96b3-8e6699776f73\",\r\n" + 
				"                           \"minEntityDisplayName\":\"apledatsg100.sg.uobnet.com\",\r\n" + 
				"                           \"minEntityName\":\"3bf5dfe2-369f-487a-af42-3fccd1f9651e\",\r\n" + 
				"                           \"numEntities\":8.0\r\n" + 
				"                        }\r\n" + 
				"                     }\r\n" + 
				"                  },\r\n" + 
				"                  {\r\n" + 
				"                     \"timestamp\":\"2019-09-12T02:53:04.407Z\",\r\n" + 
				"                     \"value\":2.7375,\r\n" + 
				"                     \"type\":\"SAMPLE\",\r\n" + 
				"                     \"aggregateStatistics\":{\r\n" + 
				"                        \"sampleTime\":\"2019-09-12T02:52:21.000Z\",\r\n" + 
				"                        \"sampleValue\":10.6,\r\n" + 
				"                        \"count\":8,\r\n" + 
				"                        \"min\":0.1,\r\n" + 
				"                        \"minTime\":\"2019-09-12T02:52:24.000Z\",\r\n" + 
				"                        \"max\":10.6,\r\n" + 
				"                        \"maxTime\":\"2019-09-12T02:52:21.000Z\",\r\n" + 
				"                        \"mean\":2.7375,\r\n" + 
				"                        \"stdDev\":3.7224943634227694,\r\n" + 
				"                        \"crossEntityMetadata\":{\r\n" + 
				"                           \"maxEntityDisplayName\":\"apledatsg93.sg.uobnet.com\",\r\n" + 
				"                           \"maxEntityName\":\"05a70b8b-1672-42ee-96b3-8e6699776f73\",\r\n" + 
				"                           \"minEntityDisplayName\":\"apledatsg100.sg.uobnet.com\",\r\n" + 
				"                           \"minEntityName\":\"3bf5dfe2-369f-487a-af42-3fccd1f9651e\",\r\n" + 
				"                           \"numEntities\":8.0\r\n" + 
				"                        }\r\n" + 
				"                     }\r\n" + 
				"                  },\r\n" + 
				"                  {\r\n" + 
				"                     \"timestamp\":\"2019-09-12T02:54:04.407Z\",\r\n" + 
				"                     \"value\":2.4875000000000003,\r\n" + 
				"                     \"type\":\"SAMPLE\",\r\n" + 
				"                     \"aggregateStatistics\":{\r\n" + 
				"                        \"sampleTime\":\"2019-09-12T02:53:21.000Z\",\r\n" + 
				"                        \"sampleValue\":10.8,\r\n" + 
				"                        \"count\":8,\r\n" + 
				"                        \"min\":0.0,\r\n" + 
				"                        \"minTime\":\"2019-09-12T02:53:24.000Z\",\r\n" + 
				"                        \"max\":10.8,\r\n" + 
				"                        \"maxTime\":\"2019-09-12T02:53:21.000Z\",\r\n" + 
				"                        \"mean\":2.4875000000000003,\r\n" + 
				"                        \"stdDev\":3.6148256231406637,\r\n" + 
				"                        \"crossEntityMetadata\":{\r\n" + 
				"                           \"maxEntityDisplayName\":\"apledatsg93.sg.uobnet.com\",\r\n" + 
				"                           \"maxEntityName\":\"05a70b8b-1672-42ee-96b3-8e6699776f73\",\r\n" + 
				"                           \"minEntityDisplayName\":\"apledatsg100.sg.uobnet.com\",\r\n" + 
				"                           \"minEntityName\":\"3bf5dfe2-369f-487a-af42-3fccd1f9651e\",\r\n" + 
				"                           \"numEntities\":8.0\r\n" + 
				"                        }\r\n" + 
				"                     }\r\n" + 
				"                  },\r\n" + 
				"                  {\r\n" + 
				"                     \"timestamp\":\"2019-09-12T02:55:04.407Z\",\r\n" + 
				"                     \"value\":2.6624999999999996,\r\n" + 
				"                     \"type\":\"SAMPLE\",\r\n" + 
				"                     \"aggregateStatistics\":{\r\n" + 
				"                        \"sampleTime\":\"2019-09-12T02:54:21.000Z\",\r\n" + 
				"                        \"sampleValue\":10.9,\r\n" + 
				"                        \"count\":8,\r\n" + 
				"                        \"min\":0.0,\r\n" + 
				"                        \"minTime\":\"2019-09-12T02:54:24.000Z\",\r\n" + 
				"                        \"max\":10.9,\r\n" + 
				"                        \"maxTime\":\"2019-09-12T02:54:21.000Z\",\r\n" + 
				"                        \"mean\":2.6624999999999996,\r\n" + 
				"                        \"stdDev\":3.6154183713645094,\r\n" + 
				"                        \"crossEntityMetadata\":{\r\n" + 
				"                           \"maxEntityDisplayName\":\"apledatsg93.sg.uobnet.com\",\r\n" + 
				"                           \"maxEntityName\":\"05a70b8b-1672-42ee-96b3-8e6699776f73\",\r\n" + 
				"                           \"minEntityDisplayName\":\"apledatsg100.sg.uobnet.com\",\r\n" + 
				"                           \"minEntityName\":\"3bf5dfe2-369f-487a-af42-3fccd1f9651e\",\r\n" + 
				"                           \"numEntities\":8.0\r\n" + 
				"                        }\r\n" + 
				"                     }\r\n" + 
				"                  }\r\n" + 
				"               ]\r\n" + 
				"            }\r\n" + 
				"         ],\r\n" + 
				"         \"warnings\":[\r\n" + 
				"\r\n" + 
				"         ],\r\n" + 
				"         \"timeSeriesQuery\":\"select cpu_percent_across_hosts where category = CLUSTER\"\r\n" + 
				"      }\r\n" + 
				"   ]\r\n" + 
				"}]";
		List<String> srr = getValuesForGivenKey(sb.toString(), "timestamp");
		
		
		System.out.println(srr.toString());
	}
	
	
	public static List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
	    JSONArray jsonArray = new JSONArray(jsonArrayStr);
	    System.out.println("jsonArray.length()  :: " + jsonArray.length() );
	    return IntStream.range(0, jsonArray.length())
	      .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
	      .collect(Collectors.toList());
	}
}
