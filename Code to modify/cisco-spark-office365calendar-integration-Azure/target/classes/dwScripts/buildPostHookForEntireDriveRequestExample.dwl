%dw 1.0
%output application/json
---
{
	"id":flowVars.uuid,
	"type":"web_hook",
	"address":flowVars.webhookUrl
}