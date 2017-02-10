%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"instanceId":key.instance_id,
	"integrationId":key.integration_id,
	"userId":key.user_id,
	"updatedDate":key.updated_date,
	"messageFormat":key.message_format,
	"createdDate":key.created_date,
	"status":key.status,
	"channelId":key.channel_id,
	"configJson":key.config_json,
	"instanceUuid":key.instance_uuid
})