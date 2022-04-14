import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Helper {

	
	public static Double convertGradeLetterToDouble(String gradeLetter) {
		Double gpa;
		switch (gradeLetter){
			case "A+": gpa = 4.3; break;
			case "A": gpa = 4.0; break;
			case "A-": gpa = 3.7; break;
			case "B+": gpa = 3.3; break;
			case "B": gpa = 3.0; break;
			case "B-": gpa = 2.7; break;
			case "C+": gpa = 2.3; break;
			case "C": gpa = 2.0; break;
			case "D": gpa = 1.0; break;
			case "F": gpa = 0.0; break;
			default: gpa = null; break;
		}
		return gpa;
	}
	
	public static String convertGradePointToGradeLetter(Double gradePoint) {
		String gradeLetter;
		if(gradePoint == null) {
			gradeLetter = null;
		}
		else if(gradePoint == 4.3){
			gradeLetter = "A+";
		}
		else if(gradePoint >= 4) {
			gradeLetter = "A";
		}
		else if(gradePoint >= 3.7) {
			gradeLetter = "A-";
		}
		else if(gradePoint >= 3.3) {
			gradeLetter = "B+";
		}
		else if(gradePoint >= 3.0) {
			gradeLetter = "B";
		}
		else if(gradePoint >= 2.7) {
			gradeLetter = "B-";
		}
		else if(gradePoint >= 2.3) {
			gradeLetter = "C+";
		}
		else if(gradePoint >= 2.0) {
			gradeLetter = "C";
		}
		else if(gradePoint >= 1) {
			gradeLetter = "D";
		}
		else {
			gradeLetter = "F";
		}
		return gradeLetter;
	}
	
	public static void sortCoursesByDate(ArrayList<Course> list) {
		Collections.sort(list, Course.compareByDate());
	}
	
	public static void outputDistribution(Distribution d,String fileName) {
		String distributionString = d.toString();
		
		try {
			  java.util.Date date = new java.util.Date();      
		      FileWriter myWriter = new FileWriter(fileName+".txt");
		      myWriter.write(date + "\n");
		      myWriter.write(distributionString);
		      myWriter.close();
		    } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
