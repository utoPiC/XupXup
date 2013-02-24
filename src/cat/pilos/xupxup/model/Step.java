package cat.pilos.xupxup.model;

public class Step {
	
	public int num;
	public String text;
	//public Enum<StepType> type;
	public String type;
	
	public Step(String text,String type,int num) {
	
		this.text=text;
		this.num=num;
		this.type=type;
		
	}
	
	
}
