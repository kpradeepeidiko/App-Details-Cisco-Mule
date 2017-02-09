%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"id":key.id,
	"trackingId":key.tracking_id,
	"userId":key.user_id,
	"integrationId":key.integration_id,
	"requestType":key.request_type,
	"statusCode":key.status_code,
	"statusMessage":key.status_message,
	"params":key.params,
	"createdDate":key.created_date,
	"updatedDate":key.updated_date
})
