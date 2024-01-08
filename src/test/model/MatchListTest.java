package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchListTest {
    private Match testMatch;
    private Match testMatch2;
    private String testTeam1;
    private String testTeam2;
    private String testTeam3;
    private String testTeam4;
    private MatchList testMatchList;

    @BeforeEach
    void runBefore() {
        testTeam1 = ("Evil Geniuses");
        testTeam2 = ("Paper Rex");
        testMatch = new Match();
        testMatch.setTeam1(testTeam1);
        testMatch.setTeam2(testTeam2);
        testTeam3 = ("C9");
        testTeam4 = ("SKT");
        testMatch2 = new Match();
        testMatch2.setTeam1(testTeam3);
        testMatch2.setTeam2(testTeam4);
        testMatchList = new MatchList();
    }

    @Test
    void testConstructor() {
        assertTrue(testMatchList.getMatchList().isEmpty());
    }

    @Test
    void testAddMatch() {
        testMatchList.addMatch(testMatch);
        assertEquals(1, testMatchList.getMatchList().size());
        assertEquals(testMatch, testMatchList.getMatchList().get(0));
    }

    @Test
    void testMultipleMatch() {
        testMatchList.addMatch(testMatch);
        assertEquals(1, testMatchList.getMatchList().size());
        testMatchList.addMatch(testMatch2);
        assertEquals(2, testMatchList.getMatchList().size());
        assertEquals(testMatch, testMatchList.getMatchList().get(0));
        assertEquals(testMatch2, testMatchList.getMatchList().get(1));
    }

    @Test
    void testMapPlayed() {
        testMatchList.addMatch(testMatch);
        testMatchList.addMatch(testMatch2);
        testMatch.setMap("Bind");
        testMatch2.setMap("Haven");
        List<String> maps = Arrays.asList("Bind", "Haven");
        assertEquals(maps, testMatchList.getMapsPlayed());
        assertEquals(testMatch, testMatchList.getMatchList().get(0));
        assertEquals(testMatch2, testMatchList.getMatchList().get(1));
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