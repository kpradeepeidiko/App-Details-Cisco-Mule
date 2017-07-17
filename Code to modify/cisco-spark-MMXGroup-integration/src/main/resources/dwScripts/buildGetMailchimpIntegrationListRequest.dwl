%dw 1.0
%output application/json
---
{
	"ListDetails":flowVars['GetList'],
	"userDetails":flowVars['userResponse']
}