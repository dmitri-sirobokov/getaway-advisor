# Backend application to manage and vote for getaways (dutch: "uitjes") suggestions.

## Prerequsites
- Postres SQL database with Postgis extention module installed. 
PostgreSQL 14 or later. Database script is located in **/src/main/database/** folder
Default schema is 'getaway'. Default url, username and password is specified in application.yaml file.
Change it to your own preference.

## Api examples
- http request examples are located in **/src/test/http/** folder