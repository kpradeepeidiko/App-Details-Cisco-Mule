%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"pdfBytes":key.pdf_bytes
})
