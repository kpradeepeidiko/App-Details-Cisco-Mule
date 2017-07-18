%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"Uservoice",
	"integration_description":"This is uservoice integration",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['exception']
	
}