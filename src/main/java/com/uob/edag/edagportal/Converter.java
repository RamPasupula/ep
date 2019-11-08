package com.uob.edag.edagportal;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Hashtable;

import org.json.JSONArray;

public class Converter {
	public JSONArray convertToJSON(ResultSet resultSet) throws Exception {
		JSONArray jsonArray = new JSONArray();
		try {
			ResultSetMetaData mdSet = resultSet.getMetaData();
			//System.out.println(mdSet.getColumnCount());
			//int i = 0; 
			while (resultSet.next()) {
				Hashtable<String, String> table = new Hashtable<String, String>();
				//System.out.println(i++);
				for (int index = 0; index < mdSet.getColumnCount(); index++) {
					
					String columnName = mdSet.getColumnName(index + 1).toLowerCase();
					// to fix metadata load
					if(columnName.contains("'"))
						columnName = columnName.replaceAll("'","");
					table.put(columnName, resultSet.getString(index + 1)==null?"":resultSet.getString(index + 1));
				}
				jsonArray.put(table);
			}
		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}

		return jsonArray;
	}
	
	
	

}
