package edu.dit.simulatorsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dit.simulatorsystem.dto.FactorialRequest;
import edu.dit.simulatorsystem.dto.FactorialResponse;
import edu.dit.simulatorsystem.service.utilities.FactorialVerifiable;

@RestController
@RequestMapping("/api/factorial")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class FactorialController {

    @Autowired
    private FactorialVerifiable factorialService;

    @PostMapping("/calculate")
    public ResponseEntity<FactorialResponse> getFactorial(@RequestBody FactorialRequest request) {
       if (request.num1() > 20) {
            return ResponseEntity.badRequest().build();
        }

        FactorialResponse response = factorialService.calculateFactorial(request);
        return ResponseEntity.ok(response);
    }
}
