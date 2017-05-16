%dw 1.0
%output application/java
---
{
	"isValidToken": false,
	"message":"Found Get Token Result Empty in DB",
	"resultFromDB":flowVars['integrationTokenResponse']
}