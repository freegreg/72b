package Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import Engine.*;
public class LiveArchiveThread extends JudgeAttributes implements Judge, Runnable {

    private static HashMap<String, HttpClient> cookies = new HashMap<String, HttpClient>();
    public static HashMap<Long, String> idsMap = new HashMap<Long, String>();
    public static HashMap<String, Submission> submissions = new HashMap<String, Submission>();
    public static Long submissionLocalId = 1L;

    public LiveArchiveThread() {
    }

    public LiveArchiveThread(String username, String password, String problemId, String languageId, String code, Long submitLocalId, int runId) {
        this.code = code;
        this.languageId = languageId;
        this.password = password;
        this.problemId = problemId;
        this.submitLocalId = submitLocalId;
        this.runId = runId;
        this.username = username;
        runner = new Thread(this);
        runner.start();
    }

    @Override
    public void run() {
        try {
            switch (runId) {
                case 1: // sign in
                    System.out.println("Signing in ..");
                    signIn(username, password);
                    System.out.println("Signed in!");
                    break;
                case 2: // submit problem & save the response in the hashmap submissions
                    System.out.println("SubmitProblem ... ");
                    submitProblem(username, password, problemId, languageId, code);
                    String mid = getMaxId(cookies.get(username));
                    idsMap.put(submitLocalId, mid);
                    System.out.println("Getting Response .. ");
                    Submission s = getLastSubmission(username, password, idsMap.get(submitLocalId));
                    int mx = 1;
                    while (!(s.getStatus().equals("Accepted") || s.getStatus().equals("Runtime error") ||s.getStatus().equals("Compilation error") || s.getStatus().equals("Time limit exceeded") || s.getStatus().equals("Presentation error"))) {
                        Thread.sleep(10000);
                        s = getLastSubmission(username, password, idsMap.get(submitLocalId));
                        mx++;
                        if (mx == 5) {
                            break;
                        }
                    }
                    submissions.put(mid, s);
                    System.out.println("Submission saved .. ");
                    break;
                case 3:
                    System.out.println("Getting Response ..");
                    Submission ss = getLastSubmission(username, password, idsMap.get(submitLocalId));
                    submissions.put(idsMap.get(submitLocalId), ss);
                    System.out.println("Submission saved ..");
                    break;
            }
        } catch (Exception e) {
            System.out.println("ERROR!");
        }
    }

    private String getMaxId(HttpClient c) throws HttpException, IOException {
        GetMethod g = new GetMethod("http://livearchive.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=9");
        c.executeMethod(g);
        String res = g.getResponseBodyAsString();
        String regex = "<td>(\\d+)</td>";
        Matcher m = Pattern.compile(regex).matcher(res);
        m.find();
        return m.group(1);
    }

    @Override
    public boolean signIn(String username, String password)
            throws Exception {
        HttpClient c = new HttpClient();
        PostMethod p = new PostMethod(
                "http://livearchive.onlinejudge.org/index.php?option=com_comprofiler&task=login");
        p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        c.executeMethod(p);
        String tem = p.getResponseBodyAsString();
        p = new PostMethod("http://livearchive.onlinejudge.org/index.php?option=com_comprofiler&task=login");
        String regex = "<input type=\"hidden\" name=\"([\\s\\S]*?)\" value=\"([\\s\\S]*?)\" />";
        Matcher m = Pattern.compile(regex).matcher(tem);
        for (int i = 0; i < 9; i++) {
            if (!m.find()) {
                return false;
            }
            if (i != 0) {
                p.addParameter(m.group(1), m.group(2));
            }
        }

        p.addParameter("username", username);
        p.addParameter("passwd", password);
        p.addParameter("remember", "yes");
        p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        c.getParams().setParameter(
                HttpMethodParams.USER_AGENT,
                "Linux-Omar");
        System.out.println("login...");
        c.executeMethod(p);
        String ch = p.getResponseBodyAsString();
        if (ch.equals("")) {
            if (!cookies.containsKey(username)) {
                cookies.put(username, c);
            }
            return true;
        }
        return false;
    }

    private ArrayList<String> getVolumes() throws HttpException, IOException {
        ArrayList<String> ret = new ArrayList<String>();
        HttpClient h = new HttpClient();
        for (Integer i = 1; i < 3; i++) {
            GetMethod g = new GetMethod("http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=" + i.toString());
            g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            h.getParams().setParameter(HttpMethodParams.USER_AGENT, "Linux-Omar");
            h.executeMethod(g);
            String regex = "<td><a href=\"(index[.]php\\?option=com_onlinejudge&)amp;(Itemid=8&)amp;(category=[\\d]+)\">Volume [\\S]+</a></td>";
            Matcher m = Pattern.compile(regex).matcher(g.getResponseBodyAsString());
            while (m.find()) {
                ret.add("http://uva.onlinejudge.org/" + m.group(1) + m.group(2) + m.group(3));
            }
        }
        return ret;
    }

