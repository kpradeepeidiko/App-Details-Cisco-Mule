%dw 1.0
%output application/java
---
{
	"payload": flowVars['getPayload'],
	"exception": flowVars['exception']
}