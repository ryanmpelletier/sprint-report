	SELECT pname, project, summary, wl.*, s.* FROM jiradb.jiraissue i 
    JOIN jiradb.project p ON (p.ID = i.project)
    JOIN jiradb.worklog wl ON (wl.issueid = i.ID)
    JOIN jiradb.ao_60db71_sprint s ON (unix_timestamp(wl.startdate) * 1000 BETWEEN s.start_date AND s.end_date ) ;
   
   
   #Since end_date does not update when completed, only use it if complete_date is null
	SELECT s.name as sprint, wl.author as author, round(wl.timeworked/60/60,2) as timeworked, wl.startdate as date
		FROM jiradb.jiraissue i 
		JOIN jiradb.project p ON (p.ID = i.project)
		JOIN jiradb.worklog wl ON (wl.issueid = i.ID)
		JOIN jiradb.ao_60db71_sprint s ON (s.start_date <= unix_timestamp(wl.startdate) * 1000 AND unix_timestamp(wl.startdate) * 1000 <= ifnull(s.COMPLETE_DATE,s.END_DATE))
		WHERE p.id = 10000
        AND (s.name LIKE ifnull(NULL,'%'))
        AND (wl.author LIKE ifnull('rpelletier','%'))
		ORDER BY s.name, startdate;
    
    #so to this query I need to add a username (author)
    #I can also add a filter to filter it by sprint
    #optionally, a user can be specified
    
    #all this is doing is showing worklogs for particular sprints
    
	#Since end_date does not update when completed, only use it if complete_date is null
    SELECT s.name as sprint, wl.author, round(wl.timeworked/60/60,2) as timeworked, wl.startdate as date
    FROM jiradb.jiraissue i 
    JOIN jiradb.project p ON (p.ID = i.project)
    JOIN jiradb.worklog wl ON (wl.issueid = i.ID)#this is where my resultset gets big
    JOIN jiradb.ao_60db71_sprint s ON (s.start_date <= unix_timestamp(wl.startdate) * 1000 AND unix_timestamp(wl.startdate) * 1000 <= ifnull(s.COMPLETE_DATE,s.END_DATE))
    #probably some way to filter projects
    WHERE p.id = 10000
	ORDER BY s.name, startdate; 
    