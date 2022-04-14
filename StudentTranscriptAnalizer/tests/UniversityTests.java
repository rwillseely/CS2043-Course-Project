import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UniversityTests {
	
	@Test
	void readsRightAmountOfStudents() {
		University uni = new University();
		uni.readInputFile("./files/SWECourseData.txt");
		ArrayList<Student> stud = uni.getStudentList();
		assertEquals(stud.size(),135);
	}
	@Test
	void printTranscript() {
		University uni = new University();
		uni.readInputFile("./files/SWECourseData.txt");
		uni.getTranscript(5324090, "transcriptTest");
	}
	
	@Test
	void testWrongStudentId() {
		University uni = new University();
		uni.readInputFile("./files/SWECourseData.txt");
		assertEquals(uni.getTranscript(1),"");
	}
	
	@Test
	void testGetCourseSectionCode() {
		Course c = new Course("2020/WI", "CS*1083", "Test CS course", 5, "FRA1", "A+");
		assertEquals("CS",c.getSectionCode());
		
	}
	
	@Test
	void testReadCourseListFromFile() {
		University uni = new University();
		ArrayList<String> courseListTest = uni.getCourseListFromFile("./files/CourseListMath.txt");
		assertEquals("MATH*1003",courseListTest.get(0));
		assertEquals("MATH*1013",courseListTest.get(1));
		assertEquals("MATH*1503",courseListTest.get(2));
		assertEquals("STAT*2593",courseListTest.get(3));
		assertEquals("CS*1303",courseListTest.get(4));
	}
	
	
}
