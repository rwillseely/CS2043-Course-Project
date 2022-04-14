import java.util.ArrayList;

public class Level {
	private String name;
	private ArrayList<String> bounds;
	
	public Level(String name, String... bounds) {
		this.name = name;
		this.bounds = new ArrayList<String>();
		for(String s: bounds) {
			this.bounds.add(s);
		}
	}
	
	public boolean hasBound(String inputBound) {
		for(String s : bounds) {
			if(s.equals(inputBound)) {
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLevels(ArrayList<String> newBounds) {
		bounds = newBounds;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
