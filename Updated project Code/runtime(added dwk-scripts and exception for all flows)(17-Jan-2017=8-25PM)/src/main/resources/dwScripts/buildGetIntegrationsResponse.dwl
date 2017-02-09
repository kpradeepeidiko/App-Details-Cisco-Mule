%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"integrationId":key.integration_id,
	"description":key.description,
	"integrationName":key.integration_name,
	"createdDate":key.created_date,
	"updatedDate":key.updated_date,
	"statusTimestamp":key.status_timestamp,
	"iconHighres":key.icon_highres,
	"iconLowres":key.icon_lowres,
	"status":key.status,
	"enablement":key.enablement
})
