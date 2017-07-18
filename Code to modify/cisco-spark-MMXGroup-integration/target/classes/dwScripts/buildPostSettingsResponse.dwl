%dw 1.0
%output application/json
---
{
	"settings":{"userDetails":flowVars.userResponse,
					"projects":flowVars.projectsResponse}
	
}