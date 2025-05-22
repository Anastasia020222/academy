package count.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("dog");
        list.add("sheep");
        list.add("cat");
        list.add("cock");
        list.add("sheep");
        list.add("cat");

        Map<String, Integer> map = countOfElement(list);

        System.out.println(map);
    }

    public static Map<String, Integer> countOfElement(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String l : list) {
            if (!map.containsKey(l)) {
                map.put(l, 1);
            } else {
                int n = map.get(l) + 1;
                map.put(l, n);
            }
        }
        return map;
    }
}