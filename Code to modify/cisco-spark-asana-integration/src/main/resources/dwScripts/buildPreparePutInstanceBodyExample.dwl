%dw 1.0
%output application/json
---
{
	"configJson":{
		"project_id" : flowVars['project_id'],
		"organization_id" : flowVars['request2']['configJson']['organization_id'],
		"team_id" : flowVars['request2']['configJson']['team_id'],
		"displayName" : flowVars['request2']['configJson']['displayName'],
		"webhook_id" : flowVars['successWebhookResponse']['data']['id'],
		"notifications": flowVars['request2']['configJson']['notifications'],
		"asana_user_id": flowVars['request2']['configJson']['asana_user_id']
	}
}