%dw 1.0
%output application/json
---
{

	"userId":flowVars.sparkProfileSettings.id,
	integrationId: flowVars.integrationId
}