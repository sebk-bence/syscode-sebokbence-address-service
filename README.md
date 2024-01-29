# Address Service

## Setup
No special setup is needed, you can run the service as-is.

The service will start at http://localhost:8181 by default.

## Authentication
Endpoints use Basic authentication with access for one user utilizing an im-memory credentials provider.

You can use the following credentials to access resources:

Username: `user1`

Password: `password1` 

## Endpoints

> `http://localhost:8181/address/{id}`
> 
> This single endpoint returns an address by id.
> Example ids:
> 
> `eb281107-c753-44c5-a64b-9cbdf5705f4e`
> 
> `ab8e72e4-7118-46c8-b607-5a555313ef93`
> 
> `2ae8d80c-f532-4d55-8508-a56f9c9f0be5`