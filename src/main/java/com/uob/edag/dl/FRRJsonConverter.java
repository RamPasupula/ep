package com.uob.edag.dl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class FRRJsonConverter {

	private List<FRRCountry> countryList = new ArrayList<FRRCountry>();
//  List<SourceSystem> sourceList = new ArrayList();

	public List<FRRCountry> getJson(ResultSet resultSet) throws Exception {
		try {
			FRRCountry country = null;

			//SourceSystem sourceSystem = null;

			while (resultSet.next()) {
				country = new FRRCountry();
				
				String countryName = resultSet.getString("COUNTRYCODE");
				country.setName(countryName);
				String loadingstatus = resultSet.getString("loadingstatus");
				Integer tot = resultSet.getInt("tot");
				// String source = resultSet.getString("Source System");
				if (countryList.contains(country)) {

					int j = this.countryList.indexOf(country);
					country = (FRRCountry) this.countryList.get(j);
					if ("Completed".equalsIgnoreCase(loadingstatus)) {
						country.setCompleted(tot);
					} else if ("Failed".equalsIgnoreCase(loadingstatus)) {
						country.setFailed(tot);

					} else if ("Running".equalsIgnoreCase(loadingstatus)) {
						country.setRunning(tot);
					} else {
						country.setPending(tot);
					}

				} else {
					if ("Completed".equalsIgnoreCase(loadingstatus)) {
						country.setCompleted(tot);
					} else if ("Failed".equalsIgnoreCase(loadingstatus)) {
						country.setFailed(tot);

					} else if ("Running".equalsIgnoreCase(loadingstatus)) {
						country.setRunning(tot);
					} else {
						country.setPending(tot);
					}
					countryList.add(country);

				}

			}

		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}

		return this.countryList;
	}

	public JSONArray convertToJSON(List<FRRCountry> countryList) throws Exception {
		JSONArray jsonArray = null;

		try {
			jsonArray = new JSONArray(countryList);
		} catch (Exception e) {

			System.err.println(e);
			throw e;
		}

		return jsonArray;
	}
}
