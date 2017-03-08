%dw 1.0
%output application/json
---
{
	"userId":flowVars['userId'],
	"deleteResponse": flowVars['putIntegrationResponse'],
	"settings": flowVars['integrationSettings'],
	"tearDownResponse": flowVars['tearDownResponse']
}