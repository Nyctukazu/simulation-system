package edu.dit.simulatorsystem.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.dit.simulatorsystem.dto.AuthRequest;
import edu.dit.simulatorsystem.dto.AuthResponse;
import edu.dit.simulatorsystem.service.utilities.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        boolean success = authService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(success));
    }
}
