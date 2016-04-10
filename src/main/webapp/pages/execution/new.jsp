<!DOCTYPE html>

<%@ page import="lian.artyom.dao.impl.TasksDAOImpl" %>
<%@ page import="lian.artyom.dao.TasksDAO" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="java.util.List" %>
<%@ page import="lian.artyom.dao.pojo.Action" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.lang.Boolean" %>
<%@ page import="java.lang.Integer" %>
<%@ page import="lian.artyom.dao.DAOHelper" %>

    <html class="no-js" lang="en" dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Task manager for EE systems</title>
        <link rel="stylesheet" href="../css/foundation.css">
        <link rel="stylesheet" href="../css/app.css">
    </head>
    <body>

    <a href='../../index.jsp' class='button'>MAIN</a>

    <div class='row'>

    <%
        String name = request.getParameter("name"),
                        status = request.getParameter("status"),
                        time = request.getParameter("time"),
                        action = request.getParameter("action"),
                        comment = request.getParameter("comment"),
                        alarm = request.getParameter("alarm");

        DAOHelper helper = DAOHelper.getDAOHelper();
        int taskId = helper.createTask(name, status, time, action, comment, alarm);

        out.println("<p>New task was successfully created and scheduled");
        out.println("<p>Task name:" + name + "</p>");
        out.println("<p>Current task status:" + status + "</p>");
        out.println("<p>Task action:" + action + "</p>");
        out.println("<p>Task scheduled time:" + time + "</p>");
        out.println("<p>Task commentary:" + comment + "</p>");
        out.println("<p>Alarm:" + alarm + "</p>");
        out.print("<a class=\'button\' href=\'../task.jsp?id=");
        out.print(taskId);
        out.print("\'>TASK</a>");
    %>

    </div>

    </body>

    </html>