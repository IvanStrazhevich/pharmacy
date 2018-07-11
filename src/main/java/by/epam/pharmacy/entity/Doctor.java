package by.epam.pharmacy.entity;

public class Doctor extends Client {
    private static final long serialVersionUID = -8413097800977296446L;
    private int licenseId;

    public Doctor() {
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

        Doctor doctor = (Doctor) o;

        return licenseId == doctor.licenseId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + licenseId;
        return result;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "licenseId=" + licenseId +
                '}';
    }
}
