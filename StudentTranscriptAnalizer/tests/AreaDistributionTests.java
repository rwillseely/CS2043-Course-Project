import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AreaDistributionTests {
	private University uni;
	private Distribution areaD;
	private DistributionSchema schema;
	private ArrayList<String> courseList;
	private CourseEquivalency equalsC;
	@BeforeEach
	void setup() {
		uni = new University();
		uni.readInputFile("./files/SWECourseDataTest.txt");
		//default schema
		schema = new DistributionSchema();
		courseList = new ArrayList<String>();
		setUpCourseList();
		areaD = new AreaDistribution(schema,courseList,uni);
		
		equalsC = new CourseEquivalency("./files/Equivalents.txt");
	}
	
	
	@Test
	void SimpleAreaDistributionTest() {
		ArrayList<String> gradeList = areaD.getGrades();
		areaD.binGrade(gradeList);
		assertEquals(1,areaD.getBinCount("Exceeds"));
		assertEquals(2,areaD.getBinCount("Meets"));
		assertEquals(0,areaD.getBinCount("Marginal"));
		assertEquals(0,areaD.getBinCount("Fails"));
		assertEquals(0,areaD.getBinCount("Others"));
	}
	
	
	@Test
	void AdvancedAreaDistributionTest() {
		University uni2 = new University();
		uni2.readInputFile("./files/AreaDistributionTestData1.txt");
		areaD.setUniversity(uni2);
		ArrayList<String> gradeList = areaD.getGrades();
		areaD.binGrade(gradeList);
		
		assertEquals(3, areaD.getBinCount("Exceeds"));
		assertEquals(2, areaD.getBinCount("Meets"));
		assertEquals(1, areaD.getBinCount("Marginal"));
		assertEquals(0, areaD.getBinCount("Fails"));
		assertEquals(0, areaD.getBinCount("Others"));
	}
	
	@Test
	void NoStudentAreaDistributionTest() {
		University empty = new University();
		areaD.setUniversity(empty);
		ArrayList<String> gradeList = areaD.getGrades();
		areaD.binGrade(gradeList);
		
		assertEquals(0, areaD.getBinCount("Exceeds"));
		assertEquals(0, areaD.getBinCount("Meets"));
		assertEquals(0, areaD.getBinCount("Marginal"));
		assertEquals(0, areaD.getBinCount("Fails"));
		assertEquals(0, areaD.getBinCount("Others"));
	}
	
	@Test
	void EmptyCourseListAreaDistributionTest() {
		courseList = new ArrayList<String>();
		areaD.setCourseList(courseList);
		ArrayList<String> gradeList = areaD.getGrades();
		areaD.binGrade(gradeList);
		
		assertEquals(0, areaD.getBinCount("Exceeds"));
		assertEquals(0, areaD.getBinCount("Meets"));
		assertEquals(0, areaD.getBinCount("Marginal"));
		assertEquals(0, areaD.getBinCount("Fails"));
		assertEquals(0, areaD.getBinCount("Others"));
	}
	@Test
	void MainFileAreaDistributionTest() {
		University fill = new University();
		fill.readInputFile("./files/SWECourseData.txt");
		fill.setUpEquivalencies("./files/Equivalents.txt");
		areaD.setUniversity(fill);
		ArrayList<String> gradeList = areaD.getGrades();
		areaD.binGrade(gradeList);
	
		System.out.println(areaD);
	}
	
	@Test
	void testAreaDistriubtionWithNoStudents() {
		University empty = new University();
		areaD.setUniversity(empty);
		ArrayList<String> gradeList = areaD.getGrades();
		areaD.binGrade(gradeList);
		
		assertEquals(0, areaD.getBinCount("Exceeds"));
		assertEquals(0, areaD.getBinCount("Meets"));
		assertEquals(0, areaD.getBinCount("Marginal"));
		assertEquals(0, areaD.getBinCount("Fails"));
		assertEquals(0, areaD.getBinCount("Others"));
	}
	
	@Test
	void testAreaDistrubtionWithCourseSections() {
		uni = new University();
		uni.readInputFile("./files/AreaDistributionTestData1.txt");
		courseList = uni.getCourseListFromFile("./files/CourseListScience.txt");
		areaD.setUniversity(uni);
		areaD.setCourseList(courseList);
		
		ArrayList<String> grades = areaD.getGrades();
		areaD.binGrade(grades);
		
		assertEquals(1, areaD.getBinCount("Exceeds"));
		assertEquals(1, areaD.getBinCount("Meets"));
		assertEquals(0, areaD.getBinCount("Marginal"));
		assertEquals(1, areaD.getBinCount("Fails"));
		assertEquals(0, areaD.getBinCount("Others"));	
	}
	
	@Test
	void testAreaDistributionWithCourseEquivalencies() {
		courseList.clear();
		courseList.add("STAT*2593");
		courseList.add("SWE*4040");
		courseList.add("ECE*2412");
		areaD.setCourseList(courseList);
		
		uni = new University();
		uni.readInputFile("./files/CourseEquivalencyDistributionTest.txt");
		uni.setUpEquivalencies("./files/Equivalents.txt");
		areaD.setUniversity(uni);
		
		areaD.binGrade(areaD.getGrades());
		
		assertEquals(0, areaD.getBinCount("Exceeds"));
		assertEquals(2, areaD.getBinCount("Meets"));
		assertEquals(0, areaD.getBinCount("Marginal"));
		assertEquals(0, areaD.getBinCount("Fails"));
		assertEquals(0, areaD.getBinCount("Others"));
  }
	
	@Test
	void testAreaDistributionWithACourseListThatDoesNotExists() {
		areaD.setCourseList(null);
		areaD.binGrade(areaD.getGrades());
	}
	void outputAreaDustribution() {
		University fill = new University();
		fill.readInputFile("./files/SWECourseData.txt");
		areaD.setUniversity(fill);
		ArrayList<String> gradeList = areaD.getGrades();
		areaD.binGrade(gradeList);
		Helper.outputDistribution(areaD, "testDisArea");

	}
	
	private void setUpCourseList() {
		courseList.add("CS*1003");
		courseList.add("ENGG*1001");
		courseList.add("ENGG*1003");
		courseList.add("CCOM*7037");
		courseList.add("CMUS*4020");
		courseList.add("MATH*1503");
		courseList.add("CHEM*2401");
		courseList.add("CHEM*2416");	
		courseList.add("CS*1083");
		courseList.add("APSC*2023");
		courseList.add("APSC*2028");
	}
}
