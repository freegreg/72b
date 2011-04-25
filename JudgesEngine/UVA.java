package Engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class UVA implements Judge{

	@Override
	public ArrayList<Problem> getAllProblems() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Submission getLastSubmission(String coderId , String password) throws Exception  {
		Submission ret = new Submission();
		URL siteUrl = new URL("http://uva.onlinejudge.org/index.php?option=com_comprofiler&" +
				"task=login");
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		String content = "username=test2&passwd=mmaw1234";
		out.writeBytes(content);
		out.flush();
		out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line ;
        
        while((line = in.readLine()) != null)
        	System.out.println(line);
        conn.disconnect();

		return ret;
	}

	@Override
	public Problem getProblemInfo(String problemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getProblemsSolved(String coderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submitProblem(String coderId, String password,
			String problemId, String language, String code) {
		// TODO Auto-generated method stub
		
	}

}
