%dw 1.0
%output application/json
---
{
  "accessToken": flowVars['GCAccessToken'],
  "expires": flowVars['expires_in']
}