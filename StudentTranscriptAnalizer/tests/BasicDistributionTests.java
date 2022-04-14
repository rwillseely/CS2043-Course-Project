import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BasicDistributionTests {
	private Level l1;
	private DistributionSchema defaultSchema1;
	private ArrayList<String> testGradeList;
	private RawDistribution rd;
	
	@BeforeEach
	void setup() {
		l1 = new Level("Exceeds", "A+", "A", "A-");
		defaultSchema1 = new DistributionSchema();
		setUpGradeArray();
		rd = new RawDistribution(defaultSchema1, null,null);
	}
	
	
	@Test
	void test() {
		//System.out.println(System.getProperty("user.dir"));
	}
	
	
	@Test
	void testGetLevelName() {
		assertEquals("Exceeds", l1.getName());
	}
	
	@Test
	void testLevelContainBounds() {
		assertTrue(l1.hasBound("A+"));
		assertTrue(l1.hasBound("A-"));
		assertFalse(l1.hasBound("B"));
	}

	@ParameterizedTest
	@CsvSource({"A+, Exceeds", "W, Others", "B-, Meets", "NCR, Fails", "C, Marginal"})
	void testGetLevelFromDistributionSchemaByBound(String bound, String expectedLevelName) {
		Level gottonLevel = defaultSchema1.findLevel(bound);
		assertEquals(expectedLevelName, gottonLevel.getName());
	}
	
	@Test
	void testCannotFindLevelFromDistributionSchema() {
		assertNull(defaultSchema1.findLevel("HELLO"));
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
	void testBinningEmptyGradeAfterDoingDistribution() {
		rd.binGrade(testGradeList);
		rd.binGrade(new ArrayList<String>());
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
