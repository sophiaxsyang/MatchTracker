package persistence;

import model.Match;
import model.MatchList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            MatchList ml = new MatchList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            MatchList ml = new MatchList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMatchList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMatchList.json");
            ml = reader.read();
            assertEquals(0, ml.getMatchList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            MatchList ml = new MatchList();
            String testTeam1 = ("Evil Geniuses");
            String testTeam2 = ("Paper Rex");
            Match testMatch = new Match();
            testMatch.setTeam1(testTeam1);
            testMatch.setTeam2(testTeam2);
            testMatch.setMap("Bind");
            String testTeam3 = ("C9");
            String testTeam4 = ("SKT");
            Match testMatch2 = new Match();
            testMatch2.setTeam1(testTeam3);
            testMatch2.setTeam2(testTeam4);
            testMatch2.setMap("Haven");
            ml.addMatch(testMatch);
            ml.addMatch(testMatch2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMatchList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMatchList.json");
            ml = reader.read();
            List<Match> matches = ml.getMatchList();
            assertEquals(2, matches.size());
            checkTeam("Evil Geniuses", "Paper Rex", matches.get(0));
            checkTeam("C9", "SKT", matches.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}