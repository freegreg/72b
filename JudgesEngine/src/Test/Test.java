package Test;
 
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import Engine.*;

public class Test {
	public static String cookie, cuki;

	/**
	 * @param args
	 * @throws Exception
	 */
	public Test(){}
	public Test(int s , String a)
	{
		System.out.println("NO");
	}
	public static void main(String[] args) throws Exception {
//		UVAThread u = new UVAThread("uvatest72" , "123456" , "456" , "1" , "1sdfsdf" , 6L , 1);
//		u = new UVAThread("uvatest72" , "123456" , "500" , "1" , "1sdfsdf" , 6L , 2);
		Judge j = new LiveArchive();
		j.getProblemTexts();
//		System.out.println(j.signIn("ojtest72", "123456"));
//		Class[] intArgsClass = new Class[] { int.class , String.class };
//		j.getClass().getConstructor(intArgsClass).newInstance(5 , "");
	
//		Test j = new Test();
//		j.getClass().getConstructor(parameterTypes);
//		j.getProblemTexts();
//		Long l = j.submitProblem("uvatest72", "123456", "961", "2", "asdassdsdda");
//		System.out.println(l);
//		Submission s = j.getLastSubmission("uvatest72", "123456", l.toString());
//		Class c = j.getClass();
//		Field f = c.getField("submissionLocalId");
//		f.set(j, 1001L);
//		j.getClass().getField("submissionLocalId").set(j, 102L);
		
//		j.getProblemTexts();
//		Scanner s = new Scanner(new File("/home/workspace/JudgesEngine/src/ProblemsTextFiles/UVA.txt"));
//		PrintWriter p = new PrintWriter(new File("/home/workspace/JudgesEngine/src/ProblemsTextFiles/UVA2.txt"));
//		while(s.hasNext()){
//			StringBuilder r = new StringBuilder(s.nextLine());
//			for(int i = 1 ; i < r.length() ; i ++)
//				if(r.charAt(i) == r.charAt(i-1) && r.charAt(i) == '"')
//					r.setCharAt(i, ' ');
//			p.write(r.toString()+"\n");
//			p.flush();
//		}
//		p.close();
//		Long l = j.submitProblem("omar_90", "mmaw1234", "TEST", "41", "ya mosahel");

		//		System.out.println(l);
//		Submission s = j.getLastSubmission("omar_90", "mmaw1234", "");
//		while(s.getDate() == null)
//		{
//			s = j.getLastSubmission("uvatest72", "123456", l.toString());
//			System.out.println("null");
//		}
//		System.out.println(s.getDate() + "\n" + s.getLanguage() + "\n" + s.getStatus() + "\n" + s.getProblemId() + "\n" + s.getRuntime());

//		Submission s = UVAThread.submissions.get(UVAThread.idsMap.get(6L));
//		 System.out.println(s.getDate() + "\n" + s.getLanguage() + "\n" + s.getStatus() + "\n" + s.getProblemId() + "\n" + s.getRuntime());
//		u.submitProblem("uvatest72", "123456", "500", "3", "ayhabal");
		
//		UVAThread u = new UVAThread();
//		u.signIn("OmarEl.Mohandes", "mmaw1234");
//		 Submission s = u.getLastSubmission("OmarEl.Mohandes", "mmaw1234" , "9021025");


		// System.err.println("Parsing .. ");
		// generateProblemFile(new Timus(), "Timus.txt");
		// System.err.println("DONE!");
		// login2("OmarEl.Mohandes", "mmtaw1234");
		//
		// login3();
		// SIGN("OmarEl.Mohandes", "mmaw1234", new HttpClient());
		// Judge[] arr = {new UVA() , new SPOJ() , new Timus() , new
		// CodeForces() , new PKU()};
		// for(int i = 0 ; i < arr.length ; i ++)
		// generateProblemFile(arr[i] ,
		// arr[i].getClass().getSimpleName()+".txt");
		//submitProblem("uvatest72", "123456", "400", "4", "asdasd" , signIn("uvatest72", "123456"));
//		System.out.println(getMaxId(signIn("uvatest72", "123456")));
		// System.out.println("Submit");
		// System.out.println(tem.replaceAll("&", "%26"));
		// Scanner ss = new Scanner(new
		// File("/home/workspace/JudgesEngine/src/ProblemsTextFiles/Timus.txt"));
		// PrintWriter p = new PrintWriter(new
		// File("/home/workspace/JudgesEngine/src/ProblemsTextFiles/Timus2.txt"));
		// StringBuilder sb = new StringBuilder();
		// int i = 0 ;
		// while(ss.hasNext())
		// {
		// String r = ss.nextLine();
		// String regex = "<IMG SRC=\"([^\"]+)\"";
		// Matcher m = Pattern.compile(regex).matcher(r);
		// if(m.find())
		// {
		// r = r.replaceAll(regex ,
		// "<IMG SRC=\"http://acm.timus.ru"+m.group(1)+"\"");
		// System.out.println(r);
		// }
		// r = r.replace("https", "http");
		// p.write(r.toString()+"\n");
		// }
		// p.flush();
		// p.close();
		// System.out.println("Submitting...");
		// System.out.println(j.signIn("uvatest72", "123456"));
		// j.getProblemTexts("/home/workspace/JudgesEngine/src/ProblemsFiles/Timus.txt");
		// j.submitProblem("uvatest72", "123456", "102", "2", "");
		// System.out.println("Getting the result ... ");
		// Submission s = j.getLastSubmission("OmarEl.Mohandes", "mmaw1234");
		// System.out.println(s.getDate() + "\n" + s.getLanguage() +"\n"
		// +s.getMemoryUsed()
		// +"\n"+s.getProblemId()+"\n"+s.getRuntime()+"\n"+s.getStatus());

		// j.submitProblem("omar_90", "OmarEl-Mohandes", "ANGELS", "41",
		// sb.toString());

		// HttpClient h = new HttpClient();
		// SIGN("OmarEl.Mohandes", "mmaw1234", h);
		// cookies.put("OmarEl.Mohandes", h);
		// "\n" + s.getStatus());
		// ArrayList<ProblemText> a =
		// j.getProblemTexts("/home/workspace/JudgesEngine/src/ProblemsFiles/SPOJ.txt");

		// Submission s = j.getLastSubmission("OmarEl.Mohandes", "mmaw1234");
		// Problem p = new Problem("id", "url", "name", "numberOfAccepted",
		// "numberOfTried");
		// Method[] m = p.getClass().getDeclaredMethods();
		// System.out.println(m.length);
		// for(int i = 0 ; i < m.length ; i ++){
		// if(m[i].toGenericString().indexOf("get") != -1)
		// System.out.print(m[i].invoke(p) + " | ");
		// }
		// try {
		// // j.submitProblem("omar_90", "OmarEl-Mohandes", "TEST", "17",
		// "ya raaaab");
		// // j.submitProblem("omar_90", "OmarEl-Mohandes", "TEST", "17",
		// "ya raaaab");
		// // j.submitProblem("omar_90", "OmarEl-Mohandes", "TEST", "17",
		// "ya raaaab");
		// // j.submitProblem("omar_90", "OmarEl-Mohandes", "TEST", "17",
		// "ya raaaab");
		// // // Submission s = j.getLastSubmission("107779JK", "");
		// // // System.out.println(s.getDate() + " " + s.getLanguage() + " " +
		// s.getProblemId() + " " + s.getStatus() + " " + s.getMemoryUsed() +
		// " " + s.getRuntime());
		// } catch (Exception e) {
		// // here is the exception.
		// e.printStackTrace();
		// }
	}

