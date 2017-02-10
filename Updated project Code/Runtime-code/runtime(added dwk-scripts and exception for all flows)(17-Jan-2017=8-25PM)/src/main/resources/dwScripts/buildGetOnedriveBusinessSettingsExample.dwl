%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"tokenId":key.token_id,
	"userId":key.user_id,
	"deltaLink":key.delta_link
})