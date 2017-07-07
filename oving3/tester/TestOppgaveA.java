package tester;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

public class TestOppgaveA {
	
	public static void runTest(String input) {
		// Create input
		ByteArrayOutputStream inputgenerator = new ByteArrayOutputStream();
		PrintStream testPrinter = new PrintStream(inputgenerator);
		testPrinter.println(input);
		testPrinter.close();
		byte[] testbytes = inputgenerator.toByteArray();
		ByteArrayInputStream inbytes = new ByteArrayInputStream(testbytes);
		System.setIn(inbytes);

		// Create stream for output
		ByteArrayOutputStream outbytestream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outbytestream));

		// Run test
		enkeltspill.Strenger.main(null);
		
		// Check output
		byte[] outbytes = outbytestream.toByteArray();
		ByteArrayInputStream testdata = new ByteArrayInputStream(outbytes);
		Scanner inn = new Scanner(testdata);

		String inputLowerCase = input.toLowerCase();
		
		String output;
		String outputToLowerCase;
		boolean foundString = false;
		
		do {
			output = inn.nextLine();
			outputToLowerCase = output.toLowerCase();
			if (inputLowerCase.equals(outputToLowerCase)) {
				assertTrue(Character.isUpperCase(output.charAt(0)));
				for (int i=1;i<output.length();i++) {
					if (Character.isAlphabetic(output.charAt(i))) {
						assertTrue(Character.isLowerCase(output.charAt(i)));
					}
				}
				foundString = true;
			}
		} while (!foundString);
	}

	@Test
	public void test() {
		runTest("tester metoden med en enkel streng");
		runTest("Tester Metoden med NOEN store bokstaver");
	}

}
