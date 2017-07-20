%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"Pagerduty",
	"integration_description":"This is pagerduty integration",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['exception']
}