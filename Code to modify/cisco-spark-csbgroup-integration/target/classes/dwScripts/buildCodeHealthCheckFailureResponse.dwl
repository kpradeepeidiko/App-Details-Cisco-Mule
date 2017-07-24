%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"codeship",
	"integration_description":"This is codeship integration",
	"integrationId": flowVars['healthChechRequest']['integrationId'],
	"exception": flowVars['exception']
	
}