%dw 1.0
%output application/json
---
{
    "applicationId": flowVars.integrationPostRequest.token.applicationId,
    "integrationId" : flowVars.integrationPostRequest.instanceId,
    "userId": flowVars.integrationPostRequest.userId,
    "accessToken": flowVars.integrationPostRequest.token.accessToken,
    "expires": flowVars.integrationPostRequest.token.expires,
    "refreshToken": flowVars.integrationPostRequest.token.refreshToken,
    "refreshExpires": flowVars.integrationPostRequest.token.refreshExpires
}