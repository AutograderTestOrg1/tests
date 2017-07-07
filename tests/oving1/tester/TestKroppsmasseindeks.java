package tester;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import oving1.Kroppsmasseindeks;

public class TestKroppsmasseindeks {
	
	public void runtest(int hoyde, int vekt, double expected) {
		// Create input
		ByteArrayOutputStream inputgenerator = new ByteArrayOutputStream();
		PrintStream testPrinter = new PrintStream(inputgenerator);
		testPrinter.println(hoyde);
		testPrinter.println(vekt);
		testPrinter.close();
		byte[] testbytes = inputgenerator.toByteArray();
		ByteArrayInputStream inbytes = new ByteArrayInputStream(testbytes);
		System.setIn(inbytes);
		
		// Create stream for output
		ByteArrayOutputStream outbytestream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outbytestream));
		
		// Run test
		Kroppsmasseindeks.main(null);
		
		// Check output
		byte[] outbytes = outbytestream.toByteArray();
		ByteArrayInputStream testdata = new ByteArrayInputStream(outbytes);
		Scanner inn = new Scanner(testdata);
		
		double resultat = -1.0;
		try {
			while (!inn.hasNextDouble()) {
				inn.next();
			}
			resultat = inn.nextDouble();
			assertEquals(expected, resultat, 0.1);
		} catch (NoSuchElementException e) {
			fail("Ingen flyttall funnet i utskrift!");
		}
	}

	@Test
	public void test() {
		runtest(180, 80, 24.69);
		runtest(175, 90, 29.39);
		runtest(150, 60, 26.67);
	}

}
