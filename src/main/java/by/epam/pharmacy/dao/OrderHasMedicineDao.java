package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface OrderHasMedicineDao<T> extends AbstractDao<T> {
    /**
     * 
     * @param orderId 
     */
    ArrayList<OrderHasMedicine> findAllMedicinesByOrderId(Integer orderId) throws DaoException;

    /**
     * 
     * @param medicineId 
     */
    T findOrderHasMedicineByMedicineId(Integer medicineId) throws DaoException;

    /**
     * 
     * @param orderId 
     * @param medicineId 
     */
    boolean deleteMedicineFromOrder(Integer orderId, Integer medicineId) throws DaoException;

    /**
     * 
     * @param orderId 
     */
    boolean deleteAllMedicineFromOrder(Integer orderId) throws DaoException;

    /**
     * 
     * @param orderId 
     * @param medicineId 
     */
    T findOrderHasMedicineByOrderIdMedicineId(Integer orderId, Integer medicineId) throws DaoException;

    /**
     * 
     * @param entity 
     */
    boolean updateRecipe(OrderHasMedicine entity) throws DaoException;
}

