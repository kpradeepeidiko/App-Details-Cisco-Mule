%dw 1.0
%output application/json
---
{
	"integration_status": "Integration is working fine",
	"integration_name":"google calendar",
	"integration_description":"This is google calendar integration",
	"integrationId": flowVars['integrationId']
}