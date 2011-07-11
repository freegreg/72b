package Engine;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class LiveArchive_old implements Judge{
 // 19400VB
	@Override
	public ArrayList<Problem> getAllProblems() throws Exception {
		ArrayList<Problem> ret = new ArrayList<Problem>();
		URL siteUrl = new URL("http://acmicpc-live-archive.uva.es/nuevoportal/volumes.php");
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes("");
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String s , f = "<tr bgcolor=#FFFFFF>" , f2 = "<tr bgcolor=#E3E3FF>" , lastFrom = null , lastTo = null;
		int size = 0;
		while((s=in.readLine())!=null)
		{	
		//	System.out.println(s + "  |  " + s.indexOf(f));
			if(s.indexOf(f) != -1|| s.indexOf(f2) != -1)
			{
				lastFrom = in.readLine();
				lastFrom = in.readLine().substring(19 , 23);
				lastTo = in.readLine().substring(19 , 23);
			}
		}
		in.close();
		conn.disconnect();
		size = Integer.parseInt(lastFrom);
		for(Integer i = 2000 ; i <= size ; i += 100){
			int to = (i == size)? Integer.parseInt(lastTo):i+99;
			for (Integer k = i ; k <= to ; k++) {
				ret.add(getProblemInfo(k.toString()));
				System.out.println(k);
			}
		}
		return ret;
	}

	@Override
	public Submission getLastSubmission(String coderId, String password , String ids)
			throws Exception {
		Submission ret = new Submission();
		URL siteUrl = new URL("http://acmicpc-live-archive.uva.es/nuevoportal/status.php?u="+coderId);
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes("");
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String s = "" , f = "<tr align=center><td>";
	    while((s=in.readLine()) != null)
	    {
	    	@SuppressWarnings("unused")
			int ind;
	    	if((ind = s.indexOf(f)) != -1){
	    		s = in.readLine();
	    		int r = 0;
	    		StringBuilder tem = new StringBuilder();
	    		for(int i = 0 ; i < s.length() ; i ++){
	    			if(s.charAt(i) == '>' || s.charAt(i) == '<')
	    				r ++;
	    			else if(r == 4)
	    				tem.append(s.charAt(i));
	    			else if(r == 5)
	    				break;
	    		}
	    		ret.setDate(tem.toString());
	    		s = in.readLine();
	    		r = 0;
	    		tem.delete(0, tem.length());
	    		StringBuilder time = new StringBuilder();
	    		StringBuilder mem = new StringBuilder();
	    		StringBuilder id = new StringBuilder();
	    		StringBuilder lang = new StringBuilder();
	    		for(int i = 0 ; i < s.length() ; i ++){
	    			if(s.charAt(i) == '>' || s.charAt(i) == '<')
	    				r++;
	    			else if(r == 2)
	    				tem.append(s.charAt(i));
	    			else if(r == 4)
	    				time.append(s.charAt(i));
	    			else if(r == 6)
	    				mem.append(s.charAt(i));
	    			else if(r == 14)
	    				lang.append(s.charAt(i));
	    			else if(r == 18)
	    				id.append(s.charAt(i));
	    			else if(r > 18)
	    				break;
	    		}
	    		ret.setMemoryUsed(mem.toString());
	    		ret.setStatus(tem.toString());
	    		ret.setRuntime(time.toString());
	    		ret.setProblemId(id.toString());
	    		ret.setLanguage(lang.toString());
	    		break;
	    	}
	    }
		return ret;

	}

	@Override
	public Problem getProblemInfo(String problemId) throws Exception {
		Problem ret = new Problem();
		ret.setType("Volume " + problemId.substring(0 , 2));
		URL siteUrl = new URL("http://acmicpc-live-archive.uva.es/nuevoportal/rankprob.php?p="+problemId+"&d=0&c=1000000");
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes("");
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String s = "" , f = "<tr align=center>";
	    in.readLine();
	    s = in.readLine();
	    Integer r = 0;
	    StringBuilder tem = new StringBuilder();
	    for(int i = 0 ; i < s.length() ; i ++){
	    	if(s.charAt(i) == '>' || s.charAt(i) == '<')
	    		r ++;
	    	else if(s.charAt(i) == '-')
	    		break;
	    	else if(r == 4)
	    		tem.append(s.charAt(i));
	    }
	    ret.setName(tem.toString());
	    ret.setId(problemId);
	    r = 0;
	    while((s=in.readLine()) != null){
	    	if(s.indexOf(f) != -1)
	    		r ++;
	    }
	    ret.setNumberOfAccepted(r.toString());
	    ret.setUrl("http://acmicpc-live-archive.uva.es/nuevoportal/data/problem.php?p="+problemId);
		return ret;
	}

	@Override
	public ArrayList<String> getProblemsSolved(String coderId)
			throws MalformedURLException, Exception {
		ArrayList<String> ret = new ArrayList<String>();
		URL siteUrl = new URL("http://acm.uva.es/archive/nuevoportal/users.php?user="+coderId);
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes("");
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String s = "" , f = "<tr bgcolor=#55FF55 align=center>";
	    while((s = in.readLine()) != null)
	    {
	    	int ind;
	    	if((ind = s.indexOf(f)) != -1)
	    	{
	    		int r = 0;
	    		StringBuilder tem = new StringBuilder();
	    		boolean flag = false;
	    		for(int i = ind+f.length() ; i < s.length() ; i ++){
	    			if(s.charAt(i) == '>' || s.charAt(i) == '<')	
	    				r ++;
	    			else if(r == 3){
	    				if(s.charAt(i) == '\"')
	    					flag = true;
	    				else if(flag)
	    					tem.append(s.charAt(i));
	    				if(tem.length() != 0 && s.charAt(i) == '\"')
	    					break;
	    			}
	    		}
	    		ret.add("http://acm.uva.es/archive/nuevoportal/data/problem.php?"+tem.toString().substring(13));
	    	}
	    }
		return ret;
	}
	// 25961XU --> my id
	@Override
	public Long submitProblem(String coderId, String password,
			String problemId, String languageId, String code)
			throws IOException {
		URL siteUrl = new URL("http://acmicpc-live-archive.uva.es/nuevoportal/mailer.php");
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		String[] params = {"problem" , "userid" , "language" , "code" };
		String[] values = {problemId , coderId , languageId , code};
		StringBuilder content = new StringBuilder();
		for(int i = 0 ; i < params.length ; i ++)
		{
			if(i != 0)
				content.append("&");
			content.append(params[i] + "=" + URLEncoder.encode(values[i] , "UTF-8"));
		}
		out.writeBytes(content.toString());
		out.flush();
		out.close();
		conn.getInputStream();
        conn.disconnect();
		return null;
	}

    @Override
    public boolean signIn(String username, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean signOut(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



	@Override
	public ArrayList<ProblemText> getProblemTexts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
