%dw 1.0
%output application/json
---
{
	"instanceId":payload[0].instance_id,
	"integrationId":payload[0].integration_id,
	"userId":payload[0].user_id,
	"updatedDate":payload[0].updated_date,
	"messageFormat":payload[0].message_format,
	"createdDate":payload[0].created_date,
	"status":payload[0].status,
	"channelId":payload[0].channel_id,
	"configJson":payload[0].config_json,
	"instanceUuid":payload[0].instance_uuid
}