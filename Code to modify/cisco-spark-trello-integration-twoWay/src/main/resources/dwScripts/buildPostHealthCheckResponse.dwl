%dw 1.0
%output application/json
---

{
"integration_status":"Integration is working fine",
"integration_name":"Trello",
"integration_description":"This is Trello integration",
"integrationId": flowVars['healthCheckRequest']['integrationId']

}