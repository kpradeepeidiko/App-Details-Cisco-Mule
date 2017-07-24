%dw 1.0
%output application/json
---
{
	"instanceUuid":flowVars.instanceUuid,
	"integrationId": flowVars.integrationPostRequest.integrationId,
	"userId": flowVars.integrationPostRequest.userId,
  	"configJson": flowVars.configJson,
  	"messageFormat": flowVars.integrationPostRequest.messageFormat,
  	"channelId": flowVars.integrationPostRequest.channelId,
 	"status": flowVars.integrationPostRequest.status
}
