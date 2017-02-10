%dw 1.0
%output application/json
---
{
	"userId": flowVars['integrationPostRequest']['userId'],
	"instanceId":flowVars['instanceId'],
	"instanceUuid":flowVars['instanceUuid'],
	"integrationId": flowVars['integrationPostRequest']['integrationId'],
  	"configJson": flowVars['integrationPostRequest']['configJson'],
  	"messageFormat": flowVars['integrationPostRequest']['messageFormat'],
  	"channelId": flowVars['integrationPostRequest']['channelId'],
 	"status": flowVars['integrationPostRequest']['status']
}