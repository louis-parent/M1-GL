<%--
  Created by IntelliJ IDEA.
  User: louis.parent@etu.umontpellier.fr
  Date: 17/02/2022
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>TP5</title>

    <script>
      function longAction()
      {
        alert("Fetching long action...");

        fetch("/TP5/long").then(data => {
          data.text().then(text => {
            alert(text);
          });
        });
      }
    </script>
  </head>
  <body>
    <button onclick="longAction()">Bonjour</button>
  </body>
</html>