    @Override
    public ArrayList<Problem> getAllProblems() throws HttpException,
            IOException {
        ArrayList<Problem> ret = new ArrayList<Problem>();
        ArrayList<String> volumes = getVolumes();
        System.out.println("Processing " + volumes.size() + " Volumes ...");
        HttpClient h = new HttpClient();
        for (int i = 0; i < volumes.size(); i++) {
            System.out.println("Parsing Volume " + i + " ... ");
            String regex = "<td><a href=\"(index[.]php\\?option=com_onlinejudge&)amp;(Itemid=8&)amp;(category=\\d+&)amp;(page=show_problem&)amp;(problem=\\d+)\">(\\d+)&nbsp;-&nbsp;([^<]+)</a></td>";
            GetMethod g = new GetMethod(volumes.get(i));
            g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            h.getParams().setParameter(HttpMethodParams.USER_AGENT, "Linux-Omar");
            h.executeMethod(g);
            Matcher m = Pattern.compile(regex).matcher(g.getResponseBodyAsString());
            while (m.find()) {
                ret.add(new Problem(m.group(6), "http://uva.onlinejudge.org/" + m.group(1) + m.group(2) + m.group(3) + m.group(4) + m.group(5), m.group(7), null, null));
            }
        }
        return ret;
    }

    @Override
    public Submission getLastSubmission(String coderId, String password, String idS)
            throws Exception {
        Submission ret = new Submission();
        Submission sub = new Submission("", "", "", "", "", "");
        if (!cookies.containsKey(coderId)) {
            if (!signIn(coderId, password)) {
                return null;
            }
        }
        HttpClient h = cookies.get(coderId);
        GetMethod g = new GetMethod("http://livearchive.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=9");
        h.executeMethod(g);
        String s = g.getResponseBodyAsString();
        idS = "<td>" + idS + "</td>";
        String problemId = "<td align=\"right\"><a href=\"[\\s\\S]*?\">([\\d]+)</a></td>"; // 1
        String language = "<td>(C\\+\\+|JAVA|ANSI C|PASCAL)</td>";
        String status = "<td>(\\s+)?(<a[^>]*>)?([^<>\\d\"]*)(</a>)?\\s*</td>\\s*" + language;
        String runtime = "<td>([\\d]+\\.[\\d]+)</td>";
        String date = "<td>([\\d]+-[\\d]+-[\\d]+ [\\d]+:[\\d]+:[\\d]+)</td>";
        int start = s.indexOf(idS);
        System.out.println(start);
        Matcher m1 = Pattern.compile(status).matcher(s);
        if (!m1.find(start)) {
            return sub;
        }
        ret.setStatus(m1.group(3));
        m1 = Pattern.compile(date).matcher(s);
        if (!m1.find(start)) {
            return sub;
        }
        ret.setDate(m1.group(1));
        m1 = Pattern.compile(runtime).matcher(s);
        if (!m1.find(start)) {
            return sub;
        }
        ret.setRuntime(m1.group(1));
        m1 = Pattern.compile(language).matcher(s);
        if (!m1.find(start)) {
            return sub;
        }
        ret.setLanguage(m1.group(1));
        m1 = Pattern.compile(problemId).matcher(s);
        if (!m1.find(start)) {
            return sub;
        }
        ret.setProblemId(m1.group(1));
        return ret;
    }

    @Override
    public Problem getProblemInfo(String problemId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<String> getProblemsSolved(String coderId) {

        return null;
    }

    /*
     * Value --> language 1 --> ANSI C 4.1.2 - GNU C Compiler with options: -lm
     * -lcrypt -O2 -pipe -ansi -DONLINE_JUDGE 2 --> JAVA 1.6.0 - Java Sun JDK 3
     * --> C++ 4.1.2 - GNU C++ Compiler with options: -lm -lcrypt -O2 -pipe
     * -DONLINE_JUDGE 4 --> PASCAL 2.0.4 - Free Pascal Compiler (non-Javadoc)
     * 
     * @see Engine.Judge#submitProblem(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Long submitProblem(String coderId, String password,
            String problemId, String language, String code) throws Exception {
        if (!cookies.containsKey(coderId)) {
            if (!signIn(coderId, password)) {
                return 0L;
            }
        }
        HttpClient c = cookies.get(coderId);
        String[] params = {"localid", "language", "code", "problemid",
            "category", "codeupl"};
        String[] values = {problemId, language, code, "", "", ""};
        PostMethod p = new PostMethod(
                "http://livearchive.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=25&page=save_submission");
        for (int i = 0; i < params.length; i++) {
            p.addParameter(params[i], values[i]);
        }
        p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        c.getParams().setContentCharset("UTF-8");
        int cc = c.executeMethod(p);
        if (cc == 301) {
            System.out.println("Submitted successfully");

        } else {
            System.out.println("Submitted Unsuccessfully");
        }
        return Long.parseLong(getMaxId(c));
    }

    @Override
    public boolean signOut(String username) {
        if (cookies.containsKey(username)) {
            cookies.remove(username);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<ProblemText> getProblemTexts() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
