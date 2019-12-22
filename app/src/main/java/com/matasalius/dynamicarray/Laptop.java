package com.matasalius.dynamicarray;

import java.util.Comparator;

public class Laptop extends DynamicArray {
    final static private double minPrice = 200.0;
    final static private double maxPrice = 5000.0;

    private String brand;
    private int coreNumber;
    private double screenSize;
    private int driveSize;
    private double price;

    public Laptop(String brand, int coreNumber, double screenSize, int driveSize, double price) {
        this.brand = brand;
        this.coreNumber = coreNumber;
        this.screenSize = screenSize;
        this.driveSize = driveSize;
        this.price = price;
    }

    public String validate() {
        String error = "";
        if (price < minPrice || price > maxPrice)
        {
            error = "Price is outside the permissible limits ["+minPrice+":"+maxPrice+"]";
        }
        return error;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-4d %5.1f %7d %8.1f %s", brand, coreNumber, screenSize, driveSize, price, validate());
    }

    public String getBrand() {
        return brand;
    }

    public int getCoreNumber() {
        return coreNumber;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public int getDriveSize() {
        return driveSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int compareTo(Laptop otherLaptop) {
        double otherPrice = otherLaptop.getPrice();
        if (price < otherPrice)
            return -1;
        else if (price > otherPrice)
            return 1;
        else
            return 0;
    }

    public final static Comparator<Laptop> byBrand
            = new Comparator<Laptop>() {
        @Override
        public int compare(Laptop l1, Laptop l2) {
            int cmp = l1.getBrand().compareTo(l2.getBrand());
            if (cmp != 0) {
                return cmp;
            }
            return l1.getBrand().compareTo(l2.getBrand());
        }
    };

    public final static Comparator byPrice = new Comparator() {
        @Override
        public int compare(Object obj1, Object obj2) {
            double price1 = ((Laptop) obj1).getPrice();
            double price2 = ((Laptop) obj2).getPrice();
            if (price1 < price2) {
                return -1;
            }
            if (price1 > price2) {
                return 1;
            }
            return 0;
        }
    };

    public final static Comparator byCoreNumber = new Comparator() {
        @Override
        public int compare(Object obj1, Object obj2) {
            Laptop l1 = (Laptop) obj1;
            Laptop l2 = (Laptop) obj2;

            if (l1.getCoreNumber() < l2.getCoreNumber()) {
                return 1;
            }
            if (l1.getCoreNumber() > l2.getCoreNumber()) {
                return -1;
            }
            if (l1.getPrice() < l2.getPrice()) {
                return 1;
            }
            if (l1.getPrice() > l2.getPrice()) {
                return -1;
            }
            return 0;
        }
    };
}
