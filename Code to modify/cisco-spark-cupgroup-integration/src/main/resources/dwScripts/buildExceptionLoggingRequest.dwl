%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"Codeship, UserVoice and Papertrail Merged App Exception",
	"integrationId":p('coreservices.integration.id')
}