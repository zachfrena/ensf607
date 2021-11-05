package Server.Model;

public class StringModel {
	
	private String myString;
	
	
	
	public void concatenate (String s1, String s2)
	{
		setMyString(s1 + s2);
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}
	
	

}
