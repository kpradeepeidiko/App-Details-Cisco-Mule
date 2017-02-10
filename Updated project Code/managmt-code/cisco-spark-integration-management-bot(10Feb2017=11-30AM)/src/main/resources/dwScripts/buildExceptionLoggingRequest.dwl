%dw 1.0
%output application/json
---
{
	"severity":"High",
	"message":"Management Flow Exception",
	"integrationId":flowVars.exceptionIntegrationId,
    "userId":flowVars.exceptionUserId
}