package commonWords;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeduplicationInterfaceImplTest {
    private static final String ROBERT_MA_KOTA = "Robert ma kota";
    private DeduplicationInterfaceImpl deduplicationClass;
    private final static String ALA_MA_KOTA = "Ala ma kota";
    private final static String PAWEL_MA_KOTA = "Pawel ma kota";
    private final static String PAWEL_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS = "Pawel idz do sklepu!!!";
    private final static String ROMAN_IDZ_DO_SKLEPU_THREE_EXCLAMATION_MARKS = "Roman idz do sklepu!!!";
    private final static String PAWEL_IDZ_DO_SKLEPU_ONE_EXCLAMATION_MARKS = "Pawel idz do sklepu!";


    @Before
    public void setup(){
        deduplicationClass = new DeduplicationInterfaceImpl();
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

    @Test
    public void getWords_test(){
        deduplicationClass.addSentence(ALA_MA_KOTA);
        deduplicationClass.addSentence("Grzegorz ma kota");
        deduplicationClass.addSentence("Pawel ma lisa");
        deduplicationClass.addSentence("Pawel ma lisa?");
        deduplicationClass.addSentence("Grzegorz biegnie");
        deduplicationClass.addSentence("Ala idzie do pracy");

        assertEquals(1, deduplicationClass.getWords().size());
        assertTrue(deduplicationClass.getWords().contains(" "));
    }


    @Test
    public void getWords_test_AfterRemove(){
        deduplicationClass.addSentence(ALA_MA_KOTA);
        deduplicationClass.addSentence("Grzegorz ma kota");
        deduplicationClass.addSentence("Pawel ma lisa");
        deduplicationClass.addSentence("Pawel ma lisa?");
        deduplicationClass.addSentence("Grzegorz biegnie");
        deduplicationClass.addSentence("Ala idzie do pracy");

        deduplicationClass.removeSentence(ALA_MA_KOTA);
        deduplicationClass.removeSentence("Grzegorz ma kota");
        deduplicationClass.removeSentence("Pawel ma lisa");
        deduplicationClass.removeSentence("Pawel ma lisa?");
        deduplicationClass.removeSentence("Grzegorz biegnie");
        deduplicationClass.removeSentence("Ala idzie do pracy");

        assertEquals(deduplicationClass.getDictionary().size(), deduplicationClass.getWords().size());
        assertTrue(deduplicationClass.getWords().contains(" "));
        assertFalse(deduplicationClass.getDictionary().values().contains(" "));
    }

    @Test
    public void getWords_test_specificSentence(){
        deduplicationClass.addSentence("ole ole,ole ole");

        assertEquals(1, deduplicationClass.getWords().size());
        assertTrue(deduplicationClass.getDictionary().values().contains("ole"));
    }

    @Test
    public void getWordsTest(){
        deduplicationClass.addSentence("Ala  ma?kota");
        deduplicationClass.addSentence("Pawel ma psa");
        deduplicationClass.addSentence("Grzegorz,ma?lisa");
        deduplicationClass.addSentence("Anna,ma!psa");

        assertTrue(deduplicationClass.getWords().contains("ma"));
    }

    @Test
    public void getWordsTest_2(){
        deduplicationClass.addSentence("Ala ma?kota");
        deduplicationClass.addSentence("Pawel ma psa");
        deduplicationClass.addSentence("Grzegorz ma?lisa");
        deduplicationClass.addSentence("Anna?ma?psa");

        assertTrue(deduplicationClass.getWords().contains("ma"));
        assertTrue(deduplicationClass.getWords().contains("?"));
        assertTrue(deduplicationClass.getWords().contains(" "));
        assertEquals(3, deduplicationClass.getWords().size());
    }

    @Test
    public void getWordsTest_3(){
        deduplicationClass.addSentence(ALA_MA_KOTA);
        deduplicationClass.addSentence("Pawel,ma kota");
        deduplicationClass.addSentence("Grzegorz,ma kota");
        deduplicationClass.addSentence("Grzgorz biegnie");
        deduplicationClass.addSentence("Anna!biegnie");
        deduplicationClass.addSentence("Anna?ma kota");

        assertTrue(deduplicationClass.getWords().contains("ma kota"));

        deduplicationClass.removeSentence("Pawel,ma kota");
        deduplicationClass.removeSentence("Anna?ma kota");
        deduplicationClass.addSentence("Anna?biegnie");
        deduplicationClass.addSentence("Anna biegnie");
        deduplicationClass.addSentence("Grzegorz?biegnie");
        deduplicationClass.removeSentence(ALA_MA_KOTA);
        deduplicationClass.removeSentence("Grzegorz,ma kota");

        assertFalse(deduplicationClass.getDictionary().values().contains("ma kota"));
        assertTrue(deduplicationClass.getWords().contains("biegnie"));
        assertFalse(deduplicationClass.getWords().contains("ma kota"));

    }



}
