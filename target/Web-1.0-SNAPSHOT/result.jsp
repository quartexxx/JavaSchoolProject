
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <h1>Result</h1>

        <p>Result: ${result}</p>


        <p>Total operations: <%= request.getAttribute("operationCounter") %></p> 

        <h2>History:</h2>
        <a id="historyButton" href="/serwer/history">Get History</a>
        <ul id="historyList"></ul>

        <a id="backButton" href="/serwer/">Go back</a>
    </body>
</html>
