%dw 1.0
%output application/json
---
{
	"tokenId":payload[0].app_token_id,
	"applicationId":payload[0].application_id,
	"integrationId":payload[0].integration_id,
	"userId":payload[0].user_id,
	"accessToken":payload[0].access_token,
	"expires":payload[0].expires,
	"refreshExpires":payload[0].refresh_expires,
	"refreshToken":payload[0].refresh_token,
	"subDomain":payload[0].sub_domain
}