%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"UserVoice Exception",
	"integrationId":p('core.services.integration.id')
}