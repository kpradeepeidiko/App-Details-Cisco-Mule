%dw 1.0
%output application/json
---
{
	"messageFromDynamicFlow":flowVars.formattedMessage
}