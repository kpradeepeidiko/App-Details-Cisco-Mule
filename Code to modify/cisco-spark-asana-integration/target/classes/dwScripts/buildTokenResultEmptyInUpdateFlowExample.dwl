%dw 1.0
%output application/json
---
{
	"statusCode":500,
	"message":"Token Result Empty In Update Flow",
	"TokenResult":flowVars['integrationTokenResponse']
}