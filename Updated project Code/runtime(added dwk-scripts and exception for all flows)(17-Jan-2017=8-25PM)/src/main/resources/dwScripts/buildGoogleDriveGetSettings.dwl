%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"tokenId":key.token_id,
	"webhookId":key.webhook_id,
	"resourceId":key.resource_id,
	"userId":key.user_id,
	"pageToken":key.page_token,
	"cursorTime":key.cursor_timestamp
})
