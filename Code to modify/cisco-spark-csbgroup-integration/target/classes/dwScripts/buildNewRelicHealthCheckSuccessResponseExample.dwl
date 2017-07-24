%dw 1.0
%output application/json
---
{
	"integration_status":"Integration is working fine",
	"integration_name":"New Relic",
    "integration_description":"This is New Relic Integration",
    "integrationId": flowVars['healthCheckToken']['integrationId']
}