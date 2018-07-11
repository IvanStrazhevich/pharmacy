package by.epam.pharmacy.entity;

public class Client extends Entity {
    private static final long serialVersionUID = -2234118294156026170L;
    private String userId;
    private String name;
    private String lastname;

    public Client() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (userId != null ? !userId.equals(client.userId) : client.userId != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        return lastname != null ? lastname.equals(client.lastname) : client.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
