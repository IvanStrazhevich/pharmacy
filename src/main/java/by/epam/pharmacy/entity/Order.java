package by.epam.pharmacy.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 
 */
public class Order extends Entity {
    private static final long serialVersionUID = -7808138802635242837L;
    private int orderId;
    private int clientId;
    private boolean payed;
    private BigDecimal orderSum;
    private ArrayList<OrderHasMedicine> orderHasMedicines;

    /**
     * 
     */
    public Order() {
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
    public int getClientId() {
        return clientId;
    }

    /**
     * 
     * @param clientId 
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * 
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * 
     * @param payed 
     */
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    /**
     * 
     */
    public BigDecimal getOrderSum() {
        return orderSum;
    }

    /**
     * 
     * @param orderSum
     */
    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    /**
     * 
     */
    public ArrayList<OrderHasMedicine> getOrderHasMedicines() {
        if(orderHasMedicines==null){
            orderHasMedicines=new ArrayList<>();
        }
        return orderHasMedicines;
    }

    /**
     * 
     * @param orderHasMedicines 
     */
    public void setOrderHasMedicines(ArrayList<OrderHasMedicine> orderHasMedicines) {
        this.orderHasMedicines = orderHasMedicines;
    }

    /**
     * 
     * @param o 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (clientId != order.clientId) return false;
        if (payed != order.payed) return false;
        if (orderSum != null ? !orderSum.equals(order.orderSum) : order.orderSum != null) return false;
        return orderHasMedicines != null ? orderHasMedicines.equals(order.orderHasMedicines) : order.orderHasMedicines == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + clientId;
        result = 31 * result + (payed ? 1 : 0);
        result = 31 * result + (orderSum != null ? orderSum.hashCode() : 0);
        result = 31 * result + (orderHasMedicines != null ? orderHasMedicines.hashCode() : 0);
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", payed=" + payed +
                ", orderSum=" + orderSum +
                ", orderHasMedicines=" + orderHasMedicines +
                '}';
    }
}
