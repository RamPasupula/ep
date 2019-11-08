package com.uob.edag.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class FRRHeatMetricConverter {

	private static final int MAX = 24;

	
	private List<FRRHeatMapMetrics> metricList = new ArrayList<FRRHeatMapMetrics>();



	public List<FRRHeatMapMetrics>  getJson(ResultSet resultSet) throws Exception {
	
		try {
			
			FRRHeatMapMetrics heatMetric = null;
			
			while (resultSet.next()) {
				String dt = resultSet.getString("BIZ_DT");
				String catg = resultSet.getString("CATEGORY");
				String subjArea = resultSet.getString("SUBJECT_AREA");
				int hour = resultSet.getInt("HOUR");
				int per = resultSet.getInt("percentage");

			
				heatMetric = new FRRHeatMapMetrics();
				heatMetric.setDate(dt);
				heatMetric.setCategory(catg);
				heatMetric.setSubjectArea(subjArea);
				heatMetric.setHour(hour);
				if (this.metricList.contains(heatMetric)) {

					int l =   this.metricList.lastIndexOf(heatMetric);
					heatMetric = new FRRHeatMapMetrics();
					heatMetric = metricList.get(l);
					  String d = heatMetric.getDate();
			        int c = heatMetric.getHour();
			        int p = heatMetric.getPer();
			       
					if( c != 0) {
			        	c++;
			        }
					while ( c < hour) { 
						heatMetric = new  FRRHeatMapMetrics(d, c++,  p,  catg,  subjArea);
						
						metricList.add(heatMetric);
				    }
					heatMetric = new  FRRHeatMapMetrics(dt, hour,  per,  catg,  subjArea);
					
					metricList.add(heatMetric);

					

				} else {
					int c = 0;
				  while ( c < hour) { 
						heatMetric = new  FRRHeatMapMetrics(dt, c++,  0,  catg,  subjArea);
						
						metricList.add(heatMetric);
					   }
					heatMetric = new  FRRHeatMapMetrics(dt, hour,  per,  catg,  subjArea);
					
					metricList.add(heatMetric);
					
					
				}

			}
		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}

		return metricList;
	}

	public JSONArray convertToJSON(List<FRRHeatMapMetrics>  metrics) throws Exception {

		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(metrics);

	
		} catch (Exception e) {

			System.err.println(e);

		}

		return jsonArray;

	}
}
