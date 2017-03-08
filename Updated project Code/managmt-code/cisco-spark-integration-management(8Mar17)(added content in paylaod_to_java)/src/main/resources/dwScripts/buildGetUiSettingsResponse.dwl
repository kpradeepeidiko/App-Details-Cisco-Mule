%dw 1.0
%output application/json
---
{
	"sparkRoomSettings": payload[0]['sparkRoomSettings'],
	"integrationSpecificSettings": payload[1]['integrationSpecificSettings']
	
	
}