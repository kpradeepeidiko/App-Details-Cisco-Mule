%dw 1.0
%output application/json
---
{
	"isValidToken": false,
	"messageFormAPI": flowVars['refreshTokenResponse']
}