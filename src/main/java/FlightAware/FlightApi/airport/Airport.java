package FlightAware.FlightApi.airport;

import java.util.Map;

public interface Airport {


    String ServiceKey = "=4Pn4j1aTm%2BcP8CsaG%2BKVRMr3XqYW0%2B1o7Tb5dkm4yKqVd7AhoR6y2M9yrb5TUfhmyUFy83CGVJBniq9bWq%2BA7A%3D%3D";

    Map<String, Object> returnAirPortList() throws Exception;

    Map<String, Object> retrunAirPortCode() throws Exception;

    Map<String, Object> DomesticStatus(String date, String deptCode, String ArrCode, String AirLine, String AirLineNum) throws Exception;


    Map<String, Object> InternationStatus() throws Exception;

}
