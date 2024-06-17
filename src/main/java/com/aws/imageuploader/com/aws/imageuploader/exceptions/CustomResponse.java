package com.aws.imageuploader.com.aws.imageuploader.exceptions;

public class CustomResponse {
    private String message;
    private boolean success;

    // Default constructor
    public CustomResponse() {}

    // Constructor with parameters
    public CustomResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Builder pattern for easy object creation
    public static CustomResponseBuilder builder() {
        return new CustomResponseBuilder();
    }

    public static class CustomResponseBuilder {
        private String message;
        private boolean success;

        public CustomResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public CustomResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public CustomResponse build() {
            return new CustomResponse(message, success);
        }
    }
}
