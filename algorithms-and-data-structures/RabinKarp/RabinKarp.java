
import java.util.ArrayList;

public class RabinKarp {

    private final static int base = 29;

    /**
     * This method uses the RabinKarp algorithm to search a given pattern in a given input text.
     *
     * @param pattern - The pattern that is searched in the text.
     * @param text    - The text in which the pattern is searched.
     * @return An ArrayList<Integer> containing the start indices of the found patterns in the input text.
     * @throws IllegalArgumentException if pattern or text is null.
     */
    static public ArrayList<Integer> search(String pattern, String text) throws IllegalArgumentException {
        if (pattern == null || text == null) {
            throw new IllegalArgumentException("Pattern or  text cannot be null!");
        }

        int patternLength = pattern.length();
        int textLength = text.length();
        ArrayList<Integer> result = new ArrayList<>();

        if (patternLength == 0 || textLength == 0 || patternLength > textLength) {
            return result;
        }

        for (int i = 0; i < textLength - patternLength; ++i) {
            int check = 1;
            for (int j = 0; j < patternLength; ++j) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    check = 0;
                    break;
                }
            }
            if (check == 1) {
                result.add(i);
            }
        }
        return result;
    }


    /**
     * This method calculates the (rolling) hash code for a given character sequence. For the calculation
     * use the base as given in the assignment sheet.
     * If previousHash has the value -1, the rolling hash code shall be initialized with
     * the characters given in the sequence. This can also be used to calculate the hashcode for the pattern!
     *
     * @param sequence      - The char sequence for which the (rolling) hash shall be computed.
     * @param lastCharacter - The character that shall be removed from the hash.
     * @param previousHash  - The previously calculated hash value that will be reused for the new hash values.
     *                      A previousHash value of -1 indicates the initialization of the rolling hash code.
     * @return calculated hash value for the given character sequence.
     */
    static public long getRollingHashValue(char[] sequence, char lastCharacter, long previousHash) {

        long hash;
        if (previousHash == -1) {
            hash = 0;
            for (char c : sequence) {
                hash = (hash * base + c);
            }
        } else {
            hash = (previousHash * base - lastCharacter * (long) Math.pow(base, sequence.length) + sequence[sequence.length - 1]);
        }
        return hash;
    }
}
