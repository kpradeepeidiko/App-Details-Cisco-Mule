%dw 1.0
%output application/json
---
{
	"office365CalendarUser":flowVars['getCalendarUserResponse'],
	"office365CalendarsList":flowVars['calendersListResponse']
}