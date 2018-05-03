package org.stepik.java.other;

/**
 * Immutable class for representing requests.
 * If you need to change the request data then create new request.
 */
public class Request {
    private final String data;

    public Request(String requestData) {
        this.data = requestData;
    }

    public String getData() {
        return data;
    }
}