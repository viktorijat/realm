Spring boot application, 

- to start the app: $mvn spring-boot:run
- It runs on port 8080:
- web endpoint to see the list of realms is: http://localhost:8080/
- rest endpoints to add new realm: http://localhost:8080/api/v1/user/realm with method POST, set the Content-Type
and Accept header to specify if you're going to provide/receive json or xml. Example json body:
{
	"name": "a",
	"description": "b"
}
- rest endpoint to get all realms (preloaded in the repository): http://localhost:8080/api/v1/user/realm with method GET
- to delete a realm: http://localhost:8080/api/v1/user/realm/1 with method DELETE
- to get a realm by id: http://localhost:8080/api/v1/user/realm/1 with method GET