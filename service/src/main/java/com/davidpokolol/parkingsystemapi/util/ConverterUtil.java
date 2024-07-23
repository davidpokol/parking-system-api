package com.davidpokolol.parkingsystemapi.util;

import com.davidpokolol.parkingsystemapi.model.exception.EnumConversionException;
import com.davidpokolol.parkingsystemapi.model.exception.StringFormatException;

public class ConverterUtil {

    public static <E extends Enum<E>> E convertStringToEnum(final Class<E> enumType, final String value) {

        try {
            return Enum.valueOf(enumType, value);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new EnumConversionException(enumType, value);
        }
    }

    public static String formatHungarianLicensePlate(final String licensePlate) {

        if (licensePlate.contains("-")) {
            return licensePlate.toUpperCase();
        }
        if (licensePlate.length() == 6) {
            return String.format(
                    "%s-%s",
                    licensePlate.substring(0, 3),
                    licensePlate.substring(3)
            ).toUpperCase();
        }

        if (licensePlate.length() == 7) {
            return String.format(
                    "%s-%s",
                    licensePlate.substring(0, 4),
                    licensePlate.substring(4)
            ).toUpperCase();
        }
        throw new StringFormatException("Failed to format license plate into hungarian format.");
    }
}
