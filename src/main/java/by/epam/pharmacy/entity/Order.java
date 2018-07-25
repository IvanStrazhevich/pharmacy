package by.epam.pharmacy.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order extends Entity {
    private static final long serialVersionUID = -7808138802635242837L;
    private int orderId;
    private int clientId;
    private boolean payed;
    private BigDecimal medicineSum;
    private ArrayList<Integer> medicineIdList;
    private ArrayList<Medicine> medicines;
    private ArrayList<OrderHasMedicine> orderHasMedicines;

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public BigDecimal getMedicineSum() {
        return medicineSum;
    }

    public void setMedicineSum(BigDecimal medicineSum) {
        this.medicineSum = medicineSum;
    }

    public ArrayList<Integer> getMedicineIdList() {
        if (this.medicineIdList == null) {
            this.medicineIdList = new ArrayList<>();
        }
        return medicineIdList;
    }

    public void setMedicineIdList(ArrayList<Integer> medicineIdList) {
        this.medicineIdList = medicineIdList;
    }

    public ArrayList<Medicine> getMedicines() {
        if (this.medicines == null) {
            this.medicines = new ArrayList<>();
        }
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    public ArrayList<OrderHasMedicine> getOrderHasMedicines() {
        if (this.orderHasMedicines == null) {
            this.orderHasMedicines = new ArrayList<>();
        }
        return orderHasMedicines;
    }

    public void setOrderHasMedicines(ArrayList<OrderHasMedicine> orderHasMedicines) {
        this.orderHasMedicines = orderHasMedicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (clientId != order.clientId) return false;
        if (payed != order.payed) return false;
        if (medicineSum != null ? !medicineSum.equals(order.medicineSum) : order.medicineSum != null) return false;
        if (medicineIdList != null ? !medicineIdList.equals(order.medicineIdList) : order.medicineIdList != null)
            return false;
        if (medicines != null ? !medicines.equals(order.medicines) : order.medicines != null) return false;
        return orderHasMedicines != null ? orderHasMedicines.equals(order.orderHasMedicines) : order.orderHasMedicines == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + clientId;
        result = 31 * result + (payed ? 1 : 0);
        result = 31 * result + (medicineSum != null ? medicineSum.hashCode() : 0);
        result = 31 * result + (medicineIdList != null ? medicineIdList.hashCode() : 0);
        result = 31 * result + (medicines != null ? medicines.hashCode() : 0);
        result = 31 * result + (orderHasMedicines != null ? orderHasMedicines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", payed=" + payed +
                ", medicineSum=" + medicineSum +
                ", medicineIdList=" + medicineIdList +
                ", medicines=" + medicines +
                ", orderHasMedicines=" + orderHasMedicines +
                '}';
    }
}