package by.epam.pharmacy.entity;

import java.sql.Timestamp;

public class DoctorLicense extends Entity{
    private static final long serialVersionUID = 1956774032109911726L;
    private int licenseId;
    private String licenseNumber;
    private Timestamp termTill;

    public DoctorLicense() {
    }

    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Timestamp getTermTill() {
        return termTill;
    }

    public void setTermTill(Timestamp termTill) {
        this.termTill = termTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DoctorLicense that = (DoctorLicense) o;

        if (licenseId != that.licenseId) return false;
        if (licenseNumber != null ? !licenseNumber.equals(that.licenseNumber) : that.licenseNumber != null)
            return false;
        return termTill != null ? termTill.equals(that.termTill) : that.termTill == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + licenseId;
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (termTill != null ? termTill.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DoctorLicense{" +
                "licenseId=" + licenseId +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", termTill=" + termTill +
                '}';
    }
}
