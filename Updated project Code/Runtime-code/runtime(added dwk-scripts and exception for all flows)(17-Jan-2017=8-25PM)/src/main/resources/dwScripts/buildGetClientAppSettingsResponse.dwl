%dw 1.0
%output application/json
---
{
	"clientAppSettingsId":payload[0].client_app_settings_id,
	"clientId":payload[0].client_id,
	"applicationId":payload[0].application_id,
	"accessClientId":payload[0].access_client_id,
	"accessClientSecret":payload[0].access_client_secret,
	"username":payload[0].username,
	"password":payload[0].password,
	"grantType":payload[0].grantType,
	"scope":payload[0].scope
}