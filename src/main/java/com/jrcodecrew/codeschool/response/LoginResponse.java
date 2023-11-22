package com.jrcodecrew.codeschool.response;

public class LoginResponse {
        String message;
        Boolean status;

        Long userId;

    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

    public Long getUserId() {
        return userId;
    }

    public LoginResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
