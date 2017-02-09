%dw 1.0
%output application/json
---
{
	"result": "Integration is working correctly.",
	"TokenResponse" : flowVars.getIntegrationTokenResponse,
	"GoogleCalenderSettingsResponse" : flowVars.conFigValues,
	"CalendarsList" : flowVars.settingsResponse
}