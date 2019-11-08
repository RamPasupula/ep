package com.uob.edag.dl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class FRRDROP2Converter {

	private List<FRRDROP2Country> countryList = new ArrayList<FRRDROP2Country>();

	public List<FRRDROP2Country> getJson(ResultSet resultSet) throws Exception {
		try {
			FRRDROP2Country country = null;

			// SourceSystem sourceSystem = null;

			while (resultSet.next()) {
				country = new FRRDROP2Country();

				String countryName = resultSet.getString("Country_Code");
				String tier = resultSet.getString("Tier");
				int pending = resultSet.getInt("Pending");
				int completed = resultSet.getInt("Completed");
				int total = resultSet.getInt("Total");
				country.setName(countryName);
				country.setTier(tier);
				if (countryList.contains(country)) {

					int j = this.countryList.indexOf(country);
					country = (FRRDROP2Country) this.countryList.get(j);
					if ("T14".equalsIgnoreCase(tier)) {
						country.setT14Completed(completed);
						country.setT14Pending(pending);
						country.setT14Total(total);
					} else if ("T15".equalsIgnoreCase(tier)) {
						country.setT15Completed(completed);
						country.setT15Pending(pending);
						country.setT15Total(total);
					} else if ("T20".equalsIgnoreCase(tier)) {
						country.setT20Completed(completed);
						country.setT20Pending(pending);
						country.setT20Total(total);
					} else if ("T30".equalsIgnoreCase(tier)) {
						country.setT30Completed(completed);
						country.setT30Pending(pending);
						country.setT30Total(total);
					}

				} else {
					if ("T14".equalsIgnoreCase(tier)) {
						country.setT14Completed(completed);
						country.setT14Pending(pending);
						country.setT14Total(total);
					} else if ("T15".equalsIgnoreCase(tier)) {
						country.setT15Completed(completed);
						country.setT15Pending(pending);
						country.setT15Total(total);
					} else if ("T20".equalsIgnoreCase(tier)) {
						country.setT20Completed(completed);
						country.setT20Pending(pending);
						country.setT20Total(total);
					} else if ("T30".equalsIgnoreCase(tier)) {
						country.setT30Completed(completed);
						country.setT30Pending(pending);
						country.setT30Total(total);
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

	public JSONArray convertToJSON(List<FRRDROP2Country> countryList) throws Exception {
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
