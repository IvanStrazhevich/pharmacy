package by.epam.pharmacy.entity;

public class Pharmacist extends Client {
    private static final long serialVersionUID = 5257165600065973373L;
    private String userId;
    private int licenseId;

    public Pharmacist() {
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
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

        if (licenseId != that.licenseId) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + licenseId;
        return result;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "userId='" + userId + '\'' +
                ", licenseId=" + licenseId +
                '}';
    }
}
