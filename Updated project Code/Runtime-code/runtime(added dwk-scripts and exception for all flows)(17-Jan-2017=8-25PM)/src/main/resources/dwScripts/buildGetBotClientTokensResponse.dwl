%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"clientTokenId":key.id,
	"integrationName":key.integration_name,
	"accessToken":key.access_token,
	"integrationId":key.integration_id
})