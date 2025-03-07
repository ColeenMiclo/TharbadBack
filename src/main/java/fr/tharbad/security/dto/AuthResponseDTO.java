package fr.tharbad.security.dto;

public class AuthResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer";

    //Constructors
    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponseDTO() {
    }

    //Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
