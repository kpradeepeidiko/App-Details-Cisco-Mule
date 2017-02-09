%dw 1.0
%output application/json
---
{
	"office365CalendarsList": flowVars.calendersListResponse,
	"office365CalendarUser" : flowVars.getCalendarUserResponse
}