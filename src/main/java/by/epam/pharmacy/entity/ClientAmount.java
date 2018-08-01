package by.epam.pharmacy.entity;

import java.math.BigDecimal;

/**
 * 
 */
public class ClientAmount extends Entity{
    private static final long serialVersionUID = 632246434979432068L;
    private int clientId;
    private BigDecimal amountDebit;
    private BigDecimal amountCredit;

    /**
     * 
     */
    public ClientAmount() {
    }

    /**
     * 
     */
    public BigDecimal getAmountDebit() {
        return amountDebit;
    }

    /**
     * 
     * @param amountDebit 
     */
    public void setAmountDebit(BigDecimal amountDebit) {
        this.amountDebit = amountDebit;
    }

    /**
     * 
     */
    public BigDecimal getAmountCredit() {
        return amountCredit;
    }

    /**
     * 
     * @param amountCredit 
     */
    public void setAmountCredit(BigDecimal amountCredit) {
        this.amountCredit = amountCredit;
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
     * @param o 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientAmount that = (ClientAmount) o;

        if (clientId != that.clientId) return false;
        if (amountDebit != null ? !amountDebit.equals(that.amountDebit) : that.amountDebit != null) return false;
        return amountCredit != null ? amountCredit.equals(that.amountCredit) : that.amountCredit == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientId;
        result = 31 * result + (amountDebit != null ? amountDebit.hashCode() : 0);
        result = 31 * result + (amountCredit != null ? amountCredit.hashCode() : 0);
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "ClientAmount{" +
                "clientId=" + clientId +
                ", amountDebit=" + amountDebit +
                ", amountCredit=" + amountCredit +
                '}';
    }
}

