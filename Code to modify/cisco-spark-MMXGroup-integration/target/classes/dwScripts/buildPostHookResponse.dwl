%dw 1.0
%output application/json
---
{
    
    "configJson": {
        "room_name": flowVars['Request1']['configJson']['room_name'],
        "list_id": flowVars['Request1']['configJson']['list_id'],
        "user_name": flowVars['Request1']['configJson']['user_name'],
        "authenticated_to": flowVars['Request1']['configJson']['authenticated_to'],
        "displayName": flowVars['Request1']['configJson']['displayName'],
        "events": flowVars['Request1']['configJson']['events'],
        "WebHookId":flowVars['WebHookId'],
        "list_name":flowVars['Request1']['configJson']['list_name']
    }
}