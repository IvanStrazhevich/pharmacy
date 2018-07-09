package by.epam.pharmacy.entity;

import java.math.BigDecimal;

public class OrderHasMedicine extends Entity{
    private static final long serialVersionUID = -4804443935303178727L;
    private int orderId;
    private int medicineId;
    private int medicineQuantity;
    private BigDecimal medicineSum;
    private int recipeId;

    public OrderHasMedicine() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(int medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public BigDecimal getMedicineSum() {
        return medicineSum;
    }

    public void setMedicineSum(BigDecimal medicineSum) {
        this.medicineSum = medicineSum;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderHasMedicine that = (OrderHasMedicine) o;

        if (orderId != that.orderId) return false;
        if (medicineId != that.medicineId) return false;
        if (medicineQuantity != that.medicineQuantity) return false;
        if (recipeId != that.recipeId) return false;
        return medicineSum != null ? medicineSum.equals(that.medicineSum) : that.medicineSum == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + orderId;
        result = 31 * result + medicineId;
        result = 31 * result + medicineQuantity;
        result = 31 * result + (medicineSum != null ? medicineSum.hashCode() : 0);
        result = 31 * result + recipeId;
        return result;
    }

    @Override
    public String toString() {
        return "OrderHasMedicine{" +
                "orderId=" + orderId +
                ", medicineId=" + medicineId +
                ", medicineQuantity=" + medicineQuantity +
                ", medicineSum=" + medicineSum +
                ", recipeId=" + recipeId +
                '}';
    }
}
