package parser.service.impl;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseParser {
    static final String REGEX_MISMATCH = "regexMisMatch";

    static void print(String s) {
        System.out.println(s);
    }
    /*
     * @param  text to apply regex
     * @param  regexPatten regex pattern to identify from the given text
     * @param  position where the data is present
     *
     *
     */
    static String getMatch(String text, String regexPattern, int positon) {
        String result = "";
        try {
            Pattern pattern = Pattern.compile(regexPattern,Pattern.CASE_INSENSITIVE|Pattern.DOTALL|Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                result = matcher.group(positon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * @return return can be null
     * */
    static Matcher getMatches(String text, String regexPattern) {
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(regexPattern,Pattern.CASE_INSENSITIVE|Pattern.DOTALL|Pattern.MULTILINE);
            matcher = pattern.matcher(text);
            return matcher;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

}
