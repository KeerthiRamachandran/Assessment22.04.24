This project implements an entity for managing requisition details with fields `allotmentId`, `brn`, and `amount`. The `brn` and `allotmentId` fields are required and cannot be null.


contains an API for save all fields in database and search API for search based on amount and also based on amount range using data base streaming.and an Update API for update brn and amount.

##API

PUT
/assessment/{allotmentId}

POST
/assessment/save-Requisition-details
Save Requisition Details request

GET
/assessment/searchByAmountRange

GET
/assessment/search/amount/{amount}



## Requirements

- Java version 17
- Maven 3.2.5
- PostgreSQL 14.10


#swagger API
http://localhost:9112/demo-service/swagger-ui/index.html#




