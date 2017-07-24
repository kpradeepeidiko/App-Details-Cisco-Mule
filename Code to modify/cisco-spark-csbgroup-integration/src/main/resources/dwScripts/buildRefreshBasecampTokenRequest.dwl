%dw 1.0
%output application/java
---
{
   "client_id": '${basecamp.integration.client.id}',
   "client_secret": '${basecamp.integration.client.secret}',
   "grant_type": 'refresh_token',
   "refresh_token": flowVars.refreshToken
}