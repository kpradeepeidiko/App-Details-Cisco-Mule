%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"tokenId":key.app_token_id,
	"applicationId":key.application_id,
	"integrationId":key.integration_id,
	"userId":key.user_id,
	"accessToken":key.access_token,
	"expires":key.expires,
	"refreshExpires":key.refresh_expires,
	"refreshToken":key.refresh_token,
	"subDomain":key.sub_domain
})