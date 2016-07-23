import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple String manipulation library without any dependencies.
 *
 * @author sam
 * @date 2016-07-15
 * @since JDK 1.5
 */

public abstract class SimpleString {

    /**
     * Append Strings to value.
     *
     * @param value   origin string
     * @param appends An array of strings to append
     * @return        full String
     */
    public static String appendString(final String value, final String... appends) {
        return appendArray(value, appends);
    }

    /**
     * Append an array of String to value.
     *
     * @param value   origin string
     * @param appends An array of strings to append
     * @return        full String
     */
    public static String appendArray(final String value, final String[] appends) {
        if(appends == null || appends.length == 0) {
            return value;
        }
        StringBuffer buffer = new StringBuffer("");
        for(String append : appends) {
            buffer.append(append);
        }
        return value + buffer.toString();
    }

    /**
     * Get the character at index. This method will take care of negative indexes.
     * The valid value of index is between -length and (length-1).
     *
     * @param value input value
     * @param index location
     * @return      A character if found else empty
     */
    public static String charAtIndex(final String value, int index) {
        if(value == null || value.isEmpty()) {
            return "";
        }
        int length = value.length();
        if(index < 0) {
            index = length + index;
        }
        return (index < length && index >= 0) ? String.valueOf(value.charAt(index)) : "";
    }

    /**
     * Returns a String array consisting of the characters in the String.
     *
     * @param value input String
     * @return      character array
     */
    public static String[] stringToArray(final String value) {
        if(value == null || value.isEmpty()) {
            return new String[0];
        }
        int length = value.length();
        String[] array = new String[length];
        String[] result = value.split("");
        if(result.length <= length) {
            return result;
        }
        for(int i = 0; i < length; i ++) {
            array[i] = result[i+1];
        }
        return array;
    }

    /**
     * Replace consecutive whitespace characters with a single space.
     *
     * @param value input String
     * @return      collapsed String
     */
    public static String collapseWhitespace(final String value) {
        if(value == null || value.isEmpty()) {
            return "";
        }
        return value.trim().replaceAll("\\s+", " ");
    }

    /**
     * Verifies that the needle is contained in the value.
     *
     * @param value   input String to search
     * @param needle  needle to find
     * @return  true if found else false.
     */
    public static boolean contains(final String value, final String needle) {
        return contains(value, needle, true);
    }

    /**
     * Verifies that the needle is contained in the value.It is case sensitive.
     *
     * @param value   input String to search
     * @param needle  needle to find
     * @param caseSensitive true or false
     * @return  true if found else false.
     */
    public static boolean contains(final String value, final String needle, final boolean caseSensitive) {
        isStringExist(value);
        if(caseSensitive) {
            return value.contains(needle);
        }
        return value.toLowerCase().contains(needle.toLowerCase());
    }

    /**
     * Verifies that all needles are contained in value. The search is case insensitive.
     *
     * @param value   input String to search
     * @param needles needles to find
     * @return true if all needles are found else false.
     */
    public static boolean containsAll(final String value, final String[] needles) {
        return containsAll(value, needles, true);
    }

    /**
     * Verifies that all needles are contained in value.
     *
     * @param value         input string to search
     * @param needles       needles to find
     * @param caseSensitive true or false
     * @return true if all needles are found else false.
     */
    public static boolean containsAll(final String value, final String[] needles, final boolean caseSensitive) {
        boolean result = true;
        for(int i = 0; i < needles.length; i ++) {
            result = contains(value, needles[i], caseSensitive);
            if(!result) {
                break;
            }
        }
        return result;
    }

    /**
     * Verifies that one or more of needles are contained in value.
     *
     * @param value   input String to search
     * @param needles needles to find
     * @return true if all needles are found else false.
     */
    public static boolean containsAny(final String value, final String[] needles) {
        return containsAny(value, needles, true);
    }

    /**
     * Verifies that one or more of needles are contained in value.
     *
     * @param value   input String to search
     * @param needles needles to find
     * @param caseSensitive true or false.
     * @return true if all needles are found else false.
     */
    public static boolean containsAny(final String value, final String[] needles, final boolean caseSensitive) {
        boolean result = false;
        for(int i = 0; i < needles.length; i ++) {
            result = contains(value, needles[i], caseSensitive);
            if(result) {
                break;
            }
        }
        return result;
    }

    /**
     * Count the number of times substr appears in value.
     *
     * @param value  input String
     * @param subStr input String to search
     * @return count of times substring exists.
     */
    public static long countSubstr(final String value, final String subStr) {
        return countSubstr(value, subStr, true);
    }

    /**
     * Count the number of times substr appears in value.
     *
     * @param value            input
     * @param subStr           input string to search
     * @param caseSensitive    whether search should be case sensitive.
     * @return
     */
    public static long countSubstr(final String value, final String subStr, final boolean caseSensitive) {
        return countSubstr(caseSensitive ? value : value.toLowerCase(), caseSensitive ? subStr : subStr.toLowerCase(), 0L);
    }

