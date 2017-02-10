%dw 1.0
%output application/json
---
{
	"guid":flowVars['guid'],
	"notebookGuid":flowVars['notebookGuid'],
	"reason":flowVars['reason'],
	"userId":flowVars['userId']
}