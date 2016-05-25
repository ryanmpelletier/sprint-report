
//scripts like this have to be placed below the body tag because velocity will run AFTER the JavaScript
//probably some initialization stuff, like counting up an array of all the sprints

//each sprint object will have an array of userTime objects

var sprints = [];

window.onload = function(){
	var sprints = document.getElementsByName("sprint");
	for(var i = 0; i < sprints.length; i++){

	    sprints[i].userTimes = [];

	    //this is so later when we initialize we can set the first one to expanded
	    if(i == 0){
	        sprints[i].isExpanded = true;
	    }else{
	        sprints[i].isExpanded = false;
	    }
	}


	var users = document.getElementsByName("user");
	for(var i = 0; i < users.length; i++){
		var sprintNumber = users[i].getAttribute("data-sprint-number");
		sprints[sprintNumber - 1].userTimes.push(users[i].innerHTML);
	}

	for(var i = 0; i < sprints.length; i++){
		alert(sprints[i].userTimes.length);
		for(var j = 0; j < sprints[i].usersTimes.length; j++){
			console.log(sprints[i].userTimes[j]);
		}
	}
}


//this function will probably take the id of a sprint, so that it can either expand or collapse the sprint
function expandOrCollapse(sprintID){
    
	if(sprints[sprintID - 1].isExpanded){
		//collapse sprint
		sprints[sprintID - 1].isExpanded = false;
		alert(sprints[sprintID - 1].userTimes[0]);
	}else{
	    //expand sprint
	    sprints[sprintID - 1].isExpanded = true;
		alert(sprints[sprintID - 1].userTimes[0]);
	    
	}
}