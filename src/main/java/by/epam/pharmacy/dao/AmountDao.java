package by.epam.pharmacy.dao;

public interface AmountDao<T> extends AbstractDao<T> {

    boolean fillAccount();
}
