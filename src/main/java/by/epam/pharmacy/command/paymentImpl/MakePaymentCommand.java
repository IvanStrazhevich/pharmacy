package by.epam.pharmacy.command.paymentImpl;

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
        try {
            paymentService.makePayment(content);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return PagePath.EDIT_ORDER_PAGE.getPage();
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}