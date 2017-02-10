%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"clientTokenId":key.client_token_id,
	"clientId":key.client_id,
	"accessToken":key.access_token,
	"expires":key.expires,
	"refreshExpires":key.refresh_expires,
	"refreshToken":key.refresh_token,
	"integrationId":key.integration_id
})