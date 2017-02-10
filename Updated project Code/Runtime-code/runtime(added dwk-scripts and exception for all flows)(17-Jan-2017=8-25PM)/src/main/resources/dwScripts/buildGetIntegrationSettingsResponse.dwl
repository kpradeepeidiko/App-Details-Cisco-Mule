%dw 1.0
%output application/json
---
{
	"settingsId":payload[0].settings_id,
	"integrationId":payload[0].integration_id,
	"integrationType":payload[0].integration_type,
	"host":payload[0].host,
	"port":payload[0].port,
	"basePath":payload[0].base_path,
	"apiVersion":payload[0].api_version,
	"setupPageUri":payload[0].setup_page_uri,
	"editPageUri":payload[0].edit_page_uri,
	"deletePageUri":payload[0].delete_page_uri
}