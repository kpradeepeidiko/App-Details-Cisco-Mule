%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"xMatters",
	"integration_description":"This is xMatters integration",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['exception']
	
}