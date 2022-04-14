import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RawDistributionTests {

	private Distribution areaR;
	private DistributionSchema schema;
	
	@BeforeEach
	void setup() {
		//default schema
		schema = new DistributionSchema();
		areaR = new RawDistribution(schema,null,null);
	}
	
	
	@Test
	void SimpleRawDistributionWithAllCoursesTest() {
		University uni = new University();
		uni.readInputFile("./files/SWECourseDataTest.txt");
		areaR.setUniversity(uni);
		
		ArrayList<String> gradeList = areaR.getGrades();
		areaR.binGrade(gradeList);
		
		assertEquals(2,areaR.getBinCount("Exceeds"));
		assertEquals(4,areaR.getBinCount("Meets"));
		assertEquals(0,areaR.getBinCount("Marginal"));
		assertEquals(0,areaR.getBinCount("Fails"));
		assertEquals(3,areaR.getBinCount("Others"));
	}
	
	@Test
	void SimpleRawDistributionWithLimitedCourseList() {
		ArrayList<String> courseList = setUpCourseList();
		
		setUpCourseList();
		areaR.setCourseList(courseList);
		
		University uni = new University();
		uni.readInputFile("./files/AreaDistributionTestData1.txt");
		areaR.setUniversity(uni);
		
		ArrayList<String> gradeList = areaR.getGrades();
		areaR.binGrade(gradeList);
		
		assertEquals(7,areaR.getBinCount("Exceeds"));
		assertEquals(7,areaR.getBinCount("Meets"));
		assertEquals(0,areaR.getBinCount("Marginal"));
		assertEquals(2,areaR.getBinCount("Fails"));
		assertEquals(3,areaR.getBinCount("Others"));
	}
	
	@Test
	void OnlyOneRepeatRawDistributionTest() {
		University uni = new University();
		uni.readInputFile("./files/SWECourseDataTest.txt");
		areaR.setUniversity(uni);
		
		ArrayList<String> gradeList = areaR.getGrades();
		areaR.binGrade(gradeList);
		
		assertEquals(0,areaR.getBinCount("RExceeds"));
		assertEquals(0,areaR.getBinCount("RMeets"));
		assertEquals(0,areaR.getBinCount("RMarginal"));
		assertEquals(0,areaR.getBinCount("RFails"));
		assertEquals(1,areaR.getBinCount("ROthers"));
	}
	
	@Test
	void RepeatTestWithAllCourseListed() {
		University uni = new University();
		uni.readInputFile("./files/RawDistributionTestData1.txt");
		areaR.setUniversity(uni);
		
		ArrayList<String> gradeList = areaR.getGrades();
		areaR.binGrade(gradeList);
		
		assertEquals(2,areaR.getBinCount("RExceeds"));
		assertEquals(2,areaR.getBinCount("RMeets"));
		assertEquals(1,areaR.getBinCount("RMarginal"));
		assertEquals(1,areaR.getBinCount("RFails"));
		assertEquals(1,areaR.getBinCount("ROthers"));
	}
	
	@Test
	void RepeatTestWithCertainCoursesListed() {
		University uni = new University();
		uni.readInputFile("./files/RawDistributionTestData1.txt");
		areaR.setUniversity(uni);
		
		areaR.setCourseList(setUpCourseList());
		
		ArrayList<String> gradeList = areaR.getGrades();
		areaR.binGrade(gradeList);
		
		assertEquals(1,areaR.getBinCount("RExceeds"));
		assertEquals(2,areaR.getBinCount("RMeets"));
		assertEquals(1,areaR.getBinCount("RMarginal"));
		assertEquals(0,areaR.getBinCount("RFails"));
		assertEquals(1,areaR.getBinCount("ROthers"));
	}
	
	@Test
	void TestWithAllInputs() {
		
		University fill = new University();
		fill.readInputFile("./files/SWECourseData.txt");
		fill.setUpEquivalencies("./files/Equivalents.txt");
		areaR.setUniversity(fill);
		ArrayList<String> gradeList = areaR.getGrades();
		areaR.binGrade(gradeList);
		
		System.out.println(areaR);
	}
	
	@Test
	void testRawDistributionWithEmptyStudentList() {
			University empty = new University();
			areaR.setUniversity(empty);
			ArrayList<String> gradeList = areaR.getGrades();
			areaR.binGrade(gradeList);
			
			assertEquals(0, areaR.getBinCount("Exceeds"));
			assertEquals(0, areaR.getBinCount("Meets"));
			assertEquals(0, areaR.getBinCount("Marginal"));
			assertEquals(0, areaR.getBinCount("Fails"));
			assertEquals(0, areaR.getBinCount("Others"));
			
			assertEquals(0, areaR.getBinCount("RExceeds"));
			assertEquals(0, areaR.getBinCount("RMeets"));
			assertEquals(0, areaR.getBinCount("RMarginal"));
			assertEquals(0, areaR.getBinCount("RFails"));
			assertEquals(0, areaR.getBinCount("ROthers"));
	}
	
	@Test
	void testRawDistributionWithSectionCode() {
		University uni = new University();
		uni.readInputFile("./files/RawDistributionTestData1.txt");
		
		ArrayList<String> courseList = uni.getCourseListFromFile("./files/CourseListScience.txt");
		areaR.setCourseList(courseList);
		areaR.setUniversity(uni);
		
		ArrayList<String> grades = areaR.getGrades();
		areaR.binGrade(grades);
		
		assertEquals(3, areaR.getBinCount("Exceeds"));
		assertEquals(2, areaR.getBinCount("Meets"));
		assertEquals(1, areaR.getBinCount("Marginal"));
		assertEquals(3, areaR.getBinCount("Fails"));
		assertEquals(0, areaR.getBinCount("Others"));
		
		assertEquals(1, areaR.getBinCount("RExceeds"));
		assertEquals(1, areaR.getBinCount("RMeets"));
		assertEquals(1, areaR.getBinCount("RMarginal"));
		assertEquals(1, areaR.getBinCount("RFails"));
		assertEquals(0, areaR.getBinCount("ROthers"));
	}
	
	@Test
	void testRawDistributionWithCourseEquivalency() {
		University uni = new University();
		uni.readInputFile("./files/CourseEquivalencyDistributionTest.txt");
		uni.setUpEquivalencies("./files/Equivalents.txt");
		
		areaR.setUniversity(uni);
		
		ArrayList<String> courseList = new ArrayList<String>();
		courseList.add("ECE*2412");
		courseList.add("STAT*2593");
		courseList.add("SWE*4040");
		courseList.add("ECE*2412");
		areaR.setCourseList(courseList);
		
		areaR.binGrade(areaR.getGrades());
		
		assertEquals(1, areaR.getBinCount("Exceeds"));
		assertEquals(4, areaR.getBinCount("Meets"));
		assertEquals(0, areaR.getBinCount("Marginal"));
		assertEquals(0, areaR.getBinCount("Fails"));
		assertEquals(2, areaR.getBinCount("Others"));
		
		assertEquals(1, areaR.getBinCount("RExceeds"));
		assertEquals(2, areaR.getBinCount("RMeets"));
		assertEquals(0, areaR.getBinCount("RMarginal"));
		assertEquals(0, areaR.getBinCount("RFails"));
		assertEquals(1, areaR.getBinCount("ROthers"));
	}
	
	@Test
	void testRawDistributionWithEquivalentCoursesWithoutKey() {
		University uni = new University();
		uni.readInputFile("./files/CourseEquivalencyDistributionTest2.txt");
		uni.setUpEquivalencies("./files/Equivalents.txt");
		
		areaR.setUniversity(uni);
		
		ArrayList<String> courseList = new ArrayList<String>();
		courseList.add("ENGG*4000");
		courseList.add("TME*4025");
		courseList.add("ECE*3242");
		courseList.add("STAT*2593");
		areaR.setCourseList(courseList);
		
		areaR.binGrade(areaR.getGrades());
		
		assertEquals(2, areaR.getBinCount("Exceeds"));
		assertEquals(2, areaR.getBinCount("Meets"));
		assertEquals(0, areaR.getBinCount("Marginal"));
		assertEquals(0, areaR.getBinCount("Fails"));
		assertEquals(2, areaR.getBinCount("Others"));
		
		assertEquals(2, areaR.getBinCount("RExceeds"));
		assertEquals(0, areaR.getBinCount("RMeets"));
		assertEquals(0, areaR.getBinCount("RMarginal"));
		assertEquals(0, areaR.getBinCount("RFails"));
		assertEquals(0, areaR.getBinCount("ROthers"));
	}
	
	@Test
	void testRawDistributionWithEveryCourseAndCourseEquivalancyies() {
		University uni = new University();
		uni.readInputFile("./files/CourseEquivalencyDistributionTest2.txt");
		uni.setUpEquivalencies("./files/Equivalents.txt");
		
		areaR.setUniversity(uni);
		
		areaR.binGrade(areaR.getGrades());
		
		assertEquals(3, areaR.getBinCount("Exceeds"));
		assertEquals(4, areaR.getBinCount("Meets"));
		assertEquals(0, areaR.getBinCount("Marginal"));
		assertEquals(0, areaR.getBinCount("Fails"));
		assertEquals(3, areaR.getBinCount("Others"));
		
		assertEquals(2, areaR.getBinCount("RExceeds"));
		assertEquals(1, areaR.getBinCount("RMeets"));
		assertEquals(0, areaR.getBinCount("RMarginal"));
		assertEquals(0, areaR.getBinCount("RFails"));
		assertEquals(1, areaR.getBinCount("ROthers"));
	}
	@Test
	void testOutputDistribution() {
		University fill = new University();
		fill.readInputFile("./files/SWECourseData.txt");
		areaR.setUniversity(fill);
		ArrayList<String> gradeList = areaR.getGrades();
		areaR.binGrade(gradeList);
		Helper.outputDistribution(areaR, "testDisRaw");
	}
	
	private ArrayList<String> setUpCourseList() {
		ArrayList<String> courseList = new ArrayList<String>();
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
		return courseList;
	}
}
