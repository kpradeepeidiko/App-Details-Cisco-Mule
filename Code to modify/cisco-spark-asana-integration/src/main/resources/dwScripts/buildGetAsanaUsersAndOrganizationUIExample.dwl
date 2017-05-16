%dw 1.0
%output application/json
---
{
	"usersDetails": flowVars['userFlowsettingsResponse'],
	"organizations": flowVars['settingsResponse']
}