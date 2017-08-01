package tester;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import enkeltspill.Spiller;

public class TestSpiller {
	Spiller testspiller, testspiller2;

	@Before
	public void setUp() throws Exception {
		testspiller = new Spiller(1, "Erlend");
		testspiller2 = new Spiller(2, "Fredrik");
	}

	@Test
	public void testSpiller() {
		testspiller = new Spiller(3, "Nina");
		assertEquals(testspiller.getNavn(), "Nina");
		assertEquals(testspiller.getPoengsum(), 0);
		assertEquals(testspiller.getId(), 3);
	}

	@Test
	public void testGetNavn() {
		assertEquals(testspiller.getNavn(), "Erlend");
		assertEquals(testspiller2.getNavn(), "Fredrik");
	}

	@Test
	public void testSetNavn() {
		testspiller.setNavn("Anne");
		assertEquals(testspiller.getNavn(), "Anne");		
		assertEquals(testspiller2.getNavn(), "Fredrik");
	}

	@Test
	public void testGetPoengsum() {
		assertEquals(testspiller.getPoengsum(), 0);
		assertEquals(testspiller2.getPoengsum(), 0);
	}

	@Test
	public void testSetPoengsum() {
		testspiller2.setPoengsum(10);
		assertEquals(testspiller.getPoengsum(), 0);
		assertEquals(testspiller2.getPoengsum(), 10);
		
		// Sjekker at verdien er uforandret hvis input er negativ.
		// Oppgaven ber ikke eksplisitt om exception slik LF bruker, derfor en try ... catch som ikke gjør noe
		try {
			testspiller.setPoengsum(-5);
		} catch (Exception e) {
			
		}
		
		assertEquals(testspiller.getPoengsum(), 0);
		assertEquals(testspiller2.getPoengsum(), 10);		
	}

	@Test
	public void testGetId() {
		assertEquals(testspiller.getId(), 1);
		assertEquals(testspiller2.getId(), 2);		
	}
	
	@Test
	public void testSkrivUtSpiller() {
		// Create stream for output
		ByteArrayOutputStream outbytestream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outbytestream));

		// Run test
		testspiller.skrivUtSpiller();
		
		// Check output
		byte[] outbytes = outbytestream.toByteArray();
		ByteArrayInputStream testdata = new ByteArrayInputStream(outbytes);
		Scanner inn = new Scanner(testdata);
		String resultat = inn.nextLine();
		
		assertTrue(resultat.contains("Erlend"));
		assertTrue(resultat.contains("1"));
		assertTrue(resultat.contains("0"));

		inn.close();
		
		// Create stream for output
		outbytestream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outbytestream));

		// Run test
		testspiller2.setPoengsum(25);
		testspiller2.skrivUtSpiller();
		
		// Check output
		outbytes = outbytestream.toByteArray();
		testdata = new ByteArrayInputStream(outbytes);
		inn = new Scanner(testdata);
		resultat = inn.nextLine();
		
		assertTrue(resultat.contains("Fredrik"));
		assertTrue(resultat.contains("2"));
		assertTrue(resultat.contains("25"));		
	}

	@Test
	public void testHoyestPoengsum() {
		testspiller2.setPoengsum(10);
		assertEquals(testspiller2, Spiller.hoyestPoengsum(testspiller, testspiller2));
		testspiller.setPoengsum(20);
		assertEquals(testspiller, Spiller.hoyestPoengsum(testspiller, testspiller2));		
	}

}
