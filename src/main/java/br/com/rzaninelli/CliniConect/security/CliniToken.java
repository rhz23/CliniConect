package br.com.rzaninelli.CliniConect.security;

public class CliniToken {

    private String token;

    public CliniToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
