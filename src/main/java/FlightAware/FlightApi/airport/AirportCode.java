package FlightAware.FlightApi.airport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.XMLFormatter;

@Service
public class AirportCode implements Airport {

    public Map<String, Object> retrunAirPortCode() throws Exception {

        /** URL */      // 1
        StringBuilder urlBuilder =
                new StringBuilder("http://openapi.airport.co.kr/service/rest/AirportCodeList/getAirportCodeList");

        // 2
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + ServiceKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("15", "UTF-8"));

        // 3
        URL url = new URL(urlBuilder.toString());

        // 4
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        System.out.println("connection.getResponseCode() = " + connection.getResponseCode());   // 응답 코드 출력

        // 5
        BufferedReader rd;

        if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else{
            rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

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
        StringBuilder urlBuilder = new StringBuilder("http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + ServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("schLineType","UTF-8") + "=" + URLEncoder.encode("D", "UTF-8")); /*국내 / 국제*/
        urlBuilder.append("&" + URLEncoder.encode("schIOType","UTF-8") + "=" + URLEncoder.encode("I", "UTF-8")); /*도착 / 출발*/
        urlBuilder.append("&" + URLEncoder.encode("schAirCode","UTF-8") + "=" + URLEncoder.encode("GMP", "UTF-8")); /*공항코드*/
        urlBuilder.append("&" + URLEncoder.encode("schStTime","UTF-8") + "=" + URLEncoder.encode("0600", "UTF-8")); /*예정시간*/
        urlBuilder.append("&" + URLEncoder.encode("schEdTime","UTF-8") + "=" + URLEncoder.encode("1800", "UTF-8")); /*변경시간*/


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        JSONObject jsonObject = XML.toJSONObject(sb.toString());
        String xmlJSONObject = jsonObject.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map;

        map = objectMapper.readValue(xmlJSONObject, new TypeReference<>() {});
        Map<String, Object> response = (Map<String, Object>) map.get("response");
        Map<String, Object> body = (Map<String, Object>) response.get("body");


        return body;
    }
}
