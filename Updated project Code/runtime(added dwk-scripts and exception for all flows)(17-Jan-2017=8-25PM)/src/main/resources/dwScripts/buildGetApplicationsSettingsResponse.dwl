%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
    "applicationId":key.application_id,
    "accessClientId":key.access_client_id,
    "name":key.name,
    "logo":key.logo,
    "contactEmail":key.contact_email,
    "scopes":key.scopes,
    "integrationId":key.integration_id,
    "redirectUrls":key.redirectly_urls
})