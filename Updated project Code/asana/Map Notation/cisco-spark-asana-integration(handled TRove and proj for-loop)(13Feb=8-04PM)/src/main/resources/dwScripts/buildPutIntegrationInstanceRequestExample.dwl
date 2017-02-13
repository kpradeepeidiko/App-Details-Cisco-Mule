%dw 1.0
%output application/json
---
{
	"configJson": {
    "room_name": flowVars.request.configJson.room_name,
    "user_name": flowVars.request.configJson.user_name,
    "displayName": flowVars.request.configJson.displayName,
    "file_id": flowVars.request.configJson.file_id,
    "id": flowVars.hookResponse.id,
    "owner_name": flowVars.request.configJson.owner_name,
    "resourceId" : flowVars.hookResponse.resourceId,
    "authenticated_to": flowVars.request.configJson.authenticated_to
  }	
}