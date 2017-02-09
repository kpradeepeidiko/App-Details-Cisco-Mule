%dw 1.0
%output application/json
---
{
	"integrationId":payload[0].integration_id,
	"description":payload[0].description,
	"integrationName":payload[0].integration_name,
	"createdDate":payload[0].created_date,
	"updatedDate":payload[0].updated_date,
	"statusTimestamp":payload[0].status_timestamp,
	"iconHighres":payload[0].icon_highres,
	"iconLowres":payload[0].icon_lowres,
	"status":payload[0].status,
	"enablement":payload[0].enablement
}