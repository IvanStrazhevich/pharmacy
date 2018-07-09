package by.epam.pharmacy.entity;

public class Pharmacist extends Client {
    private static final long serialVersionUID = 5257165600065973373L;
    private int clientId;
    private int licenseId;

    public Pharmacist() {
    }

    @Override
    public int getClientId() {
        return clientId;
    }

    @Override
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pharmacist that = (Pharmacist) o;

        if (clientId != that.clientId) return false;
        return licenseId == that.licenseId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + clientId;
        result = 31 * result + licenseId;
        return result;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "clientId=" + clientId +
                ", licenseId=" + licenseId +
                '}';
    }
}
