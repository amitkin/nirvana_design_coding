package com.mylearning.onlinetests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mylearning.onlinetests.MinimumSupplier.Supplier;
import org.junit.Test;

public class MinimumSupplierTest {

    @Test
    public void test1() {
        Set<Supplier> suppliers = new HashSet<>();
        Supplier s1 = new Supplier("S1", Stream.of("P1", "P3").collect(Collectors.toSet()));
        Supplier s2 = new Supplier("S2", Stream.of("P2", "P4").collect(Collectors.toSet()));
        Supplier s3 = new Supplier("S3", Stream.of("P1", "P2").collect(Collectors.toSet()));
        Supplier s4 = new Supplier("S4", Stream.of("P2", "P3").collect(Collectors.toSet()));
        Supplier s5 = new Supplier("S5", Stream.of("P1", "P3").collect(Collectors.toSet()));

        suppliers.add(s1);
        suppliers.add(s2);
        suppliers.add(s3);
        suppliers.add(s4);
        suppliers.add(s5);

        MinimumSupplier minimumSupplier = new MinimumSupplier(suppliers);

        List<String> productsToBuy = Arrays.asList("P1", "P2");
        Set<Supplier> result = minimumSupplier.getMinimumSuppliers(productsToBuy);
        assertEquals(new HashSet<>(Arrays.asList(s3)), result);
    }

    @Test
    public void test2() {
        Set<Supplier> suppliers = new HashSet<>();
        Supplier s1 = new Supplier("S1", Stream.of("P1", "P3", "P2", "P4").collect(Collectors.toSet()));
        Supplier s2 = new Supplier("S2", Stream.of("P2", "P4").collect(Collectors.toSet()));
        Supplier s3 = new Supplier("S3", Stream.of("P1", "P2", "P3").collect(Collectors.toSet()));
        Supplier s4 = new Supplier("S4", Stream.of("P4").collect(Collectors.toSet()));
        Supplier s5 = new Supplier("S5", Stream.of("P1", "P3", "p4", "P5", "P6").collect(Collectors.toSet()));

        suppliers.add(s1);
        suppliers.add(s2);
        suppliers.add(s3);
        suppliers.add(s4);
        suppliers.add(s5);

        MinimumSupplier minimumSupplier = new MinimumSupplier(suppliers);

        List<String> productsToBuy = Arrays.asList("P1", "P2", "P3", "P4");
        Set<Supplier> result = minimumSupplier.getMinimumSuppliers(productsToBuy);
        assertEquals(new HashSet<>(Arrays.asList(s1)), result);
    }

    @Test
    public void test3() {
        Set<Supplier> suppliers = new HashSet<>();
        Supplier s1 = new Supplier("S1", Stream.of("P1", "P3", "P2", "P4").collect(Collectors.toSet()));
        Supplier s2 = new Supplier("S2", Stream.of("P2", "P5").collect(Collectors.toSet()));
        Supplier s3 = new Supplier("S3", Stream.of("P1", "P2", "P4").collect(Collectors.toSet()));
        Supplier s4 = new Supplier("S4", Stream.of("P5").collect(Collectors.toSet()));
        Supplier s5 = new Supplier("S5", Stream.of("P1", "P3", "p4", "P6").collect(Collectors.toSet()));

        suppliers.add(s1);
        suppliers.add(s2);
        suppliers.add(s3);
        suppliers.add(s4);
        suppliers.add(s5);

        MinimumSupplier minimumSupplier = new MinimumSupplier(suppliers);

        List<String> productsToBuy = Arrays.asList("P1", "P2", "P3", "P5");
        Set<Supplier> result = minimumSupplier.getMinimumSuppliers(productsToBuy);
        assertEquals(new HashSet<>(Arrays.asList(s1, s2)), result);
    }

    @Test
    public void test4() {
        Set<Supplier> suppliers = new HashSet<>();
        Supplier s1 = new Supplier("S1", Stream.of("P1").collect(Collectors.toSet()));
        Supplier s2 = new Supplier("S2", Stream.of("P2", "P5").collect(Collectors.toSet()));
        Supplier s3 = new Supplier("S3", Stream.of("P1", "P2", "P4").collect(Collectors.toSet()));
        Supplier s4 = new Supplier("S4", Stream.of("P5", "P3", "P2", "P4").collect(Collectors.toSet()));
        Supplier s5 = new Supplier("S5", Stream.of("P1", "P3", "p4", "P6", "P7").collect(Collectors.toSet()));

        suppliers.add(s1);
        suppliers.add(s2);
        suppliers.add(s3);
        suppliers.add(s4);
        suppliers.add(s5);

        MinimumSupplier minimumSupplier = new MinimumSupplier(suppliers);

        List<String> productsToBuy = Arrays.asList("P1", "P2");
        Set<Supplier> result = minimumSupplier.getMinimumSuppliers(productsToBuy);
        assertEquals(new HashSet<>(Arrays.asList(s3)), result);
    }
}
