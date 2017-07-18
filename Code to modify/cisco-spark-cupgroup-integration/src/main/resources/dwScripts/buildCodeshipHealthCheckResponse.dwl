%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"codeship",
	"integration_description":"This is codeship integration",
	"integrationId": flowVars['integrationId']
	
	
}