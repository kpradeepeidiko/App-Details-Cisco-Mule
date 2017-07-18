%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"Basecamp",
	"integration_description":"This is basecamp integration",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['exception']
	
}