	public static void generateProblemFile(Judge j, String filename)
			throws Exception {
		PrintWriter p = new PrintWriter(new File(
				"/home/workspace/JudgesEngine/src/ProblemsFiles/" + filename));
		ArrayList<Problem> al = j.getAllProblems();
		System.out.println("Writting to a File ..");
		p.write("|		ID		|		NAME		|		URL		\n");
		for (int i = 0; i < al.size(); i++)
			p.write(al.get(i).getId() + " | " + al.get(i).getName() + " | "
					+ al.get(i).getUrl() + "\n");
		p.flush();
		p.close();
		System.out.println("Saved Successfully!");
	}

	@SuppressWarnings("unused")
	private static void login2(String username, String password)
			throws Exception {
		URL siteUrl = new URL(
				"http://uva.onlinejudge.org/index.php?option=com_comprofiler&task=login");
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes("");
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder s = new StringBuilder();
		String tem = "";
		while ((tem = in.readLine()) != null)
			s.append(tem + "\n");
		String reg = "<input type=\"hidden\" name=\"([\\s\\S]*?)\" value=\"([\\s\\S]*?)\" />";
		Matcher matcher = Pattern.compile(reg).matcher(s.toString());
		ArrayList<String> r1 = new ArrayList<String>();
		ArrayList<String> r2 = new ArrayList<String>();
		int number = 0;
		while (matcher.find()) {
			String name = matcher.group(1);
			String value = matcher.group(2);
			if (number > 0 && number < 9) {
				System.out.println(name + " = " + value);
				r1.add(name);
				r2.add(value);
			}
			++number;
		}
		String clot = "username=" + username + "&passwd=" + password
				+ "&remember=yes";
		for (int i = 0; i < r1.size(); i++) {
			clot += "&";
			clot += r1.get(i) + "=" + r2.get(i);
		}
		System.out.println(clot);
		conn.disconnect();
		conn = (HttpURLConnection) siteUrl.openConnection();
		// conn.connect();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(clot);
		out.flush();
		out.close();
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		PrintWriter p = new PrintWriter(new File("remembertest.html"));
		while ((tem = in.readLine()) != null)
			p.write(tem + "\n");
		p.flush();
		p.close();

	}

