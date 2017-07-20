%dw 1.0
%output application/java
---
{
	"severity":"High",
	"message":"Management Webhook Flow Exception",
	"integrationId":flowVars.exceptionIntegrationId,
    "userId":flowVars.exceptionUserId
}