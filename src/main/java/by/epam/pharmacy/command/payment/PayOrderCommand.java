package by.epam.pharmacy.command.payment;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.service.PaymentService;
import by.epam.pharmacy.service.impl.OrderServiceImpl;
import by.epam.pharmacy.service.impl.PaymentServiceImpl;

/**
 *
 */
public class PayOrderCommand implements RequestCommand<SessionRequestContent> {
    private PaymentService paymentService = new PaymentServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        try {
            if (paymentService.validateForCreateOrUpdatePayment(content)) {
                paymentService.createOrUpdatePayment(content);
                paymentService.proceedToPayment(content);
                page = PagePath.PAYMENT_PAGE.getPage();
            } else {
                orderService.showOrder(content);
                page = PagePath.EDIT_ORDER_PAGE.getPage();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}

