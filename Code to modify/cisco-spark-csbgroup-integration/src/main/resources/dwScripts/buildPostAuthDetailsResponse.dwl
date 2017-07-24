%dw 1.0
%output application/json
---
{
 	"accessToken":flowVars['postTokenResponse']['access_token'],
 	"refreshToken":flowVars['postTokenResponse']['refresh_token'],
 	"expires":flowVars['postTokenResponse']['expires_in'],
 	"refreshExpires":"",
 	"scopes":flowVars['postTokenResponse']['scopes'],
 	"tokenType":flowVars['postTokenResponse']['token_type']
}