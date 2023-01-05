package FlightAware.FlightApi.airport;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AirportCodeTest {

    @Test
    public void 코드_테스트() throws Exception{
        AirportCode airportCode = new AirportCode();
        airportCode.retrunAirPortCode();
    }

}