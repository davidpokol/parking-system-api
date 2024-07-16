package com.davidpokolol.parkingsystemapi.service.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EnumConversionException extends RuntimeException {
    private final Class<? extends Enum<?>> enumType;
    private final String value;
}
