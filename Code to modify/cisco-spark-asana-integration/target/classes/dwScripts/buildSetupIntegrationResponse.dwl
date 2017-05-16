%dw 1.0
%output application/json
---
{
	"statusCode":200,
	"setupIntegrationResponse":flowVars['hookDetailsResponse']
}