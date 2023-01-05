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
public class AirportCode {

    private final String ServiceKey = "4Pn4j1aTm%2BcP8CsaG%2BKVRMr3XqYW0%2B1o7Tb5dkm4yKqVd7AhoR6y2M9yrb5TUfhmyUFy83CGVJBniq9bWq%2BA7A%3D%3D";

    public Map<String, Object> retrunAirPortCode() throws Exception {

        /** URL */      // 1
        StringBuilder urlBuilder =
                new StringBuilder("http://openapi.airport.co.kr/service/rest/AirportCodeList/getAirportCodeList");

        // 2
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + ServiceKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));

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

        System.out.println("stringBuilder = " + stringBuilder);
        System.out.println("stringBuilderClassName = " + stringBuilder.getClass().getName());

        // 8
        JSONObject jsonObject = XML.toJSONObject(stringBuilder.toString());
        String xmlJSONObject = jsonObject.toString();
        System.out.println("\"============================\" = " + "============================");

        System.out.println("xmlJSONObject = " + xmlJSONObject);

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

        return resultMap;
    }
}
