package by.epam.pharmacy.entity;

/**
 * 
 */
public class User extends Entity{
    private static final long serialVersionUID = -5709073289050764193L;
    private int userId;
    private String login;
    private String password;
    private String accessLevel;
    private ClientDetail clientDetail;

    /**
     * 
     */
    public User() {
    }

    /**
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 
     */
    public String getLogin() {
        return login;
    }

    /**
     * 
     * @param login 
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * 
     * @param accessLevel 
     */
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * 
     */
    public ClientDetail getClientDetail() {
        return clientDetail;
    }

    /**
     * 
     * @param clientDetail 
     */
    public void setClientDetail(ClientDetail clientDetail) {
        this.clientDetail = clientDetail;
    }

    /**
     * 
     * @param o 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (accessLevel != null ? !accessLevel.equals(user.accessLevel) : user.accessLevel != null) return false;
        return clientDetail != null ? clientDetail.equals(user.clientDetail) : user.clientDetail == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (accessLevel != null ? accessLevel.hashCode() : 0);
        result = 31 * result + (clientDetail != null ? clientDetail.hashCode() : 0);
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", clientDetail=" + clientDetail +
                '}';
    }
}

