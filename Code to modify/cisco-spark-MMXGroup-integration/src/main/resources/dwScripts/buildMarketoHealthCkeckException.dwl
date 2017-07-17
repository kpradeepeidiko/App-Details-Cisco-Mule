%dw 1.0
%output application/json
---
{
	"integration_status": "The below integration has some issues",
	"integration_name": "Marketo",
	"integration_description": "This is marketo integrarion",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['healthCheckException']
}