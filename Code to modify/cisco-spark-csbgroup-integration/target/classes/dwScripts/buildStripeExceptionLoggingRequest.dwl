%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"Stripe Exception",
	"integrationId":p('stripe.integration.id'),
	"date":flowVars.date
}