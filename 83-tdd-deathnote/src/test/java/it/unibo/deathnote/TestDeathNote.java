package it.unibo.deathnote;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeathNote {

    @Test
    void testInvalidRuleNumbers() {
        DeathNote deathNote = new DeathNoteImpl();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deathNote.getRule(0));
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());

        exception = assertThrows(IllegalArgumentException.class, () -> deathNote.getRule(-1));
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isBlank());
    }

    @Test
    void testNonEmptyRules() {
        DeathNote deathNote = new DeathNoteImpl();

        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            String rule = deathNote.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    void testDeathNoteEntries() {
        DeathNote deathNote = new DeathNoteImpl();

        assertFalse(deathNote.isNameWritten("John"));

        deathNote.writeName("John");
        assertTrue(deathNote.isNameWritten("John"));

        assertFalse(deathNote.isNameWritten("Another"));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    void testWriteDeathCause() throws InterruptedException {
        DeathNote deathNote = new DeathNoteImpl();

        assertThrows(IllegalStateException.class, () -> deathNote.writeDeathCause("Heart failure"));

        deathNote.writeName("Alice");
        assertEquals("Heart attack", deathNote.getDeathCause("Alice"));

        deathNote.writeName("Bob");
        assertTrue(deathNote.writeDeathCause("Karting accident"));
        assertEquals("Karting accident", deathNote.getDeathCause("Bob"));

        Thread.sleep(100);

        assertFalse(deathNote.writeDeathCause("Drowning"));
        assertEquals("Karting accident", deathNote.getDeathCause("Bob"));
    }

    @Test
    void testWriteDeathDetails() throws InterruptedException {
        DeathNote deathNote = new DeathNoteImpl();

        assertThrows(IllegalStateException.class, () -> deathNote.writeDetails("Exhaustion"));

        deathNote.writeName("Alice");
        assertEquals("", deathNote.getDeathDetails("Alice"));

        assertTrue(deathNote.writeDetails("Ran for too long"));
        assertEquals("Ran for too long", deathNote.getDeathDetails("Alice"));

        deathNote.writeName("Bob");
        Thread.sleep(6100);

        assertFalse(deathNote.writeDetails("Fell asleep"));
        assertEquals("", deathNote.getDeathDetails("Bob"));
    }
}