%dw 1.0
%output application/json
---
{
	"integrationGeneralSettings": flowVars.integrationSettings,
	"integrationSpecificSettings": flowVars.dynamicSettings,
	"sparkRoomSettings": flowVars.sparkRoomSettings,
	"sparkProfileSettings": flowVars.sparkProfileSettings		
}