%dw 1.0
%output application/json
---
{
	"result": "Integration is working correctly.",
	"TokenResponse" : flowVars.getIntegrationTokenResponse,
	"webhookId" : flowVars.config.webhook_id
}