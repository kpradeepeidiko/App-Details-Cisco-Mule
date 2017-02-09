%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"Paper Trail Exception",
	"integrationId":p('coreservices.integration.id')
}