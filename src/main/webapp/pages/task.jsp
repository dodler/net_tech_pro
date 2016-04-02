<!DOCTYPE html>
<html>
<body>

<%@ page import="lian.artyom.dao.TasksDAO"%>
<%@ page import="lian.artyom.dao.impl.TasksDAOImpl"%>
<%@ page import="lian.artyom.dao.pojo.Task"%>
<%@ page import="java.util.List"%>
<%@ page import="java.lang.StringBuffer"%>


<%
    TasksDAO tasksDao = new TasksDAOImpl();

    String taskId = request.getParameter("id"), backUrl=request.getParameter("sender");

    if(backUrl != null && !backUrl.isEmpty()){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<p>");
        buffer.append("<a href=\"");
        buffer.append(backUrl);
        buffer.append("\">Back");
        buffer.append("</a></p>\n");
        out.println(buffer.toString());
    }

    if (taskId != null){
        Task task = tasksDao.getTask(Integer.valueOf(taskId));
        StringBuffer buffer = new StringBuffer();

        buffer.append("<p>");
        buffer.append(task.toString());
        buffer.append("</p>");

        out.println(buffer.toString());
    }else{
        out.println("Sorry, task id is not specified, request can't be handled. ");
    }
%>

</body>
</html>