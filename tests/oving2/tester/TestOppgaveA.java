package tester;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

public class TestOppgaveA {
	
	public static double doubleFromCompoments(String heltallsdel, String desimaldel) {
		heltallsdel = heltallsdel.trim();
		desimaldel = desimaldel.trim();
		double resultat;
		resultat = Double.parseDouble(heltallsdel);
		resultat += Double.parseDouble(desimaldel)/Math.pow(10, desimaldel.length());
		return resultat;
	}
	
	public void runtest(double a, double b, double gamma, double forventet) {
		// Create input
		ByteArrayOutputStream inputgenerator = new ByteArrayOutputStream();
		PrintStream testPrinter = new PrintStream(inputgenerator);
		testPrinter.printf("%f%n", a);
		testPrinter.printf("%f%n", b);
		testPrinter.printf("%f%n", gamma);
		testPrinter.close();
		byte[] testbytes = inputgenerator.toByteArray();
		ByteArrayInputStream inbytes = new ByteArrayInputStream(testbytes);
		System.setIn(inbytes);

		// Create stream for output
		ByteArrayOutputStream outbytestream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outbytestream));

		// Run test
		oppgaver.OppgaveA.main(null);
		
		// Check output
		byte[] outbytes = outbytestream.toByteArray();
		ByteArrayInputStream testdata = new ByteArrayInputStream(outbytes);
		Scanner inn = new Scanner(testdata);
		
		String innlest;
		String[] splittet;
		
		double resultat = -1.0;
		try {
			do {
				innlest = inn.next();
				splittet = innlest.split(",");
				if (splittet.length == 2) {
					resultat = doubleFromCompoments(splittet[0], splittet[1]);
				} else {
					splittet = innlest.split("\\.");
					if (splittet.length == 2) {
						resultat = doubleFromCompoments(splittet[0], splittet[1]);
					}
				} 
			} while (resultat == -1.0);
			assertEquals(forventet, resultat, 0.1);
		} catch (NoSuchElementException e) {
			fail("Ingen flyttall funnet i utskrift!");
		}
		inn.close();
	}

	@Test
	public void test() {
		runtest(5, 5, 90, 12.5);
		runtest(5.0, 0, 0, 0);
		runtest(0, 5, 0, 0);
		runtest(3, 4, 90, 6);
		runtest(3, 4, 45, 4.24);
	}

}
