package sentenceDictionary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeduplicationClassTest {
    private static final String ROBERT_MA_KOTA = "Robert ma kota";
    private DeduplicationClass deduplicationClass;
    private final static String ALA_MA_KOTA = "Ala ma kota";
    private final static String PAWEL_MA_KOTA = "Pawel ma kota";
    private final static String PAWEL_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS = "Pawel idz do sklepu!!!";
    private final static String ROMAN_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS = "Roman idz do sklepu!!!";
    private final static String PAWEL_IDZ_DO_SKLEPU_ONE_EXCLAMATION_MARKS = "Pawel idz do sklepu!";


    @Before
    public void setup(){
        deduplicationClass = new DeduplicationClass();
    }

    @Test
    public void addSingleSentence() {
        //given
        deduplicationClass.addSentence(ALA_MA_KOTA);
        //when
        int mapSize = deduplicationClass.getDictionary().size();
        //then
        assertEquals(1, mapSize);
    }

    @Test(expected = DeduplicationInterface.NonExistentSentence.class)
    public void getSentence() throws Exception {
        //given
        deduplicationClass.addSentence(ALA_MA_KOTA);
        //when
        deduplicationClass.getSentence("Ala ma psa i lisa");
        //then expect NonExistentSentence Exception
    }

    @Test
    public void addTwoSentences() {
        //given
        deduplicationClass.addSentence(ALA_MA_KOTA);
        deduplicationClass.addSentence(PAWEL_MA_KOTA);
        //when
        int mapSize = deduplicationClass.getDictionary().size();
        //then
        assertEquals(4, mapSize);
        assertTrue(deduplicationClass.getDictionary().values().contains("ma kota"));
        assertTrue(deduplicationClass.getDictionary().values().contains("Pawel"));
    }

    @Test(expected = DeduplicationInterface.NonExistentSentence.class)
    public void removeSentence_nonExist() throws DeduplicationInterface.NonExistentSentence {
        deduplicationClass.removeSentence(PAWEL_MA_KOTA);
    }

    @Test
    public void removeSentences() throws DeduplicationInterface.NonExistentSentence {
        //given
        deduplicationClass.addSentence(ALA_MA_KOTA);
        deduplicationClass.addSentence(PAWEL_MA_KOTA);
        deduplicationClass.addSentence(ROBERT_MA_KOTA);
        //when
        deduplicationClass.removeSentence(ALA_MA_KOTA);
        int mapSize = deduplicationClass.getDictionary().size();
        //then
        assertEquals(4, mapSize);
        assertTrue(deduplicationClass.getDictionary().values().contains("ma kota"));
        assertFalse(deduplicationClass.getDictionary().values().contains("Ala"));

        //when
        deduplicationClass.removeSentence(PAWEL_MA_KOTA);
        //then
        assertEquals(1, deduplicationClass.getDictionary().size());
        assertTrue(deduplicationClass.getDictionary().values().contains(ROBERT_MA_KOTA));
    }

    @Test
    public void testAddSentencesWithSpecialCharacters() {
        //when
        deduplicationClass.addSentence(PAWEL_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS);
        deduplicationClass.addSentence(ROMAN_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS);
        //then
        assertEquals(5, deduplicationClass.getDictionary().size());
        assertTrue(deduplicationClass.getDictionary().values().contains("!!!"));
        assertTrue(deduplicationClass.getDictionary().values().contains("Pawel"));
        assertTrue(deduplicationClass.getDictionary().values().contains(" "));

    }

    @Test(expected = DeduplicationInterface.NonExistentSentence.class)
    public void testGetSentenceWithSpecialCharacters() throws DeduplicationInterface.NonExistentSentence {
        //given
        deduplicationClass.addSentence(PAWEL_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS);
        deduplicationClass.addSentence(ROMAN_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS);
        //when
        deduplicationClass.getSentence(PAWEL_IDZ_DO_SKLEPU_ONE_EXCLAMATION_MARKS);
        //then expect NonExistentSentence Exception
    }

    @Test
    public void testAddSentenceWithSingleExclamationMark() throws DeduplicationInterface.NonExistentSentence {
        //when
        deduplicationClass.addSentence(PAWEL_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS);
        deduplicationClass.addSentence(ROMAN_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS);
        deduplicationClass.addSentence(PAWEL_IDZ_DO_SKLEPU_ONE_EXCLAMATION_MARKS);
        //then
        assertEquals(6, deduplicationClass.getDictionary().size());
        assertTrue(deduplicationClass.getDictionary().values().contains("!"));
        assertFalse(deduplicationClass.getDictionary().values().contains("!!!"));
    }

    @Test
    public void testDuplicatedWordsInSentence(){
        //when
        deduplicationClass.addSentence("ole ole");
        //then
        assertEquals(2, deduplicationClass.getDictionary().size());
        assertTrue(deduplicationClass.getDictionary().values().contains("ole"));
        assertTrue(deduplicationClass.getDictionary().values().contains(" "));
    }

}
