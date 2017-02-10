%dw 1.0
%output application/json
---
{
	"id":payload[0].GENERATED_KEY,
	"message":"Operation successfully executed"
}