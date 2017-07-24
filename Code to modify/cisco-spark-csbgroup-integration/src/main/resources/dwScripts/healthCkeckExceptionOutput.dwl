%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"Stripe",
	"integration_description":"This is Stripe Integration",
	"integrationId":flowVars['healthCheckRequest']['integrationId'],
	"exception": flowVars['healthCheckException']
}