	public static void SIGN(String username, String password, HttpClient c)
			throws Exception {
		PostMethod p = new PostMethod(
				"http://uva.onlinejudge.org/index.php?option=com_comprofiler&task=login");
		p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		c.executeMethod(p);
		String tem = p.getResponseBodyAsString();
		p = new PostMethod(
				"http://uva.onlinejudge.org/index.php?option=com_comprofiler&task=login");
		String regex = "<input type=\"hidden\" name=\"([\\s\\S]*?)\" value=\"([\\s\\S]*?)\" />";
		Matcher m = Pattern.compile(regex).matcher(tem);
		for (int i = 0; i < 9; i++) {
			m.find();
			if (i != 0)
				p.addParameter(m.group(1), m.group(2));
		}

		p.addParameter("username", username);
		p.addParameter("passwd", password);
		p.addParameter("remember", "yes");
		p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		c.getParams().setParameter(HttpMethodParams.USER_AGENT, "Linux-Omar");
		System.out.println("login...");
		int code = c.executeMethod(p);
		if (code == 301)
			System.out.println("Signed In");
		else
			System.out.println("ERROR");
	}

	public static void submitProblem(String coderId, String password,
			String problemId, String language, String code , HttpClient c) throws Exception {
	
		String[] params = { "localid", "language", "code", "problemid",
				"category", "codeupl" };
		String[] values = { problemId, language, code, "", "", "" };
		PostMethod p = new PostMethod(
				"http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=25&page=save_submission");
		for (int i = 0; i < params.length; i++) {
			p.addParameter(params[i], values[i]);
		}
		p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		c.getParams().setContentCharset("UTF-8");
		int cc = c.executeMethod(p);
		if (cc == 301) {
			System.out.println("Submitted successfully");
			System.out.println(p.getResponseBodyAsString());

		} else {
			System.out.println("Submitted Unsuccessfully");
		}
	}

	public static HttpClient signIn(String username, String password) throws Exception {
		HttpClient c = new HttpClient();
		PostMethod p = new PostMethod(
				"http://uva.onlinejudge.org/index.php?option=com_comprofiler&task=login");
		p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		c.executeMethod(p);
		String tem = p.getResponseBodyAsString();
		p = new PostMethod(
				"http://uva.onlinejudge.org/index.php?option=com_comprofiler&task=login");
		String regex = "<input type=\"hidden\" name=\"([\\s\\S]*?)\" value=\"([\\s\\S]*?)\" />";
		Matcher m = Pattern.compile(regex).matcher(tem);
		for (int i = 0; i < 9; i++) {
			if (!m.find())
				return null;
			if (i != 0) {
				p.addParameter(m.group(1), m.group(2));
			}
		}

		p.addParameter("username", username);
		p.addParameter("passwd", password);
		p.addParameter("remember", "yes");
		p.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		c.getParams().setParameter(HttpMethodParams.USER_AGENT, "Linux-Omar");
		System.out.println("login...");
		c.executeMethod(p);
		String ch = p.getResponseBodyAsString();
		return c;
	}
	public static String getMaxId(HttpClient c) throws HttpException, IOException
	{
		GetMethod g = new GetMethod("http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=9");
		c.executeMethod(g);
		String res = g.getResponseBodyAsString();
		String regex = "<td>(\\d+)</td>";
		Matcher m = Pattern.compile(regex).matcher(res);
		m.find();
		return m.group(1);
	}
}
