package com.beecow.utils;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import static com.beecow.utils.Helper.addLog;
public class APIUtil {
    public static JSONObject sendPost(String url,  Object requestBody ) {
        try {
            // Create request connection
            URL obj = null;
            try {
                obj = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) obj.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // add reuqest header
            con.setRequestMethod("POST");
            //con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
            //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            // if (requestBody instanceof String) {
            //     con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            // } else {
            con.setRequestProperty("Content-Type", "application/json");
            //}
            //con.setRequestProperty("X-CSRF-Token", csrfToken);
            //con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestProperty("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIsIFJPTEVfQURNSU4iLCJleHAiOjE1MjA5MTc3MDF9.FTXcQTaDSOAIxKcYrCCAnGIuPMT3torAIoCmxAl8ELsPso1vyT_tz1xPsYW5OJlSoXP1cT4RZ5BsN-kNC4zT6w");
            //con.setRequestProperty("Cookie", cookie);
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(requestBody.toString());
            wr.flush();
            wr.close();
            addLog("\nSending 'POST' request to URL : " + url);
            addLog("\nPost parameters : " + requestBody);
            // Get response code
            int responseCode = con.getResponseCode();
            addLog("\nResponse Code : " + responseCode);
            // Get response body
            BufferedReader in;
            if (responseCode != 200 && responseCode != 201) {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            addLog(response.toString());
            // Return Json object
            JSONParser parser = new JSONParser();
            Object jsonObject = null;
            try {
                jsonObject = parser.parse(response.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(jsonObject instanceof Long){
                jsonObject = parser.parse("{\"id\": " + jsonObject.toString() + "}");
            }
            if(jsonObject instanceof Boolean){
                return null;
            }
            return (JSONObject) jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static JSONObject sendGet(String url) {
        try {
            // Create request connection
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Add request header
            con.setRequestMethod("GET");
            //con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
            //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type", "application/json");
            //con.setRequestProperty("X-CSRF-Token", csrfToken);
            //con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestProperty("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIsIFJPTEVfQURNSU4iLCJleHAiOjE1MjA5MTc3MDF9.FTXcQTaDSOAIxKcYrCCAnGIuPMT3torAIoCmxAl8ELsPso1vyT_tz1xPsYW5OJlSoXP1cT4RZ5BsN-kNC4zT6w");
            //con.setRequestProperty("Cookie", cookie);
            // Send get request
            addLog("\nSending 'GET' request to URL : " + url);
            // Get response code
            int responseCode = con.getResponseCode();
            addLog("\nResponse Code : " + responseCode);
            // Get response body
            BufferedReader in;
            if (responseCode != 200 && responseCode != 201) {
                in = new BufferedReader(new InputStreamReader(
                        con.getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            addLog(response.toString());
            // Return Json object
            JSONParser parser = new JSONParser();
            Object jsonObject = parser.parse(response.toString());
            if(jsonObject instanceof Long){
                jsonObject = parser.parse("{\"id\": " + jsonObject.toString() + "}");
            }
            if(jsonObject instanceof Boolean){
                return null;
            }
            return (JSONObject) jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sendDelete(String url, String id, String type) throws IOException, ParseException {
        // Create request connection
        // String type: TEXT, JOB, BUYING
        url = url + id +"?type="+ type;
        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Add request header
        con.setRequestMethod("DELETE");
        //con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
        //con.setRequestProperty("X-CSRF-Token", csrfToken);
        //con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIsIFJPTEVfQURNSU4iLCJleHAiOjE1MjA5MTc3MDF9.FTXcQTaDSOAIxKcYrCCAnGIuPMT3torAIoCmxAl8ELsPso1vyT_tz1xPsYW5OJlSoXP1cT4RZ5BsN-kNC4zT6w");
        //con.setRequestProperty("Cookie", cookie);
        // Send get request
        addLog("\nSending 'DELETE' request to URL : " + url);
        // Get response code
        int responseCode = con.getResponseCode();
        addLog("\nResponse Code : " + responseCode);
        // Get response body
        BufferedReader in;
        if (responseCode != 200 && responseCode != 201) {
            in = new BufferedReader(new InputStreamReader(
                    con.getErrorStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        addLog(response.toString());
        System.out.print("\nresponse " + response.toString());
        // Return Json object
//        JSONParser parser = new JSONParser();
//        Object jsonObject = parser.parse(response.toString());
//        JSONObject jsonObj = null;
//        JSONArray jsonArr = null;
//        jsonObj = objectToJSONObject(jsonObject);
//        jsonArr = objectToJSONArray(jsonObject);
//        if (jsonObj != null) {
//            //process JSONObject
//            return response.toString();
//        } else if (jsonArr != null) {
//            //process JSONArray
//            jsonObject = parser.parse("{\"id\": " + jsonObject.toString() + "}");
//            System.out.print("\nJSONOBJECT " + jsonObject);
//            return response.toString();
//        }
        return response.toString();
    }

    public static String sendGetString(String url) throws IOException, ParseException {
        // Create request connection
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Add request header
        con.setRequestMethod("GET");
        //con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
        //con.setRequestProperty("X-CSRF-Token", csrfToken);
        //con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIsIFJPTEVfQURNSU4iLCJleHAiOjE1MjA5MTc3MDF9.FTXcQTaDSOAIxKcYrCCAnGIuPMT3torAIoCmxAl8ELsPso1vyT_tz1xPsYW5OJlSoXP1cT4RZ5BsN-kNC4zT6w");
        //con.setRequestProperty("Cookie", cookie);
        // Send get request
        addLog("\nSending 'GET' request to URL : " + url);
        // Get response code
        int responseCode = con.getResponseCode();
        addLog("\nResponse Code : " + responseCode);
        // Get response body
        BufferedReader in;
        if (responseCode != 200 && responseCode != 201) {
            in = new BufferedReader(new InputStreamReader(
                    con.getErrorStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        addLog(response.toString());
        System.out.print("\nresponse " + response.toString());
        // Return Json object
        JSONParser parser = new JSONParser();
        Object jsonObject = parser.parse(response.toString());
        JSONObject jsonObj = null;
        JSONArray jsonArr = null;
        jsonObj = objectToJSONObject(jsonObject);
        jsonArr = objectToJSONArray(jsonObject);
        if (jsonObj != null) {
            //process JSONObject
            return response.toString();
        } else if (jsonArr != null) {
            //process JSONArray
            jsonObject = parser.parse("{\"id\": " + jsonObject.toString() + "}");
            System.out.print("\nJSONOBJECT " + jsonObject);
            return response.toString();
        }
        return response.toString();
    }

    public static JSONObject objectToJSONObject(Object object){
        Object json = null;
        JSONObject jsonObject = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONObject) {
            jsonObject = (JSONObject) json;
        }
        return jsonObject;
    }
    public static JSONArray objectToJSONArray(Object object){
        Object json = null;
        JSONArray jsonArray = null;
        try {
            json = new JSONTokener(object.toString()).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json instanceof JSONArray) {
            jsonArray = (JSONArray) json;
        }
        return jsonArray;
    }


    public static JSONObject parseDataToJsonObject(String data){
        Object obj;
        JSONParser parser = new JSONParser();
        try {
            if (data.indexOf(".json") != -1) {
                obj = parser.parse(new FileReader(System
                        .getProperty("user.dir") + "/json/" + data));
            } else {
                obj = parser.parse(data);
            }
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String replaceStringValue(String string, String key, String value){
        int begin_index = string.indexOf(key);
        int end_index = string.indexOf("&", begin_index);
        if(value.contains("@")){
            value = value.replace("@", "%40");
        }
        return string.replace(string.substring(begin_index + key.length() + 1, end_index), value);
    }
    public static JSONObject sendHTTPRequest(String method, String url, String requestBody) {
        try {
            // Create request connection
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // add reuqest header
            con.setRequestMethod(method);
            //con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:28.0) Gecko/20100101 Firefox/28.0");
            //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            //con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //con.setRequestProperty("X-CSRF-Token", csrfToken);
            // con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX1VTRVIsIFJPTEVfQURNSU4iLCJleHAiOjE1MjA5MTc3MDF9.FTXcQTaDSOAIxKcYrCCAnGIuPMT3torAIoCmxAl8ELsPso1vyT_tz1xPsYW5OJlSoXP1cT4RZ5BsN-kNC4zT6w");
            //con.setRequestProperty("Cookie", cookie);
            // Send HTTP request
            addLog("\nSending '" + method + "' request to URL : " + url);
            if (!method.equals("GET") && !method.equals("DELETE")) {
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(
                        con.getOutputStream());
                wr.writeBytes(requestBody);
                wr.flush();
                wr.close();
                addLog("\nPost parameters : " + requestBody);
            }
            // Get response code
            int responseCode = con.getResponseCode();
            addLog("\nResponse Code : " + responseCode);
            // Get response body
            BufferedReader in;
            if (responseCode != 200 && responseCode != 201) {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            addLog(response.toString());
            // Return Json object
            JSONParser parser = new JSONParser();
            Object jsonObject = parser.parse(response.toString());
            if(jsonObject instanceof Long){
                jsonObject = parser.parse("{\"id\": " + jsonObject.toString() + "}");
            }
            if(jsonObject instanceof Boolean){
                return null;
            }
            return (JSONObject) jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}