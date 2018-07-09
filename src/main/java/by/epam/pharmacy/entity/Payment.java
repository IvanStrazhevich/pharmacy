package by.epam.pharmacy.entity;

import java.math.BigDecimal;

public class Payment extends Entity{

    private static final long serialVersionUID = 989913517597836500L;
    private int paymentId;
    private int orderId;
    private BigDecimal orderSum;
    private boolean paymentConfirmed;

    public Payment() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    public boolean isPaymentConfirmed() {
        return paymentConfirmed;
    }

    public void setPaymentConfirmed(boolean paymentConfirmed) {
        this.paymentConfirmed = paymentConfirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Payment payment = (Payment) o;

        if (paymentId != payment.paymentId) return false;
        if (orderId != payment.orderId) return false;
        if (paymentConfirmed != payment.paymentConfirmed) return false;
        return orderSum != null ? orderSum.equals(payment.orderSum) : payment.orderSum == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + paymentId;
        result = 31 * result + orderId;
        result = 31 * result + (orderSum != null ? orderSum.hashCode() : 0);
        result = 31 * result + (paymentConfirmed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", orderSum=" + orderSum +
                ", paymentConfirmed=" + paymentConfirmed +
                '}';
    }
}
