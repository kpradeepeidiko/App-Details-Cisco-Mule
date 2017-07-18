%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"mailchimp",
	"integration_description":"This is mailchimp integration",
	"integrationId": flowVars['integrationId']
}