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

    <%
       String id = request.getParameter("id");

        DAOHelper helper = DAOHelper.getDAOHelper();
        helper.deleteTask(id);
    %>

    <p class='primary callout'>Delete successfull </p>


    </div>
    </body>

    </html>