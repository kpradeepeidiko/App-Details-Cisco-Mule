%dw 1.0
%output application/java
---
{
	"domain":p('core.services.domain'),
	"body": flowVars.exceptionMessage,
	"severity":"Critical",
    "integrationId":p('basecamp.integration.id'),
      "userId":flowVars.response.instanceDetails.userId
}