package tester;

public class TestTester {

	public static void main(String[] args) {
		System.out.println(TestOppgaveA.doubleFromCompoments("1293", "1234567"));
		String[] test = "123.456".split("\\.");
		for (String testen: test) {
			System.out.println(testen);
		}
	}

}
