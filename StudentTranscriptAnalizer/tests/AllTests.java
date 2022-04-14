import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({	AreaDistributionTests.class, BasicDistributionTests.class,
					CustomDistributionTests.class, UniversityTests.class,
					RawDistributionTests.class, CourseEquivalencyTest.class})
public class AllTests {

}
//if you run this file, then it will run all the tests in the above parameters
