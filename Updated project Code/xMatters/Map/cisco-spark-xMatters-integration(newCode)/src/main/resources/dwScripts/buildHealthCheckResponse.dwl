%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"xMatters",
	"integration_description":"This is xMatters Integration",
	"integrationId": flowVars['integrationId']
}