package by.epam.pharmacy.entity;

public class Pharmacist extends ClientDetail {
    private static final long serialVersionUID = 5257165600065973373L;
    private int licenseId;

    public Pharmacist() {
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

        return licenseId == that.licenseId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + licenseId;
        return result;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "licenseId=" + licenseId +
                '}';
    }
}
