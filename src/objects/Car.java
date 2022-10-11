package objects;

/* module java.base */
import java.io.Serializable;

/**
 *
 * @author Joshua Farrell
 */
public class Car implements Comparable<Car>, Serializable {

    private String make;
    private String model;
    private String year;
    private int odometer;
    
    /**
     * DEPRECATED - please use Car(String make, String model, String year, int odometer)
     * @deprecated
     * @see Car(String make, String model, String year, int odometer)
     */
    @Deprecated
    public Car() {
        //System.out.println("You must enter a car's make, model, year and odometer");
    }

    /**
     * This is used to instantiate Car objects
     * 
     * @param make The Make of the car
     * @param model The Model of the car
     * @param year The Year of the car
     * @param odometer The Odometer of the car
     */
    public Car(String make, String model, String year, int odometer) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
    }
    
    /**
     *
     */
    public void display() {
        display(this);
    }
    
    /**
     *
     * @param car
     */
    public static void display(Car car) {
        String tempString = (" " + car.make + " " + car.model);
        
        int ptr = 20 - tempString.length();
        
        /* If the length is more than 20, pop out the car's make and model */
        if (ptr < 0) {
            System.out.println(tempString);
            printSpace(20);
        } else {
            System.out.print(tempString);
            printSpace(ptr);
        }
        
        System.out.println("Year: " + car.year);
        
        printSpace(12);
        System.out.println("km travelled: " + car.odometer);
        System.out.println(" -------------------------------");
    }
    
    /**
     *
     * @param p
     */
    public static void printSpace(int p) {
        for (int i = 0; i < p; i++) {
            System.out.print(" ");
        }
    }
    
//    public void setMake(String make) {
//        this.make = make;
//    }
    
    /**
     *
     * @return
     */
    public String getMake() {
        return make;
    }
    
    /**
     *
     * @return
     */
    public String getModel() {
        return model;
    }
    
    /**
     *
     * @return
     */
    public String getYear() {
        return year;
    }
    
    /**
     *
     * @return
     */
    public int getOdometer() {
        return odometer;
    }
    
    /**
     *
     * @param car
     * @return
     */
    @Override
    public int compareTo(Car car) {
        if (this.make.compareTo(car.make) != 0) {
            //System.out.println("### this.make.compareTo(car.make) = " + this.make.compareTo(car.make));
            return this.make.compareTo(car.make);
        } else {
            return -1;
        }
    }
}
