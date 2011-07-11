/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author omar
 */
public class LiveArchive implements Judge{

    @Override
    public boolean signIn(String username, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean signOut(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long submitProblem(String coderId, String password, String problemId, String languageId, String code) throws IOException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Submission getLastSubmission(String coderId, String password, String ids) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Problem> getAllProblems() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> getProblemsSolved(String coderId) throws MalformedURLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Problem getProblemInfo(String problemId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }


	@Override
	public ArrayList<ProblemText> getProblemTexts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    
}
