%dw 1.0
%output application/json
---
{
	"isValidToken": false,
	"ListDetails":flowVars['GetList'],
	"userDetails":flowVars['userResponse']
}