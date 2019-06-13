const ws = new WebSocket("ws://echo.websocket.org");

const sendBtn = document.getElementById("send");

sendBtn.onclick = function(event) {
  event.preventDefault();
  const text = document.getElementById("text");
  ws.send(text.value);
}

ws.onopen = function() {
  const sender = document.getElementById("sender");
  sender.innerText = '손님' + Math.round(Math.random() * 100);
}

ws.onmessage = function(event) {
  const data = event.data;
  const text = document.getElementById("text");
  const messageDiv = document.getElementById("message");
  
  const div = document.createElement("div");
  const sender = document.createElement("h5");
  sender.innerText = document.getElementById("sender").innerText;
  const message = document.createElement("p");
  message.innerText = text.value;
  div.appendChild(sender);
  div.appendChild(message);
  messageDiv.appendChild(div);
  text.value = "";
  text.focus();
}

ws.onclose = function(event) {
  const code = event.code;
  const reason = event.reason;
  const wasClean = event.wasClean;
  console.log(code, reason, wasClean);
}

ws.onerror = function() {};