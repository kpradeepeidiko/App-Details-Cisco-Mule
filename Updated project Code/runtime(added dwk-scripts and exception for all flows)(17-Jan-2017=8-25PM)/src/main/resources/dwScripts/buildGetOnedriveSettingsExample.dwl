%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"tokenId":key.token_id,
	"userId":key.user_id,
	"deltaId":key.delta_id
})