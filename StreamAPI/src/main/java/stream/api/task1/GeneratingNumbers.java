package stream.api.task1;

import java.util.*;
import java.util.stream.Collectors;

public class GeneratingNumbers {
    public static void main(String[] args) {

        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, List<Order>> map2 = groupingProducts(orders);
        Map<String, Double> map3 = getTotalCostProduct(map2);
        sortDescendingCost(map3);
        getTreeProduct(orders);
    }

    public static Map<String, List<Order>> groupingProducts(List<Order> orders) {
        Map<String, List<Order>> map1 = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));

        System.out.println("Группировка заказов по продуктам");
        map1.entrySet().forEach(System.out::println);
        return map1;
    }

    public static Map<String, Double> getTotalCostProduct(Map<String, List<Order>> map) {
        Map<String, Double> map2 = new HashMap<>();
        for (var l : map.entrySet()) {
            double n = l.getValue().stream()
                    .mapToDouble(Order::getCost)
                    .sum();
            map2.put(l.getKey(), n);
        }
        System.out.println("\nОбщая стоимость по каждому продукту: ");
        map2.entrySet().forEach(System.out::println);
        return map2;
    }

    public static void sortDescendingCost(Map<String, Double> map3) {
        LinkedHashMap<String, Double> sorted = map3.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        System.out.println("\nСортировка в порядке убывания по общей стоимости:");
        sorted.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public static void getTreeProduct(List<Order> list) {
        List<Order> l = list.stream()
                .sorted(Comparator.comparingDouble(Order::getCost).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nТри самых дорогих продукта:");
        l.forEach(System.out::println);
    }
}