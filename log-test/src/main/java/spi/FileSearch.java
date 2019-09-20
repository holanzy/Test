package spi;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jinbiao.yao on 2019/2/21.
 */
public class FileSearch implements Search {
    @Override
    public List serch(String keyword) {
        List<String> list = Arrays.asList("FileSearch");
        return list;
    }
}
