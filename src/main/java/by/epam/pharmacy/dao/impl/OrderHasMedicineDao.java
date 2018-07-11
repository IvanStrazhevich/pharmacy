package by.epam.pharmacy.dao.impl;

import by.epam.pharmacy.connection.ProxyConnection;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderHasMedicineDao extends AbstractDaoImpl<OrderHasMedicine>{

    private static final String SELECT_ALL_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum from order_has_medicine";
    private static final String SELECT_BY_ORDER_ID_PSTM = "select order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum from order_has_medicine where order_order_id = ?";
    private static final String INSERT_MEDICINE_IN_ORDER_PSTM = "insert into order_has_medicine(order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum) values(?,?,?,?)";
    private static final String DELETE_ALL_FROM_ORDER_PSTM = "delete from order_has_medicine where order_order_id = ?";
    private static final String DELETE_MEDICINE_FROM_ORDER_PSTM = "delete from order_has_medicine where order_id = ? and medicine_mdc_id";
    private static final String UPDATE_PSTM = "update order set ohm_med_quantity=?, ohm_med_sum=? where order_order_id = ? and medicine_mdc_id = ?";
    private static Logger logger = LogManager.getLogger();
    private ProxyConnection proxyConnection;

    public OrderHasMedicineDao() throws DaoException {
        proxyConnection = super.proxyConnection;
    }



    @Override
    public List<OrderHasMedicine> findAll() throws DaoException {
        return null;
    }

    @Override
    public OrderHasMedicine findEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(OrderHasMedicine entity) throws DaoException {
        return false;
    }

    @Override
    public boolean create(OrderHasMedicine entity) throws DaoException {
        return false;
    }

    @Override
    public boolean update(OrderHasMedicine entity) throws DaoException {
        return false;
    }
}
