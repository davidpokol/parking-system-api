package hu.foursys.ParkingSystem.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BadRequestError {
    private final List<String> messages;
}
