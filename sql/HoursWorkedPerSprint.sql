SELECT
	si.sprint,
    wl.author,
    round(sum(wl.timeworked) / 60/60, 2) as timeWorked
FROM sprint_issues si
JOIN jiradb.worklog wl ON wl.issueid = si.issueid
WHERE si.projectid = 10000 #changethis
	#and si.sprintid IN ('205') #changethistoo
    and si.sprintid = 1
    and wl.created > (str_to_date('01,01,1970','%d,%m,%Y') + (si.sprintstart/1000/60/60/24))#this is confusing to me
GROUP BY si.sprint, wl.author
ORDER BY si.sprint;