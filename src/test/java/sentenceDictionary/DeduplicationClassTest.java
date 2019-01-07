package sentenceDictionary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeduplicationClassTest {
    private DeduplicationClass deduplicationClass;
    private final static String ALA_MA_KOTA = "Ala ma kota";

    @Before
    public void setup(){
        deduplicationClass = new DeduplicationClass();
    }

    @Test
    public void addSentence() {
        deduplicationClass.addSentence(ALA_MA_KOTA);
        int mapSize = deduplicationClass.getDictionary().size();
        assertEquals(1, mapSize);
    }

    @Test
    public void addTwoSentences() {
        deduplicationClass.addSentence(ALA_MA_KOTA);
        deduplicationClass.addSentence("Pawel ma kota");
        int mapSize = deduplicationClass.getDictionary().size();
        assertEquals(4, mapSize);
    }

    @Test
    public void removeSentence() throws Exception {

    }

    @Test
    public void getDictionary(){

    }

    @Test(expected = DeduplicationInterface.NonExistentSentence.class)
    public void getSentence() throws Exception {
        deduplicationClass.addSentence(ALA_MA_KOTA);
        deduplicationClass.getSentence("Ala ma psa i lisa");
    }
}
