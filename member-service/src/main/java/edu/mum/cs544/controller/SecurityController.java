package edu.mum.cs544.controller;

import edu.mum.cs544.models.AuthenticateRequest;
import edu.mum.cs544.models.AuthenticateResponse;
import edu.mum.cs544.security.JwtUtil;
import edu.mum.cs544.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping({"/hello"})
    public String sayHello() {

        return "Hello World";
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Incorrect Username or Password", badCredentialsException);
        }

        final UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticateRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticateResponse(jwt));
    }
}
