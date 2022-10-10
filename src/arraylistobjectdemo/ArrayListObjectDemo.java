package arraylistobjectdemo;

/* module java.base */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import objects.Car;

/**
 * AT1 Activity 6 OOP 2 - Array List Object Demo
 *
 * @version 1.00 27 Sep 2022
 * @author Joshua Farrell
 */
public class ArrayListObjectDemo {

    public static void main(String[] args) {
        //ArrayList<Car> cars = new ArrayList<>();
        String fileName;
        
        if (checkFile(args)) {
            fileName = args[1];
        } else {
            fileName = "cars.dat";
        }
        
        ArrayList<Car> cars = loadFile(fileName);
        
        acceptInput(cars);
    }
    
    public static boolean checkFile(String[] args) {
        /* Checks if args[1] has a filename */
        if (args.length < 1) {
            //System.out.println("Please enter an input file as an argument ");
            return false;
        }
        return true;
    }
    
    public static ArrayList<Car> loadFile(String fileName) {
        ArrayList<Car> loadCars = new ArrayList<>();
        
        deserialize(loadCars, fileName);
        
        
        
        return loadCars;
    }
    
    public static void acceptInput(ArrayList<Car> cars) {
        boolean isUserFinished = false;
        
        Scanner scan = new Scanner(System.in);
        String userInput = "";
        
        while (!isUserFinished) {
            printMenu();
            try {
                userInput = scan.next();
                System.out.println("");
            } catch (InputMismatchException ex) {
                
            }
            
            switch (userInput) {
                case "0": /* Exit program */
                    isUserFinished = true;
                    break;
                case "1":
                    addNewCar(cars, scan);
                    break;
                case "2":
                    displayList(cars);
                    break;
                case "3":
                    sortList(cars);
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    break;
            }
        }
    }
    
    public static void printMenu() {
        System.out.println("Array List Object Demo");
        System.out.println("======================");
        System.out.println("0 - Exit this program");
        System.out.println("1 - Add new car");
        System.out.println("2 - Display list of cars");
        System.out.println("3 - Sort list of cars");
        System.out.println("4 - Search for a car - UNIMPLEMENTED");
        System.out.println("5 - Delete a car - UNIMPLEMENTED");
        System.out.println("6 - Load list of cars - UNIMPLEMENTED");
        System.out.println("7 - Save list of cars - UNIMPLEMENTED");
        System.out.println("");
        System.out.print("Enter a menu option here: ");
    }
    
    public static void addNewCar(ArrayList<Car> cars, Scanner scan) {
        boolean isListFinished = false;
        try {
            while (!isListFinished) {
                /* Get the make of the car from the user */
                /* scan.nextLine() is used to ignore whitespaces */
                System.out.print("Enter a car make: ");
                scan.next();
                String newMake = scan.nextLine();

                /* Get the model */
                System.out.print("Enter a car model: ");
                String newModel = scan.nextLine();

                /* Get the year */
                System.out.print("Enter a car year: ");
                String newYear = scan.nextLine();

                /* Get the odometer */
                System.out.print("Enter a car odometer: ");
                int newOdometer = scan.nextInt();

                Car newCar = new Car(newMake, newModel, newYear, newOdometer);

                cars.add(newCar);

                isListFinished = askForAnotherCar(scan);
            }
        } catch (InputMismatchException ex) {
            //System.out.println(ex.toString());
            System.out.println("Car was not added, odometer must be an integer value.");
        }
    }
    
    public static boolean askForAnotherCar(Scanner scan) {
        System.out.print("Do you want to add a new car? [Y] or [N]: ");
        
        Pattern p = Pattern.compile("[Y[y]]");
        Matcher m = p.matcher(scan.next());
        boolean b = m.matches();
        
        return !b;  /* The inverse of b must be returned to allow another car to be added */
    }
    
    public static void displayList(ArrayList<Car> cars) {
        /* This allows displayList() to be called independently with sortList() */
        displayList(cars, false);
    }
    
    public static void displayList(ArrayList<Car> cars, boolean isListToBeSorted) {
        if (isListToBeSorted) {
            sortList(cars);
        }
        
        /* Display a list of all cars */
        for (Car c : cars) {
            c.display();
        }
    }
    
    public static void sortList(ArrayList<Car> cars) {
        Collections.sort(cars);
    }
    
    public static void searchCar(ArrayList<Car> cars, String searchString) {
        int indx = cars.indexOf(searchString);
        //int result = Collections.binarySearch(cars, indx);
    }
    
    public static void deleteCar(ArrayList<Car> cars, String deleteString, Scanner scan) {
        //try {
        
        System.out.println("\r\nAre you sure you want to delete?");
        cars.remove(cars.indexOf(deleteString));
        //}
        //catch (Exception ex) {
            
        //}
    }
    
    public static void serialize(String filename) {
        
    }
    
    public static void deserialize(ArrayList<Car> cars, String filename) {
        Car object = null;
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            
            object = (Car)in.readObject();
            
            in.close();
            file.close();            
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            
        }
    }
}
