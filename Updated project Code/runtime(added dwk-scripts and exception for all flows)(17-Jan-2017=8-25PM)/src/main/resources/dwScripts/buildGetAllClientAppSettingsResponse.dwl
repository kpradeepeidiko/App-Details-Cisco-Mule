%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"clientAppSettingsId":key.client_app_settings_id,
	"clientId":key.client_id,
	"applicationId":key.application_id,
	"accessClientId":key.access_client_id,
	"accessClientSecret":key.access_client_secret,
	"username":key.username,
	"password":key.password,
	"grantType":key.grantType,
	"scope":key.scope
})