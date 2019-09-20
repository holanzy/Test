package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by jinbiao.yao on 2019/2/21.
 */
public class SearchFactory {
    private SearchFactory() {
    }
    public static Search newSearch() {
        Search search = null;
        ServiceLoader<Search> serviceLoader = ServiceLoader.load(Search.class);
        Iterator<Search> searchs = serviceLoader.iterator();
        if (searchs.hasNext()) {
            search = searchs.next();
        }
        return search;
    }
}
