package com.mylearning.onlinetests.arcesium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class FootballAppTest {

    /*
    Fitness  Factor
    - minHeight = 5.8
    - maxBmi = 23

    StrikerExperienceFactor
    - minGoalsScored = 50

    DefenderExperienceFactor
    - minGoalsDefended = 30

    Input : name(string) Height(decimal) BMI(decimal) Scores(int) Defends(int)
    */

    @Test
    public void testNoFit() {
        List<List<String>> playerInfos = new ArrayList<>();
        playerInfos.add(Arrays.asList("Boateng", "6.1", "22", "24", "45"));
        playerInfos.add(Arrays.asList("Kaka", "6", "22", "1", "1"));
        playerInfos.add(Arrays.asList("Ronaldo", "5.8", "24", "120", "0"));
        playerInfos.add(Arrays.asList("Suarez", "5.9", "20", "100", "1"));

        List<List<String>> result = Result.getSelectionStatus(playerInfos);
        List<String> output = result.stream().map(r -> String.join(" ", r)).collect(Collectors.toList());

        Assert.assertEquals(4, output.size());
        Assert.assertEquals("Boateng SELECT DEFENDER", output.get(0));
        Assert.assertEquals("Kaka REJECT NA", output.get(1));
        Assert.assertEquals("Ronaldo REJECT NA", output.get(2));
        Assert.assertEquals("Suarez SELECT STRIKER", output.get(3));
    }

    @Test
    public void testUnEqualRejectStrikers() {
        //Reject strikers to make it equal
        List<List<String>> playerInfos = new ArrayList<>();
        playerInfos.add(Arrays.asList("Boateng", "6.1", "22", "24", "45"));
        playerInfos.add(Arrays.asList("Kaka", "6", "22", "1", "1"));
        playerInfos.add(Arrays.asList("Ronaldo", "5.8", "21", "120", "0"));
        playerInfos.add(Arrays.asList("Suarez", "5.9", "20", "100", "1"));

        List<List<String>> result = Result.getSelectionStatus(playerInfos);
        List<String> output = result.stream().map(r -> String.join(" ", r)).collect(Collectors.toList());

        Assert.assertEquals(4, output.size());
        Assert.assertEquals("Boateng SELECT DEFENDER", output.get(0));
        Assert.assertEquals("Kaka REJECT NA", output.get(1));
        Assert.assertEquals("Ronaldo SELECT STRIKER", output.get(2));
        Assert.assertEquals("Suarez REJECT NA", output.get(3));
    }

    @Test
    public void testUnEqualRejectDefenders() {
        //Reject defenders to make it equal
        List<List<String>> applications = new ArrayList<>();
        applications.add(Arrays.asList("Boateng", "6.1", "22", "24", "45"));
        applications.add(Arrays.asList("Kaka", "6", "22", "1", "1"));
        applications.add(Arrays.asList("Ronaldo", "5.8", "21", "25", "32"));
        applications.add(Arrays.asList("Suarez", "5.9", "20", "100", "1"));

        List<List<String>> result = Result.getSelectionStatus(applications);
        List<String> output = result.stream().map(r -> String.join(" ", r)).collect(Collectors.toList());

        Assert.assertEquals(4, output.size());
        Assert.assertEquals("Boateng SELECT DEFENDER", output.get(0));
        Assert.assertEquals("Kaka REJECT NA", output.get(1));
        Assert.assertEquals("Ronaldo REJECT NA", output.get(2));
        Assert.assertEquals("Suarez SELECT STRIKER", output.get(3));
    }

    @Test
    public void testEqualAllocation() {
        List<List<String>> playerInfos = new ArrayList<>();
        playerInfos.add(Arrays.asList("Boateng", "6.1", "22", "24", "45"));
        playerInfos.add(Arrays.asList("Kaka", "6", "22", "1", "1"));
        playerInfos.add(Arrays.asList("Ronaldo", "5.8", "21", "120", "0"));
        playerInfos.add(Arrays.asList("Suarez", "5.9", "20", "100", "1"));
        playerInfos.add(Arrays.asList("Harry", "5.9", "20", "25", "40"));
        playerInfos.add(Arrays.asList("Jack", "5.9", "20", "100", "42"));
        playerInfos.add(Arrays.asList("Nicole", "5.9", "20", "101", "45"));

        List<List<String>> result = Result.getSelectionStatus(playerInfos);
        List<String> output = result.stream().map(r -> String.join(" ", r)).collect(Collectors.toList());

        Assert.assertEquals(7, output.size());
        Assert.assertEquals("Boateng SELECT DEFENDER", output.get(0));
        Assert.assertEquals("Harry SELECT DEFENDER", output.get(1));
        Assert.assertEquals("Jack SELECT DEFENDER", output.get(2));
        Assert.assertEquals("Kaka REJECT NA", output.get(3));
        Assert.assertEquals("Nicole SELECT STRIKER", output.get(4));
        Assert.assertEquals("Ronaldo SELECT STRIKER", output.get(5));
        Assert.assertEquals("Suarez SELECT STRIKER", output.get(6));
    }

    @Test
    public void testUnEqualAndEqualAllocation() {
        List<List<String>> playerInfos = new ArrayList<>();
        playerInfos.add(Arrays.asList("Boateng", "6.1", "22", "24", "45"));
        playerInfos.add(Arrays.asList("Kaka", "6", "22", "1", "1"));
        playerInfos.add(Arrays.asList("Ronaldo", "5.8", "21", "120", "0"));
        playerInfos.add(Arrays.asList("Suarez", "5.9", "20", "100", "1"));
        playerInfos.add(Arrays.asList("Harry", "5.9", "20", "100", "40"));
        playerInfos.add(Arrays.asList("Jerry", "6", "20", "101", "41"));
        playerInfos.add(Arrays.asList("Tom", "6.1", "20", "102", "42"));
        playerInfos.add(Arrays.asList("Simon", "5.9", "20", "103", "43"));

        List<List<String>> result = Result.getSelectionStatus(playerInfos);
        List<String> output = result.stream().map(r -> String.join(" ", r)).collect(Collectors.toList());

        Assert.assertEquals(8, output.size());
        Assert.assertEquals("Boateng SELECT DEFENDER", output.get(0));
        Assert.assertEquals("Harry REJECT NA", output.get(1));
        Assert.assertEquals("Jerry SELECT DEFENDER", output.get(2));
        Assert.assertEquals("Kaka REJECT NA", output.get(3));
        Assert.assertEquals("Ronaldo SELECT STRIKER", output.get(4));
        Assert.assertEquals("Simon SELECT DEFENDER", output.get(5));
        Assert.assertEquals("Suarez SELECT STRIKER", output.get(6));
        Assert.assertEquals("Tom SELECT STRIKER", output.get(7));
    }

    @Test
    public void testUnEqualAllocateDefenders() {
        //allocate defenders
        List<List<String>> playerInfos = new ArrayList<>();
        playerInfos.add(Arrays.asList("Boateng", "6.1", "22", "24", "45"));
        playerInfos.add(Arrays.asList("Kaka", "6", "22", "1", "1"));
        playerInfos.add(Arrays.asList("Ronaldo", "5.8", "21", "120", "0"));
        playerInfos.add(Arrays.asList("Suarez", "5.9", "20", "100", "1"));
        playerInfos.add(Arrays.asList("Harry", "5.9", "20", "100", "45"));
        playerInfos.add(Arrays.asList("Jerry", "6", "20", "101", "41"));
        playerInfos.add(Arrays.asList("Tom", "6.1", "20", "102", "42"));
        playerInfos.add(Arrays.asList("Simon", "5.9", "20", "103", "43"));

        List<List<String>> result = Result.getSelectionStatus(playerInfos);
        List<String> output = result.stream().map(r -> String.join(" ", r)).collect(Collectors.toList());

        Assert.assertEquals(8, output.size());
        Assert.assertEquals("Boateng SELECT DEFENDER", output.get(0));
        Assert.assertEquals("Harry SELECT DEFENDER", output.get(1));
        Assert.assertEquals("Jerry REJECT NA", output.get(2));
        Assert.assertEquals("Kaka REJECT NA", output.get(3));
        Assert.assertEquals("Ronaldo SELECT STRIKER", output.get(4));
        Assert.assertEquals("Simon SELECT STRIKER", output.get(5));
        Assert.assertEquals("Suarez SELECT STRIKER", output.get(6));
        Assert.assertEquals("Tom SELECT DEFENDER", output.get(7));
    }

    @Test
    public void testUnEqualAllocateStrikers() {
        //allocate strikers
        List<List<String>> playerInfos = new ArrayList<>();
        playerInfos.add(Arrays.asList("Boateng", "6.1", "22", "24", "45"));
        playerInfos.add(Arrays.asList("Kaka", "6", "22", "1", "1"));
        playerInfos.add(Arrays.asList("Ronaldo", "5.8", "21", "120", "0"));
        playerInfos.add(Arrays.asList("Suarez", "5.9", "20", "45", "32"));
        playerInfos.add(Arrays.asList("Harry", "5.9", "20", "100", "45"));
        playerInfos.add(Arrays.asList("Jerry", "6", "20", "101", "41"));
        playerInfos.add(Arrays.asList("Tom", "6.1", "20", "102", "42"));
        playerInfos.add(Arrays.asList("Simon", "5.9", "20", "103", "43"));

        List<List<String>> result = Result.getSelectionStatus(playerInfos);
        List<String> output = result.stream().map(r -> String.join(" ", r)).collect(Collectors.toList());

        Assert.assertEquals(8, output.size());
        Assert.assertEquals("Boateng SELECT DEFENDER", output.get(0));
        Assert.assertEquals("Harry SELECT DEFENDER", output.get(1));
        Assert.assertEquals("Jerry REJECT NA", output.get(2));
        Assert.assertEquals("Kaka REJECT NA", output.get(3));
        Assert.assertEquals("Ronaldo SELECT STRIKER", output.get(4));
        Assert.assertEquals("Simon SELECT STRIKER", output.get(5));
        Assert.assertEquals("Suarez SELECT DEFENDER", output.get(6));
        Assert.assertEquals("Tom SELECT STRIKER", output.get(7));
    }

}