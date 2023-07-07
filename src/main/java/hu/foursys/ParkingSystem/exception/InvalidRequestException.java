package hu.foursys.ParkingSystem.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidRequestException extends RuntimeException {
    private final List<String> errors;
    public InvalidRequestException(List<String> errors) {
        this.errors = errors;
    }
}
