%dw 1.0
%output application/json
---
{
	"integrationId": flowVars['integrationId'],
	"userId": flowVars['userId'],
	"roomType":flowVars['roomType'],
	"sparkResponse": flowVars['getMessageById']
}