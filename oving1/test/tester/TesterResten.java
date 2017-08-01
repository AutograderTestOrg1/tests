package tester;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

import org.junit.Test;

import oving1.Befolkning;
import oving1.Kroppsmasseindeks;
import oving1.LeggSammenTall;

public class TesterResten {
	
	public boolean enTestLeggSammenTall(int forventet, int ... tall) {
		// Create input
		ByteArrayOutputStream inputgenerator = new ByteArrayOutputStream();
		PrintStream testPrinter = new PrintStream(inputgenerator);
		for (int a=0;a<tall.length;a++) {
			testPrinter.println(tall[a]);
		}
		testPrinter.close();
		byte[] testbytes = inputgenerator.toByteArray();
		ByteArrayInputStream inbytes = new ByteArrayInputStream(testbytes);
		System.setIn(inbytes);
		
		// Create stream for output
		ByteArrayOutputStream outbytestream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outbytestream));
		
		// Run test
		LeggSammenTall.main(null);
		
		// Check output
		byte[] outbytes = outbytestream.toByteArray();
		ByteArrayInputStream testdata = new ByteArrayInputStream(outbytes);
		Scanner inn = new Scanner(testdata);
		
		int sisteTall = 0;
		while (inn.hasNext()) {
			if (inn.hasNextInt()) {
				sisteTall = inn.nextInt();
			} else {
				inn.next();
			}
		}
		return forventet == sisteTall;
		//assertEquals(forventet, sisteTall);
	}

	@Test
	public void testLeggSammenTall() {
		Logger l = Logger.getLogger(this.getClass().getName());
		Score s = new Score("Legg sammen tall", 20, 3);
		s.IncIf(enTestLeggSammenTall(34, 5, 8, 6, 7, 8, 0));
		s.IncIf(enTestLeggSammenTall(5910, 56, 12, 5673, 23, 67, 23, 56, -1));
		s.IncIf(enTestLeggSammenTall(7, 3, 4, -15));
		l.info(s.toJSON());
	}
	
	@Test 
	public void testBefolkning() {
		Logger l = Logger.getLogger(this.getClass().getName());
		Score s = new Score("Test befolkning", 20, 10);
		// Create stream for output
		ByteArrayOutputStream outbytestream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outbytestream));
		
		// Run test
		Befolkning.main(null);
		
		// Check output
		byte[] outbytes = outbytestream.toByteArray();
		ByteArrayInputStream testdata = new ByteArrayInputStream(outbytes);
		Scanner inn = new Scanner(testdata);

		double resultat = 0.0;
		double forventet = 7.29;
		double multiplikator = 1.0114;
		boolean harRiktigVerdi;
		try {
			for (int a=0;a<10;a++) {
				harRiktigVerdi = false;
				while (!harRiktigVerdi) {
					if (!inn.hasNextDouble()) {
						inn.next();
					} else {
						resultat = inn.nextDouble();
						if (resultat < 15) harRiktigVerdi = true;
					}
				}
				forventet *= multiplikator;
				s.IncIf(forventet > resultat - 0.02 && forventet < resultat + 0.02);
				//assertEquals(forventet, resultat, 0.02);
			}
		} catch (NoSuchElementException e) {
			// fail("Forventet å finne 10 befolkningsverdier mellom 7 og 15 (i milliarder)!");
		}
		l.info(s.toJSON());
	}

}
