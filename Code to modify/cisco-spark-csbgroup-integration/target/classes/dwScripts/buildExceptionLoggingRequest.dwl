%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"Basecamp Exception",
	"integrationId":p('basecamp.integration.id'),
	"date":flowVars.date
}