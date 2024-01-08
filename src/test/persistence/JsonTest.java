package persistence;

import model.Match;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTeam(String t1, String t2, Match match) {
        assertEquals(t1, match.getT1());
        assertEquals(t2, match.getT2());
    }
}
