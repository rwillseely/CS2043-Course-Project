import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CustomDistributionTests {
	private Level l1;
	private CustomSchema customSchema1;
	private ArrayList<String> testGradeList;
	private RawDistribution rd;
	
	//also includes the same tests as BasicDistributionTests except levels
	//(for compatibility with custom distributions)
	
	@BeforeEach
	void setup() {
		l1 = new Level("Exceeds", "A+", "A", "A-");
		customSchema1 = new CustomSchema();
		setUpGradeArray();
		rd = new RawDistribution(customSchema1, null,null);
	}
	
	
	@Test
	void test() {
		System.out.println(System.getProperty("user.dir"));
	}

	@ParameterizedTest
	@CsvSource({"A+, Exceeds", "W, Others", "B-, Meets", "NCR, Fails", "C, Marginal"})
	void testGetLevelFromDistributionSchemaByBound(String bound, String expectedLevelName) {
		Level gottonLevel = customSchema1.findLevel(bound);
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	@Test
	void testCannotFindLevelFromDistributionSchema() {
		assertNull(customSchema1.findLevel("HELLO"));
	}
	
	@Test
	void testDistributionGetLevelCount() {
		rd.binGrade(new ArrayList<String>());
		//does not bin any grades, just does setup to set counts to be 0
		assertEquals(0,rd.getBinCount("Exceeds"));
		assertEquals(0,rd.getBinCount("Marginal"));
		assertEquals(0,rd.getBinCount("Others"));
		assertEquals(0,rd.getBinCount("Meets"));
		assertEquals(0,rd.getBinCount("Fails"));
	}
	
	@Test
	void testBasicDistribution() {
		rd.binGrade(testGradeList);
		assertEquals(3,rd.getBinCount("Exceeds"));
		assertEquals(4,rd.getBinCount("Meets"));
		assertEquals(2,rd.getBinCount("Marginal"));
		assertEquals(3,rd.getBinCount("Fails"));
		assertEquals(1,rd.getBinCount("Others"));
	}
	
	@Test
	void testBinningTheSameLetterGrade() {
		ArrayList<String> testGrades = new ArrayList<String>();
		String g1 = "A+";
		testGrades.add(g1);
		testGrades.add(g1);
		testGrades.add(g1);
		rd.binGrade(testGrades);
		assertEquals(3,rd.getBinCount("Exceeds"));
	}
	
	@Test
	void testAddingCreatedLevel() {
		Level l2 = new Level("Test", "T1", "T2", "T3");
		customSchema1.addLevel(l2);
		String expectedLevelName = "Test";
		Level gottonLevel = customSchema1.findLevel("T1");
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	@Test
	void testAddingLevelOnCreation() {
		customSchema1.addLevel("Test", "T1", "T2", "T3");
		String expectedLevelName = "Test";
		Level gottonLevel = customSchema1.findLevel("T1");
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	@Test
	void testRemovingLevel() {
		customSchema1.removeLevel(l1);
		assertNull(customSchema1.findLevel("Exceeds"));
	}
	
	@Test
	void testRemovingLevelByName() {
		customSchema1.removeLevel("Exceeds");
		assertNull(customSchema1.findLevel("Exceeds"));
	}
	
	@Test
	void testRenameLevel() {
		Level l2 = new Level("Test", "T1");
		customSchema1.addLevel(l2);
		customSchema1.renameLevel(l2, "Testing");
		String expectedLevelName = "Testing";
		Level gottonLevel = customSchema1.findLevel("T1");
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	@Test
	void testRenameLevelByName() {
		Level l2 = new Level("Test", "T1");
		customSchema1.addLevel(l2);
		customSchema1.renameLevel("Test", "Testing");
		String expectedLevelName = "Testing";
		Level gottonLevel = customSchema1.findLevel("T1");
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	@Test
	void testSetLevelBounds() {
		Level l2 = new Level("Test", "T1", "T2");
		ArrayList<String> newBounds = new ArrayList<String>();
		newBounds.add("T3");
		newBounds.add("T4");
		customSchema1.addLevel(l2);
		customSchema1.setBounds(l2, newBounds);
		String expectedLevelName = "Test";
		Level gottonLevel = customSchema1.findLevel("T3");
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	@Test
	void testSetLevelBoundsByName() {
		Level l2 = new Level("Test", "T1", "T2");
		ArrayList<String> newBounds = new ArrayList<String>();
		newBounds.add("T3");
		newBounds.add("T4");
		customSchema1.addLevel(l2);
		customSchema1.setBounds("Test", newBounds);
		String expectedLevelName = "Test";
		Level gottonLevel = customSchema1.findLevel("T3");
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	public void setUpGradeArray() {
		testGradeList = new ArrayList<String>();
		testGradeList.add("A+");
		testGradeList.add("A");
		testGradeList.add("A-");
		testGradeList.add("B+");
		testGradeList.add("B");
		testGradeList.add("B-");
		testGradeList.add("C+");
		testGradeList.add("C");
		testGradeList.add("D");
		testGradeList.add("F");
		testGradeList.add("W");
		testGradeList.add("NCR");
		testGradeList.add("CR");
	}
	
}