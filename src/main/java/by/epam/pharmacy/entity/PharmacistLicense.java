package by.epam.pharmacy.entity;

import java.sql.Timestamp;

/**
 * 
 */
public class PharmacistLicense extends Entity{
    private static final long serialVersionUID = 6563045699693799857L;
    private int licenseId;
    private String licenseNumber;
    private Timestamp termTill;

    /**
     * 
     */
    public PharmacistLicense() {
    }

    /**
     * 
     */
    public int getLicenseId() {
        return licenseId;
    }

    /**
     * 
     * @param licenseId 
     */
    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    /**
     * 
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * 
     * @param licenseNumber 
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * 
     */
    public Timestamp getTermTill() {
        return termTill;
    }

    /**
     * 
     * @param termTill 
     */
    public void setTermTill(Timestamp termTill) {
        this.termTill = termTill;
    }

    /**
     * 
     * @param o 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PharmacistLicense that = (PharmacistLicense) o;

        if (licenseId != that.licenseId) return false;
        if (licenseNumber != null ? !licenseNumber.equals(that.licenseNumber) : that.licenseNumber != null)
            return false;
        return termTill != null ? termTill.equals(that.termTill) : that.termTill == null;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + licenseId;
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (termTill != null ? termTill.hashCode() : 0);
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "PharmacistLicense{" +
                "licenseId=" + licenseId +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", termTill=" + termTill +
                '}';
    }
}

