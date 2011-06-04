package Engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class UVA implements Judge{

	private Integer getFirstCount(StringBuilder s , StringBuilder f)
	{
		int i = s.indexOf(f.toString())+f.length();
		return Integer.parseInt(s.substring(i, s.indexOf("\"" , i)));
	}
	@Override
	public ArrayList<Problem> getAllProblems() throws HttpException, IOException {
		// 3 - 11
		ArrayList<Problem> ret = new ArrayList<Problem>(); 
		String url = "http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=";
		StringBuilder u = new StringBuilder("http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=");
		StringBuilder f = new StringBuilder("index.php?option=com_onlinejudge&amp;Itemid=8&amp;category=3&amp;page=show_problem&amp;problem=");
		String space = "&nbsp;";
		Integer j = 36 , id = 100;
		System.out.println("Processing ...");
		for(Integer i = 3 ; i <= 11 ; i ++){
			System.out.println("Parsing " + i + " ... ");
			HttpClient h = new HttpClient();
			GetMethod g = new GetMethod(url+i.toString());
			g.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			h.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8");
			h.executeMethod(g);
			StringBuilder s = new StringBuilder(g.getResponseBodyAsString());
			f = new StringBuilder(f.substring(0, f.indexOf("category=")+9) + i.toString() + f.substring(f.indexOf("&amp;page")));
			u = new StringBuilder(u.substring(0, u.indexOf("category=")+9) + i.toString() + u.substring(u.indexOf("&page")));
			j = getFirstCount(s , f);
			for(int k = 0 ; k < 100 ; k ++){
				String tem = f + j.toString();
				ret.add(new Problem((id ++).toString() , u + (j++).toString() , null , null , null));
				int indF = s.indexOf(tem.toString()) + tem.length()+3 , indT = s.indexOf("</a>", indF);
				//System.out.println(indF + " " + indT);
				String name = "";
				for(int ii = indF-1 , c = 0 ; ii < indT ; ii ++)
				{
					if(s.charAt(ii) == '&'){
						c ++;
						name += " ";
					}
					if(c > 0 && c < 7){
						c ++;
						continue;
					}
					c = 0;
					name += s.charAt(ii);
				}
				ret.get(ret.size()-1).setName(name);
 			}
		}
		System.out.println("DONE");
		return ret;
	}
	
	@Override
	public Submission getLastSubmission(String coderId , String password) throws Exception  {

		return null;
	}

	@Override
	public Problem getProblemInfo(String problemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getProblemsSolved(String coderId)  {
		
		return null;
	}

	@Override
	public void submitProblem(String coderId, String password,
			String problemId, String language, String code) {
		// TODO Auto-generated method stub
		
	}

}
