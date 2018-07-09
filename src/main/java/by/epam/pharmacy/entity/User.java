package by.epam.pharmacy.entity;

public class User extends Entity{
    private static final long serialVersionUID = -5709073289050764193L;
    private int clientClId;
    private String auLogin;
    private String auPassword;
    private String auAccessLevel;

    public User() {
    }

    public int getClientClId() {
        return clientClId;
    }

    public void setClientClId(int clientClId) {
        this.clientClId = clientClId;
    }

    public String getAuLogin() {
        return auLogin;
    }

    public void setAuLogin(String auLogin) {
        this.auLogin = auLogin;
    }

    public String getAuPassword() {
        return auPassword;
    }

    public void setAuPassword(String auPassword) {
        this.auPassword = auPassword;
    }

    public String getAuAccessLevel() {
        return auAccessLevel;
    }

    public void setAuAccessLevel(String auAccessLevel) {
        this.auAccessLevel = auAccessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (clientClId != user.clientClId) return false;
        if (auLogin != null ? !auLogin.equals(user.auLogin) : user.auLogin != null) return false;
        if (auPassword != null ? !auPassword.equals(user.auPassword) : user.auPassword != null) return false;
        return auAccessLevel != null ? auAccessLevel.equals(user.auAccessLevel) : user.auAccessLevel == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientClId;
        result = 31 * result + (auLogin != null ? auLogin.hashCode() : 0);
        result = 31 * result + (auPassword != null ? auPassword.hashCode() : 0);
        result = 31 * result + (auAccessLevel != null ? auAccessLevel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "clientClId=" + clientClId +
                ", auLogin='" + auLogin + '\'' +
                ", auPassword='" + auPassword + '\'' +
                ", auAccessLevel=" + auAccessLevel +
                '}';
    }
}
