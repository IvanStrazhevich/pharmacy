package by.epam.pharmacy.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order extends Entity{
    private static final long serialVersionUID = -7808138802635242837L;
    private int orderId;
    private int clientId;
    private boolean payed;
    private BigDecimal medicineSum;
    private ArrayList<Integer> medicineIdList;

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
        return medicineIdList;
    }

    public void setMedicineIdList(ArrayList<Integer> medicineIdList) {
        this.medicineIdList = medicineIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (clientId != order.clientId) return false;
        if (payed != order.payed) return false;
        if (medicineSum != null ? !medicineSum.equals(order.medicineSum) : order.medicineSum != null) return false;
        return medicineIdList != null ? medicineIdList.equals(order.medicineIdList) : order.medicineIdList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + orderId;
        result = 31 * result + clientId;
        result = 31 * result + (payed ? 1 : 0);
        result = 31 * result + (medicineSum != null ? medicineSum.hashCode() : 0);
        result = 31 * result + (medicineIdList != null ? medicineIdList.hashCode() : 0);
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
                '}';
    }
}
