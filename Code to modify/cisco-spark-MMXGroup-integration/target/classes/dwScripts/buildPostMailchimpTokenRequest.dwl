%dw 1.0
%output application/json
---
{
	"client_id": payload['clientId'],
    "client_secret": payload['clientSecret'],
 	"grant_type": payload['grantType'],
	"code": payload['authSettings.code'],
	"redirect_uri":payload['redirectUri']
}