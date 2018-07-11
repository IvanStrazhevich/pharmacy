package by.epam.pharmacy.entity;

public class ClientDetail extends Entity {
    private static final long serialVersionUID = -2234118294156026170L;
    private int clientId;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String postcode;
    private String country;
    private String city;
    private String address;

    public ClientDetail() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDetail clientDetail = (ClientDetail) o;

        if (clientId != clientDetail.clientId) return false;
        if (name != null ? !name.equals(clientDetail.name) : clientDetail.name != null) return false;
        if (lastname != null ? !lastname.equals(clientDetail.lastname) : clientDetail.lastname != null) return false;
        if (email != null ? !email.equals(clientDetail.email) : clientDetail.email != null) return false;
        if (phone != null ? !phone.equals(clientDetail.phone) : clientDetail.phone != null) return false;
        if (postcode != null ? !postcode.equals(clientDetail.postcode) : clientDetail.postcode != null) return false;
        if (country != null ? !country.equals(clientDetail.country) : clientDetail.country != null) return false;
        if (city != null ? !city.equals(clientDetail.city) : clientDetail.city != null) return false;
        return address != null ? address.equals(clientDetail.address) : clientDetail.address == null;
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientDetail{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
