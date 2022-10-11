package arraylistobjectdemo;

/* module java.base */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;
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
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n"); /* Use the ENTER key as a delimiter*/
        
        if (checkFile(args)) {
            fileName = args[1];
        } else {
            fileName = "result.bin";
        }
        
        ArrayList<Car> cars = new ArrayList<>();
        
        acceptInput(cars, scan, fileName); /* run driver code */
    }

    /**
     * This will check args if there is a fileName
     * 
     * @param args The command line arguments from main()
     * @return if there is no file name in args, return false
     * else return true
     */
    public static boolean checkFile(String[] args) {
        /* Checks if args[1] has a filename */
        if (args.length < 1) {
            //System.out.println("Please enter an input file as an argument ");
            return false;
        }
        return true;
    }

    /**
     * This is the driver code that runs the menu system and calls
     * various methods.
     * 
     * @param cars The ArrayList<Car> object that is used throughout the program
     * @param scan The Scanner object to enable user input
     * @param fileName The file name which comes from args[1] or a default file name
     */
    public static void acceptInput(ArrayList<Car> cars, Scanner scan, String fileName) {
        boolean isUserFinished = false;

        String userInput = "";
        
        deserialize(cars, fileName);
        
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
                    searchCar(cars, scan);
                    break;
                case "5":
                    editCar(cars, scan);
                    break;
                case "6":
                    break;
                case "7": /* Load .bin file */
                    deserialize(cars, fileName);
                    break;
                case "8": /* Save .bin file */
                    serialize(cars, fileName);
                    break;
                default:
                    break;
            }
        }
    }

    /** 
     * This is used to print a menu interface
     */
    public static void printMenu() {
        System.out.println("Array List Object Demo");
        System.out.println("======================");
        System.out.println("0 - Exit this program");
        System.out.println("1 - Add new car");
        System.out.println("2 - Display list of cars");
        System.out.println("3 - Sort list of cars");
        System.out.println("4 - Search for a car");
        System.out.println("5 - Edit a car - PARTIALLY IMPLEMENTED");
        System.out.println("6 - Delete a car - UNIMPLEMENTED");
        System.out.println("7 - Load list of cars");
        System.out.println("8 - Save list of cars");
        System.out.println("");
        System.out.print("Enter a menu option here: ");
    }

    /**
     * This is used to add a new car to the passed in ArrayList<Car>
     * @param cars The ArrayList<Car> to add a Car class object to
     * @param scan The Scanner object to enable user input
     * @throws InputMismatchException
     */
    public static void addNewCar(ArrayList<Car> cars, Scanner scan) {
        boolean isListFinished = false;
        try {
            while (!isListFinished) {
                /* Get the make of the car from the user */
                /* scan.nextLine() is used to ignore whitespaces */
                String newMake;
                String newModel;
                String newYear;
                
                int newOdometer = 0;
                
                System.out.print("Enter a car make: ");
                //scan.hasNext();
                newMake = scan.next();

                /* Get the model */
                System.out.print("Enter a car model: ");
                newModel = scan.next();

                /* Get the year */
                System.out.print("Enter a car year: ");
                newYear = scan.next();

                /* Get the odometer */
                System.out.print("Enter a car odometer: ");
                newOdometer = scan.nextInt();

                Car newCar = new Car(newMake, newModel, newYear, newOdometer);

                cars.add(newCar);

                isListFinished = askForAnotherCar(scan, "Do you want to add a new car?");
            }
        } catch (InputMismatchException ex) {
            //System.out.println(ex.toString());
            System.out.println("Car was not added, odometer must be an integer value.");
        }
    }

    /** 
     * This will ask the user for another car
     * @param scan The Scanner object to enable user input
     * @param message The message to output to the user
     * @return !b
     */
    public static boolean askForAnotherCar(Scanner scan, String message) {
        System.out.print(message + " [Y] or [N]: ");

        Pattern p = Pattern.compile("[Y[y]]");
        Matcher m = p.matcher(scan.next());
        boolean b = m.matches();

        return !b;
        /* The inverse of b must be returned to allow another car to be added */
    }

    /**
     * This is used to display a list of Car objects held within an ArrayList<Car>
     * @param cars The ArrayList<Car> to read Car class object from
     */
    public static void displayList(ArrayList<Car> cars) {
        /* This allows displayList() to be called independently with sortList() */
        displayList(cars, false);
    }
    
    /**
     * This is used to display a list of Car objects held within an ArrayList<Car>
     * and can also be used to sort the ArrayList<Car>
     * @param cars The ArrayList<Car> to read Car class object from
     * @param isListToBeSorted true to sort the ArrayList<Car>
     */
    public static void displayList(ArrayList<Car> cars, boolean isListToBeSorted) {
        if (isListToBeSorted) {
            sortList(cars);
        }

        /* Display a list of all cars */
        for (Car c : cars) {
            c.display();
        }
        System.out.println();
    }

    public static void sortList(ArrayList<Car> cars) {
        Collections.sort(cars);
    }

    public static void searchCar(ArrayList<Car> cars, Scanner scan) {
        String searchString;
        
        System.out.println("\nPlease enter a car's make here: ");
        searchString = scan.next();
        
        int indx = cars.indexOf(searchString);
        
        //int indx = Collections.binarySearch(cars, searchString);
        
        if (indx >= 0) {
            System.out.println("Car " + searchString + " found at index " + indx);
        } else {
            System.out.println("No car " + searchString + " found");
        }
        //int result = Collections.binarySearch(cars, indx);
    }
    
    public static void editCar(ArrayList<Car> cars, Scanner scan) {
        System.out.print("Enter the make of a car to edit: ");
        String searchString = scan.next();
               
        String tempMake;
        String tempModel;
        String tempYear;
        int tempOdometer;
        
        for (Car car : cars) {
            tempMake = car.getMake();
            tempModel = car.getModel();
            tempYear = car.getYear();
            tempOdometer = car.getOdometer();
            if (tempMake.equals(searchString)) {
                
                
                System.out.println("Are you sure you want to edit this car?\r\n");
                car.display();
                boolean isCarEditable = askForAnotherCar(scan, "");

                if (isCarEditable) {
                    try {
                        System.out.print("Enter a car make: ");
                        //scan.hasNext();
                        tempMake = scan.next();

//                        /* Get the model */
//                        System.out.print("Enter a car model: ");
//                        tempModel = scan.next();
//
//                        /* Get the year */
//                        System.out.print("Enter a car year: ");
//                        tempYear = scan.next();
//
//                        /* Get the odometer */
//                        System.out.print("Enter a car odometer: ");
//                        tempOdometer = scan.nextInt();
                        
                        /* Only commit the changes if there are no exceptions */
//                        car.setMake(tempMake);
                    } catch (InputMismatchException ex) {
                        
                    }
                    
                    
                }
            }
        }
    }

    public static void deleteCar(ArrayList<Car> cars, String deleteString, Scanner scan) {
        //try {

        System.out.println("\r\nAre you sure you want to delete?");
        cars.remove(cars.indexOf(deleteString));
        //}
        //catch (Exception ex) {

        //}
    }

    public static void serialize(ArrayList<Car> cars, String filename) {
        Vector<Car> resultVector = new Vector<>();

        for (Car c : cars) {
            resultVector.add(c);
        }
        System.out.println("Writing to file...");

        try {
            FileOutputStream fileOut = new FileOutputStream(filename); // binary file "result.bin"
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut); // output stream

            objectOut.writeObject(resultVector); // save the object in the binary file
            fileOut.close(); // close the file
            System.out.println("Completed - result.bin"); // user feedback
        } catch (IOException e) { // catch if IO exceptions happen
            System.out.println(e);
        }
    }

    public static void deserialize(ArrayList<Car> cars, String filename) {
        cars.clear();
        Vector v = new Vector();
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            v = (Vector) objectIn.readObject();

            objectIn.close();
            fileIn.close();
            
            String pluralText = "";
            if (v.size() > 1) {
               pluralText = "s";
            }
            System.out.println(v.size() + " car" + pluralText + " imported");
            
        } catch (IOException err) { // handle IO exception
            System.err.println(err);
        } catch (ClassNotFoundException err) { // handle ClassNotFound exception
            System.err.println(err);
        }

        for (int i = 0; i < v.size(); i++) {
            Car temp = (Car) v.elementAt(i);
            cars.add(temp);
            //temp.display();
        }
    }
}
