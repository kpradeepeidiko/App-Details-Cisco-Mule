%dw 1.0
%output application/json
---
payload map ((key, indexOfKey) -> {
	"pdfBytes":key.doc_pdf_bytes
})
