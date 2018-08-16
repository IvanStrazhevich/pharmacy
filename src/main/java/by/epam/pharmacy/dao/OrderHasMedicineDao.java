package by.epam.pharmacy.dao;

import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;

import java.util.ArrayList;

public interface OrderHasMedicineDao<T> extends AbstractDao<T> {

    /**
     * Delete medicine from Order according params
     * @param orderId id of Order
     * @param medicineId id of Medicine
     * @return true if deleted false if exception thrown
     */
    boolean deleteMedicineFromOrder(Integer orderId, Integer medicineId) throws DaoException;


    /**
     * Find medicine from Order according params
     * @param orderId id of Order
     * @param medicineId id of Medicine
     * @return OrderHasMedicine record
     */
    T findOrderHasMedicineByOrderIdMedicineId(Integer orderId, Integer medicineId) throws DaoException;

    /**
     * Update recipe id in record
     * @param entity
     * @return true if updated false if exception thrown
     */
    boolean updateRecipe(OrderHasMedicine entity) throws DaoException;

    /**
     * @param orderId
     */
    ArrayList<OrderHasMedicine> findAllMedicinesByOrderId(Integer orderId) throws DaoException;

    /**
     * @param orderId
     */
    boolean deleteAllMedicineFromOrder(Integer orderId) throws DaoException;
    /**
     * @param medicineId
     */
    T findOrderHasMedicineByMedicineId(Integer medicineId) throws DaoException;



}

