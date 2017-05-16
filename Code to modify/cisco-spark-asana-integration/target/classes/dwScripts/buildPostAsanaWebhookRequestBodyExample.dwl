%dw 1.0
%output application/json
---
{
	"data":{
		"resource": flowVars['project_id'],
    	"target": flowVars['webhook_url']
	}
}