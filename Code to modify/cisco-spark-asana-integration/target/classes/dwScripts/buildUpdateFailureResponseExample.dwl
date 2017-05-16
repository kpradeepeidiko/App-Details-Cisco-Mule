%dw 1.0
%output application/json
---
{
	"statusCode":500,
	"updateIntegrationResponse": flowVars['updateIntegrationResponse'],
	"statusDisableResponse":flowVars['statusDisableResponse']
}

