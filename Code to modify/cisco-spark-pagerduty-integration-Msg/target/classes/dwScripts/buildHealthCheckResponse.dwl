%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"Pagerduty",
	"integration_description":"This is pagerduty integration",
	"integrationId": flowVars['integrationId']
}