%dw 1.0
%output application/json
---
{
  "accessToken": flowVars['token'],
  "expires": flowVars['cDateTimeValue']
}