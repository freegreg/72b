<%-- 
    Document   : index
    Created on : Jul 4, 2011, 3:43:08 PM
    Author     : omar
--%>

<%@page import="java.lang.reflect.Method"%>
<%@page import="Engine.Problem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Engine.Submission"%>
<%@page import="Engine.Timus"%>
<%@page import="Engine.PKU"%>
<%@page import="Engine.LiveArchive"%>
<%@page import="Engine.CodeForces"%>
<%@page import="Engine.UVA"%>
<%@page import="Engine.Judge"%>
<%@page import="Engine.SPOJ"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String id = request.getParameter("ID");
            String jid = request.getParameter("JID");
            Judge j = null;
            if (jid == "UVA") {
                j = new UVA();
            } else if (jid == "SPOJ") {
                j = new SPOJ();
            } else if (jid == "CODEFORCES") {
                j = new CodeForces();
            } else if (jid == "LIVEARCHIVE") {
                j = new LiveArchive();
            } else if (jid == "PKU") {
                j = new PKU();
            } else if (jid == "Timus") {
                j = new Timus();
            }
            // Sign In
            if (id.equals("1")) {
                out.print(j.signIn(request.getParameter("username"), request.getParameter("password")));
            } // Sign Out
            else if (id.equals("2")) {
                out.print(j.signOut(request.getParameter("username")));
            } // Submit Problem
            else if (id.equals("3")) {
                j.submitProblem(request.getParameter("coderId"), request.getParameter("password"), request.getParameter("problemId"), request.getParameter("languageId"), request.getParameter("code"));
            } // Get Last Submission
            else if (id.equals("4")) {
                Submission s = j.getLastSubmission(request.getParameter("username"), request.getParameter("password"));
                out.print(s.getDate() + "\n");
                out.print(s.getLanguage() + "\n");
                out.print(s.getMemoryUsed() + "\n");
                out.print(s.getProblemId() + "\n");
                out.print(s.getRuntime() + "\n");
                out.print(s.getStatus() + "\n");
            } 
            // Get All Problems
            else if (id.equals("5")) {
                ArrayList<Problem> a = j.getAllProblems();
                for (int k = 0; k < a.size(); k++) {
                    Method[] m = a.get(k).getClass().getDeclaredMethods();
                    for (int i = 0; i < m.length; i++) {
                        if (m[i].toGenericString().indexOf("get") != -1) {
                            out.print(m[i].invoke(a.get(k)) + " | ");
                        }
                    }
                }

            } else if (id.equals("6")) {
                
            } else if (id.equals("7")) {
            }
        %>
    </body>
</html>
