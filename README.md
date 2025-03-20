
# Oxbury Bank Java Task
   
## Description
The purpose of this task was to create a REST API that would interact with a database of my choosing to perform different defined functions. For the purposes of this I've used a H2 internal DB. This database gets populated and created on startup.

## Build

```bash
    mvn clean install
```

## Test
```bash
    mvn test
```

## Mappings
Minus the specific search endpoint all of the endpoints lie on 
> http://http://localhost:8080/oxbury

The search endpoint lies on
> http://localhost:8080/oxbury/searchByCategory

## Endpoints
### Requests 
GET
> http://http://localhost:8080/oxbury

#### URL Params
|Parameter| Description | Required | Type | Example |
| -------- | ------------- | ------------- | ------------- | --------- |
| NA | NA | NA | NA | NA |

### Response 200
> HTTP status 200 OK

#### Body
```json
[
    {
        "manufacturer": "Coca Cola",
        "retailer": "Tesco",
        "productCode": "A1235",
        "transactionID": "123e4567-e89b-12d3-a456-426614174000",
        "transactionDate": "Jan 1, 2024",
        "quantity": 123.0,
        "value": 44456.0
    }
]
```

POST
> http://http://localhost:8080/oxbury

#### URL Params
|Parameter| Description | Required | Type | Example |
| -------- | ------------- | ------------- | ------------- | --------- |
| manufacturer | the name of the manufacturer | true | string | pepsi |
| retailer | the name of the retailer | true | string | tesco |
| productCode | the product code of the item | true | int | 134 |
| transactionID | the name of the transaction | true | string | afjkmfkna |
| quantity | the quantity in the transaction | true | double | 1.0 |
| value | the value of the transaction | true | double | 1.0 |

### Response 200
> HTTP status 200 OK

#### Body
```json
{
    "statusCode": "OK",
    "successString": "data successfully added to db"
}
```

DELETE
> http://http://localhost:8080/oxbury

#### URL Params
|Parameter| Description | Required | Type | Example |
| -------- | ------------- | ------------- | ------------- | --------- |
| adminToken | admin token to authorise deletions | false | string | manager |
| manufacturer | the name of the manufacturer | true | string | pepsi |
| retailer | the name of the retailer | true | string | tesco |
| productCode | the product code of the item | true | int | 134 |
| transactionID | the name of the transaction | true | string | afjkmfkna |


### Response 200
> HTTP status 200 OK

#### Body
```json
{
    "statusCode": "OK",
    "successString": "data successfully removed db"
}
```

### Response 500
> HTTP status 500 Internal Server Error

#### Body
```json
{
    "statusCode": "INTERNAL_SERVER_ERROR",
    "successString": "no auth token"
}
```



GET
> http://http://localhost:8080/oxbury/searchByCategory

#### URL Params
|Parameter| Description | Required | Type | Example |
| -------- | ------------- | ------------- | ------------- | --------- |
| manufacturer | the name of the manufacturer | true | string | pepsi |
| retailer | the name of the retailer | true | string | tesco |
| productCode | the product code of the item | true | int | 134 |
| fromDate | date to start searching from | true | int | 2024-01-01 |
| toDate | date to search up to | true | int | 2024-02-01 |


### Response 200
> HTTP status 200 OK

#### Body
```json
[
    {
        "manufacturer": "Pepsi",
        "retailer": "Tesco",
        "productCode": "A1235",
        "transactionID": "123e4567-e89b-12d3-a456-426614174000",
        "transactionDate": "Jan 1, 2024",
        "quantity": 123.0,
        "value": 44456.0
    }
]
```

## Database
H2 Database can be accessed locally as follows

### Access
> http://localhost:8080/h2-console

|username| password |
| -------- | ------------- | 
| sa | password |

