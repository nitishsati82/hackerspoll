/*
 * 
 * 
 * 
 * This js file is used for todo app client operation
 * 
 * 
 * 
 * 
 * 
 * */

var COMPLETE_COUNTER = 0;
var LABEL_ARRAY = [];
var dataArray = [];
var  counter = 0;
function startOnLoadToDo(){
	setDatePicker();
	$("#forgotPassword").click(function(){
		  $('#loginModal').modal('hide');
		  $("#forgetPasswordModal").modal();
	  });
	  $("#noAccount").click(function(){
		  $('#loginModal').modal('hide');
		  $("#signUp").modal();
	  });
}
/*jquery date picker setting*/
function setDatePicker(){
	var sDateFormat="dd-mm-yy";	
	$( ".dateTime" ).datepicker({
		 dateFormat : sDateFormat // SET THE FORMAT.
	    ,yearRange : '1920:2050' //starting year
	    ,changeMonth : true // month list
		,changeYear : true //year list
		,orientation: "top"
		,onClose: function(dateText, inst) {
            $(this).datepicker('option', 'dateFormat', sDateFormat); //clear texfield if date is not in correct format
            $(this).change();
        }
	});
}
/*show confirm alert*/
function showConirmPopup(message) {
	var result = confirm(message);
	if (!result) {
		return false;
	} else {
		return true;
	}
}
/*load and paint lists*/
function loadExpertArea(areaData){
	var ul = document.getElementById("expertAreaList");
	$(ul).empty();
	document.getElementById("taskLabel").value = "";
	$('#taskLabel').empty();
	$('#taskLabel').append($('<option>').val('').text('--Select--'));
	if(LABEL_ARRAY!=undefined && LABEL_ARRAY.length>0){
		for(var i=0;i<areaData.length;i++){
			var li = document.createElement("li");
			var labelText = document.getElementById("labelText");
			var input = document.createElement("input");
			li.appendChild(document.createTextNode(LABEL_ARRAY[i].label_name));
			li.appendChild(document.createTextNode(LABEL_ARRAY[i].label_name));
			li.setAttribute("id", LABEL_ARRAY[i].id);
			li.setAttribute("label_type", LABEL_ARRAY[i].created_by);
			li.setAttribute("class", "list-group-item d-flex justify-content-between align-items-center pointer");
			li.setAttribute('onclick', "_loadLabelTask(this);");
		
			var optionTextAndValue = LABEL_ARRAY[i].label_name; 
	        $('#taskLabel').append($('<option>').val(optionTextAndValue).text(optionTextAndValue));
	        ul.appendChild(li);
		}
	}
}

/*set data*/
function showCandidateList(obj){
	if(obj!=undefined && obj.length>0){
		for(var i=0;i<obj.length;i++){
			var data = obj[i].id+"^"+obj[i].hackername+"^"+obj[i].solved_challanges+"^"+obj[i].explevel+"^"+obj[i].vote;
			var rowData = "<tr id='row_"+obj[i].id+"'><td>"+obj[i].hackername+"</td><td>"+obj[i].solved_challanges+"</td><td>"+obj[i].explevel+"</td><td><a href='#'><span class='glyphicon glyphicon-edit' onclick='editCandidate(this);' data='"+data+"' id='edit_"+obj[i].id+"'></span></td><td><a href='#'><span class='glyphicon glyphicon-trash' deleted='N' onclick='deleteCandidate(this);' data='"+data+"' id='delete_"+obj[i].id+"'></span></td></tr>";
			var voteData = "<tr id='row_"+obj[i].id+"'><td>"+obj[i].hackername+"</td><td>"+obj[i].vote+"</td></tr>";
 
			$("#candidateTable tbody").append(rowData);
			$("#voteTable tbody").append(voteData);
		}
	}
}

/*set data*/
function viewCandidateList(obj){
	if(obj!=undefined && obj.length>0){
		for(var i=0;i<obj.length;i++){
			var data = obj[i].id+"^"+obj[i].hackername+"^"+obj[i].solved_challanges+"^"+obj[i].explevel;
			var rowData = "<tr id='row_"+obj[i].id+"'><td width='20%'>"+obj[i].hackername+"</td><td><a href='#'><span title='click here to view candidate details' class='glyphicon glyphicon-fullscreen' onclick='editCandidate(this);' data='"+data+"' id='show_"+obj[i].id+"'></span></td></tr>";
			 $("#candidateTable tbody").append(rowData);
		}
	}
}

