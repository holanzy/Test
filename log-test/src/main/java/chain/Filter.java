package chain;

/**
 * Created by jinbiao.yao on 2019/1/17.
 */
public interface Filter {
    void doFilter(Request request,Response response,FilterChain chain);
}
