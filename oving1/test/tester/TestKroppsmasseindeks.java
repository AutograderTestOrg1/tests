package tester;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import oving1.Kroppsmasseindeks;

public class TestKroppsmasseindeks {
	
	public boolean runtest(int hoyde, int vekt, double expected) {
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
			return  resultat > expected - 0.1 && resultat < expected + 0.1;
			//assertEquals(expected, resultat, 0.1);
		} catch (NoSuchElementException e) {
			//fail("Ingen flyttall funnet i utskrift!");
			return false;
		}
	}

	@Test
	public void test() {
		Logger log = Logger.getLogger(this.getClass().toString());
		Score s = new Score("Test Kroppsmasseindeks", 20, 3);
		s.IncIf(runtest(180, 80, 24.69));
		s.IncIf(runtest(175, 90, 29.39));
		s.IncIf(runtest(150, 60, 26.67));
		log.info(s.toJSON());
	}

}
