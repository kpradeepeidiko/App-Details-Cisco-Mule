%dw 1.0
%output application/json
---
{
	"domain":payload.domain,
	"body": payload.body,
	"messageSeverity":payload.severity,
    "integrationId":payload.integrationId,
    "userId":payload.userId
}