let stompClient = null;

const URL = "http://localhost:8082"

function connect() {
    let socket = new SockJS(URL + '/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);

        stompClient.subscribe(URL + '/view/messages', function (greeting) {
            console.log("Message received: " + greeting.body)
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }

    console.log("Disconnected");
}

function sendName() {
    stompClient.send(URL + "/notification",
        {},
        JSON.stringify({
            'message': "Notifications are working. Hopefully."
        }));
}

function showGreeting(message) {
    console.log(message);
}

connect();