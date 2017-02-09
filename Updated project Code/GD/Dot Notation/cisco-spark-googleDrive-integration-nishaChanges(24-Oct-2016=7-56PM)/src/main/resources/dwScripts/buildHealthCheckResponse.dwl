%dw 1.0
%output application/json
---
{
	"result": "Integration is working correctly.",
	"UserIdAndIntegrationId": flowVars.healthCheckRequest,
	"TokenResponse" : flowVars.getIntegrationTokenResponse,
	"GoogleDriveSettingsResponse" : flowVars.getGDSettingsResponse
}