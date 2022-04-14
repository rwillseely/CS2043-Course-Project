import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RawDistribution extends Distribution {
	private Map<String, Integer> repeats;
	
	
	public RawDistribution(DistributionSchema schemaIn, ArrayList<String> courseList,University uni) {
		super(schemaIn, courseList, uni);
		repeats = new HashMap<String, Integer>();
	}
	
	public ArrayList<String> getGrades(){
		ArrayList<String> gradeList = new ArrayList<String>();
		ArrayList<Student> studentList = super.getStudents();
		ArrayList<String> courseList = super.getCourseList();
		super.setUpMap(repeats);
		
		for(Student s : studentList) {
			if(courseList == null) {
				gradeList.addAll(getStudentGrades(s));
			}
			else {
				courseList = super.setUpEquivalencies(courseList);
				gradeList.addAll(getStudentGrades(s,courseList));
			}
		}
		return gradeList;
	}
	
	public Integer getBinCount(String level) {
		if(level.charAt(0) == 'R') {
			return super.getBinCount(repeats, level.substring(1));
		}
		return super.getBinCount(level);
	}
	
	private ArrayList<String> getStudentGrades(Student s){
		ArrayList<String> gradeList = new ArrayList<String>();
		ArrayList<Course> courses = s.getCourses();
		ArrayList<ArrayList<Course>> binnedCourses = new ArrayList<ArrayList<Course>>();
		
		for(Course c : courses) {
			gradeList.add(c.getGrade());
			int index = doesBinnedCourseListContainCourse(binnedCourses, c.getCode());
			binCourse(binnedCourses,c,index);	
		}
		binRepeatedCourses(binnedCourses);
		//this will return all grades for a student, no matter the courseList
		return gradeList;
	}
	
	private ArrayList<String> getStudentGrades(Student s, ArrayList<String> courseList){
		ArrayList<String> gradeList = new ArrayList<String>();
		ArrayList<Course> courses = s.getCourses();
		ArrayList<ArrayList<Course>> binnedCourses = new ArrayList<ArrayList<Course>>();
		
		for(Course c : courses) {
			if(courseList.contains(c.getCode()) || courseList.contains(c.getSectionCode())) {
				gradeList.add(c.getGrade());
				int index = doesBinnedCourseListContainCourse(binnedCourses, c.getCode());
				//repeating
				binCourse(binnedCourses,c,index);	
			}
		}
		binRepeatedCourses(binnedCourses);
		//this will return all grades for a student, no matter the courseList
		return gradeList;
	}
	
	private int doesBinnedCourseListContainCourse(ArrayList<ArrayList<Course>> binnedCourses, String courseCode) {
		for(int i = 0; i < binnedCourses.size(); i++) {
			ArrayList<Course> list = binnedCourses.get(i);
			String currentCourseCode = list.get(0).getCode();
			//same course code, or one is equivalent to the other with one being the key
			if(currentCourseCode.equals(courseCode) || super.areEquivalent(currentCourseCode, courseCode)) {
				return i;
			}
			//if two course are equivalent without one being the key, need to check key is present
			else if(super.equivalentValues(currentCourseCode, courseCode)) {
				//is the key present in the courselist
				if(super.getCourseList() == null || super.doesCourseListContainKey(courseCode)) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	private void binCourse(ArrayList<ArrayList<Course>> binnedCourses, Course c, int index) {
		if(index == -1) {
			ArrayList<Course> temp = new ArrayList<Course>();
			temp.add(c);
			binnedCourses.add(temp);
		}
		else {
			binnedCourses.get(index).add(c);
		}
	}
	
	private void binRepeatedCourses(ArrayList<ArrayList<Course>> binnedCourses) {
		for(ArrayList<Course> list : binnedCourses) {
			if(list.size() > 1) {
				Helper.sortCoursesByDate(list);
				list.remove(0);
				binRepeats(list);
			}
		}
	}
	
	
	
	private void binRepeats(ArrayList<Course> binnedCourses) {
		ArrayList<String> grades = new ArrayList<String>();
		for(Course c : binnedCourses) {
			grades.add(c.getGrade());
		}
		super.binGrade(grades, repeats);
	}
	
	public String toString() {
		String result = super.toString() + "\n" + "Repeats: \n";
		ArrayList<Level> levels = super.getLevels();
		
		for(Level l : levels) {
			String repeatLevelName = "R" + l.getName();
			result += l.getName() + ": " + getBinCount(repeatLevelName) + "\n";
		}
		return result;
	}
	
}