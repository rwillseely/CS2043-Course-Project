import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Course {
	
	private String term;
	
	private String code;
	
	private String title;
	
	private double creditHour;
	
	private String section;
	
	private String grade;
	
	public Course(String term, String code, String title, double creditHour,String section, String grade) {
		this.term = term;
		this.code = code;
		this.title = title;
		this.creditHour = creditHour;
		this.section = section;
		this.grade = grade;
	}
	
	public String getTerm() {
		return term;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getCreditHour() {
		return creditHour;
	}
	
	public String getSection() {
		return section;
	}
	
	public String getGrade() {
		return grade;
	}

	
	public String getSectionCode() {
		Scanner sc =new Scanner(code);
		sc.useDelimiter("\\*");
		return sc.next();
	}
	public static Comparator<Course> compareByDate(){
		Comparator<Course> comp = new Comparator<Course>() {
			@Override
			public int compare(Course c1, Course c2) {
				String year1 = c1.getTerm().substring(0,4);
				String year2 = c2.getTerm().substring(0,4);
				if(!year1.equals(year2)) {
					return year1.compareTo(year2);
				}
				else {
					String semester1 = c1.getTerm().substring(5);
					String semester2 = c2.getTerm().substring(5);
					return semester2.compareTo(semester1);
				}
			}
		};
		return comp;
	}
  
}
