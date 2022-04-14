import java.util.ArrayList;

public class AreaDistribution extends Distribution {

	
	public AreaDistribution(DistributionSchema schemaIn, ArrayList<String> courseList, University uni) {
		super(schemaIn, courseList,uni);
	}
	
	@Override
	ArrayList<String> getGrades() {
		ArrayList<String> gradeList = new ArrayList<String>();
		ArrayList<Student> studentList = super.getStudents();
		ArrayList<String> courseList = super.getCourseList();
		if(courseList == null) {
			return gradeList;
		}
		courseList = super.setUpEquivalencies(courseList);
		//if courseList is null then it will return no grades
		
		for(Student s : studentList) {	
			String newGrade = getGPAAverage(s,courseList);
			if(newGrade != null) {
				gradeList.add(newGrade);
			}
		}
		return gradeList;
	}

	private String getGPAAverage(Student s, ArrayList<String> courseList) {
		ArrayList<Course> studentCourses = s.getCourses();
		Double gpaAverage = 0.0;
		double totalCreditHours = 0;
		
		for(Course c : studentCourses) {
			if(courseList.contains(c.getCode()) || courseList.contains(c.getSectionCode())) {
				Double gradePoint =  Helper.convertGradeLetterToDouble(c.getGrade());
				//right now the code will ignore any values that are unusual values such as CR and W
				if(gradePoint != null) {
					gpaAverage += gradePoint * c.getCreditHour();
					totalCreditHours += c.getCreditHour();
				}
			}
		}
		gpaAverage = (gpaAverage)/totalCreditHours;
		return (totalCreditHours == 0) ? null : Helper.convertGradePointToGradeLetter(gpaAverage);
	}
}
