package spi;

/**
 * Created by jinbiao.yao on 2019/2/21.
 */
public class SearchTest {
    public static void main(String[] args) {
        Search search = SearchFactory.newSearch();
        System.out.println(search.serch("java spi test"));
    }

}
