%dw 1.0
%output application/json
---
{
	"hookResponse":flowVars['webhookResponse'],
	"userEvents":flowVars['integrationsInstanceResponse'][0]['configJson'],
	"instanceDetails":flowVars['integrationsInstanceResponse'][0]
}