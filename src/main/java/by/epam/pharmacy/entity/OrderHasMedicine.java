package by.epam.pharmacy.entity;

import java.math.BigDecimal;

/**
 * 
 */
public class OrderHasMedicine extends Entity{
    private static final long serialVersionUID = -4804443935303178727L;
    private int orderId;
    private int medicineId;
    private int medicineQuantity;
    private BigDecimal medicineSum;
    private int recipeId;
    private Medicine medicine;
    private Order order;
    private Recipe recipe;

    /**
     * 
     */
    public OrderHasMedicine() {
    }

    /**
     * 
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * 
     * @param orderId 
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * 
     */
    public int getMedicineId() {
        return medicineId;
    }

    /**
     * 
     * @param medicineId 
     */
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    /**
     * 
     */
    public int getMedicineQuantity() {
        return medicineQuantity;
    }

    /**
     * 
     * @param medicineQuantity 
     */
    public void setMedicineQuantity(int medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    /**
     * 
     */
    public BigDecimal getMedicineSum() {
        return medicineSum;
    }

    /**
     * 
     * @param medicineSum 
     */
    public void setMedicineSum(BigDecimal medicineSum) {
        this.medicineSum = medicineSum;
    }

    /**
     * 
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * 
     * @param recipeId 
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * 
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * 
     * @param medicine 
     */
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    /**
     * 
     */
    public Order getOrder() {
        return order;
    }

    /**
     * 
     * @param order 
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * 
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * 
     * @param recipe 
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * 
     * @param o 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderHasMedicine that = (OrderHasMedicine) o;

        if (orderId != that.orderId) return false;
        if (medicineId != that.medicineId) return false;
        if (medicineQuantity != that.medicineQuantity) return false;
        if (recipeId != that.recipeId) return false;
        if (medicineSum != null ? !medicineSum.equals(that.medicineSum) : that.medicineSum != null) return false;
        if (medicine != null ? !medicine.equals(that.medicine) : that.medicine != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        return recipe != null ? recipe.equals(that.recipe) : that.recipe == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + medicineId;
        result = 31 * result + medicineQuantity;
        result = 31 * result + (medicineSum != null ? medicineSum.hashCode() : 0);
        result = 31 * result + recipeId;
        result = 31 * result + (medicine != null ? medicine.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "OrderHasMedicine{" +
                "orderId=" + orderId +
                ", medicineId=" + medicineId +
                ", medicineQuantity=" + medicineQuantity +
                ", medicineSum=" + medicineSum +
                ", recipeId=" + recipeId +
                ", medicine=" + medicine +
                ", order=" + order +
                ", recipe=" + recipe +
                '}';
    }
}

