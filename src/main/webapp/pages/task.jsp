<!DOCTYPE html>
<html class="no-js" lang="en" dir="ltr">
<head>

    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task review</title>
    <link rel="stylesheet" href="css/foundation.css">
    <link rel="stylesheet" href="css/app.css">
</head>

<body>

<%@ page import="lian.artyom.dao.TasksDAO"%>
<%@ page import="lian.artyom.dao.impl.TasksDAOImpl"%>
<%@ page import="lian.artyom.dao.pojo.Task"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.StringBuffer"%>

<a href='../index.jsp' class='button'>MAIN </a>

<div class="row">

<%
    TasksDAO tasksDao = new TasksDAOImpl();

    String taskId = request.getParameter("id"), backUrl=request.getParameter("sender");

    if (taskId != null){
        Task task = tasksDao.getTask(Integer.valueOf(taskId));
        StringBuffer buffer = new StringBuffer();

        buffer.append("<p>");
        buffer.append(task.toString());
        buffer.append("</p>");

        buffer.append("\n");

        buffer.append("<p><a href=\'modifyTask.jsp?id=");
        buffer.append(task.getId());
        buffer.append("\' class=\'button\'>modify</a></p>");

        out.println(buffer.toString());
    }else{
        out.println("Sorry, task id is not specified, request can't be handled. ");
    }
%>

</div>

</body>
</html>