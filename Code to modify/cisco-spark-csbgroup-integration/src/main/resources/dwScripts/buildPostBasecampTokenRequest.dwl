%dw 1.0
%output application/java
---
{
	"client_id": '${bitbucket.integration.client.id}',
    "client_secret": '${bitbucket.integration.client.secret}',
 	"grant_type": '${bitbucket.integration.grant.type}',
	"code": payload.authSettings.code
	
}