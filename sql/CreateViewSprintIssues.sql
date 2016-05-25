#changed to use concat, ifnull, and no to_number

CREATE OR REPLACE VIEW SPRINT_ISSUES AS
(
	SELECT
	ji.id as issueid,
	concat(p.pkey,'-',ji.issuenum) as issue_key,
	status.pname as status,
	ji.summary as summary,
	s.name as sprint,
	s.id as sprintid,
	s.start_date as sprintstart,
	p.pname as project,
	p.id as projectid,
	IFNULL(ji.timeestimate, 0)/60/60 as timeestimate,
	IFNULL(ji.timespent, 0)/60/60 as timespent
	FROM jiradb.project p
	JOIN jiradb.jiraissue ji ON (ji.project = p.id)
	LEFT JOIN jiradb.issuestatus status ON status.id = ji.issuestatus
	JOIN (SELECT * FROM jiradb.customfieldvalue WHERE customfield = 10004) cfv ON 
	cfv.issue = ji.id
	JOIN jiradb.ao_60db71_sprint s on s.id = cfv.stringvalue
);