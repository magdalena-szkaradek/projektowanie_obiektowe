package commonWords;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeduplicationInterfaceImpl implements DeduplicationInterface {
    @Override
    public void addSentence(String sentence) {

    }

    @Override
    public void removeSentence(String sentence) {

    }

    @Override
    public Map<Integer, String> getDictionary() {
        return null;
    }

    @Override
    public List<Integer> getSentence(String sentence) throws NonExistentSentence {
        return null;
    }

    @Override
    public Set<String> getWords() {
        return null;
    }
}
