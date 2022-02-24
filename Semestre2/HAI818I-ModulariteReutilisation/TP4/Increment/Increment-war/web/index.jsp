<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="counter" scope="page" class="increment.CounterBean" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Counter</title>
    </head>
    <body>
        <%
            if(request.getParameter("start") != null)
            {
                counter.start();
            }
            else if(request.getParameter("stop") != null)
            {
                counter.stop();
            }
            else if(request.getParameter("incr") != null)
            {
                counter.increment();
            }
            else if(request.getParameter("decr") != null)
            {
                counter.decrement();
            }
        %>
        <h1>Increment JSP</h1>
        <h2>Counter : <% out.println(counter.getCount()); %></h2>
        <form action="${pageContext.request.contextPath}" method="get">        
            <input type="submit" name="start" value="Start" />
            <input type="submit" name="stop" value="Stop" />
            <input type="submit" name="incr" value="+" />
            <input type="submit" name="decr" value="-" />
        </form>
    </body>
</html>
