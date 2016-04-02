<!DOCTYPE html>

<%@ page import="lian.artyom.dao.impl.TasksDAOImpl" %>
<%@ page import="lian.artyom.dao.TasksDAO" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="java.util.List" %>
<%@ page import="lian.artyom.dao.pojo.Action" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.lang.Boolean" %>
<%@ page import="java.lang.Integer" %>

    <html>
    <head>

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

            out.println("<p>" + name + "</p>");
            out.println("<p>" + status + "</p>");
            out.println("<p>" + action + "</p>");
            out.println("<p>" + time + "</p>");
            out.println("<p>" + comment + "</p>");
            out.println("<p>" + alarm + "</p>");

            int taskId = 0;
            try{
            taskId = dao.createTask(name, Boolean.valueOf(status), new java.util.Date(Date.valueOf(time).getTime()),
             Integer.valueOf(action), comment, Boolean.valueOf(alarm));
             }catch(Throwable e){
                e.printStackTrace(response.getWriter());
                out.println(e.getMessage());
             }
            out.println("<meta http-equiv=\"refresh\" content=\"0\"; url=\"task.jsp?id=\"" + taskId+ "\" />");

        }
    %>

    </head>

    <body>
        <h1>Create and schedule new task</h1>

    <%
        if (name == null){
            out.println("<form action=\'new\' method=\'POST\'>");
            out.println("<p><textarea name=\'name\'>");
            out.println("</textarea></p>");

            out.println("<p>Active?<input type=\'checkbox\' name=\'status\'>");
            out.println("</input></p>");

            out.println("<p>End time<input type=\"date\" name=\'time\'>");
            out.println("</imput></p>");

            out.println("<p><select name=\'action\'>");
            out.println("<option selected=\"SimpleAction\" >Select task action</option>");
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
            out.println("</textarea></p>");

            out.println("<p>Alarm?<input type=\'checkbox\' name=\'alarm\'>");
            out.println("</imput></p>");

            out.println("<p><input type=\'submit\' value=\'Submit\' />");
            out.println("</form></p>");
            out.println("</form>");
        }
    %>

    </body>

    </html>