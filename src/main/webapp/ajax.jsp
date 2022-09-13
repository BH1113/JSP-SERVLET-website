<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello world</title>
</head>
<body>
    <form action="./RequestServlet" method="GET">
        <input type="submit" value="GET">
    </form>
    <form action="./RequestServlet" method="POST">
         <input type="submit" value="POST">
    </form>
    <input id="btn_put" type="button" value="PUT"><br>
    <input id="btn_del" type="button" value="DELETE">
    <script type="text/javascript">
        var xhr = new XMLHttpRequest();
        document.getElementById("btn_put").addEventListener("click", function() {
            xhr.onreadystatechange = function() {
                if (xhr.readtyState === xhr.DONE) {
                    if (xhr.status === 200 || xhr.status === 201) {
                        console.log(xhr.responseText);
                    } else {
                        console.error(xhr.responseText);
                    }
                }
            };

            xhr.open("PUT", "http://localhost:8080/webtest/RequestServlet");
            xhr.send();
        });

        document.getElementById("btn_del").addEventListener("click", function() {
            xhr.onreadystatechange = function() {
                if (xhr.readtyState === xhr.DONE) {
                    if (xhr.status === 200 || xhr.status === 201) {
                        console.log(xhr.responseText);
                    } else {
                        console.error(xhr.responseText);
                    }
                }
            };

            xhr.open("DELETE", "http://localhost:8080/webtest/RequestServlet");
            xhr.send();
        });
    </script>
</body>
</html>