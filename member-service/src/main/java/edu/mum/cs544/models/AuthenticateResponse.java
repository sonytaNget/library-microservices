package edu.mum.cs544.models;

public class AuthenticateResponse {

    private String jwt;

    public AuthenticateResponse(){}

    public AuthenticateResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
