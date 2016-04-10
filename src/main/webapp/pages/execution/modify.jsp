<!DOCTYPE html>

<%@ page import="lian.artyom.dao.impl.TasksDAOImpl" %>
<%@ page import="lian.artyom.dao.TasksDAO" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="java.util.List" %>
<%@ page import="lian.artyom.dao.pojo.Action" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.lang.Boolean" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="lian.artyom.dao.pojo.Task" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="lian.artyom.dao.DAOHelper" %>

    <html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modifying task</title>
        <link rel="stylesheet" href="../css/foundation.css">
        <link rel="stylesheet" href="../css/app.css">
    </head>

    <body>
    <a href='../../index.jsp' class='button'>MAIN </a>

    <div class='row'>

    <p class='primary callout'>Task was updated. New parameters: </p>

    <%
       String name = request.getParameter("name"),
        status = request.getParameter("status"),
        time = request.getParameter("time"),
        action = request.getParameter("action"),
        comment = request.getParameter("comment"),
        alarm = request.getParameter("alarm"),
        id = request.getParameter("id");

        DAOHelper helper = DAOHelper.getDAOHelper();
        helper.modifyTask(id, name, status, time, action, comment, alarm);

        out.println("<p>New task was successfully created and scheduled");
        out.println("<p>Task name:" + name + "</p>");
        out.println("<p>Current task status:" + status + "</p>");
        out.println("<p>Task action:" + action + "</p>");
        out.println("<p>Task scheduled time:" + time + "</p>");
        out.println("<p>Task commentary:" + comment + "</p>");
        out.println("<p>Alarm:" + alarm + "</p>");
        out.print("<a class=\'button\' href=\'../task.jsp?id=");
        out.print(id);
        out.print("\'>TASK</a>");
    %>


    </div>
    </body>

    </html>