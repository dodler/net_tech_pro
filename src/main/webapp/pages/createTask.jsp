<!DOCTYPE html>

<%@ page import="lian.artyom.dao.impl.TasksDAOImpl" %>
<%@ page import="lian.artyom.dao.TasksDAO" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="java.util.List" %>
<%@ page import="lian.artyom.dao.pojo.Action" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.lang.Boolean" %>
<%@ page import="java.lang.Integer" %>

    <html class="no-js" lang="en" dir="ltr">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Task manager for EE systems</title>
        <link rel="stylesheet" href="css/foundation.css">
        <link rel="stylesheet" href="css/app.css">
    </head>
    <body>

    <a href='../index.jsp' class='button'>MAIN</a>

    <div class='row'>

    <%
        TasksDAO dao = new TasksDAOImpl();
        String name = request.getParameter("name"),
                        status = request.getParameter("status"),
                        time = request.getParameter("time"),
                        action = request.getParameter("action"),
                        comment = request.getParameter("comment"),
                        alarm = request.getParameter("alarm");

        List<Action> actions = dao.getAllActions();
        if (name != null){
            out.println("<p>New task was successfully created and scheduled");
            out.println("<p>Task name:" + name + "</p>");
            out.println("<p>Current task status:" + status + "</p>");
            out.println("<p>Task action:" + action + "</p>");
            out.println("<p>Task scheduled time:" + time + "</p>");
            out.println("<p>Task commentary:" + comment + "</p>");
            out.println("<p>Alarm:" + alarm + "</p>");

            int taskId = 0;
            try{
            taskId = dao.createTask(name, Boolean.valueOf(status), new java.util.Date(Date.valueOf(time).getTime()),
             Integer.valueOf(action), comment, Boolean.valueOf(alarm));
             }catch(Throwable e){
                e.printStackTrace(response.getWriter());
                out.println(e.getMessage());
             }

        }
    %>

            <h1 class='primary callout'>Create and schedule new task</h1>
            <form action='execution/new.jsp' method='POST'>

            <p><textarea name='name'></textarea></p>

            <fieldset class='fieldset'><label>Active
            <input type='checkbox' name='status' aria-describedby='activeDescription'>
            </input></label>
            <p class='help-text' id='activeDescription'>If task is active, it will be marked and alarmed</p>
            </fieldset>

            <p>End time<input type='date' name='time'>
            </imput></p>
            <p><select name='action'>
            <option selected='SimpleAction' >Select task action</option>
    <%
            StringBuffer buffer = new StringBuffer();
            for(Action a:actions){
                buffer.append("<option value=\"");
                buffer.append(a.getId());
                buffer.append("\">");
                buffer.append(a.getName());
                buffer.append("</option>");
                buffer.append("\n");
            }
            out.println(buffer.toString());
    %>
            </select></imput></p>

            <p>Commentary<textarea name='comment'></textarea></p>

            <fieldset class='fieldset'>
            <label>Alarm<input type='checkbox' name='alarm' aria-describedby='alarmDescription'>
            <p class='help-text' id='alarmDescription'>On scheduled date a notification will be sent.</p>
            </imput></label> </fieldset>

            <p><input class='button' type='submit' value='Submit' />
            </form></p>
            </form>

    </div>

    </body>

    </html>