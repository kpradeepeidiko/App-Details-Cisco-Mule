%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"Papertrail",
	"integration_description":"This is papertrail integration",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['exception']
	
}