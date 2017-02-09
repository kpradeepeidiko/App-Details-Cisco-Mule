%dw 1.0
%output application/json
---
{
	"webhookUrl":flowVars['webhookUrl'],
	"instanceUuid":flowVars['instanceUuid']
}