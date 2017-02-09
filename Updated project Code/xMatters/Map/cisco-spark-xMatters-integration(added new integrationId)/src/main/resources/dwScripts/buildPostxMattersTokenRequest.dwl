%dw 1.0
%output application/java
---
{
	"client_id": payload.clientId,
    "client_secret": payload.clientSecret,
 	"grant_type": payload.grantType,
	"code": payload.code
}