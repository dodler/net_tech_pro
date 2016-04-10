<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task manager for EE systems</title>
    <link rel="stylesheet" href="css/foundation.css">
    <link rel="stylesheet" href="css/app.css">
</head>
<body>

<%@ page import="lian.artyom.tools.DbTool" %>
<%@ page import="lian.artyom.dao.TasksDAO"%>
<%@ page import="lian.artyom.dao.impl.TasksDAOImpl"%>
<%@ page import="lian.artyom.dao.pojo.Task"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.StringBuffer"%>

<a href='../index.jsp' class='button'>MAIN </a>

<div class='row'>

<h1 class='primary callout'>Here you can configure and schedule new task.</h1>

<p class='primary callout'>Tasks:</h1>

</div>

<%
    TasksDAO dao = new TasksDAOImpl();
    List<Task> tasks = dao.getAllTasks();

    StringBuffer buffer = new StringBuffer();
    for(Task task:tasks){
        buffer.append("<div class=\'row\'>");
        buffer.append("<div class=\'large-2 columns\'>");
        buffer.append("<p><a class=\'button\' href=\'task.jsp?id=" );
        buffer.append(task.getId());
        buffer.append("&sender=all \'>");
        buffer.append(task.getName());
        buffer.append("</a></p>");
        buffer.append("</div>");

        buffer.append("<div class=\'large-2 columns\'>");
        buffer.append("<a href=\'execution/delete.jsp?id=");
        buffer.append(task.getId());
        buffer.append("\'>");
        buffer.append("<p class=\'alert button\'>Delete</p></a>");
        buffer.append("</div></div>\n");
    }
    out.println(buffer.toString());

%>

</body>
</html>
