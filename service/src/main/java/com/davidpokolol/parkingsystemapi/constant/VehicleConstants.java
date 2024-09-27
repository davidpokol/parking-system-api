package com.davidpokolol.parkingsystemapi.constant;

public class VehicleConstants {

    private VehicleConstants() {
    }

    public static final String GET_ALL_VEHICLES_TEXT = "Getting all vehicles.";
    public static final String GET_VEHICLE_BY_ID_TEXT = "Getting a vehicle with ID: {}";
    public static final String GET_VEHICLE_BY_LICENSE_PLATE_TEXT = "Getting a vehicle with license plate: {}";
    public static final String CREATE_VEHICLE_TEXT = "Creating a vehicle: {}";
    public static final String UPDATE_VEHICLE_TEXT = "Updating a vehicle with ID: {} to: {}";
    public static final String DELETE_VEHICLE_TEXT = "Deleting a vehicle with ID: {}.";
    public static final String VEHICLE_NOT_FOUND_WITH_ID_TEXT = "Vehicle not found with ID: ";
    public static final String VEHICLE_NOT_FOUND_WITH_LICENSE_PLATE_TEXT = "Vehicle not found with license plate: ";

    public static final String VEHICLE_LICENSE_PLATE_PATTERN = "^([A-Z]{3}-?\\d{3}|[A-Z]{4}-?\\d{3})$";
    public static final String VEHICLE_PATTERN_DOES_NOT_MATCH = "must be a hungarian license plate";
}
