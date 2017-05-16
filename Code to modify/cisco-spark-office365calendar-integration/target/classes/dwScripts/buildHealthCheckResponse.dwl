%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"Office 365",
	"integration_description":"This is office 365 integration",
	"integrationId": flowVars['integrationId']
}