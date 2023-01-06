package FlightAware.FlightApi.controller;

import FlightAware.FlightApi.airport.Airport;
import FlightAware.FlightApi.airport.AirportCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AirportCodeController {

    private final Airport airport;

    @GetMapping("/code")
    public Map<String, Object> ccode() throws Exception {
        return airport.retrunAirPortCode();
    }

    @GetMapping("/list")
    public Map<String, Object> clist() throws Exception{
        return airport.returnAirPortList();
    }

    @GetMapping("/domestic")
    public Map<String, Object> cDomestic() throws Exception{
        return airport.DomesticStatus("20230116", "CJU", "GMP", "OZ", "8900");
    }

    @GetMapping("/Internation")
    public Map<String, Object> cInternation() throws Exception{
        return airport.InternationStatus();
    }


}
