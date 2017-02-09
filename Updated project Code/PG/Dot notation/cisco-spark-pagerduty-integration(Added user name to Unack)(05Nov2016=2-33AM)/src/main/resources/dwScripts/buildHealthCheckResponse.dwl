%dw 1.0
%output application/json
---
{
	"result":"Integration is working correctly.",
	"generateWebhookUrlResponse": flowVars.generateWebhookUrlResponse,
	"instanceWebhookUrl": flowVars.configJsonDetails.webhookUrl
}