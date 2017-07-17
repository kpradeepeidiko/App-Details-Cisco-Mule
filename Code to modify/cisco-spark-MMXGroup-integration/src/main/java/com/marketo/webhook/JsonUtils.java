package com.marketo.webhook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

	public static String jsonString2MapKey(String jsonString) {
		String keyIs = "";
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			Iterator<?> keyset = jsonObject.keys();

			ArrayList<Object> al = new ArrayList<Object>();

			while (keyset.hasNext()) {
				String key = (String) keyset.next();
				al.add(new KeyOrder(jsonString.indexOf(key), key));
			}

			Collections.sort(al, new KeyOrder());
			Iterator<Object> itr2 = al.iterator();
			while (itr2.hasNext()) {
				KeyOrder key = (KeyOrder) itr2.next();

				keyIs = keyIs + "," + key.keys;
			}
			keyIs = keyIs.replaceFirst(",", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return keyIs;

	}

	public static String jsonString2MapRuntimeValues(String jsonString, String keysValue) throws JSONException {

		String accountFieldResponse = "";
		try {

			Map<String, Object> keys = new LinkedHashMap<String, Object>();

			JSONObject jsonObject = new JSONObject(jsonString);

			String[] strLevel = keysValue.split("\\,");
			for (int i = 0; i < strLevel.length; i++) {
				String levl = strLevel[i];

				if (strLevel[i].equals("Campaign Id") || strLevel[i].equals("Campaign Name")
						|| strLevel[i].equals("Lead Id") || strLevel[i].equals("Updated At")
						|| strLevel[i].equals("Created Date")) {
					jsonObject.remove(strLevel[i]);
				}

				if (jsonObject.isNull(levl) || jsonObject.get(levl).equals("")) {

					keys.remove(strLevel[i]);
				}

				else {

					keys.put(strLevel[i], jsonObject.get(levl));
				}

			}

			String runtimevalues = keys.toString();
			String bullet = "\u2022";
			runtimevalues = runtimevalues.replaceAll("[{}]", "");

			runtimevalues = runtimevalues.replaceAll("=", " : ");
			accountFieldResponse = runtimevalues.replaceAll(", ", "\n\n" + bullet + " ");
			accountFieldResponse = bullet + " " + accountFieldResponse;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountFieldResponse;

	}

}