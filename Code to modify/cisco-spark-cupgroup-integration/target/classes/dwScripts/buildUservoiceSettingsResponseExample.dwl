%dw 1.0
%output application/json
---
{
  "webhookDetails": {
    "webhookUrl":flowVars['webhook'],
    "instanceUuid":flowVars['uuid']
  }
}