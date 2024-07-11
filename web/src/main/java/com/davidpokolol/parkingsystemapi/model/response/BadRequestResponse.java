package com.davidpokolol.parkingsystemapi.model.response;

import java.util.List;

public record BadRequestResponse(List<String> errors) {
    public void addError(String error) {
        errors.add(error);
    }
}
