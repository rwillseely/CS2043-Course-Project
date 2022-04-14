import java.util.ArrayList;

public class Student {
	
	private int studentNumber;
	
	private String degree;
	
	private String name;
	
	private ArrayList<Course> courseList;
	
	public Student(int studentNumber, String degree, String name) {
		this.studentNumber = studentNumber;
		this.degree = degree;
		this.name = name;
		this.courseList = new ArrayList<Course>();
	}
	
	public int getStudentNumber() {
		return studentNumber;
	}
	
	public String getDegree() {
		return degree;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Course> getCourses(){
		return courseList;
	}
	
	public void addCourse(Course courseIn) {
		courseList.add(courseIn);
	}
	
	public void setDegree(String degreeIn) {
		degree = degreeIn;
	}
	
	public String toString() {
		String format = "";
		
		format +="Name: " + name + " ";
		format += "Student ID: " + studentNumber +" ";
		format += "Degree: " + degree +":\n\n";
		
		ArrayList<Course> sortedCourseList = new ArrayList<>();
		sortedCourseList.addAll(courseList);
		Helper.sortCoursesByDate(sortedCourseList);
		
		for(int i=0;i<courseList.size();i++) {
			format+= sortedCourseList.get(i).getTerm() + "\t";
			format+= sortedCourseList.get(i).getCode() + "\t";
			format+= sortedCourseList.get(i).getSection() + "\t";
			format+= sortedCourseList.get(i).getTitle() + "\t";
			format+= sortedCourseList.get(i).getGrade() + "\t";
			format+= sortedCourseList.get(i).getCreditHour() + "\n";
			if(i != courseList.size()-1) {
				if(isNextTerm(sortedCourseList,i)) {
					format+= "---------------------------------------------------------------------------";
					format+= "\n";
				}
			}
		}
		
		return format;
	}
	
	public boolean isNextTerm(ArrayList<Course> sortedCourseList,int index) {
		String term1 = sortedCourseList.get(index).getTerm();
		String term2 = sortedCourseList.get(index+1).getTerm();
		
		if(term1.equals(term2)) {
			return false;
		}
		else {
			return true;
		}
	}
}
