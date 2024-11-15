package congestion.calculator.controller;

import congestion.calculator.dto.TaxRulesRequest;
import congestion.calculator.factory.VehicleFactory;
import congestion.calculator.model.Vehicle;
import congestion.calculator.service.CongestionTaxCalculatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class CongestionTaxCalculatorController {

    private final CongestionTaxCalculatorService congestionTaxCalculatorService;

    @Autowired
    public CongestionTaxCalculatorController(CongestionTaxCalculatorService congestionTaxCalculatorService) {
        this.congestionTaxCalculatorService = congestionTaxCalculatorService;
    }

    @GetMapping("/tax-congestion-calculator/{vehicleModel}")
    public ResponseEntity<Integer> CalculateCongestionTax(@PathVariable String vehicleModel,
                                                          @Valid @RequestBody TaxRulesRequest request) {
        Vehicle vehicle;
        try {
            vehicle = VehicleFactory.getVehicle(vehicleModel);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        int tax;
        try {
            tax = congestionTaxCalculatorService.getTax(vehicle, request.getDatesTime());
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "an error happened...");
        }

        return new ResponseEntity<>(tax, HttpStatus.OK);
    }
}
