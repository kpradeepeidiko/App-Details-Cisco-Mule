%dw 1.0
%output application/json
---
  {	
	"configJson": {
		
		"project_id":flowVars['projectId'],
		"displayName":flowVars['displayName'],
	    "notifications": flowVars['events'],
	    "event":flowVars['event'],
	    "commentType":flowVars['commentType'],
	  	"webhook_id":flowVars['postHookResponse']['id'],
	  	"isScheduledEntry":flowVars['isScheduledEntry']
		}
		
	}