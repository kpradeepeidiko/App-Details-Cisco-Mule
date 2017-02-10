%dw 1.0
%output application/json
---
{
	"clientTokenId":payload[0].client_token_id,
	"clientId":payload[0].client_id,
	"accessToken":payload[0].access_token,
	"expires":payload[0].expires,
	"refreshExpires":payload[0].refresh_expires,
	"refreshToken":payload[0].refresh_token
}