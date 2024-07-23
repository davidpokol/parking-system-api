# 📄 Parking System API Documentation

## Vehicle

### Get all vehicles

```http
  GET /v1/vehicles
```

### Get a vehicle

```http
  GET /v1/vehicles/{id}
```

#### Parameter

| Name  | Type   | Description              |
|:------|:-------|:-------------------------|
| *`id` | `Long` | `Id` of vehicle to fetch |

### Add a vehicle

```http
  POST /v1/vehicles
```

#### Request Body

| Content Type       | Schema    | Description         |
|:-------------------|:----------|:--------------------|
| `application/json` | `Vehicle` |  Vehicle to persist |

### Update a vehicle

```http
  PUT /v1/vehicles/{id}
```

#### Parameter

| Name  | Type   | Description               |
|:------|:-------|:--------------------------|
| *`id` | `Long` | `Id` of vehicle to upadte |

#### Request Body

| Content Type       | Schema   | Description       |
|:-------------------|:---------|:------------------|
| `application/json` | `Vehicle`| Vehicle to update |

```http
  DELETE /v1/vehicles/{id}
```

### Delete a vehicle

#### Parameter

| Name  | Type   | Description               |
|:------|:-------|:--------------------------|
| *`id` | `Long` | `Id` of vehicle to delete |

## Parking Garage

### Get all parking garages

```http
  GET /v1/parking-garages
```

### Get a parking garage

```http
  GET /v1/parking-garages/{id}
```

#### Parameter

| Name  | Type   | Description                     |
|:------|:-------|:--------------------------------|
| *`id` | `Long` | `Id` of parking garage to fetch |

### Add a parking garage

```http
  POST /v1/parking-garages
```

#### Request Body

| Content Type       | Schema           | Description               |
|:-------------------|:-----------------|:--------------------------|
| `application/json` | `Parking Garage` | Parking garage to persist |

### Update a parking garage

```http
  PUT /v1/parking-garages/{id}
```

#### Parameter

| Name  | Type   | Description                      |
|:------|:-------|:---------------------------------|
| *`id` | `Long` | `Id` of parking garage to upadte |

#### Request Body

| Content Type       | Schema           | Description              |
|:-------------------|:-----------------|:-------------------------|
| `application/json` | `Parking Garage` | Parking garage to update |

```http
  DELETE /v1/parking-garages/{id}
```

### Delete a parking garage

#### Parameter

| Name  | Type   | Description                      |
|:------|:-------|:---------------------------------|
| *`id` | `Long` | `Id` of parking garage to delete |

## Parking

### Get all parking records

```http
  GET /v1/parking-records
```

### Get a parking record

```http
  GET /v1/parking-records/{id}
```

#### Parameter

| Name  | Type   | Description                     |
|:------|:-------|:--------------------------------|
| *`id` | `Long` | `Id` of parking record to fetch |

### Add a parking record

```http
  POST /v1/parking-records
```

#### Request Body

| Content Type       | Schema          | Description               |
|:-------------------|:----------------|:--------------------------|
| `application/json` | `Parking`       |  Parking record to persist|

## Schemas

### Vehicle

| Property	           | Type	    | Description                                                                    |
|:--------------------|:---------|:-------------------------------------------------------------------------------|
| `id	`               | Integer	 |                                                                                |
| *`licensePlate`	    | String	  | pattern: `^([A-Z]{3}-?\d{3}\|[A-Z]{4}-?\d{3})$`<br/>e.g. `ASD-123`, `ASDF-123`|             
| *`vehicleCategory`	 | Enum	    | values: `M1`, `N1`, `N2`, `N3`, `L`, `O`, `T`, `C`                             | 

### Vehicle Response

| Property	          | Type	   | Description                                                                    |
|:-------------------|:--------|:-------------------------------------------------------------------------------|
| *`id`              | Integer | 	                                                                              |
| *`licensePlate`    | String	 | pattern: `^([A-Z]{3}-?\d{3}\|[A-Z]{4}-?\d{3})$`<br/>e.g. `ASD-123`, `ASDF-123` |
| *`vehicleCategory` | Enum	 | values: `M1`, `N1`, `N2`, `N3`, `L`, `O`, `T`, `C`                       |

### Parking Garage

| Property	        | Type	     | Description                  |
|:-----------------|:----------|:-----------------------------|
| `id`             | 	Integer	 |                              |
| *`address`       | 	String	  | minLength: 10, maxLength: 60 |
| *`parkingSpaces` | 	Integer	 | min: 10, max: 1000           |

### Parking Garage Response

| Property	        | Type	    | Description                    |
|:-----------------|:---------|:-------------------------------|
| *`id`	           | Integer	 |                                |
| *`address`       | 	String  | 	 minLength: 10, maxLength: 60 |
| *`parkingSpaces` | 	Integer | min: 10, max: 1000             |

### Parking

| Property	              | Type	     | Description                                                                           |
|:-----------------------|:----------|:--------------------------------------------------------------------------------------|
| `id`	                  | Integer   | 	                                                                                     |
| *`vehicleLicensePlate` | 	String   | 	 pattern: <br/>`^([A-Z]{3}-?\d{3}\|[A-Z]{4}-?\d{3})$`<br/>e.g. `ASD-123`, `ASDF-123` |
| *`parkingGarageId`     | 	Integer	 |                                                                                       |
| *`startTime`           | 	Date     | 	 format: date-time, must be a past date                                              |
| *`endTime`	            | Date      | 	 format: date-time, must be a past or a present date                                 |

### Parking Response

| Property	         | Type	                   | Description                               |
|:------------------|:------------------------|:------------------------------------------|
| *`id`	            | Integer	                |                                           |
| *`vehicle`	       | Vehicle Response        | 	                                         |
| *`parkingGarage`	 | Parking Garage Response | 	                                         |
| *`startTime`	     | String	                 | format: date-time, past date           |
| *`endTime`	       | String	                 | format: date-time, past or a present date |

note: fields marked with `*` are required