function showExprtArea(obj){
	$('#expertList').empty();
	if(obj!=undefined && obj.length>0){
		for(var i=0;i<obj.length;i++){
			var optionTextAndValue = obj[i].expertarea; 
	        $('#expertList').append($('<option>').val(optionTextAndValue).text(optionTextAndValue));
	        var rowData = "<tr id='row_"+obj[i].id+"'><td width='20%'>"+optionTextAndValue+"</td></tr>";
			 $("#expList tbody").append(rowData);
	  }
	}
}

function showMyModal(){
	document.getElementById("addToDo_Form").reset();
	var expertAreaTable = document.getElementById("expertAreaTable");
	$(expertAreaTable).empty();
	$('#myModal').modal('show');
	$('#voteCandidateButton').hide();
	dataArray = [];
}
function closeModal(){
	$('#myModal').modal('hide');
}
function editCandidate(obj) {
	var data = $(obj).attr("data");
	var id = $(obj).attr("id");
	var hackerName = document.getElementById('hackername');
	var solved_challanges = document.getElementById('solved_challanges');
	var explevel = document.getElementById('explevel');
	var addCandidateButton = document.getElementById('addCandidateButton');
	var candidate_id = document.getElementById('candidate_id');
	if(id.split("_")[0]=='edit'){
		addCandidateButton.value="Edit candidate";
		$('#voteCandidateButton').show();
	}else if(id.split("_")[0]=='show'){
		addCandidateButton.value="Vote";
		addCandidateButton.title="Click here to vote to candidate.";
	}
	var parentIndex = obj.parentElement.parentElement.parentElement.rowIndex;
	if(data!=undefined){
		data = data.split("^");
		hackerName.value = data[1];
		solved_challanges.value = data[2];
		explevel.value = data[3];
		candidate_id.value = id.split("_")[1];
	}
	setExpertAreaValue(id.split("_")[1],id.split("_")[0]);
	$('#myModal').modal('show');
}

/*add expert area data*/
function addExpertAreaTable(){
	var expertList = document.getElementById("expertList");
	var expertAreaTable = document.getElementById("expertAreaTable");
	if(expertList!=undefined){
		if(expertList.value==''){
			alert("Kindly select expert area.");
			return false;
		}
		if(dataArray.length>0){
			for(var i=0;i<dataArray.length;i++){
				if(dataArray[i]==expertList.value){
					alert("expertise area already added.");
					return false;
				}
			}
		}
		var totalRow = expertAreaTable.rows.length;
		if(totalRow>0)counter = totalRow;
		dataArray.push(expertList.value);
		expertAreaTable.innerHTML += '<tr id="expArea_"'+expertList.value+'"><td width="50%"><span>'+expertList.value+'</span></td><td width="50%"><input class="form-control inputBox" id="input_'+counter+'" data="input_'+expertList.value+'" type="number" pattern="\d{1,5}" title="Only digits" value="0" maxlength="1" min="0" max="5"></td><td width="10%"><a href="#"><span class="glyphicon glyphicon-trash" title="click here to delete expert area." onclick="deleteExpertArea(this);" deleted="N" data="" id="'+counter+'_delete_'+expertList.value+'"></span></a></td></tr>'
		counter++;
	}
}
/*set expert area data*/
function setExpertAreaValue(candidateId,action){
	var url = 'http://localhost:9090/candidate/getAllExpertArea/'+candidateId;
	var expertAreaTable = document.getElementById("expertAreaTable");
	$(expertAreaTable).empty();
	var rowcount = 0;
	$.ajax({
		type: "GET",
		url: url
	}).done(function(response){
		if(response!=null){
			for(var i=0;i<response.length;i++){
				if(action=='show'){
					expertAreaTable.innerHTML += '<tr id="expArea_"'+response[i].expertarea+'"><td width="50%"><span>'+response[i].expertarea+'</span></td><td width="50%"><input disabled class="form-control inputBox" id="input_'+response[i].id+'" data="input_'+response[i].expertarea+'_" dataid="'+response[i].id+'"  value="'+response[i].rating+'" title="'+response[i].rating+'" style="text-align: center;"></td></tr>'
				}else{
					expertAreaTable.innerHTML += '<tr id="expArea_"'+response[i].expertarea+'"><td width="40%"><span>'+response[i].expertarea+'</span></td><td width="50%"><input class="form-control inputBox" id="input_'+rowcount+'" org-id="input_'+response[i].id+'" data="input_'+response[i].expertarea+'_" dataid="'+response[i].id+'"  value="'+response[i].rating+'" type="number" pattern="\d{1,5}" title="Only digits" maxlength="1" min="0" max="5"></td><td width="10%"><a href="#"><span class="glyphicon glyphicon-trash" title="click here to delete expert area." onclick="deleteExpertArea(this);" deleted="N" data="'+candidateId+'" id="'+response[i].id+'_delete_'+response[i].expertarea+'"></span></a></td></tr>'
					dataArray.push(response[i].expertarea);
				}
				rowcount++;
			}
		}
		
	}).fail(function(){
		console.log(" Fail api ");
	})
}

