%dw 1.0
%output application/json
---
{
	"googleDriveUser": flowVars['getUsersResponse'],
	"googleDriveFolders&Files": flowVars['foldersResponse']
}