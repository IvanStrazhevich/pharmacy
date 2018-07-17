package by.epam.pharmacy.service;

public enum PagesEnum {
    ERROR_PAGE("/jsp/ErrorPage.jsp"),
    LOGIN_PAGE("/jsp/LoginPage.jsp"),
    REGISTER_PAGE("/jsp/RegisterPage.jsp"),
    EDIT_USER_DATA_PAGE("/jsp/EditUserDataPage.jsp"),
    UPLOAD_RESULT_PAGE("/jsp/UploadResultPage.jsp"),
    WELCOME_PAGE("/jsp/WelcomePage.jsp"),
    MISSED_FILE_PAGE("/jsp/MissedFilePage.jsp"),
    RECIPE_APPROVAL_PAGE("/jsp/doctor/RecipeApprovalPage.jsp"),
    RECIPE_LIST_PAGE("/jsp/doctor/RecipeListPage.jsp"),
    EDIT_MEDICINE("/jsp/pharmacist/EditMedicinePage.jsp"),
    EDIT_USER_ACCESS_LEVEL_PAGE("/jsp/pharmacist/EditUserAccessLevelPage.jsp"),
    USER_LIST_PAGE("/jsp/pharmacist/UserListPage.jsp"),
    EDIT_ORDER_PAGE("/jsp/EditOrderPage.jsp"),
    FIND_MEDICINE_PAGE("/jsp/FindMedicinePage.jsp"),
    ORDER_PAGE("/jsp/OrderPage.jsp"),
    HEADER_PAGE("/HeaderPage.jsp"),
    FOOTER_PAGE("/FooterPage.jsp"),
    INDEX_PAGE("/index.jsp");


    String page;

    PagesEnum(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
