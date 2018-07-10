package by.epam.pharmacy.entity;

import java.math.BigDecimal;
import java.util.HashMap;

public class PharmacyAccount  extends Entity{
    private static final long serialVersionUID = 8948130327928735189L;
    private BigDecimal accountDebit;
    private BigDecimal accountCredit;
    private HashMap<Integer,BigDecimal> loans;

    public PharmacyAccount() {
    }

    public BigDecimal getAccountDebit() {
        return accountDebit;
    }

    public void setAccountDebit(BigDecimal accountDebit) {
        this.accountDebit = accountDebit;
    }

    public BigDecimal getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(BigDecimal accountCredit) {
        this.accountCredit = accountCredit;
    }

    public HashMap<Integer, BigDecimal> getLoans() {
        return loans;
    }

    public void setLoans(HashMap<Integer, BigDecimal> loans) {
        this.loans = loans;
    }

}
