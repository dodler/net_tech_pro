<!DOCTYPE html>
<html>
<body>

<%@ page import="lian.artyom.tools.DbTool" %>
<%@ page import="lian.artyom.dao.TasksDAO"%>
<%@ page import="lian.artyom.dao.impl.TasksDAOImpl"%>
<%@ page import="lian.artyom.dao.pojo.Task"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.StringBuffer"%>


<h1>Here you can configure and schedule new task.</h1>

<%
    TasksDAO dao = new TasksDAOImpl();
    List<Task> tasks = dao.getAllTasks();

    StringBuffer buffer = new StringBuffer();
    buffer.append("<h1>Tasks:</h1>");
    for(Task task:tasks){
        buffer.append("<p><a href=\"pages/task.jsp?id=" );
        buffer.append(task.getId());
        buffer.append("&");
        buffer.append("sender=all \" />");
        buffer.append(task.getName());
        buffer.append("</p>");
    }
    out.println(buffer.toString());

%>

</body>
</html>
