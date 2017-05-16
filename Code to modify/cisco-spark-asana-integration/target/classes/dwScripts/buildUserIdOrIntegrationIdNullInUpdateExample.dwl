%dw 1.0
%output application/json
---
{
	"statusCode":500,
	"message":"integrationId or UserId are empty in update Flow",
	"IntegrationId":flowVars['integrationId'],
	"userId":flowVars['userId']
}