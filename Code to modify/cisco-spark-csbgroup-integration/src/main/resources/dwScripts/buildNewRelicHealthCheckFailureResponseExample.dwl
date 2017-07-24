%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"New Relic",
	"integration_description":"This is new relic Integration",
	"integrationId":flowVars['healthCheckRequest']['integrationId'],
	"exception": flowVars['healthCheckException']
}