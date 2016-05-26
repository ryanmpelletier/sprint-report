-- SELECT
-- 	si.sprint,
--     round(sum(wl.timeworked) / 60/60, 2) as timeWorked
-- FROM sprint_issues si
-- JOIN jiradb.worklog wl ON wl.issueid = si.issueid
-- WHERE si.projectid = 10200 #changethis
--     and wl.created > (str_to_date('01,01,1970','%d,%m,%Y') + (si.sprintstart/1000/60/60/24))
-- GROUP BY si.sprint
-- ORDER BY si.sprint;

SELECT 
--  si.project,
--  si.projectid,
--  si.issueid,
    si.sprint,
    si.sprintstart,
    si.sprintend,
--  wl.author,
    sum(wl.timeworked),
    unix_timestamp(wl.STARTDATE) * 1000 as worklogstart
--  wl.timeworked
 FROM jiradb.sprint_issues2 si JOIN jiradb.worklog as wl ON (wl.issueid = si.issueid) WHERE projectid = 10200
 GROUP BY (worklogstart > sprintstart AND worklogstart < sprintend);
 
--  One issue I am having is that sprint issues has issues being in more than 1 sprint, then when I join in the
--  worklog I get multiple "links" to sprints per worklog entry, which is why I need to filter based on time!
 
 SELECT 
	 si.project,
	 si.projectid,
	 si.issueid,
	 si.sprint,
	 si.sprintstart,
	 si.sprintend,
	 wl.author,
	 unix_timestamp(wl.STARTDATE) * 1000 as worklogstart,
	 wl.timeworked
	 FROM jiradb.sprint_issues2 si JOIN jiradb.worklog as wl ON (wl.issueid = si.issueid
     AND (unix_timestamp(wl.STARTDATE) * 1000 > sprintstart AND unix_timestamp(wl.STARTDATE) * 1000 < sprintend))
     WHERE projectid = 10200
     ;