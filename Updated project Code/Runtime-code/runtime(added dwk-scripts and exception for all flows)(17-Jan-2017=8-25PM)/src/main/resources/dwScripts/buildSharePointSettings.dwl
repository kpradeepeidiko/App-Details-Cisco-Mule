%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"userId":key.user_id,
	"tokenId":key.token_id,
	"deltaId":key.delta_id
})
