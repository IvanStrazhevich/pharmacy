package by.epam.pharmacy.entity;

import java.math.BigDecimal;

/**
 * 
 */
public class PharmacyAccount  extends Entity{
    private static final long serialVersionUID = 8948130327928735189L;
    private BigDecimal accountDebit;
    private BigDecimal accountCredit;
    private int clientId;


    /**
     * 
     */
    public PharmacyAccount() {
    }

    /**
     * 
     */
    public BigDecimal getAccountDebit() {
        return accountDebit;
    }

    /**
     * 
     * @param accountDebit 
     */
    public void setAccountDebit(BigDecimal accountDebit) {
        this.accountDebit = accountDebit;
    }

    /**
     * 
     */
    public BigDecimal getAccountCredit() {
        return accountCredit;
    }

    /**
     * 
     * @param accountCredit 
     */
    public void setAccountCredit(BigDecimal accountCredit) {
        this.accountCredit = accountCredit;
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

        PharmacyAccount that = (PharmacyAccount) o;

        if (clientId != that.clientId) return false;
        if (accountDebit != null ? !accountDebit.equals(that.accountDebit) : that.accountDebit != null) return false;
        return accountCredit != null ? accountCredit.equals(that.accountCredit) : that.accountCredit == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accountDebit != null ? accountDebit.hashCode() : 0);
        result = 31 * result + (accountCredit != null ? accountCredit.hashCode() : 0);
        result = 31 * result + clientId;
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "PharmacyAccount{" +
                "accountDebit=" + accountDebit +
                ", accountCredit=" + accountCredit +
                ", clientId=" + clientId +
                '}';
    }
}

