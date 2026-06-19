package edu.dit.simulatorsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import edu.dit.simulatorsystem.dto.FibonacciRequest;
import edu.dit.simulatorsystem.dto.FibonacciResponse;
import edu.dit.simulatorsystem.service.FibonacciServiceImpl;

@RestController
@RequestMapping("/api/fibonacci")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class FibonacciController {

    @Autowired
    private FibonacciServiceImpl fibonacciService;

    @PostMapping("/check")
    public ResponseEntity<FibonacciResponse> checkNumber(@RequestBody FibonacciRequest request) {
        double inputNumber = request.number();
        boolean result = fibonacciService.isFibonacci(inputNumber);
        FibonacciResponse response = new FibonacciResponse(inputNumber, result);
        return ResponseEntity.ok(response);

    }
    
}
