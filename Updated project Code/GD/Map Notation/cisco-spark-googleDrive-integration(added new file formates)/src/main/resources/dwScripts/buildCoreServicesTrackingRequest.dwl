%dw 1.0
%output application/json
---
{
  "date": now,
  "integrationId": flowVars.integrationId,
  "fields": {
    "transactionId": "sdsefefer",
    "specificField1": "specific value 1",
    "specificField2": "specific value 2",
    "specificField3": "specific value 3"
  }
}