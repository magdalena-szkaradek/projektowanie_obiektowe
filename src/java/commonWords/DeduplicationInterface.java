package commonWords;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DeduplicationInterface {

    /**
     * Metoda dodajÄ…ca zdanie
     * @param sentence - podawane zdanie
     */
    public void addSentence(String sentence);

    /**
     * Metoda usuwajaca zdanie
     * @param  sentence - zdanie, ktore ma zostac usuniete
     */
    public void removeSentence(String sentence);

    /**
     * Metoda pozwalajaca na wyswietlenie aktualnego slownika.
     * Kazdemu slowu w momencie dodania do slownika jest przypisywana kolejna wartosc klucza typu Integer.
     * @return slownik
     */
    public Map<Integer, String> getDictionary();

    /**
     * Wyjatek generowany w momencie proby usuniecia zdania wczesniej nie wprowadzonego
     */
    public class NonExistentSentence extends Exception {
    }
    /**
     * Metoda pozwalajaca na na odtworzenie zdania na podstawie aktualnego slownika
     * @param sentence - zdanie, ktore ma byc zwrocone
     * @throws NonExistenceSentence - wyjatek, ktory ma wystapic jesli nie ma mozliwosci odtworzenia zdania na podstawie aktualnego slownika
     * @return zdanie (wartosci kluczy poszczegolnych slow w slowniku)
     */
    public List<Integer> getSentence(String sentence) throws NonExistentSentence;

    /**
     * Metoda wyswietlajaca 10% najczesciej uzywanych slow
     * @return zbior 10% najczesciej uzywanych slow
     */
    public Set<String> getWords();
}
