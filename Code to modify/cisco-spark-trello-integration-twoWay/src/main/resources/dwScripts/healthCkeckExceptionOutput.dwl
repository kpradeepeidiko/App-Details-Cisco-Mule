%dw 1.0
%output application/json
---
{
"integration_status":"Integration has some issues",
"integration_name":"Trello",
"integration_description":"This is Trello Integration",
"integrationId":flowVars['healthCheckRequest']['integrationId'],
"exception": flowVars['healthCheckException']
}