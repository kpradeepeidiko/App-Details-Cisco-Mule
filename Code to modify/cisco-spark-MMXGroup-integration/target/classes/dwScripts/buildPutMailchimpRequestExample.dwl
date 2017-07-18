%dw 1.0
%output application/java
---
{
    "url": payload['url'],
    "sources": payload['sources'],
    "events": payload['events'],
    "webhook id":payload['id']
}