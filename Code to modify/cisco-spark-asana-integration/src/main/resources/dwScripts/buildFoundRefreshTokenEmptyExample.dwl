%dw 1.0
%output application/java
---
{
	"isValidToken": false,
	"message":"Found Refresh Token Empty in DB",
	"dbResult":flowVars.integrationTokenResponse[0]
}