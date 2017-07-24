%dw 1.0
%output application/json
---
  {
  "payload_url":flowVars['url'],
  "types": payload
}