function deleteExpertArea(obj){
	var data = $(obj).attr("data");
	var id = $(obj).attr("id");
	var deleteC = showConirmPopup("Are you sure to delete expert area.");
	if(!deleteC)return false;
	//if(data=='')return true;
	var parentIndex = obj.parentElement.parentElement.parentElement.rowIndex;
	var rowParentElement = obj.parentElement.parentElement.parentElement;
	if(rowParentElement!=undefined){
		rowParentElement.style.display="none";
		$("#"+id).attr("deleted","Y");
	}
}
function deleteCandidate(obj){
	var data = $(obj).attr("data");
	var id = $(obj).attr("id");
	var candidateId = id.split("_")[1];
	var deleteC = showConirmPopup("Are you sure to delete expert area.");
	if(!deleteC)return false;
	//if(data=='')return true;
	var parentIndex = obj.parentElement.parentElement.parentElement.rowIndex;
	var rowParentElement = obj.parentElement.parentElement.parentElement;
	if(rowParentElement!=undefined){
		rowParentElement.style.display="none";
		$("#"+id).attr("deleted","Y");
	}
	
	try{
		var url="http://localhost:9090/candidate/delete/"+candidateId;     
		$.ajax({
			type: "POST",
			url: url
		}).done(function(obj){
			if(obj=='Y'){
				alert("Candidate deleted successfully.");
				updateCandidateTable();
			}else{
				alert("Error in operation.")
			}

		}).fail(function(){
			console.log(" Fail api ");
		})
	}catch (e){
		alert(e);
	}
}
function updateCandidateTable(){
	try{
		var url = "http://localhost:9090/candidate/getlist"     
		$.ajax({
			type: "GET",
			url: url
		}).done(function(obj){
			$('#candidateTable tbody').empty();
			$("#voteTable tbody").empty();
			showCandidateList(obj);
		}).fail(function(){
			console.log(" Fail api ");
		})
	}catch (e){
		alert(e);
	}
}

/*logout*/
function logout(){
	try{
		var created_by = document.getElementById("created_by");
		var sessionKey = document.getElementById("sessionKey");
		var url="http://localhost:9090/userservice/logout/"+created_by.value+"/"+sessionKey.value;     
		$.ajax({
			type: "GET",
			url: url
		}).done(function(obj){
			if(obj=='Y'){
				$('#loginlink').show();
				$('#addNewExpertArea').hide();
				$('#logOutLink').hide();
				$('#mainDiv').hide();
				$('#viewAllExpert').hide();
				$('#viewAlVote').hide();
				created_by.value = "";
				sessionKey.value = "";
			}

		}).fail(function(){
			console.log(" Fail api ");
		})
	}catch (e){
		alert(e);
	}
}
/*login user*/
function loginUser(data){
	try{
		$('#loginModal').modal('hide');
		document.getElementById("login_form").reset();
		$('#loginlink').hide();
		$('#logOutLink').show();
		$('#mainDiv').show();
		$('#viewAllExpert').show();
		$('#viewAlVote').show();
		$('#addNewExpertArea').show();
		var created_by = document.getElementById("created_by");
		var sessionKey = document.getElementById("sessionKey");
		if(created_by!=undefined && created_by.value==''){
			created_by.value= data.id;
		}
		if(sessionKey!=undefined && sessionKey.value==''){
			sessionKey.value= data.session_index;
		}
	}catch (e){
		alert(e);
	}
}
/*load tasks */
function _loadLabelTask(obj){
	try{
		var id = $(obj).attr("id");
		var data = obj.innerText;
		var created_by = document.getElementById("created_by").value;
		var sessionKey = document.getElementById("sessionKey").value;
		var url="http://localhost:9596/todoservice/getToDoTaks/"+created_by+"/"+data+"/"+sessionKey;     
		$.ajax({
			type: "GET",
			url: url
		}).done(function(obj){
			$('#myTaskDetails').show();
			setDataInTable(obj);
		}).fail(function(){
			console.log(" Fail api ");
		})
	}catch (e){
		alert(e);
	}
}

function sortCandidate(obj){
	try{
		var sortBy = document.getElementById(obj.id).value;
		var url = "http://localhost:9090/candidate/geSortList/"+sortBy;     
		$.ajax({
			type: "GET",
			url: url
		}).done(function(obj){
			$('#candidateTable tbody').empty();
			$("#voteTable tbody").empty();
			showCandidateList(obj);
		}).fail(function(){
			console.log(" Fail api ");
		})
	}catch (e){
		alert(e);
	}
}