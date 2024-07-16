package com.davidpokolol.parkingsystemapi.util;

import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingGarageRequestException;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidParkingRequestException;
import com.davidpokolol.parkingsystemapi.model.exception.InvalidVehicleRequestException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

public class RequestValidationHandlerUtil {

    public static void checkForVehicleRequestErrors(BindingResult bindingResult) {

        List<String> errors = checkForRequestErrors(bindingResult);
        if (!errors.isEmpty()) {
            throw new InvalidVehicleRequestException("Invalid vehicle request", errors);
        }
    }
    public static void checkForParkingGarageRequestErrors(BindingResult bindingResult) {

        List<String> errors = checkForRequestErrors(bindingResult);
        if (!errors.isEmpty()) {
            throw new InvalidParkingGarageRequestException("Invalid parking garage request", errors);
        }
    }
    public static void checkForParkingRequestErrors(BindingResult bindingResult) {

        List<String> errors = checkForRequestErrors(bindingResult);
        if (!errors.isEmpty()) {
            throw new InvalidParkingRequestException("Invalid parking request", errors);
        }
    }

    private static List<String> checkForRequestErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(RequestValidationHandlerUtil::fieldErrorToMessage)
                    .toList();
        }
        return Collections.emptyList();
    }

    private static String fieldErrorToMessage(FieldError fieldError) {
        return fieldError.getField() + " - " + fieldError.getDefaultMessage();
    }
}