package Java2_Project;

import java.io.Serializable;

public class FitnessRecord implements Serializable {
    private String product_id;
    private String product_type;
    private String product_name;
    private String brand_name;
    private double price;
    private int product_qty;
    private String customer_id;
    private String product_location;

    // Constructors
    public FitnessRecord() {
        // Default constructor
    }

    public FitnessRecord(String product_id, String product_type,
                         String product_name, String brand_name,
                         double price, int product_qty, String customer_id,
                         String product_location) {
        this.product_id = product_id;
        this.product_type = product_type;
        this.product_name = product_name;
        this.brand_name = brand_name;
        this.price = price;
        this.product_qty = product_qty;
        this.customer_id = customer_id;
        this.product_location = product_location;

    }

    public String getProduct_id() {
        return null;
    }

    public String getProduct_type() {
        return null;
    }
}