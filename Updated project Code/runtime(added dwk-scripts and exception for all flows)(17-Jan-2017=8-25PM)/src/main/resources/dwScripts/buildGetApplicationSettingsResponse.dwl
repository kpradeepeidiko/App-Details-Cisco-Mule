%dw 1.0
%output application/json
---
{
    "applicationId":payload[0].application_id,
    "accessClientId":payload[0].access_client_id,
    "name":payload[0].name,
    "logo":payload[0].logo,
    "contactEmail":payload[0].contact_email,
    "scopes":payload[0].scopes,
    "integrationId":payload[0].integration_id,
    "redirectUrls":payload[0].redirect_urls,
    "accessClientSecret":payload[0].access_client_secret
    }