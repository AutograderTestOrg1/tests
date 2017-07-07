package tester;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestOppgaveD {

	@Test
	public void testD() {
		double resultat;
		resultat = oppgaver.OppgaveD.sumGeometriskRekkeForLoop(2, 1, 10);
		assertEquals(22, resultat, 0.1);
		resultat = oppgaver.OppgaveD.sumGeometriskRekkeForLoop(2, 2, 5);
		assertEquals(126, resultat, 0.1);	
		resultat = oppgaver.OppgaveD.sumGeometriskRekkeForLoop(2, 0.5, 5);
		assertEquals(3.94, resultat, 0.01);
		resultat = oppgaver.OppgaveD.sumGeometriskRekkeForLoop(3, 3, 3);
		assertEquals(120, resultat, 0.1);
	}

}
