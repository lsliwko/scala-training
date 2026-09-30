package test;
import java.util.*;

public class MainAppJavaCarFleet {

//There are n cars traveling to the same destination on a one-lane highway.
//
//You are given two arrays of integers position and speed, both of length n.
//
//position[i] is the position of the ith car (in miles)
//speed[i] is the speed of the ith car (in miles per hour)
//The destination is at position target miles.
//
//A car can not pass another car ahead of it. It can only catch up to another car and then drive at the same speed as the car ahead of it.
//
//A car fleet is a non-empty set of cars driving at the same position and same speed. A single car is also considered a car fleet.
//
//If a car catches up to a car fleet the moment the fleet reaches the destination, then the car is considered to be part of the fleet.
//
//Return the number of different car fleets that will arrive at the destination.
//
//Example 1:
//
//Input: target = 10, position = [1,4], speed = [3,2]
//
//Output: 1
//Explanation: The cars starting at 1 (speed 3) and 4 (speed 2) become a fleet, meeting each other at 10, the destination.
//
//Example 2:
//
//Input: target = 10, position = [4,1,0,7], speed = [2,2,1,1]
//
//Output: 3
//Explanation: The cars starting at 4 and 7 become a fleet at position 10. The cars starting at 1 and 0 never catch up to the car ahead of them. Thus, there are 3 car fleets that will arrive at the destination.

    public static void main(String[] args) {
        MainAppJavaCarFleet mainApp = new MainAppJavaCarFleet();

        {
            var target = 10;
            var position = new int[] {1,4};
            var speed = new int[] {3,2};
            var result = mainApp.carFleet(target, position, speed);
            System.out.println(Arrays.toString(position) + ": " + result);
        }
        {
            var target = 10;
            var position = new int[] {4,1,0,7};
            var speed = new int[] {2,2,1,1};
            var result = mainApp.carFleet(target, position, speed);
            System.out.println(Arrays.toString(position) + ": " + result);
        }
        {
            var target = 10;
            var position = new int[] {};
            var speed = new int[] {};
            var result = mainApp.carFleet(target, position, speed);
            System.out.println(Arrays.toString(position) + ": " + result);
        }
        {
            var target = 10;
            var position = new int[] {1};
            var speed = new int[] {1};
            var result = mainApp.carFleet(target, position, speed);
            System.out.println(Arrays.toString(position) + ": " + result);
        }
        {
            var target = 10;
            var position = new int[] {1,2,3};
            var speed = new int[] {1,1,1};
            var result = mainApp.carFleet(target, position, speed);
            System.out.println(Arrays.toString(position) + ": " + result);
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        
        Car[] cars = new Car[position.length];
        for (int i = 0; i < position.length; i++) {
            cars[i] = new Car(position[i], speed[i]);
        }
        Arrays.sort(cars, Comparator.comparingInt(o -> o.position));
        
        var stack = new ArrayDeque<Car>();
        for (var car : cars) {
            stack.push(car);
        }

        int groups = 0;
        List<Car> carsInGroup = new ArrayList<>();
        while (!stack.isEmpty()) {
            carsInGroup.clear();
            
            //take first from group
            var leadCar = stack.pop();
            carsInGroup.add(leadCar);
            
            float totalTimeToArriveLeadCar = (target - leadCar.position()) / (float)leadCar.speed();
            while (!stack.isEmpty()) {
                var nextCar = stack.peek();
                var positionWhenLeadCarArrives = nextCar.position() + totalTimeToArriveLeadCar * nextCar.speed();
                //arrives together with lead
                if (positionWhenLeadCarArrives >= target) {
                    carsInGroup.add(nextCar);
                    stack.pop();
                } else {
                    break;
                }
            }
            
            System.out.println("GROUP: " + carsInGroup);
            groups++;
        }
        
        return groups;
    }

    record Car(
        int position,
        int speed
    ) {}
    
}
