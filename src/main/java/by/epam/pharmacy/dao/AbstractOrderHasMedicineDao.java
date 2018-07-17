package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;

import java.util.List;

public interface AbstractOrderHasMedicineDao<T> extends AbstractDao<T> {
    List<OrderHasMedicine> findAllMedicinesByOrderId(Integer orderId) throws DaoException;

    boolean deleteMedicineFromOrder(Integer orderId, Integer medicineId) throws DaoException;

    boolean deleteAllMedicineFromOrder(Integer id) throws DaoException;
}
