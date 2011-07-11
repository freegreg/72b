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
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * @author root
 *
 */
public class PKU implements Judge {

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
	public Submission getLastSubmission(String coderId, String password, String ids)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Engine.Judge#getAllProblems()
	 */
	@Override
	public ArrayList<Problem> getAllProblems() throws Exception {
			System.out.println("Processing ...");
			ArrayList<Problem> ret = new ArrayList<Problem>();
			String u = "http://poj.org/" , f = "problem?id=";
			Integer id = 1000;
			HttpClient c = new HttpClient();
			c.getParams().setParameter(HttpClientParams.USER_AGENT, "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8");
			GetMethod g = new GetMethod();
			for(Integer i = 1 ;  ; i ++){
				System.out.println("Parsing Volume " + i   + " ...");
				g = new GetMethod(u+"problemlist?volume="+i.toString());
				g.getParams().setParameter(HttpClientParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(10 , true));
				c.executeMethod(g);
				int ind = 0;
				StringBuilder s = new StringBuilder(g.getResponseBodyAsString());
				if((ind = s.indexOf(f)) == -1)break;
				ind += f.length()+id.toString().length()+1;
				boolean flag = false;
				for(Integer j = 0 ; j < 100 ; j ++ ){
					ret.add(new Problem(id.toString() , u+f+(id++).toString() , s.substring(ind, s.indexOf("</a>" , ind)) , null , null	));
					while((s.indexOf(f+id.toString(), ind)) == -1)
					{
						if(s.indexOf(f , ind) == -1)
						{
							flag = true;
							break;
						}
						j ++;
						id ++;
					}
					if(flag)break;
					ind = s.indexOf(f+id.toString(), ind);
					ind += f.length()+id.toString().length()+1;
				}
			}
			System.out.println("DONE");
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
