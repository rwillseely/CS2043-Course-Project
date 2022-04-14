import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class University {
	
	ArrayList<Student> studentList;
	CourseEquivalency equivalencies;
	
	public University() {
		studentList = new ArrayList<Student>();
		equivalencies = null;
	}
	
	public ArrayList<Student> getStudentList(){
		return studentList;
	}
	
	public void setUpEquivalencies(String fileName) {
		equivalencies = new CourseEquivalency(fileName);
		equivalencies.readFile();
	}
	
	public ArrayList<String> getCourseEquivalancies(String courseCode) {
		if(equivalencies == null) {
			return null;
		}
		ArrayList<String> toReturn = equivalencies.getEquivalents(courseCode);
		return toReturn;
	}
	
	public boolean areEquivalent(String course1, String course2) { 
		if(equivalencies == null) {
			return false;
		}
		return equivalencies.areEquivalent(course1, course2);
	}
	
	public  boolean equivalentValues(String course1, String course2) {
		if(equivalencies == null) {
			return false;
		}
		return equivalencies.equivalentValues(course1, course2);
	}
	
	public String getCourseKey(String course) {
		return equivalencies.getCourseKey(course);
	}
	public boolean readInputFile(String fileName) {
		
		try {
			File in = new File(fileName);
			Scanner scan = new Scanner(in);
			
			while(scan.hasNextLine()) {
				
				String line = scan.nextLine();
				
				parseLine(line);
				
			}
			scan.close();
			return true;
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		}
		
	}
	
	private void parseLine(String line) {
		ArrayList<String> parseLine = new ArrayList<String>(Arrays.asList(line.split("\t")));
		
		String name = parseLine.get(0);
		int studentNumber = Integer.parseInt(parseLine.get(1));
		String degree = parseLine.get(2);
		
		String term = parseLine.get(3);
		String code = parseLine.get(4);
		String title = parseLine.get(5);
		String grade = parseLine.get(6);
		double creditHour = Double.parseDouble(parseLine.get(7));
		String section = parseLine.get(9);
		
		//if student not in list add student to list
		if(!studentInList(studentNumber)) {
			studentList.add(new Student(studentNumber,degree,name));
		}
		
		//get student from student list then add course;
		for(int i=0;i<studentList.size();i++) {
			if(studentNumber == studentList.get(i).getStudentNumber()) {
				if(!(degree.equals(studentList.get(i).getDegree()))) {
					studentList.get(i).setDegree(degree);
				}
				studentList.get(i).addCourse(new Course(term,code,title,creditHour,section,grade));
			}
		}
				
	}
	
	private boolean studentInList(int studentID) {
		//if list empty student not in list.
		if(studentList.size() == 0) {
			return false;
		}
		//if student name given is same as one in list student is in list
		for(int i=0;i<studentList.size();i++) {
			if(studentID == studentList.get(i).getStudentNumber()){
				return true;
			}
		}
		//if not empty and student name is not in line then student not in list.
		return false;
	}
	
	public String getTranscript(int studentID) {
		String format = "";
		Student stu = null;
		
		for(int i=0;i<studentList.size();i++) {
			if(studentID == studentList.get(i).getStudentNumber()){
				stu = studentList.get(i);
			}
		}
		
		if(stu == null) { return format; }
		
		format += stu.toString();
		
		return format;
	}
	
	public void getTranscript(int studentID,String fileName) {
		String transcript = getTranscript(studentID);
		
		try {
			  java.util.Date date = new java.util.Date();
		      FileWriter myWriter = new FileWriter(fileName+".txt");
		      myWriter.write(date + "\n");
		      myWriter.write(transcript);
		      myWriter.close();
		    } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public ArrayList<String> getCourseListFromFile(String filePath){
		ArrayList<String> courseList = new ArrayList<String>();
		try {
			File in = new File(filePath);
			Scanner scan = new Scanner(in);
			
			while(scan.hasNextLine()) {
				
				courseList.add(scan.nextLine());
				
				
			}
		}
		catch(Exception e) {
			return null;
		}
		return courseList;
	}
	
}
