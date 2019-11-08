package com.uob.edag.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class AccountJSONconverter {
	List<Account> accountList = new ArrayList<Account>();

	public List<Account> getAccount(ResultSet resultSet) throws Exception {
		try {

			while (resultSet.next()) {

				Account account = null;

				int value = resultSet.getInt("value");
				String type = resultSet.getString("type");
				String date = resultSet.getString("bizdate");
				account = new Account();
				account.setDate(date);
				if (accountList.contains(account)) {
					int i = accountList.indexOf(account);
					account = accountList.get(i);
					account.setType(type);
					account.setValueC(value);
				} else {
					account.setType(type);
					account.setValueI(value);
					accountList.add(account);
				}

			}
		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}

		return accountList;
	}

	public JSONArray convertToJSON(List<Account> accountList) throws Exception {
		JSONArray jsonArray = null;
		try {

			jsonArray = new JSONArray(accountList);

		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}

		return jsonArray;
	}

	public static void main(String[] args) {
		ArrayList<Account> mylist = new ArrayList<Account>();
		Account acc = new Account();
		acc.setDate("d1");
		Account acc1 = new Account();

		acc1.setDate("d1");
		Account acc2 = new Account();
		acc2.setDate("d1");
		mylist.add(acc);

		JSONArray jsArray2 = new JSONArray(mylist);

		String str = "The State of the water = 'ICE'";
		str = str.replaceAll("'", "");

		System.out.println(str);
	}

}
