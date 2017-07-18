%dw 1.0
%output application/json
---
{
 	"configJson": {
        "room_name": flowVars['Request']['configJson']['room_name'],
        "list_id": flowVars['Request']['configJson']['list_id'],
        "user_name": flowVars['Request']['configJson']['user_name'],
        "authenticated_to": flowVars['Request']['configJson']['authenticated_to'],
        "displayName": flowVars['Request']['configJson']['displayName'],
        "events": flowVars['Request']['configJson']['events'],
        "WebHookId":flowVars['WebHookId']
    }
}