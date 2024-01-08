package persistence;

import model.MatchList;
import model.Match;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MatchList ml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMatchList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMatchList.json");
        try {
            MatchList ml = reader.read();
            assertEquals(0, ml.getMatchList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMatchlist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMatchList.json");
        try {
            MatchList ml = reader.read();
            List<Match> matches = ml.getMatchList();
            assertEquals(2, matches.size());
            checkTeam("fnc", "c9", ml.getMatchList().get(0));
            checkTeam("eg", "skt", ml.getMatchList().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}