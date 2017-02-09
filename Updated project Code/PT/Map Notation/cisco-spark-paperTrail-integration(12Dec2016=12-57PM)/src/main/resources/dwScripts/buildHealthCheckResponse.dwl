%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"Papertrail",
	"integration_description":"This is papertrail integration",
	"integrationId": flowVars['integrationId'],
	"generateWebhookUrlResponse": flowVars['generateWebhookResponse'],
	"instanceWebhookUrl": flowVars['configJsonResponse']['webhookUrl']
}