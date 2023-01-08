package FlightAware.FlightApi.airport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Service
public class AirportCode implements Airport {

    public Map<String, Object> retrunAirPortCode() throws Exception {

        /** URL */      // 1
        StringBuilder urlBuilder =
                new StringBuilder(codeURL);

        // 2
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8"));

        // 3
        URL url = new URL(urlBuilder.toString());

        // 4
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        setConnection(connection);

        System.out.println("connection.getResponseCode() = " + connection.getResponseCode());   // 응답 코드 출력

        // 5s
        BufferedReader rd = BufferedReaderGetStream(connection);

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        // 6
        while( (line = rd.readLine()) != null){
            stringBuilder.append(line);
        }

        // 7
        rd.close();
        connection.disconnect();

        // 8
        JSONObject jsonObject = XML.toJSONObject(stringBuilder.toString());
        String xmlJSONObject = jsonObject.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        // 9
        map = objectMapper.readValue(xmlJSONObject, new TypeReference<>() {});

        // 10
        Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");

        Map<String, Object> resultMap = new HashMap<>();

        // 11
        resultMap.put("body", body);

        Map<String, Object> items = (Map<String, Object>) body.get("items");
        List<Map<String, Object>> item = (List<Map<String,java.lang.Object>>) items.get("item");

//        int index;
//
//        for(int i=0; i<item.size(); i++){
//            if(item.get(i).get("cityKor")){
//               index = i;
//            }
//        }

        return items;
    }

    public Map<String, Object> returnAirPortList() throws Exception {
        StringBuilder urlBuilder = new StringBuilder(AirPortURL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + ServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("schLineType","UTF-8") + "=" + URLEncoder.encode("D", "UTF-8")); /*국내 / 국제*/
        urlBuilder.append("&" + URLEncoder.encode("schIOType","UTF-8") + "=" + URLEncoder.encode("I", "UTF-8")); /*도착 / 출발*/
        urlBuilder.append("&" + URLEncoder.encode("schAirCode","UTF-8") + "=" + URLEncoder.encode("GMP", "UTF-8")); /*공항코드*/
        urlBuilder.append("&" + URLEncoder.encode("schStTime","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*예정시간*/
        urlBuilder.append("&" + URLEncoder.encode("schEdTime","UTF-8") + "=" + URLEncoder.encode("1800", "UTF-8")); /*변경시간*/


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        setConnection(connection);

        BufferedReader rd = BufferedReaderGetStream(connection);

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        connection.disconnect();

        JSONObject jsonObject = XML.toJSONObject(sb.toString());
        String xmlJSONObject = jsonObject.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map;

        map = objectMapper.readValue(xmlJSONObject, new TypeReference<>() {});
        Map<String, Object> response = (Map<String, Object>) map.get("response");
        Map<String, Object> body = (Map<String, Object>) response.get("body");
        Map<String, Object> item = (Map<String, Object>) body.get("item");


        return body;
    }

    public Map<String, Object> DomesticStatus(String date, String deptCode, String ArrCode, String AirLine, String AirLineNum) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(domesticURL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + ServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("schDate","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*검색일자*/
        urlBuilder.append("&" + URLEncoder.encode("schDeptCityCode","UTF-8") + "=" + URLEncoder.encode(deptCode, "UTF-8")); /*도착 도시 코드*/
        urlBuilder.append("&" + URLEncoder.encode("schArrvCityCode","UTF-8") + "=" + URLEncoder.encode(ArrCode, "UTF-8")); /*출항 도시 코드*/
        urlBuilder.append("&" + URLEncoder.encode("schAirLine","UTF-8") + "=" + URLEncoder.encode(AirLine, "UTF-8")); /*항공편 코드*/
        urlBuilder.append("&" + URLEncoder.encode("schFlightNum","UTF-8") + "=" + URLEncoder.encode(AirLineNum, "UTF-8")); /*항공편 넘버*/


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        setConnection(connection);

        BufferedReader rd = BufferedReaderGetStream(connection);

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        connection.disconnect();

        JSONObject jsonObject = XML.toJSONObject(sb.toString());
        String xmlJSONObject = jsonObject.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map;

        map = objectMapper.readValue(xmlJSONObject, new TypeReference<>() {});
        Map<String, Object> response = (Map<String, Object>) map.get("response");
        Map<String, Object> body = (Map<String, Object>) response.get("body");
        Map<String, Object> item = (Map<String, Object>) body.get("items");

        return item;
    }

//    public Map<String, Object> InternationStatus() throws Exception {
//        StringBuilder urlBuilder = new StringBuilder("http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList");
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + ServiceKey); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("schDate","UTF-8") + "=" + URLEncoder.encode("20121010", "UTF-8")); /*검색일자*/
//        urlBuilder.append("&" + URLEncoder.encode("schDeptCityCode","UTF-8") + "=" + URLEncoder.encode("GMP", "UTF-8")); /*도착 도시 코드*/
//        urlBuilder.append("&" + URLEncoder.encode("schArrvCityCode","UTF-8") + "=" + URLEncoder.encode("PUS", "UTF-8")); /*출항 도시 코드*/
//        urlBuilder.append("&" + URLEncoder.encode("schAirLine","UTF-8") + "=" + URLEncoder.encode("AB", "UTF-8")); /*항공편 코드*/
//        urlBuilder.append("&" + URLEncoder.encode("schFlightNum","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*항공편 넘버*/
//
//
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        setConnection(conn)
//
//        BufferedReader rd = BufferedReaderGetStream(connection);
//
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//
//        rd.close();
//        conn.disconnect();
//
//        JSONObject jsonObject = XML.toJSONObject(sb.toString());
//        String xmlJSONObject = jsonObject.toString();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> map;
//
//        map = objectMapper.readValue(xmlJSONObject, new TypeReference<>() {});
//        Map<String, Object> response = (Map<String, Object>) map.get("response");
//        Map<String, Object> body = (Map<String, Object>) response.get("body");
//
//
//        return body;
//    }

    public String findDeptCodeByName(String cityKor) throws Exception{
        StringBuilder urlBuilder =
                new StringBuilder(codeURL);

        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + ServiceKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8"));

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        setConnection(connection);

        BufferedReader rd = BufferedReaderGetStream(connection);

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while( (line = rd.readLine()) != null){
            stringBuilder.append(line);
        }

        rd.close();
        connection.disconnect();

        JSONObject jsonObject = XML.toJSONObject(stringBuilder.toString());
        String xmlJSONObject = jsonObject.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        map = objectMapper.readValue(xmlJSONObject, new TypeReference<>() {});

        Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
        Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("body", body);

        Map<String, Object> items = (Map<String, Object>) body.get("items");
        List<Map<String, Object>> item = (List<Map<String,java.lang.Object>>) items.get("item");

        return getCityCodeFromcityKor(item, cityKor);
    }

    public static void setConnection(HttpURLConnection connection) throws Exception {
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
    }

    public String getCityCodeFromcityKor(List<Map<String, Object>> item, String cityKor){
        for(Map<String, Object> ite : item) {
            if((ite.get("cityKor").toString()).indexOf((cityKor)) >= 0){
                System.out.println("ite = " + ite);
                return (String) ite.get("cityCode");
            }
        }
        return null;
    }

    public BufferedReader BufferedReaderGetStream(HttpURLConnection connection) throws Exception{
        BufferedReader rd;
        if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else{
            rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        return rd;
    }
}
