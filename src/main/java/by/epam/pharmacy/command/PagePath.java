package by.epam.pharmacy.command;

/**
 * Page path hrefs
 */
public enum PagePath {
    WELCOME_PAGE("/jsp/WelcomePage.jsp"),
    LOGIN_PAGE("/jsp/LoginPage.jsp"),
    REGISTER_PAGE("/jsp/RegisterPage.jsp"),
    EDIT_USER_DATA_PAGE("/jsp/EditUserDataPage.jsp"),
    CLIENT_DETAIL_PAGE("/jsp/ClientDetailPage.jsp"),
    MEDICINE_LIST_PAGE("/jsp/MedicineListPage.jsp"),
    EDIT_MEDICINE("/jsp/pharmacist/EditMedicinePage.jsp"),
    USER_LIST_PAGE("/jsp/pharmacist/UserListPage.jsp"),
    EDIT_USER_ACCESS_LEVEL_PAGE("/jsp/pharmacist/EditUserAccessLevelPage.jsp"),
    EDIT_ORDER_PAGE("/jsp/EditOrderPage.jsp"),
    FIND_MEDICINE_PAGE("/jsp/FindMedicinePage.jsp"),
    RECIPE_LIST_PAGE("/jsp/doctor/RecipeListPage.jsp"),
    RECIPE_APPROVAL_PAGE("/jsp/doctor/RecipeApprovalPage.jsp"),
    ORDER_PAGE("/jsp/OrderPage.jsp"),
    PAYMENT_PAGE("/jsp/PaymentPage.jsp"),
    HEADER_PAGE("/HeaderPage.jsp"),
    FOOTER_PAGE("/FooterPage.jsp"),
    INDEX_PAGE("/pharmacy/index.jsp"),
    MISSED_FILE_PAGE("/MissedFilePage.jsp"),
    ERROR_PAGE("/ErrorPage.jsp");


    private String page;

    /**
     * @param page
     */
    PagePath(String page) {
        this.page = page;
    }

    /**
     *
     */
    public String getPage() {
        return page;
    }

}

