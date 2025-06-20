package com.mylearning.design.patterns.pluralsight.creational.builder;

public class LunchOrder {

    /**
     * Builder for LunchOrder enforces:
     * Immutability of LunchOrder (no setters)
     * Requires presence of either (bread and condiments) or meat
     */
    public static class Builder {
        private String bread;
        private String condiments;
        private String veggies;
        private String meat;

        public Builder(String bread, String condiments) {
            if (bread == null || condiments == null) {
                throw new NullPointerException("bread or condiments cannot be null");
            }
            this.bread = bread;
            this.condiments = condiments;
        }

        public Builder(String meat) {
            if (meat == null) {
                throw new NullPointerException("meat cannot be null");
            }
            this.meat = meat;
        }

        public LunchOrder build() {
            return new LunchOrder(this);
        }

        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder condiments(String condiments) {
            this.condiments = condiments;
            return this;
        }

        public Builder veggies(String veggies) {
            this.veggies = veggies;
            return this;
        }

        public Builder meat(String meat) {
            this.meat = meat;
            return this;
        }
    }

    private String bread;
    private String condiments;
    private String veggies;
    private String meat;

    // Private constructor to enforce usage of the Builder
    private LunchOrder(Builder builder) {
        this.bread = builder.bread;
        this.condiments = builder.condiments;
        this.veggies = builder.veggies;
        this.meat = builder.meat;
    }

    public String getBread() {
        return bread;
    }

    public String getCondiments() {
        return condiments;
    }

    public String getVeggies() {
        return veggies;
    }

    public String getMeat() {
        return meat;
    }

    public static void main(String[] args) {
        LunchOrder lunch = new Builder("Lunch").bread("Bread").build();
    }
}
