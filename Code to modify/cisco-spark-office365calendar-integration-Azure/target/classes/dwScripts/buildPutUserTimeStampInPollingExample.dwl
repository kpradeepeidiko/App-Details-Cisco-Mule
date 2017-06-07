%dw 1.0
%output application/json
---
{
	"userId":sessionVars['userId'],
	"cursorTime":flowVars['time2']
}