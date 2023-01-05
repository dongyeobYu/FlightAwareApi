package FlightAware.FlightApi.controller;

import FlightAware.FlightApi.airport.AirportCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AirportCodeController {

    private final AirportCode airportCode;

    @GetMapping("/test")
    public Map<String, Object> codeList() throws Exception {
        return airportCode.retrunAirPortCode();
    }


}