    private static long countSubstr(String value, String subStr, long count) {
        int position = value.indexOf(subStr);
        if(position == -1) {
            return count;
        }
        return countSubstr(value.substring(position + 1), subStr, ++count);
    }

    /**
     * Test if the value ends with search.
     *
     * @param value  The input String
     * @param search The String to search
     * @return true or false
     */
    public static boolean endsWith(final String value, final String search) {
        return endsWith(value, search, true);
    }

    /**
     * Test if value ends with search. It is case sensitive.
     *
     * @param value         input string
     * @param search        string to search
     * @param caseSensitive true or false
     * @return true or false
     */
    public static boolean endsWith(final String value, final String search, final boolean caseSensitive) {
        return endsWith(value, search, value.length(), caseSensitive);
    }

    /**
     * Test if value ends with search.
     *
     * @param value         input string
     * @param search        string to search
     * @param position      position till which you want to search.
     * @param caseSensitive true or false
     * @return true of false
     */
    public static boolean endsWith(final String value, final String search, final int position, final boolean caseSensitive) {
        isStringExist(value);
        int remainingLength = position - search.length();
        if(caseSensitive) {
            return value.indexOf(search, remainingLength) > -1;
        }
        return value.toLowerCase().indexOf(search.toLowerCase(), remainingLength) > -1;
    }

    /**
     * Ensures that the value begins with prefix.If it doesn't exist,it's prepended.It is case sensitive.
     *
     * @param value  input
     * @param prefix prefix
     * @return string with prefix if it was not present.
     */
    public static String ensureLeft(final String value, final String prefix) {
        return ensureLeft(value, prefix, true);
    }

    /**
     * Ensures that the value begins with prefix. If it doesn't exist, it's prepended.
     *
     * @param value         input
     * @param prefix        prefix
     * @param caseSensitive true or false
     * @return string with prefix if it was not present.
     */
    public static String ensureLeft(final String value, final String prefix, final boolean caseSensitive) {
        isStringExist(value);
        if(caseSensitive) {
            return value.startsWith(prefix) ? value : prefix + value;
        }
        String _value = value.toLowerCase();
        String _prefix = prefix.toLowerCase();
        return _value.startsWith(_prefix) ? value : prefix + value;
    }

    /**
     * Ensures that the value ends with suffix.If it doesn't, it's appended. This operation is case sensitive.
     * @param value
     * @param suffix The substr to be ensured to be right
     * @return The string which is guaranteed to start with substr.
     */
    public static String ensureRight(final String value, final String suffix) {
        return ensureRight(value, suffix, true);
    }

    /**
     * Ensures that the value ends with suffix. If it doesn't, it's appended.
     *
     * @param value         The input string
     * @param suffix        The substr to be ensured to be right.
     * @param caseSensitive Use case (in-)sensitive matching for determining if value already ends with suffix.
     * @return The String which is guaranteed to start with substr.
     */
    public static String ensureRight(final String value, final String suffix, boolean caseSensitive) {
        return endsWith(value, suffix, caseSensitive) ? value : appendString(value, suffix);
    }

    /**
     *  Returns the first n chars of string.
     *
     * @param value The input string.
     * @param n     Number of chars to return.
     * @return The first n chars
     */
    public static String firstChars(final String value, final int n) {
        isStringExist(value);
        return value.substring(0, n);
    }

    /**
     * Return the first char of String.
     *
     * @param value The input string
     * @return The first char.
     */
    public static String headChar(final String value) {
        return firstChars(value,1);
    }

    /**
     * The indexOf() method returns the index within the calling string of the first occurrence of the specified value,
     * starting the search at from index.Returns -1 if the value is not found.
     *
     * @param value         The input string.
     * @param needle        The search string.
     * @param offset        The offset to start searching from.
     * @param caseSensitive boolean to indicate whether should be case sensitive.
     * @return Returns position of first occurrence of needle.
     */
    public static int indexOf(final String value, final String needle, int offset, boolean caseSensitive) {
        isStringExist(value);
        if(caseSensitive) {
            return value.indexOf(needle, offset);
        }
        return value.toLowerCase().indexOf(needle.toLowerCase(), offset);
    }

    /**
     * Tests if two Strings are unequal.
     *
     * @param first  The first String.
     * @param second The second String.
     * @return true if first and second are not equal false otherwise.
     */
    public static boolean unequal(final String first, final String second) {
        return !Objects.equals(first, second);
    }

