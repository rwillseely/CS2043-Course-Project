import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Distribution {
	private DistributionSchema schema;
	private ArrayList<String> courseList;
	private Map<String, Integer> distribution;
	private University uni; 
	
	public Distribution(DistributionSchema schemaIn, ArrayList<String> courseList, University uni) {
		schema = schemaIn;
		this.courseList = courseList;
		distribution = new HashMap<String, Integer>();
		this.uni = uni;
	}
	

	
	public void setUpMap() {
		ArrayList<Level> levels = schema.getLevels();
		for(Level l : levels) {
			if(distribution.get(l.getName()) == null) {	
				distribution.put(l.getName(), 0);
			}
		}
	}
	
	public void setUpMap(Map<String, Integer> map) {
		ArrayList<Level> levels = schema.getLevels();
		for(Level l : levels) {
			if(map.get(l.getName()) == null) {	
				map.put(l.getName(), 0);
			}
		}
	}
	
	public Level findLevel(String letterGrade) {
		return schema.findLevel(letterGrade);
	}
	
	public ArrayList<Level> getLevels(){
		return schema.getLevels();
	}
	
	public void increaseLevelCount(String level) {
		Integer currentValue = distribution.get(level);
		distribution.put(level, currentValue + 1);
	}
	
	public void increaseLevelCount(Map<String, Integer> map,String level) {
		Integer currentValue = map.get(level);
		map.put(level, currentValue + 1);
	}
	
	public Integer getBinCount(String level) {
		return distribution.get(level);
	}
	
	public Integer getBinCount(Map<String, Integer> map,String level) {
		return map.get(level);
	}
	
	public void binGrade(ArrayList<String> grades) {
		setUpMap();
		for(String g: grades) {
			Level foundLevel = findLevel(g);
			if(foundLevel == null) {
				//if no level found, will go into "Others"
				increaseLevelCount("Others");
			}
			else {
				increaseLevelCount(foundLevel.getName());
			}
		}
	}
	
	public void binGrade(ArrayList<String> grades,Map<String, Integer> map ) {
		setUpMap(map);
		for(String g: grades) {
			Level foundLevel = findLevel(g);
			if(foundLevel == null) {
				//if no level found, will go into "Others"
				increaseLevelCount(map,"Others");
			}
			else {
				increaseLevelCount(map,foundLevel.getName());
			}
		}
	}
	
	public ArrayList<Student> getStudents(){
		return uni.getStudentList();
	}
	
	public ArrayList<String> getCourseList(){
		return courseList;
	}
	
	public void setCourseList(ArrayList<String> courseList) {
		this.courseList = courseList;
	}
	public void setUniversity(University uni) {
		this.uni = uni;
	}
	
	public boolean areEquivalent(String course1, String course2) { 
		return uni.areEquivalent(course1, course2);
	}
	
	public  boolean equivalentValues(String course1, String course2) {
		return uni.equivalentValues(course1, course2);
	}
	
	public ArrayList<String> getCourseEquivalencies(String courseCode){
		ArrayList<String> toReturn = uni.getCourseEquivalancies(courseCode);
		if(toReturn == null) {
			return new ArrayList<String>();
		}
		return toReturn;
	}
	
	public boolean doesCourseListContainKey(String equivalentCourse) {
		String key = uni.getCourseKey(equivalentCourse);
		if(key == null) {
			return false;
		}
		return courseList.contains(key);
	}
	
	public ArrayList<String> setUpEquivalencies(ArrayList<String> courseList) {
		ArrayList<String> newCourseList = (ArrayList<String>) courseList.clone();
		for(String s : courseList) {
			ArrayList<String> test = (ArrayList<String>) getCourseEquivalencies(s);
			newCourseList.addAll(test);
		}
		return newCourseList;
	}
	
	public String toString() {
		String result = "";
		ArrayList<Level> levels = schema.getLevels();
		for(Level l : levels) {
			result += l.getName() + ": " + getBinCount(l.getName()) + "\n";
		}
		return result;
	}
	abstract ArrayList<String> getGrades();
}