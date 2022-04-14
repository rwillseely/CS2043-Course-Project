import java.util.ArrayList;
import java.util.Scanner;
public class StaguiDriver{
	
	public static University uni;
	public static DistributionSchema schema;
	public static Distribution dis;
	public static ArrayList<String> raw;
	public static ArrayList<String> area;
	public static ArrayList<String> courseList;
	public static CustomSchema cust;
	public static String outputFileName;
	
	public static void main(String args[]){
	
		Stagui.begin();
	
	}
	
	public static void start(String courseData, String courseListIn, String courseEquiv){
		
		uni = new University();
		
		String outputFileName = "out.txt";
		
		//SWECourseData
		
		if(courseData.length() != 0)
			uni.readInputFile("../files/" + courseData + ".txt");
		else
			uni.readInputFile("../files/SWECourseData.txt");
	
		schema = new DistributionSchema();
		cust = new CustomSchema();
		
		courseList = new ArrayList<String>();
		
		courseList = uni.getCourseListFromFile("../files/" + courseListIn + ".txt");
		
		uni.setUpEquivalencies("../files/" + courseEquiv + ".txt");
		
	}
	
	public static String rawDist(){
		String toReturn = "";
		dis = new RawDistribution(schema,null,uni);
		raw = dis.getGrades();
		dis.binGrade(raw);
		return dis.toString();
	}
	
	public static String custRawDist(){
		dis = new RawDistribution(cust,null,uni);
		raw = dis.getGrades();
		dis.binGrade(raw);
		return dis.toString();
	}
	
	public static String areaDist(){
		dis = new AreaDistribution(schema,courseList,uni);
		area = dis.getGrades();
		dis.binGrade(area);
		return dis.toString();
	}
	
	public static String getStudentTranscript(int stuNum){
		return uni.getTranscript(stuNum);
	}
	
	public static void newDefCust(){
		cust = new CustomSchema();
	}
	
	public static void newCust(String nameOne, String boundsOne, String nameTwo, String boundsTwo){
		Scanner scan = new Scanner(boundsOne);
		ArrayList<String> boundsList = new ArrayList<String>();
		while(scan.hasNext()){
			boundsList.add(scan.next());
		}
		Level levelOne = new Level(nameOne, boundsList.toArray(new String[0]));
		boundsList.clear();
		scan = new Scanner(boundsTwo);
		while(scan.hasNext()){
			boundsList.add(scan.next());
		}
		Level levelTwo = new Level(nameTwo, boundsList.toArray(new String[0]));
		cust = new CustomSchema(levelOne, levelTwo);
		Level other = new Level("Others", "W");
		cust.addLevel(other);
	}
	
	public static void addLevel(String levelName, String bounds){
		Scanner scan = new Scanner(bounds);
		ArrayList<String> boundsList = new ArrayList<String>();
		while(scan.hasNext()){
			boundsList.add(scan.next());
		}
		Level level = new Level(levelName, boundsList.toArray(new String[0]));
		cust.addLevel(level);
	}
	
	public static void deleteLevel(String levelName){
		cust.removeLevel(levelName);
	}
	
	public static void renameLevel(String levelName, String newName){
		cust.renameLevel(levelName, newName);
	}
	
	public static void setLevelBounds(String levelName, String newBounds){
		Scanner scan = new Scanner(newBounds);
		ArrayList<String> boundsList = new ArrayList<String>();
		while(scan.hasNext()){
			boundsList.add(scan.next());
		}
		cust.setBounds(levelName, boundsList);
	}
	
	public static void changeOutputFile(String newFileName){
		outputFileName = newFileName;
	}
	
	public static void writeOutput(){
		Helper.outputDistribution(dis, outputFileName);
	}
}