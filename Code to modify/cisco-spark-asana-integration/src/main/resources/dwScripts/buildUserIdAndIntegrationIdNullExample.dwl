%dw 1.0
%output application/java
---
{
	"isValidToken": false,
	"message": "Found UserId or IntegrationId Null",
	"userId":flowVars['userId'],
	"integrationId":flowVars['integrationId']
}