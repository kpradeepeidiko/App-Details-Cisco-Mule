%dw 1.0
%output application/json
---
{
	"message":"Exception in settings flow",
	"exception": flowVars['exception']
}