package Engine;

public class ProblemSpoj
{
	private String url;
	private String type;
	private String problemCode;
	private String problemName;
	public String getProblemCode() {
		return problemCode;
	}
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}
	public String getProblemName() {
		return problemName;
	}
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	public ProblemSpoj(String u)
	{
		url = u;
	}
	ProblemSpoj(){}
	public void setType(String t)
	{
		type = t;
	}
	public String getType()
	{
		return type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}