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

    <html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modifying task</title>
        <link rel="stylesheet" href="css/foundation.css">
        <link rel="stylesheet" href="css/app.css">

    <%
        TasksDAO dao = new TasksDAOImpl();
        String name = request.getParameter("name"),
                        status = request.getParameter("status"),
                        time = request.getParameter("time"),
                        action = request.getParameter("action"),
                        comment = request.getParameter("comment"),
                        alarm = request.getParameter("alarm"),
                        id = request.getParameter("id");

        if (id != null && name != null){
            out.println("<p>Task was updated. New parameters:");
            out.println("<p>Task name:" + name + "</p>");
            out.println("<p>Current task status:" + status + "</p>");
            out.println("<p>Task action:" + action + "</p>");
            out.println("<p>Task scheduled time:" + time + "</p>");
            out.println("<p>Task commentary:" + comment + "</p>");
            out.println("<p>Alarm:" + alarm + "</p>");

            int taskId = 0;
            try{
            dao.modifyTask(Integer.valueOf(id), name, Boolean.valueOf(status), new java.util.Date(Date.valueOf(time).getTime()),
             Integer.valueOf(action), comment, Boolean.valueOf(alarm));
             }catch(Throwable e){
                e.printStackTrace(response.getWriter());
                out.println(e.getMessage());
             }
            out.println("<meta http-equiv=\"refresh\" content=\"5\"; url=\"task.jsp?id=\"" + taskId+ "\" />");

        }
    %>

    </head>

    <body>

    <%
        if (id != null){
            Task task = dao.getTask(Integer.valueOf(id));
            List<Action> actions = dao.getAllActions();
            Action taskAction = task.getAction();
    %>
        <h1>Modify existing task.</h1>
        <form action='modifyTask.jsp' method='GET' id='modify'>

        <p><textarea name='name'><%out.println(task.getName());%></textarea></p>
        <p><input name='id' hidden value=<% out.print("\'" + id + "\'"); %> />

    <%

            if (!task.isStatus()){
                out.println("<p>Active?<input type=\'checkbox\' name=\'status\'>");
            }else{
                out.println("<p>Active?<input type=\'checkbox\' name=\'status\' checked=\'checked\' >");
            }
            out.println("</input></p>");

            out.println("<p>End time<input type=\"date\" name=\'time\'>");
            out.println("</input></p>");

            out.println("<p><select name=\'action\'>");
            out.println("<option selected=\' "+ taskAction.getName() + "\' >Select task action</option>");
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
            out.println("</select>");

            out.println("</imput></p>");

            out.println("<p>Commentary<textarea name=\'comment\'>");
            out.println(task.getComment());
            out.println("</textarea></p>");

            if (!task.isAlarm()){
                out.println("<p>Alarm?<input type=\'checkbox\' name=\'alarm\'>");
            }else{
                out.println("<p>Alarm?<input type=\'checkbox\' name=\'alarm\' checked=\'checked\'>");
            }
            out.println("</imput></p>");

            //out.println("<p><input type=\'submit\' value=\'Submit\' class=\'button\'/>");
    %>
            <a href='#' onclick="document.getElementById('modify').submit(); return false;" class='success button'>Save</a>
            <a href='javascript:history.go(0)' class='alert button'>Discard</a>
            </form>
    <%
        }
    %>

    </body>

    </html>