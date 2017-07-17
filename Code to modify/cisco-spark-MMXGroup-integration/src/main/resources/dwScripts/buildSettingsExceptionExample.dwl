%dw 1.0
%output application/json
---
{
	"Payload": flowVars['getPayload'],
	"exception": flowVars['exception']
}