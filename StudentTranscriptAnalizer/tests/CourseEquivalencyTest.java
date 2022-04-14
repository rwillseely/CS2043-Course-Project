import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CourseEquivalencyTest {

	@Test
	void InputFileWithValidFile() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		boolean actual = c.readFile();
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	void InputFileWithInvalidFile() {
		String fileName = "ThisIsNotAFile";
		CourseEquivalency c = new CourseEquivalency(fileName);
		boolean actual = c.readFile();
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	void GetEquivalentsForValidCourseNumber() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		c.readFile();
		String courseNum = "ECE*2412";
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("CS*3113");
		ArrayList<String> actual = c.getEquivalents(courseNum);
		assertEquals(expected, actual);
	}
	
	@Test
	void GetEquivalentsForValidCourseNumberMultipleEquivalents() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		c.readFile();
		String courseNum = "SWE*4040";
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("ENGG*4000");
		expected.add("TME*4025");
		ArrayList<String> actual = c.getEquivalents(courseNum);
		assertEquals(expected, actual);
	}
	
	@Test
	void GetEquivalentsForInvalidCourseNumber() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		c.readFile();
		String courseNum = "Not a Course";
		ArrayList<String> expected = null;
		ArrayList<String> actual = c.getEquivalents(courseNum);
		assertEquals(expected, actual);
	}
	
	@Test 
	void TestAreEquivalentShouldReturnTrue() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		c.readFile();
		String course1 = "ENGG*4000";
		String course2 = "SWE*4040";
		boolean expected = true;
		boolean actual = c.areEquivalent(course1, course2);
		assertEquals(expected, actual);
		
		actual = c.areEquivalent(course2, course1);
		assertTrue(actual);
	}
	
	@Test
	void TestAreEquivalentWithTwoUnequivalentCourses() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		c.readFile();
		String course1 = "CS*2043";
		String course2 = "CS*2333";
		boolean expected = false;
		boolean actual = c.areEquivalent(course1, course2);
		assertEquals(expected, actual);
		
		assertFalse(c.areEquivalent(course2, course1));
	}
	
	@Test
	void TestEquivalentValuesAreEquivalent() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		c.readFile();
		String course1 = "TME*4025";
		String course2 = "ENGG*4000";
		boolean expected = true;
		boolean actual = c.equivalentValues(course1,  course2);
		assertEquals(expected, actual);
		
		assertTrue(c.equivalentValues(course2, course1));
	}
	
	@Test
	void TestEquivalentValuesNotEquivalent() {
		String fileName = "./files/Equivalents.txt";
		CourseEquivalency c = new CourseEquivalency(fileName);
		c.readFile();
		String course1 = "STAT*1793";
		String course2 = "CS*3113";
		boolean expected = false;
		boolean actual = c.equivalentValues(course1, course2);
		assertEquals(expected, actual);
	}
}
