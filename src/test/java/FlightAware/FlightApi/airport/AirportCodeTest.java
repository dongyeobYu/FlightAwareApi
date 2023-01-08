package FlightAware.FlightApi.airport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class AirportCodeTest {

    @Autowired
    private Airport airport;



    @Test
    public void 코드_테스트() throws Exception{
        AirportCode airportCode = new AirportCode();
        airportCode.retrunAirPortCode();
    }

    @Test
    public void 리스트_테스트() throws Exception{
        System.out.println("airport.returnAirPortList() = " + airport.returnAirPortList());
    }

    @Test
    public void 검색_테스트() throws Exception{
        System.out.println(airport.findDeptCodeByName("도모데도보"));
    }


}