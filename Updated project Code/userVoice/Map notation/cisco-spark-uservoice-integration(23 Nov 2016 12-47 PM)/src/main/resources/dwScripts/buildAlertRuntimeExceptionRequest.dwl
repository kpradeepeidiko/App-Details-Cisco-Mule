%dw 1.0
%output application/java
---
{
	"domain":p('core.services.domain'),
	"body": flowVars.exceptionMessage,
	"severity":"Error",
    "integrationId":p('core.services.integration.id'),
    "userId":flowVars.Response.instanceDetails.userId
}