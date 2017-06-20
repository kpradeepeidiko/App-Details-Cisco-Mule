%dw 1.0
%output application/json
---
  {	
	"configJson": {
		
		"board_id": flowVars['settingsResponse']['configJson']['board_id'],
		"list_id": flowVars['settingsResponse']['configJson']['list_id'],
		"list_id":flowVars['settingsResponse']['configJson']['list_id'],
		"displayName": flowVars['settingsResponse']['configJson']['displayName'],
		"notifications": flowVars['settingsResponse']['configJson']['notifications'],
		"webhook_id":flowVars['hookResponse']['id']
		
		
	}

	}