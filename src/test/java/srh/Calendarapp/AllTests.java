package srh.Calendarapp;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(RecursiveTest.class);
		suite.addTestSuite(SendInviteTest.class);
		suite.addTestSuite(TaskInputTest.class);
		suite.addTestSuite(UserLoginTest.class);
		//$JUnit-END$
		return suite;
	}

}
