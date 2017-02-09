%dw 1.0
%output application/json
---
{
	"applicationId":payload[0].application_id,
	"applicationName":payload[0].application_name,
	"description":payload[0].description,
	"authorizationType":payload[0].authorization_type,
	"authorizationEndpoint":payload[0].authorization_endpoint,
	"tokenEndpoint":payload[0].token_endpoint,
	"tokenValidationUrl":payload[0].token_validation_url,
	"clientId":payload[0].client_id
}