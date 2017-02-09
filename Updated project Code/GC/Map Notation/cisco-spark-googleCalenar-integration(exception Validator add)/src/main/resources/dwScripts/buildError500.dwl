%dw 1.0
%output application/json
---
{
	"error":"Internal Error",
	"cause":flowVars.exceptionDetails.message
}