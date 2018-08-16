package by.epam.pharmacy.dao;

public interface AmountDao<T> extends AbstractDao<T> {

    /**
     * Fill an Amount
     */
    boolean fillAmount();
}
