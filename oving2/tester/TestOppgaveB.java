package tester;

import static org.junit.Assert.*;

import org.junit.Test;

import oppgaver.OppgaveBogC;

public class TestOppgaveB {

	
	
	@Test
	public void testB() {
		assertEquals(0, OppgaveBogC.fallengde(0), 0.1);
		assertEquals(4.9, OppgaveBogC.fallengde(1), 0.1);
		assertEquals(122.6, OppgaveBogC.fallengde(5), 0.1);
		assertEquals(490, OppgaveBogC.fallengde(10), 1.0);

	}

}
