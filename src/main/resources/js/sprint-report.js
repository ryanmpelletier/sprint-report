
//scripts like this have to be placed below the body tag because velocity will run AFTER the JavaScript
//probably some initialization stuff, like counting up an array of all the sprints

//each sprint object will have an array of userTime objects
var sprints = [];
sprints = document.getElementsByName("sprint");
for(var i = 0; i < sprints.length; i++){

    //this is so later when we initialize we can set the first one to expanded
    if(i == 0){
        sprints[i].isExpanded = true;
    }else{
        sprints[i].isExpanded = false;
    }
    
    sprints[i].userTimes = {};
}


//this function will probably take the id of a sprint, so that it can either expand or collapse the sprint
function expandOrCollapse(sprintID){
    
	if(sprints[sprintID].isExpanded){
		//collapse sprint
		alert("Collapsing Sprint " + sprintID);
		sprints[sprintID].isExpanded = false;
	}else{
	    //expand sprint
	    alert("Expanding Sprint " + sprintID);
	    sprints[sprintID].isExpanded = true;
	}
}