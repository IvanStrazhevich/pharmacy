package by.epam.pharmacy.command.impl.payment;

import by.epam.pharmacy.command.PagePath;
import by.epam.pharmacy.command.RequestCommand;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.PaymentService;
import by.epam.pharmacy.service.impl.PaymentServiceImpl;

public class MakePaymentCommand implements RequestCommand<SessionRequestContent> {
    private PaymentService paymentService = new PaymentServiceImpl();

    /**
     * @param content
     */
    @Override
    public String execute(SessionRequestContent content) throws CommandException {
        String page = null;
        try {
            if (!paymentService.checkIfRepeat(content)) {
                paymentService.makePayment(content);
                page = PagePath.EDIT_ORDER_PAGE.getPage();
            } else {
                page = PagePath.INDEX_PAGE.getPage();
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
