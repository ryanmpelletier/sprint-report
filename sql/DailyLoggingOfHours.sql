#there must be a view called SPRINT_ISSUE
#changed this to use date_format, and round
#this query needs a sprintId and an projectId which are used to join to the sprintIssue table
SELECT
	si.sprint,
    wl.author,
    date_format(wl.startdate, '%Y-%m-%d') as startDate,
    round(sum(wl.timeworked)/60/60,2) as timeWorked
    FROM SPRINT_ISSUES si
    JOIN jiradb.worklog wl ON wl.issueid = si.issueid
    WHERE si.projectid = 10200
		#and si.sprintid IN ('482')
        #and wl.created > sysdate() - 5
        #and si.sprintid = 1
	GROUP BY si.sprint, wl.author, date_format(wl.startdate, '%Y-%m-%d')
    ORDER BY si.sprint, date_format(wl.startdate, '%Y-%m-%d') DESC;
    

