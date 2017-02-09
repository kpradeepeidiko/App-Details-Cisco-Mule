%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"userId":key.user_id,
	"cursorTime":key.cursor_timestamp
})
