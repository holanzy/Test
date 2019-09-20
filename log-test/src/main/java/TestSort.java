import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jinbiao.yao on 2019/5/27.
 */
class testS<T> {
    public int age;
    public String name;

    @Override
    public String toString() {
        return age + "name:" + name;
    }
}

public class TestSort {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(2);
        System.out.println(a.add(b));

    }

    public String longestPalindrome(String s) {
        int maxLen = 0;
        int maxTmp = 0;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(s, i, i);
            int len2 = expandCenter(s, i, i + 1);
            maxTmp = Math.max(len1, len2);
            if (maxLen < maxTmp) {
                maxLen = maxTmp;
                index = i;
            }
        }
        int len = 0;
        if (maxLen % 2 == 0) {
            len = maxLen / 2 - 1;
        } else {
            len = maxLen / 2;
        }
        return s.substring(index - len, index - len + maxLen);
    }

    public int expandCenter(String s, int left, int right) {
        while (left > 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
