package com.mylearning.onlinetests.arcesium;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Design a production ready application football team selection based on input "player applications" and provided selection criteria.
Output should either have both selection groups(Strikers & Defenders) or none, which should have equal number of candidates who meets the criteria.
Select best candidate if there is a clash. Below are class diagrams for selection criteria:
Fitness  Factor
- minHeight = 5.8
- maxBmi = 23

StrikerExperienceFactor
- minGoalsScored = 50

DefenderExperienceFactor
- minGoalsDefended = 30

Sample Input:
Sorted based on name and space separated.
First line: number of rows
Second line: number of columns
Next Lines: name(string) Height(decimal) BMI(decimal) Scores(int) Defends(int)

4
5
Boateng 6.1 22 24 45
Kaka 6 22 1 1
Ronaldo 5.8 21 120 0
Suarez 5.9 20 100 1

Sample Output:
Sorted based on name.

Boateng SELECT DEFENDER
Kaka REJECT NA
Ronaldo SELECT STRIKER
Suarez REJECT NA

As you can see in sample output there are equal number of defenders and strikers are finally selected and rest are rejected even though they could have matched the criteria.
*/
public class FootballApp {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int applicationsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int applicationsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> playerInfos = new ArrayList<>();

        IntStream.range(0, applicationsRows).forEach(i -> {
            try {
                playerInfos.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).collect(Collectors.toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<String>> result = Result.getSelectionStatus(playerInfos);

        List<String> output = result.stream().map(r -> r.stream().collect(joining(" "))).map(r -> r + "\n").collect(Collectors.toList());

        output.forEach(System.out::print);
    }


}

class Player {
    private String name;
    private Float height;
    private Float bmi;
    private Integer scores;
    private Integer defends;
    private PlayerGroup group;

    public PlayerGroup getGroup() {
        return group;
    }

    public void setGroup(PlayerGroup group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getBmi() {
        return bmi;
    }

    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getDefends() {
        return defends;
    }

    public void setDefends(Integer defends) {
        this.defends = defends;
    }
}

class FitnessFactor {
    private double minHeight;
    private double minBmi;

    public FitnessFactor(double minHeight, double minBmi) {
        this.minHeight = minHeight;
        this.minBmi = minBmi;
    }

    boolean isFit(Player p) {
        return p.getHeight() >= this.minHeight && p.getBmi()  <= this.minBmi;
    }
}

class StrikerExperienceFactor {
    private int minGoalsScored;

    public StrikerExperienceFactor(int minGoalsScored) {
        this.minGoalsScored = minGoalsScored;
    }

    boolean isStriker(Player p) {
        return p.getScores() >= this.minGoalsScored;
    }
}

class DefenderExperienceFactor {
    private int minGoalsDefended;

    public DefenderExperienceFactor(int minGoalsDefended) {
        this.minGoalsDefended = minGoalsDefended;
    }

    boolean isDefender(Player p) {
        return p.getDefends() >= this.minGoalsDefended;
    }
}

enum Selection {
    SELECT,
    REJECT
}

enum PlayerGroup {
    STRIKER,
    DEFENDER,
    NA
}

class Result {

    public static List<List<String>> getSelectionStatus(List<List<String>> applications) {
        //Populate players using applications
        List<Player> players = populatePlayers(applications);
        FitnessFactor fitnessFactor = new FitnessFactor(5.8, 23);
        StrikerExperienceFactor strikerExperienceFactor  = new StrikerExperienceFactor(50);
        DefenderExperienceFactor defenderExperienceFactor = new DefenderExperienceFactor(30);

        List<Player> strikerOnly = new ArrayList<>(); //Only striker players
        List<Player> defenderOnly = new ArrayList<>(); //Only defender players
        List<Player> strikerDefenderBoth = new ArrayList<>(); //Striker and defender players both
        List<Player> rejected = new ArrayList<>(); //Rejected players

        for (Player player : players) {
            if (fitnessFactor.isFit(player)) {
                if (strikerExperienceFactor.isStriker(player) && defenderExperienceFactor.isDefender(player)) {
                    strikerDefenderBoth.add(player);
                    player.setGroup(PlayerGroup.NA);
                } else {
                    if (strikerExperienceFactor.isStriker(player) && !defenderExperienceFactor.isDefender(player)) {
                        strikerOnly.add(player);
                        player.setGroup(PlayerGroup.STRIKER);
                    } else if (!strikerExperienceFactor.isStriker(player) && defenderExperienceFactor
                            .isDefender(player)) {
                        defenderOnly.add(player);
                        player.setGroup(PlayerGroup.DEFENDER);
                    } else {
                        player.setGroup(PlayerGroup.NA);
                        rejected.add(player);
                    }
                }
            } else {
                player.setGroup(PlayerGroup.NA);
                rejected.add(player);
            }
        }

        //Check if we got unequal number of strikers and defenders
        int diff = Math.abs(strikerOnly.size() - defenderOnly.size());

        //check if we need to allocate players who are both striker and defender
        int commonPlayers = strikerDefenderBoth.size();
        while((diff == 0 &&  commonPlayers > 1) || (diff > 0 &&  commonPlayers >= 1)) {
            //testUnEqualAndEqualAllocation
            if(diff == 0) { //testEqualAllocation
                //allocate to both striker and defender
                int toAllocate = commonPlayers / 2;
                strikerDefenderBoth.sort(Comparator.comparing(Player::getScores).reversed());
                List<Player> strikersAllocated = strikerDefenderBoth.stream().limit(toAllocate).collect(Collectors.toList());
                strikersAllocated.forEach(p -> {
                    p.setGroup(PlayerGroup.STRIKER);
                    strikerOnly.add(p);
                });

                //remove all strikers allocated from strikerDefenderBoth
                strikerDefenderBoth.removeAll(strikersAllocated);
                commonPlayers = commonPlayers - toAllocate;

                //allocate equal number of defenders
                strikerDefenderBoth.sort(Comparator.comparing(Player::getDefends).reversed());
                List<Player> defendersAllocated = strikerDefenderBoth.stream().limit(toAllocate).collect(Collectors.toList());
                defendersAllocated.forEach(p -> {
                    p.setGroup(PlayerGroup.DEFENDER);
                    defenderOnly.add(p);
                });

                //remove all defenders allocated from strikerDefenderBoth
                strikerDefenderBoth.removeAll(defendersAllocated);
                commonPlayers = commonPlayers - toAllocate;
            } else {
                if(strikerOnly.size() <= defenderOnly.size()) { //testUnEqualAllocateStrikers
                    strikerDefenderBoth.sort(Comparator.comparing(Player::getScores).reversed());
                    List<Player> strikersAllocated = strikerDefenderBoth.stream().limit(diff).collect(Collectors.toList());
                    strikersAllocated.forEach(p -> {
                        p.setGroup(PlayerGroup.STRIKER);
                        strikerOnly.add(p);
                    });
                    strikerDefenderBoth.removeAll(strikersAllocated);
                    commonPlayers = commonPlayers - diff;
                } else { //testUnEqualAllocateDefenders
                    strikerDefenderBoth.sort(Comparator.comparing(Player::getDefends).reversed());
                    List<Player> defendersAllocated = strikerDefenderBoth.stream().limit(diff).collect(Collectors.toList());
                    defendersAllocated.forEach(p -> {
                        p.setGroup(PlayerGroup.DEFENDER);
                        defenderOnly.add(p);
                    });
                    strikerDefenderBoth.removeAll(defendersAllocated);
                    commonPlayers = commonPlayers - diff;
                }
            }
            diff = Math.abs(strikerOnly.size() - defenderOnly.size());
        }

        //Check if we got unequal number of strikers and defenders then reject and make it equal
        if(diff > 0) {
            if(strikerOnly.size() < defenderOnly.size()) { //testUnEqualRejectDefenders
                defenderOnly.sort(Comparator.comparing(Player::getDefends));
                defenderOnly.stream().limit(diff).forEach(p -> {
                    p.setGroup(PlayerGroup.NA);
                });
            } else if(defenderOnly.size() < strikerOnly.size()) { //testUnEqualRejectStrikers
                strikerOnly.sort(Comparator.comparing(Player::getScores));
                strikerOnly.stream().limit(diff).forEach(p -> {
                    p.setGroup(PlayerGroup.NA);
                });
            }
        }
        return generateResult(players);
    }

    private static List<List<String>>  generateResult(List<Player> players) {
        players.sort(Comparator.comparing(Player::getName));
        List<List<String>> result = new ArrayList<>();
        for (Player player : players) {
            List<String> temp = new ArrayList<>();
            temp.add(player.getName());
            if (PlayerGroup.NA.equals(player.getGroup())) {
                temp.add(Selection.REJECT.toString());
                temp.add(player.getGroup().toString());
            } else {
                temp.add(Selection.SELECT.toString());
                temp.add(player.getGroup().toString());
            }
            result.add(temp);
        }
        return result;
    }

    private static List<Player> populatePlayers(List<List<String>> playerInfos) {
        List<Player> players = new ArrayList<>();
        playerInfos.forEach(playerInfo -> {
            Player p = new Player();
            p.setName(playerInfo.get(0));
            p.setHeight(Float.valueOf(playerInfo.get(1)));
            p.setBmi(Float.valueOf(playerInfo.get(2)));
            p.setScores(Integer.valueOf(playerInfo.get(3)));
            p.setDefends(Integer.valueOf(playerInfo.get(4)));
            players.add(p);
        });
        return players;
    }
}
