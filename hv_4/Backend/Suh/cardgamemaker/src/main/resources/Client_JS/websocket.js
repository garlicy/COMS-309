var ws;

function connect() {
    var username = document.getElementById("username").value;
    var url = "ws://localhost:8080/websocket/" + username;
   // var url = "ws://echo.websocket.org";

    ws = new WebSocket(url);

    ws.onmessage = function(event) {
        console.log(event.data);

        // display on browser
        var log = document.getElementById("log");
        log.innerHTML += event.data + "\n";
    };

    ws.onopen = function(event) {
        var log = document.getElementById("log");
        log.innerHTML += "Connected to " + event.currentTarget.url + "\n";
    };
}

function send() {  // this is how to send messages
    var content = document.getElementById("msg").value;
    ws.send(content);
}
