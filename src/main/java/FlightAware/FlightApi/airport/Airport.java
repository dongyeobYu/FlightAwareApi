package FlightAware.FlightApi.airport;

import java.util.Map;

public interface Airport {


    String ServiceKey = "=4Pn4j1aTm%2BcP8CsaG%2BKVRMr3XqYW0%2B1o7Tb5dkm4yKqVd7AhoR6y2M9yrb5TUfhmyUFy83CGVJBniq9bWq%2BA7A%3D%3D";

    String codeURL = "http://openapi.airport.co.kr/service/rest/AirportCodeList/getAirportCodeList";

    String domesticURL = "http://openapi.airport.co.kr/service/rest/FlightScheduleList/getDflightScheduleList";

    String AirPortURL = "http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList";

    Map<String, Object> returnAirPortList() throws Exception;

    String findDeptCodeByName(String name) throws Exception;

    Map<String, Object> retrunAirPortCode() throws Exception;

    Map<String, Object> DomesticStatus(String date, String deptCode, String ArrCode, String AirLine, String AirLineNum) throws Exception;

//    Map<String, Object> InternationStatus() throws Exception;


}
