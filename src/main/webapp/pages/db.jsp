<!DOCTYPE html>

<%@ page import="lian.artyom.tools.DbTool" %>

<%@ page isErrorPage="true" %>

    <html>

    <body>

        <h1>Tool for db debug</h1>

    <%
    
        String sqlRequest = request.getParameter("sql");
        out.println("<!--" + sqlRequest + "-->");

        if (sqlRequest == null){
            out.println("<form action=\'db\' method=\'POST\'>");
            out.println("<textarea name=\'sql\'>");
            out.println("</textarea>");
            out.println("<input type=\'submit\' value=\'Submit\' />");
            out.println("</form>");
        }
    %>

    <%
        if (sqlRequest != null){
            String resp = "!";
            try{
                resp = new DbTool().sendRequest(sqlRequest);

                out.println("</p>" + resp + "</p>");
            }catch(Throwable e){

                e.printStackTrace(response.getWriter());

                out.println("<p>print section started </p>");
                out.println("<p>resp:{" + resp + "}</p>");
                out.println("<p>Exception:{" + e + "} </p>");
                out.println("<p>sql request:{" + sqlRequest + "}</p>");
                out.println("<p>failed to send request\n"+ e.getMessage() + "</p>");
                out.println("<p>print section finished </p>");
            }
        }
    %>

    </body>

    </html>