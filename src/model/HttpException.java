package model;

public class HttpException extends Exception {
    public final int statusCode;

    public HttpException(int statusCode, String message) {
        super(message); // Exception 자체 속성
        this.statusCode = statusCode;
    }
}