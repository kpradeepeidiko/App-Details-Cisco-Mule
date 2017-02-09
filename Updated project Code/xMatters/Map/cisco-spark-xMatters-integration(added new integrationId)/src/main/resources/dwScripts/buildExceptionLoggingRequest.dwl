%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"Pager Duty Exception",
	"integrationId":p('coreservices.integration.id'),
	"date":flowVars.date
}