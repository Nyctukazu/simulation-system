package edu.dit.simulatorsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dit.simulatorsystem.dto.MultiplicationRequest;
import edu.dit.simulatorsystem.dto.MultiplicationResponse;
import edu.dit.simulatorsystem.service.MultiplicationServiceImpl;

@RestController
@RequestMapping("/api/multiplication")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class MultiplicationController {

    @Autowired
    private MultiplicationServiceImpl multiplicationService;

    @PostMapping("/table")
    public ResponseEntity<MultiplicationResponse> generateTable(@RequestBody MultiplicationRequest request) {
        MultiplicationResponse response = multiplicationService.calculateProduct(request);
        return ResponseEntity.ok(response);
    }
}
