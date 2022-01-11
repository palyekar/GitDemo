Feature: Validating Place API's.
Scenario: Verify if place is being added successfully using AddPlaceAPI.
 Given : Add Place Payload
 When : user calls "AddPlaceAPI" with POST request
 Then : the API call got success with status code 200
 And : "status" in responce body is "OK"
 And : "scope" in responce body is "APP"