package com.davidpokolol.parkingsystemapi.service.util;

import com.davidpokolol.parkingsystemapi.service.model.exception.EnumConversionException;
import jakarta.annotation.Nonnull;

public class ConverterUtil {

    public static <E extends Enum<E>> E convertStringToEnum(
            @Nonnull final Class<E> enumType,
            @Nonnull final String value) {

        try {
            return Enum.valueOf(enumType, value);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new EnumConversionException(enumType, value);
        }
    }

}
