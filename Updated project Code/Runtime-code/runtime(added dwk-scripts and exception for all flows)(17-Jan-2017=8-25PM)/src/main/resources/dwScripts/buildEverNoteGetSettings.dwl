%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"userId":key.user_id,
	"evernoteUserId":key.evernote_user_id,
	"createdDate":key.created_date,
	"instanceUuid":key.instance_uuid
})