    /**
     * Inserts 'substr' into the 'value' at the 'index' provided
     *
     * @param value  The input string
     * @param substr The String to insert
     * @param index  The index to insert substr
     * @return String with substr added.
     */
    public static String insertStringAtIndex(final String value, final String substr, final int index) {
        isStringExist(value);
        if(index > value.length()) {
            return value;
        }
        return appendString(value.substring(0, index), substr, value.substring(index));
    }

    /**
     * Verifies if String is uppercase.
     *
     * @param value The input String.
     * @return true if string uppercase false otherwise.
     */
    public static boolean isUpperCase(final String value) {
        isStringExist(value);
        for(int i = 0; i < value.length(); i++) {
            if(Character.isLowerCase(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if String is lower case.
     *
     * @param value The input string.
     * @return true if string is lowercase false otherwise.
     */
    public static boolean isLowerCase(final String value) {
        isStringExist(value);
        for(int i = 0; i < value.length(); i++) {
            if(Character.isUpperCase(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * Return the last n chars of string.
     *
     * @param value The input string
     * @param n     Number of chars to return
     * @return n last characters
     */
    public static String lastChars(final String value, int n) {
       isStringExist(value);
        if(n > value.length()) {
            return value;
        }
        return value.substring(value.length() - n);
    }

    /**
     * Checks whether Object is String.
     *
     * @param value The input String.
     * @return true if Object is a String false otherwise.
     */
    public static boolean isString(final Object value) {
        if(value == null) {
            throw new IllegalArgumentException("value can't be null");
        }
        return value instanceof String;
    }

    /**
     * This method returns the index within the calling String object of the last occurrence of the specified value, searching backwards from the offset.
     * Returns -1 if the value is not found. The search starts from the end and case sensitive.
     *
     * @param value  The input String
     * @param needle The search String
     * @return Return position of the last occurrence of 'needle'.
     */
    public static int lastIndexOf(final String value, final String needle) {
        return lastIndexOf(value, needle, value.length(), true);
    }

    /**
     * This method returns the index within the calling String object of the last occurrence of the specified value,
     * searching backwards from the offset.
     * Returns -1 if the value is not found.
     * The search starts from the end and case sensitive.
     *
     * @param value         The input String.
     * @param needle        The search String.
     * @param caseSensitive whether search should be case sensitive.
     * @return Return position of the last occurrence of 'needle'.
     */
    public static int lastIndexOf(final String value, final String needle, boolean caseSensitive) {
        return lastIndexOf(value, needle, value.length(), caseSensitive);
    }

    /**
     * This method returns the index within the calling String object of the last occurrence of the specified value,
     * searching backwards from the offset.
     * Returns -1 if the value is not found.
     *
     * @param value         The input String.
     * @param needle        The search String.
     * @param offset        The index to start search from.
     * @param caseSensitive whether search should be case sensitive.
     * @return Return position of the last occurrence of 'needle'.
     */
    public static int lastIndexOf(final String value, final String needle, final int offset, final boolean caseSensitive) {
        isStringExist(value);
        if(caseSensitive) {
            return value.lastIndexOf(needle, offset);
        }
        return value.toLowerCase().lastIndexOf(needle.toLowerCase(), offset);
    }

    /**
     * Removes all spaces on left.
     *
     * @param value The input string
     * @return String without left border spaces.
     */
    public static String leftTrim(final String value) {
        isStringExist(value);
        return value.replaceAll("^\\s+","");
    }

    /**
     * Removes all spaces on right.
     *
     * @param value The input String
     * @return String without right borders spaces.
     */
    public static String rightTrim(final String value) {
        isStringExist(value);
        return value.replaceAll("\\s+$", "");
    }

    /**
     *  Returns length of String.Delegates to java.lang.String length method.
     * @param value The input string
     * @return Length of the String
     */
    public static int stringLength(final String value) {
        isStringExist(value);
        return value.length();
    }

    /**
     *  Return a new String starting with prepends.
     *
     * @param value    The input String
     * @param prepends Strings to prepend
     * @return The prepended String
     */
    public static String prepend(final String value, final String... prepends) {
        return prependArray(value, prepends);
    }

    /**
     * Return a new String starting with prepends.
     *
     * @param value    The input String
     * @param prepends Strings to prepend
     * @return The prepended String
     */
    public static String prependArray(final String value, final String[] prepends) {
        if(prepends == null || prepends.length == 0) {
            return value;
        }
        StringBuffer buffer = new StringBuffer("");
        for(String prepend : prepends) {
            buffer.append(prepend);
        }
        return buffer.toString() + value;
    }

    /**
     * Returns a new String with the prefix removed, if present. This is case sensitive.
     *
     * @param value         The input String.
     * @param prefix        String to remove on left.
     * @return The String without prefix
     */
    public static String removeLeft(final String value, final String prefix) {
        return removeLeft(value, prefix, true);
    }

    /**
     * Returns a new String with the prefix removed, if present.
     *
     * @param value         The input String.
     * @param prefix        String to remove on left.
     * @param caseSensitive ensure case sensitivity
     * @return The String without prefix
     */
    public static String removeLeft(final String value, final String prefix, final boolean caseSensitive) {
        isStringExist(value);
        if(caseSensitive) {
            return value.startsWith(prefix) ? value.substring(prefix.length()) : value;
        }
        return value.toLowerCase().startsWith(prefix.toLowerCase()) ? value.substring(prefix.length()) : value;
    }

    /**
     * Remove all non word characters.
     *
     * @param value The input String
     * @return String without non-word characters.
     */
    public static String removeNonWords(final String value) {
        isStringExist(value);
        return value.replaceAll("[^\\w]+","");
    }

    /**
     * Returns a new String with the 'suffix' removed, if present. Search is case sensitive.
     *
     * @param value  The input String.
     * @param suffix The suffix to remove.
     * @return The String without suffix.
     */
    public static String removeRight(final String value, final String suffix) {
        return removeRight(value, suffix, true);
    }

    /**
     * Returns a new String with the 'suffix' removed, if present.
     *
     * @param value  The input string.
     * @param suffix The suffix to remove.
     * @param caseSensitive whether search should be case sensitive or not
     * @return The String without suffix.
     */
    public static String removeRight(final String value, final String suffix, final boolean caseSensitive) {
        return endsWith(value, suffix, caseSensitive) ? value.substring(0, value.toLowerCase().lastIndexOf(suffix.toLowerCase())) : value;
    }

    /**
     *  Remove all spaces and replace for value.
     *
     * @param value The input string
     * @return String without spaces.
     */
    public static String removeSpaces(final String value) {
        isStringExist(value);
        return value.replaceAll("\\s", "");
    }

    /**
     * Replace all occurrences of 'search' value to 'newvalue'.Uses String replace method.
     * @param value
     * @param search
     * @param newValue
     * @param caseSensitive whether search should be case
     * @return String replaced with 'newvalue'.
     */
    public static String replaceString(final String value, final String search, final String newValue, final boolean caseSensitive) {
        isStringExist(value);
        if(caseSensitive) {
            return value.replace(search, newValue);
        }
        return Pattern.compile(search, Pattern.CASE_INSENSITIVE).matcher(value).replaceAll(Matcher.quoteReplacement(newValue));
    }

    /**
     * Reverse the input String.
     *
     * @param value The input String.
     * @return Reverse String
     */
    public static String reverse(final String value) {
        isStringExist(value);
        return new StringBuilder(value).reverse().toString();
    }

    /**
     * Truncate the string securely , not cutting a word in half.It always returns the last full word.
     *
     * @param value  The input String.
     * @param length Max size of the truncated String.
     * @param filler String that will be added to the end of the return string.
     * @return The truncated String
     */
    public static String safeTruncate(final String value, final int length, final String filler) {
        isStringExist(value);
        if(length == 0) {
            return "";
        }
        if(length >= value.length()) {
            return value;
        }
        String[] words = words(value);
        StringBuffer buffer = new StringBuffer(" ");
        int spaceCount = 0;
        for(String word : words) {
            if(buffer.length() + word.length() + filler.length() + spaceCount > length) {
                break;
            } else {
                buffer.append(word);
                spaceCount ++;
            }
        }
        return appendString(buffer.toString(), filler);
    }

    /**
     * Alias to String split function.Defined only for completeness.
     *
     * @param value The input String
     * @param regex The delimiting regular expression.
     * @return String Array
     */
    public static String[] split(final String value, final String regex) {
        isStringExist(value);
        return value.split(regex);
    }

    /**
     * Splits a String to words.
     * @param value The input String.
     * @return Words Array.
     */
    public static String[] words(final String value) {
        isStringExist(value);
        return value.split("\\W+");
    }

    public static String truncate(final String value, final int length, final String filler) {
        isStringExist(value);
        if(length == 0) {
            return "";
        }
        if(length >= value.length()) {
            return value;
        }
        return appendString(value.substring(0, length - filler.length()), filler);
    }

    /**
     * Alice of substring method.
     *
     * @param value The input string.
     * @param begin Start of slice.
     * @param end   End of slice.
     * @return The String sliced.
     */
    public static String slice(final String value, int begin, int end) {
        isStringExist(value);
        return value.substring(begin, end);
    }

    /**
     * Return tail of the String.
     *
     * @param value The input String.
     * @return String tail.
     */
    public static String tail(final String value) {
        return lastChars(value, value.length() - 1);
    }

    /**
     * Verifies if String is exist.
     *
     * @param value The input value.
     * @return true if String is exist false otherwise
     *
     */
    private static void isStringExist(final String value) {
        if(value == null || value.equals("")) {
            throw new IllegalArgumentException("value can't be null");
        }
    }
}
