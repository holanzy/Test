package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by jinbiao.yao on 2019/2/21.
 */
public class PrefixMatcherTest {
    public static void main(String[] args) {
        ServiceLoader<PrefixMatcher> matcher = ServiceLoader.load(PrefixMatcher.class);
        Iterator<PrefixMatcher> matcherIter = matcher.iterator();
        while (matcherIter.hasNext()) {
            PrefixMatcher wordMatcher = matcherIter.next();
            System.out.println(wordMatcher.getClass().getName());
            String[] prefixes = new String[] {"a", "b", "c", "d", "e", "f", "g", "i",
                    "l", "n", "p", "r", "s", "t", "v", "w", "do", "finally"};
            for (String prefix: prefixes) {
                System.out.println(wordMatcher.obtainMatchedWords(prefix));
            }
        }

    }
}
