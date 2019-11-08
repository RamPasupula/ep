package com.uob.edag.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
public class FRRMetricConverter {


	private List<FRRMetrics> metricsList = new ArrayList<FRRMetrics>();

	public List<FRRMetrics> getJson(ResultSet resultSet) throws Exception {
		
		try {
			FRRMetrics metric = null;

			// SourceSystem sourceSystem = null;

		
			
			
			
			while (resultSet.next()) {
				

				int hour = resultSet.getInt("HOUR");
				
				String date = resultSet.getString("BIZ_DT");
				String catg = resultSet.getString("CATEGORY");
				int per = resultSet.getInt("percentage");
			
			
				
				/*
				 * if(! flag) { metric= new FRRMetrics(); metric.setHour(0); if(0 != hour) {
				 * 
				 * 
				 * metricsList.add(0,metric); }
				 * 
				 * flag = true; }
				 */
				
			
				metric = new FRRMetrics();
				metric.setDate(date);
				metric.setHour(hour);
				
				if (metricsList.contains(metric)) {

					int j = this.metricsList.lastIndexOf(metric);
					metric = (FRRMetrics) this.metricsList.get(j);
					
					int tt= metric.getHour();
					int foundationTot = metric.getFoundation();;
					int financeTot = metric.getFinance();
					int productsTot =metric.getProducts();
					int riskTot =metric.getRisk();
					int counter = tt;
					  while(++ tt < hour) {
					  
					  metric = new FRRMetrics(date, tt ,foundationTot ,financeTot ,productsTot , riskTot); 
					  metricsList.add(metric);
					  
					  }
					 
					if ("foundation".equalsIgnoreCase(catg)) {
						
						metric.setFoundation(per);
						metric = new FRRMetrics(date, hour,per ,financeTot ,productsTot, riskTot);
					} else if ("finance".equalsIgnoreCase(catg)) {
						
						metric.setFinance(per);
						
						metric = new FRRMetrics(date, hour,foundationTot, per ,productsTot, riskTot);
					} else if ("products".equalsIgnoreCase(catg)) {
						
						metric.setProducts(per);
						metric = new FRRMetrics(date, hour,foundationTot ,financeTot ,per, riskTot);
					} else if ("risk".equalsIgnoreCase(catg)) {
						metric.setRisk(per);
						metric = new FRRMetrics(date, hour,foundationTot ,financeTot ,productsTot,per);
					}
				
					
					if(counter != hour) {
						
						 metricsList.add(metric);
					}
					
				} else {
					
					
					
					
					
					
					if ("foundation".equalsIgnoreCase(catg)) {
						
						metric.setFoundation(per);
						
					} else if ("finance".equalsIgnoreCase(catg)) {
						
						metric.setFinance(per);
						
					} else if ("products".equalsIgnoreCase(catg)) {
						
						metric.setProducts(per);
						
					} else if ("risk".equalsIgnoreCase(catg)) {
					
						metric.setRisk(per);
						
					}
					
					metric = new FRRMetrics(date, hour,metric.getFoundation() ,metric.getFinance() ,metric.getProducts() , metric.getRisk());
					
				
					metricsList.add(metric);
				}
			

			}
			/*
			 * while(counter <= MAX) {
			 * 
			 * metric = new FRRMetrics(counter ++ ,FRRMetrics.foundationTot
			 * ,FRRMetrics.financeTot ,FRRMetrics.productsTot , FRRMetrics.riskTot);
			 * metricsList.add(metric); }
			 */
			
			
			/*
			 * metric2= new FRRMetrics(); metric2.setHour("24"); if(!
			 * metricsList.contains(metric2)) { metric2 = new FRRMetrics("24",0,0 ,0, 0);
			 * metricsList.add(metricsList.size(),metric2);
			 * 
			 * }
			 */

		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}

		return this.metricsList;
	}

	public JSONArray convertToJSON(List<FRRMetrics> metrics) throws Exception {

	/*	StringBuilder sb = new StringBuilder("[[\"Hour\",\"Foundation\",\"Finance\",\"Products\",\"Risk\"],");

		for (FRRMetrics metric : metrics) {
			sb.append(metric.toString()).append(",");
		}
		sb.replace(sb.length()-1, sb.length(), "");
		sb.append("]");
    System.out.println(sb.toString());
    
    */
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(metrics);
		
		} catch (Exception e) {

			System.err.println(e);
			
		}

		return jsonArray;

	}
}
