import java.util.ArrayList;

public class CustomSchema extends DistributionSchema{
	
	ArrayList<Level> levels = new ArrayList<Level>();
	
	public CustomSchema(Level levelOne, Level levelTwo) {
		levels.add(levelOne);
		levels.add(levelTwo);
	}
	
	public CustomSchema() {
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
	
	public void setBounds(Level levelToUpdate, ArrayList<String> newBounds) {
		int i = levels.indexOf(levelToUpdate);
		levels.get(i).setLevels(newBounds);
	}
	
	public void setBounds(String levelName, ArrayList<String> newBounds) {
		for(Level l : levels) {
			if(l.getName().equals(levelName))
				l.setLevels(newBounds);
		}
	}
	
	public void renameLevel(Level levelToName, String newName) {
		int i = levels.indexOf(levelToName);
		levels.get(i).setName(newName);
	}
	
	public void renameLevel(String levelName, String newName) {
		for(Level l : levels) {
			if(l.getName().compareTo(levelName) == 0)
				l.setName(newName);
		}
	}
	
	public void addLevel(Level toAdd) {
		levels.add(toAdd);
	}
	
	public void addLevel(String name, String...bounds) {
		levels.add(new Level(name, bounds));
	}
	
	public void removeLevel(Level toRemove) {
		levels.remove(toRemove);
	}
	
	public void removeLevel(String name) {
		Level saved = new Level("NULL", "N");
		for(Level l : levels) {
			if(l.getName().compareTo(name) == 0)
				saved = l;
		}
		levels.remove(saved);
	}
}
