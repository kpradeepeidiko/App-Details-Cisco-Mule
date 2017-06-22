%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"Trello Exception",
	"integrationId":p('coreservices.integration.id')	
}