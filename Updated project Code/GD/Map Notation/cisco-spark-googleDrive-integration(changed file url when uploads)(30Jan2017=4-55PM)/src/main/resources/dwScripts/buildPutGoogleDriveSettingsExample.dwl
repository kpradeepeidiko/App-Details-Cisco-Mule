%dw 1.0
%output application/json
---
{
	"pageToken":flowVars['newPageToken'],
	"cursorTime": flowVars['time2']	
}