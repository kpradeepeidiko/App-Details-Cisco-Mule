%dw 1.0
%output application/json
---
{
	"userId":flowVars['request2']['userId'],
	"cursorTime":flowVars['time2']
}