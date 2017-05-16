%dw 1.0
%output application/java
---
{
	"isValidToken": false,
	"message":"Generate Token By Refresh Token Failed",
	"errorIs" : flowVars['refreshTokenResponse']
}