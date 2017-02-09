%dw 1.0
%output application/java
---
{
	"webhookUrl":flowVars.webhookUrl,
	"instanceUuid":flowVars.instanceUuid
}