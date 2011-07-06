package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
  
public class GetCookiePrintAndSetValue {  
  
  public static void main(String args[]) throws Exception {  
  
    HttpClient client = new HttpClient();  
    client.getParams().setParameter("login_user", "omar_90");  
    client.getParams().setParameter("password", "OmarEl-Mohandes");  
  
    PostMethod method = new PostMethod("https://www.spoj.pl/login/"); 
    PostMethod method2 = new PostMethod("https://www.spoj.pl/myaccount/");  
    HttpMethod r = new PostMethod("https://www.spoj.pl/login/");
 //   HttpMethod r2 = new PostMethod("https://www.spoj.pl/myaccount/");
    try{  
      client.executeMethod(r);  
      Cookie[] cookies = client.getState().getCookies();  
      for (int i = 0; i < cookies.length; i++) {  
        Cookie cookie = cookies[i];  
        System.out.println(  
          "Cookie: " + cookie.getName() +  
          ", Value: " + cookie.getValue() +  
          ", IsPersistent?: " + cookie.isPersistent() +  
          ", Expiry Date: " + cookie.getExpiryDate() +  
          ", Comment: " + cookie.getComment());  
        } 
      client.executeMethod(r);
      PrintWriter p = new PrintWriter(new File("test.html"));
      p.write(r.getResponseBodyAsString());
      p.flush();
      p.close();
   //   BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    } catch(Exception e) {  
      System.err.println(e);  
    } finally {  
      method.releaseConnection(); 
      r.releaseConnection();
    }  
  }  
}  