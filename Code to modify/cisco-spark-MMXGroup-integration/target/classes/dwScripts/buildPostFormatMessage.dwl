%dw 1.0
%output application/json
---
{
	"formatMessage":[{
		"markDown":flowVars['bufferStream'],
		"text":null,
		"html":null
	}]
}