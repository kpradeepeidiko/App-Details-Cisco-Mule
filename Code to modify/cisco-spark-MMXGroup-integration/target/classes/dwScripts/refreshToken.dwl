%dw 1.0
%output application/json
---
{
	"accessToken":flowVars.refreshResult.access_token,
	"expires":flowVars.refreshResult.expires_in,
	"refreshToken":flowVars.refreshResult.refresh_token
}