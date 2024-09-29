package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Monitor", 800),
                new Order("Smartphone", 900.0),
                new Order("Monitor", 600)
        );

        Map<String, Double> productTotalCost = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));

        System.out.println(productTotalCost);

        List<Map.Entry<String, Double>> sortedProducts = productTotalCost.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toList());

        System.out.println(sortedProducts);

        List<Map.Entry<String, Double>> topThreeProducts = sortedProducts.stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Три самых дорогих продукта и их общая стоимость");
        topThreeProducts.forEach(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue()));

    }
}