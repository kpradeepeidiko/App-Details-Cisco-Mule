%dw 1.0
%output application/json
---
{
	"formatMessage":[{
		"markDown":flowVars.postMessage,
		"text":null,
		"html":null
	}]
}