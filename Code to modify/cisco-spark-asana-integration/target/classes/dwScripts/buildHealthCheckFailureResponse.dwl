%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"Asana",
	"integration_description":"This is asana integration",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['exception']
	
}