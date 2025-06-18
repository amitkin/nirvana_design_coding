package com.mylearning.lld.trafficsignal;

public class EnhancedTrafficSignalSystemDemo {

    public static void main(String[] args) {
        // Create the enhanced intersection controller
        EnhancedIntersectionController intersectionController = new EnhancedIntersectionController();

        // Add traffic ways (including U-turns)
        intersectionController.addTrafficWay("North-South");
        intersectionController.addTrafficWay("East-West");
        intersectionController.addTrafficWay("Left-Turn");
        intersectionController.addTrafficWay("U-Turn");

        // Simulate traffic density
        intersectionController.setTrafficDensity("North-South", 5); // Low traffic
        intersectionController.setTrafficDensity("East-West", 15); // High traffic
        intersectionController.setTrafficDensity("Left-Turn", 8);  // Medium traffic
        intersectionController.setTrafficDensity("U-Turn", 20);    // High traffic

        // Start automated control
        System.out.println("Automated traffic signal control with dynamic timing:");
        intersectionController.automatedControl();
    }
}

