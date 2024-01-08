package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private Match testMatch;
    private String testTeam1;
    private String testTeam2;

    @BeforeEach
    void runBefore() {
        testTeam1 = ("Evil Geniuses");
        testTeam2 = ("Paper Rex");
        testMatch = new Match();
        testMatch.setTeam1(testTeam1);
        testMatch.setTeam2(testTeam2);
    }

    @Test
    void testConstructor() {
        assertEquals("Evil Geniuses", testMatch.getT1());
        assertEquals("Paper Rex", testMatch.getT2());
        assertEquals(0, testMatch.getScoreTeam1());
        assertEquals(0, testMatch.getScoreTeam2());
    }

    @Test
    void testSetMap() {
        testMatch.setMap("Lotus");
        assertEquals("Lotus", testMatch.getMap());
        testMatch.setMap("Ascent");
        assertEquals("Ascent", testMatch.getMap());
    }

    @Test
    void testSetScoreT1() {
        testMatch.setScoreTeam1(11);
        assertEquals(11, testMatch.getScoreTeam1());
        testMatch.setScoreTeam1(15);
        assertEquals(15, testMatch.getScoreTeam1());
    }

    @Test
    void testSetScoreT2() {
        testMatch.setScoreTeam2(0);
        assertEquals(0, testMatch.getScoreTeam2());
        testMatch.setScoreTeam2(5);
        assertEquals(5, testMatch.getScoreTeam2());
    }


}