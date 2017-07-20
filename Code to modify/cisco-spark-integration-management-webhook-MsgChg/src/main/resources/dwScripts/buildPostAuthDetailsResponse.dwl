%dw 1.0
%output application/json
---
{
	"integrationGeneralSettings": flowVars.integrationSettings,
	"integrationSpecificAuthDetails": flowVars.dynamicSettings
}