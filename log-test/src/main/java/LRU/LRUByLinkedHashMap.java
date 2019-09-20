package LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jinbiao.yao on 2019/9/19.
 * 使用LinkedHashMap实现LRU缓存替换策略
 */
public class LRUByLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new LRUByLinkedHashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
            if (i == 9) {
                System.out.println(map.get(0));
            }
        }
    }

    LRUByLinkedHashMap() {
        //true:按访问顺序排序（访问后会改变插入顺序），false:按插入顺序排序
        super(16, 0.75f, true);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //当size大于10时，会替换掉最近最少访问的key
        return this.size() > 10;
    }
}
