package com.uob.edag.dl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

public class DLJsonConverter {

	private static final Logger logger = LogManager.getLogger(DLJsonConverter.class);

	Map<String, Object> regionMap = null;
	Map<String, Object> countryMap = null;
	Map<String, Object> sourceMap = null;
	List<Region> regionList = new ArrayList<Region>();
	List<Country> countryList = new ArrayList<Country>();
	List<SourceSystem> sourceList = new ArrayList<SourceSystem>();

	public List<Region> getJson(ResultSet resultSet) throws Exception {
		try {
			Country country = null;

			SourceSystem sourceSystem = null;
			FileSystem file = new FileSystem();
			Long fileSize = Long.valueOf(0L);
			String arrivalTimeString = null;
			this.regionMap = new HashMap<String, Object>();
			this.countryMap = new HashMap<String, Object>();
			this.sourceMap = new HashMap<String, Object>();
			while (resultSet.next()) {

				Region region = null;

				String regionName = resultSet.getString("REGION");
				String countryName = resultSet.getString("COUNTRY_CODE");
				String source = resultSet.getString("Source_System");
				String fileName = resultSet.getString("FILE_NAME");
				String date = "";
			//	String bizdt = resultSet.getString("BIZ_DT"); 
				arrivalTimeString = resultSet.getString("SRC_FILE_ARRIVAL_TIME");
				Long recordsCount = resultSet.getLong("Records Count");
				String fileS = resultSet.getString("File_Size");
				String tableName = resultSet.getString("HIVE_TABLE_NAME");
				Long rowInput = resultSet.getLong("ROWS_INPUT");
				String loadingStatus ="F";
				
				if( recordsCount.equals(rowInput) ) {
					loadingStatus= "S";
					date = resultSet.getString("LOAD_TIME");	
				}
				
			//	String failReason =resultSet.getString("FAILURE_REASON");
				if (!StringUtils.isEmpty(fileS)) {
					fileSize = Long.valueOf(fileS);
				}

				file = new FileSystem();
				file.setDate(date);
				file.setName(tableName + " {" + recordsCount + "}");
				file.setArrivalTime(arrivalTimeString);
				file.setRecordsCount(recordsCount.toString());
				file.setFileSize(fileSize);
				file.setFileName(fileName);
				file.setRowInput(rowInput);
				file.setLoadingStatus(loadingStatus);
			//	file.setFailReason(failReason);

				if (! "S".equalsIgnoreCase(loadingStatus)) {

					file.setIsCompleted("orange");

				}
				if (this.regionMap.containsKey(regionName)) {
					region = new Region();
					region.setName(regionName);
					int l = this.regionList.indexOf(region);
					region = regionList.get(l);

					country = new Country();
					country.setName(countryName);
					sourceSystem = new SourceSystem();
					sourceSystem.setName(source);

					this.countryList = (List) this.regionMap.get(regionName);

					if (this.countryMap.containsKey(countryName)) {

						int j = this.countryList.indexOf(country);
						country = (Country) this.countryList.get(j);
						this.sourceList = (List) this.countryMap.get(countryName);
						if (this.sourceList.contains(sourceSystem)) {

							int k = this.sourceList.indexOf(sourceSystem);
							sourceSystem = (SourceSystem) this.sourceList.get(k);
							if (! "S".equalsIgnoreCase(loadingStatus)) {

								file.setIsCompleted("orange");
								country.setIsCompleted("orange");
								sourceSystem.setIsCompleted("orange");

							}

							if (!"orange".equalsIgnoreCase(sourceSystem.getIsCompleted())) {
								sourceSystem.setIsCompleted("green");
								country.setIsCompleted("green");
							}
							sourceSystem.getChildren().add(file);
						} else {

							sourceSystem.getChildren().add(file);
							if (! "S".equalsIgnoreCase(loadingStatus)) {
								sourceSystem.setIsCompleted("orange");

							}

							if (!"orange".equalsIgnoreCase(sourceSystem.getIsCompleted())) {
								sourceSystem.setIsCompleted("green");

							}
							this.sourceList.add(sourceSystem);
						}

						country.getChildren().addAll(this.sourceList);
						this.countryMap.put(countryName, this.sourceList);
					} else {

						this.sourceList = new ArrayList();
						sourceSystem.getChildren().add(file);
						
						if (! "S".equalsIgnoreCase(loadingStatus)) {
							sourceSystem.setIsCompleted("orange");
							country.setIsCompleted("orange");
						}
						this.sourceList.add(sourceSystem);
						country.getChildren().addAll(this.sourceList);
						
						if (!"orange".equalsIgnoreCase(sourceSystem.getIsCompleted())) {
							sourceSystem.setIsCompleted("green");
							country.setIsCompleted("green");
						}
						this.countryList.add(country);
						this.countryMap.put(countryName, this.sourceList);
					}

					region.getChildren().addAll(this.countryList);

					if (! "S".equalsIgnoreCase(loadingStatus)) {
						region.setIsCompleted("orange");
					}
					if (!"orange".equalsIgnoreCase(region.getIsCompleted())) {
						region.setIsCompleted("green");
					}

					this.regionList.set(l, region);

					this.regionMap.put(regionName, this.countryList);

					continue;
				}
				region = new Region();
				region.setName(regionName);

				this.countryList = new ArrayList();
				country = new Country();
				country.setName(countryName);
				sourceSystem = new SourceSystem();
				sourceSystem.setName(source);
				if (! "S".equalsIgnoreCase(loadingStatus)) {
					region.setIsCompleted("orange");
					file.setIsCompleted("orange");
					country.setIsCompleted("orange");
					sourceSystem.setIsCompleted("orange");

				}
				if (!"orange".equalsIgnoreCase(region.getIsCompleted())) {
					region.setIsCompleted("green");
					country.setIsCompleted("green");
					sourceSystem.setIsCompleted("green");
				}
				this.sourceList = new ArrayList();
				sourceSystem.getChildren().add(file);
				this.sourceList.add(sourceSystem);
				country.getChildren().addAll(this.sourceList);
				this.countryList.add(country);
				this.countryMap.put(countryName, this.sourceList);
				region.getChildren().addAll(this.countryList);
				this.regionMap.put(regionName, this.countryList);
                this.regionList.add(region);

			}

		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}

		return this.regionList;
	}

	public JSONArray convertToJSON(List<Region> regionList) throws Exception {
		JSONArray jsonArray = null;

		try {
			jsonArray = new JSONArray(regionList);
		
		} catch (Exception e) {

			System.err.println(e);
			throw e;
		}
		
		return jsonArray;
	}
}
