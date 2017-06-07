%dw 1.0
%output application/json
---
{
	"integration_status":"Integration has some issues",
	"integration_name":"Office 365",
	"integration_description":"This is Offic 365 integration",
	"integrationId": flowVars['integrationId'],
	"exception": flowVars['exception']
}