import java.util.ArrayList;

public class DistributionSchema {
	private ArrayList<Level> levels;
	
	public DistributionSchema() {
		levels = new ArrayList<Level>();
		
		Level exceeds = new Level("Exceeds", "A+", "A", "A-");
		Level meets = new Level("Meets", "B+", "B", "B-", "CR");
		Level marginal = new Level("Marginal", "C+", "C");
		Level fails = new Level("Fails", "D", "F", "NCR");
		Level others = new Level("Others", "W");
		
		levels.add(exceeds);
		levels.add(meets);
		levels.add(marginal);
		levels.add(fails);
		levels.add(others);
	}
	
	public ArrayList<Level> getLevels(){
		return levels;
	}
	
	public Level findLevel(String letterGrade) {
		for(Level l : levels) {
			if(l.hasBound(letterGrade)) {
				return l;
			}
		}
		return null;
	}
	
	public void addLevel(String name, String...bounds) {
		levels.add(new Level(name, bounds));
	}
	
}
