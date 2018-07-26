package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface AbstractOrderHasMedicineDao<T> extends AbstractDao<T> {
    ArrayList<OrderHasMedicine> findAllMedicinesByOrderId(Integer orderId) throws DaoException;

    T findOrderHasMedicineByMedicineId(Integer medicineId) throws DaoException;

    boolean deleteMedicineFromOrder(Integer orderId, Integer medicineId) throws DaoException;

    boolean deleteAllMedicineFromOrder(Integer id) throws DaoException;

    T findOrderHasMedicineByOrderIdMedicineId(Integer orderId, Integer medicineId) throws DaoException;

    boolean updateRecipe(OrderHasMedicine entity) throws DaoException;
}
