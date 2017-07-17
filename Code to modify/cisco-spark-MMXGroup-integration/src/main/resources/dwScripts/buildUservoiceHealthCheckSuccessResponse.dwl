%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"Uservoice",
	"integration_description":"This is uservoice integration",
	"integrationId": flowVars['integrationId']
}