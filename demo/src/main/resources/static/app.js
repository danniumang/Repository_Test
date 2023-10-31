var stompClient=null;
function setConnected(connected){
console.log('setConnected: ' + connected);
	$("#connect").prop("disabled",connected);
	$("#disconnect").prop("disabled",!connected);
	if(connected){
		$("#conversation").show();
		$("#char").show();
	}else{
		$("#conversation").hide();
		$("#char").hide();
	}
	$("#greetings").html("");
	
	
}
function connect(){
	if(!$("#name").val()){
	console.log('Nnameget ');
		return;
	}
	var socket=new SockJS('/chat');
	stompClient=Stomp.over(socket);
	stompClient.connect({},function(frame){
	console.log('stompClient.connect');
		setConnected(true);
		stompClient.subscribe('/topic/greetings',function(greeting){
		console.log('Received: ' + greeting.body);
			showGreeting(JSON.parse(greeting.body));
		});
	});
}
function disconnect(){
	if(stompClient!==null){
		stompClient.disconnect();
	}
	setConnected(false);
}
function sendName(){
	stompClient.send("/app/helloWebSocket",{},JSON.stringify({'name':$("#name").val(),'content':$("#content").val()}));
}
function showGreeting(message){
	$("#greetings").append("<div>"+messge.name+":"+message.content+"</div>");
}
$(function (){
	$("#connet").click((function(){connect();}));
	$("#disconnet").click((function(){disconnect();}));
	$("#send").click((function(){sendName()();}));
})