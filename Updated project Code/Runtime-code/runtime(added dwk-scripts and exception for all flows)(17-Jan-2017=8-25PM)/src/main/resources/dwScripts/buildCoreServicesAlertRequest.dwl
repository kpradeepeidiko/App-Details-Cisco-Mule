%dw 1.0
%output application/json
---
{
  "messageDate": now,
  "severity": "ERROR",
  "integrationId": flowVars.integrationId,
  "integrationInstanceId": flowVars.instanceId,
  "userId": flowVars.userId,
  "message": "Exception:" + flowVars.exceptionDetails.message
}