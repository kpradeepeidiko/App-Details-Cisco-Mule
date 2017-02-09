%dw 1.0
%output application/json
---
{
	"result":"Integration is working correctly.",
	"webhookDetails": flowVars['webhookResponse'],
	"UserIdAndIntegrationId" :flowVars['healthCheckRequest']
}