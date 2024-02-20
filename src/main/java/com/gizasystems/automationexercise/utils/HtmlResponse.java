package com.gizasystems.automationexercise.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JsonOrgJsonProvider;
import com.shaft.api.RestActions;
import com.shaft.tools.io.ReportManager;
import com.shaft.tools.io.internal.FailureReporter;
import io.restassured.path.json.exception.JsonPathException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HtmlResponse {

    /**
     * This implementation is to handle the *PathNotFoundException* that happens when we have json object but inside html or xml tags, so it's not represented as json object
     * Note: Will delete this class after we implement this logic to SHAFT and have a new release
     *
     * @param jsonPath
     * @return
     */
    public static String getResponseJSONValue(String jsonPath) {
        String searchPool = "";
        String jsonResponse = RestActions.getLastResponse().asPrettyString();
        String jsonObject = jsonResponse.substring(jsonResponse.indexOf("{"), jsonResponse.lastIndexOf("}") + 1);
        Configuration confOrgJsonProvider = Configuration.builder().jsonProvider(new JsonOrgJsonProvider()).build();

        try {
            if (jsonPath.contains("?")) {
                JSONArray jsonValue = JsonPath.compile(jsonPath).read(new JSONObject(jsonObject), confOrgJsonProvider);
                searchPool = String.valueOf(jsonValue.get(0));
            } else {
                Object jsonValue = JsonPath.compile(jsonPath).read(new JSONObject(jsonObject), confOrgJsonProvider);
                searchPool = String.valueOf(jsonValue);
            }
        } catch (JSONException ex) {
            FailureReporter.fail(ex.getMessage());

        } catch (ClassCastException var4) {
            FailureReporter.fail("Incorrect jsonPath \"" + jsonPath + "\"");
        } catch (IllegalArgumentException | JsonPathException var5) {
            FailureReporter.fail("Failed to parse the JSON document");
        }

        if (searchPool != null) {
            return searchPool;
        } else {
            ReportManager.logDiscrete("Either actual value is \"null\" or couldn't find anything that matches with the desired jsonPath \"" + jsonPath + "\"");
            return null;
        }
    }
}
