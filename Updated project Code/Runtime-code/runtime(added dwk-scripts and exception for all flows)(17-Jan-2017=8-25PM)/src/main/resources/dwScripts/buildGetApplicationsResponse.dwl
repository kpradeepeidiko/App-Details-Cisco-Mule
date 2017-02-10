%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"applicationId":key.application_id,
	"applicationName":key.application_name,
	"description":key.description,
	"authorizationType":key.authorization_type,
	"authorizationEndpoint":key.authorization_endpoint,
	"tokenEndpoint":key.token_endpoint,
	"tokenValidationUrl":key.token_validation_url
})
