package tester;

import static org.junit.Assert.*;

import org.junit.Test;

import static oppgaver.OppgaveEtilH.*;

public class TestOppgaveEogFogH {

	@Test
	public void testE() {
		assertTrue(erDeleligMed(10, 2));
		assertTrue(erDeleligMed(15, 3));
		assertTrue(erDeleligMed(15, 5));
		assertFalse(erDeleligMed(15, 2));
		assertFalse(erDeleligMed(13, 3));
	}
	
	@Test public void testF() {
		assertEquals(minsteDivisor(4), 2);
		assertEquals(minsteDivisor(20), 2);
		assertEquals(minsteDivisor(21), 3);
		assertEquals(minsteDivisor(49), 7);
		assertEquals(minsteDivisor(13), 13);
	}
	
	@Test public void testH() {
		assertTrue(erPrimtall(13));
		assertFalse(erPrimtall(12));
		assertTrue(erPrimtall(2));
		assertTrue(erPrimtall(23));
		assertFalse(erPrimtall(143));
		assertTrue(erPrimtall(107));
	}

}
