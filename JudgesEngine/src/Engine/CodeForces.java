/**
 * 
 */
package Engine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @author root
 *
 */
public class CodeForces implements Judge {

	private Integer getFirstCount(StringBuilder s , String f){
		int i = s.indexOf(f.toString())+f.length();
		return Integer.parseInt(s.substring(i, s.indexOf("/" , i)));
	}
	/* (non-Javadoc)
	 * @see Engine.Judge#submitProblem(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Long submitProblem(String coderId, String password,
			String problemId, String languageId, String code)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Engine.Judge#getLastSubmission(java.lang.String, java.lang.String)
	 */
	@Override
	public Submission getLastSubmission(String coderId, String password , String idS)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Engine.Judge#getAllProblems()
	 */
	@Override
	public ArrayList<Problem> getAllProblems() throws Exception {

		ArrayList<Problem> ret =  new ArrayList<Problem>();
		System.out.println("Parsing ...");
		HttpClient c = new HttpClient();
		GetMethod g = new GetMethod("http://codeforces.com/problemset/");
		g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		c.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8");
		String u = "http://codeforces.com/problemset/problem/" , f = "/problemset/problem/";
		String t = "EDCBA";
		c.executeMethod(g);
		StringBuilder s = new StringBuilder(g.getResponseBodyAsString());
		int ind = 0;
		for(Integer i = getFirstCount(s, f) ; i >= 1 ; i --){
			for(Integer j = 0 ;  j < t.length() ; j ++ ){
				if(s.indexOf(f+i.toString()+"/"+t.charAt(j)) == -1)
						continue;
				System.out.println(i);
				ind = s.indexOf(f+i.toString()+"/"+t.charAt(j) ,++ ind);
				ind = s.indexOf(f+i.toString()+"/"+t.charAt(j) ,++ ind);
				ret.add(new Problem(i.toString()+t.charAt(j) , u+i.toString()+"/"+t.charAt(j), null , null , null ));
				ind += f.length()+i.toString().length()+4;
				String res = "";
				for(int k = ind ; s.charAt(k) != '<' ; k ++){
					if(res.length() > 0 && res.charAt(res.length()-1) == ' ' && s.charAt(k) == ' ')continue;
						res += s.charAt(k);
				}
				System.out.println(res.substring(2));
				ret.get(ret.size()-1).setName(res.substring(2));
			}
		}
		System.out.println("Parsed Successfully!");
		return ret;
	}

	/* (non-Javadoc)
	 * @see Engine.Judge#getProblemsSolved(java.lang.String)
	 */
	@Override
	public ArrayList<String> getProblemsSolved(String coderId)
			throws MalformedURLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Engine.Judge#getProblemInfo(java.lang.String)
	 */
	@Override
	public Problem getProblemInfo(String problemId) throws Exception {
		// TODO Auto-generated method stub
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
