package com.mylearning.onlinetests;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//Salesforce Phone screening
public class MinimumSupplier {
    private Set<Supplier> suppliers;

    public MinimumSupplier(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    static class Supplier {
        String name;
        Set<String> products;

        public Supplier(String name, Set<String> products) {
            this.name = name;
            this.products = products;
        }

        public String getName() {
            return name;
        }

        public Set<String> getProducts() {
            return products;
        }

        @Override
        public String toString() {
            return "Supplier{" +
                    "name='" + name + '\'' +
                    ", products=" + Arrays.toString(products.toArray()) +
                    '}';
        }
    }

    /*
    It uses greedy approach to pick the supplier supplying maximum products first out of the products that need to be bought
    To achieve this it uses priority queue and custom comparator on the size of products from productsToBuy supplied by supplier
    After choosing one of the supplier it checks for the next supplier which has other new products to offer from productsToBuy
    */
    public Set<Supplier> getMinimumSuppliers(List<String> productsToBuy) {

        PriorityQueue<Map.Entry<Supplier, Set<String>>> supplierToProductHeap = new PriorityQueue<>(
                (o1, o2) -> o2.getValue().size() - o1.getValue().size());
        for (Supplier supplier : suppliers) {
            //get intersection of their products with productsToBuy
            Set<String> products = new HashSet<>(supplier.getProducts());
            products.retainAll(productsToBuy);
            if (!products.isEmpty()) {
                //put it in the max heap ordered by size of products that can be bought
                supplierToProductHeap.offer(new SimpleImmutableEntry<>(supplier, products));
            }
        }

        Set<Supplier> result = new HashSet<>();

        //pick the suppliers in order of the size of products they are supplying
        Set<String> productsAlreadyBought = new HashSet<>();

        while(!supplierToProductHeap.isEmpty()) {
            //if intersection of their product is non-zero add it to suppliers list else skip it
            Map.Entry<Supplier, Set<String>> currentSupplier = supplierToProductHeap.poll();
            Set<String> supplierProvidingProducts = new HashSet<>(currentSupplier.getValue());
            supplierProvidingProducts.removeAll(productsAlreadyBought);
            if(!supplierProvidingProducts.isEmpty()) {
                result.add(currentSupplier.getKey());
                productsAlreadyBought.addAll(supplierProvidingProducts);
            }
        }

        return result;
    }
}
