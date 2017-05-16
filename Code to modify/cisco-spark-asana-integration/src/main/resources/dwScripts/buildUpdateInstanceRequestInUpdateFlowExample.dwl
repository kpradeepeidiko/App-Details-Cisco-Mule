%dw 1.0
%output application/json
---
{
	"configJson":{
		"project_id" : flowVars['updateRequest']['configJson']['project_id'],
		"organization_id" : flowVars['updateRequest']['configJson']['organization_id'],
		"team_id" : flowVars['updateRequest']['configJson']['team_id'],
		"displayName" : flowVars['updateRequest']['configJson']['displayName'],
		"webhook_id" : flowVars['updateRequest']['configJson']['webhook_id'],
		"notifications": flowVars['updateRequest']['configJson']['notifications'],
		"asana_user_id": flowVars['updateRequest']['configJson']['asana_user_id']
	}
}