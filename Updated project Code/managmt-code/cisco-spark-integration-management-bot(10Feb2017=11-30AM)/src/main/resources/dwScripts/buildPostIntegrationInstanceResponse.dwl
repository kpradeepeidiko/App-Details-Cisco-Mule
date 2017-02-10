%dw 1.0
%output application/json
---
{
	"integrationId":flowVars['integrationPostRequest']['integrationId'],
	"settings":flowVars['integrationSettings'],
	"integrationInstanceResponse":flowVars['integrationInstancePostResult'],
	"startResponse": flowVars['startIntegrationResponse']
	
}