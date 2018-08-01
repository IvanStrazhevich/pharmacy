package by.epam.pharmacy.entity;

/**
 * 
 */
public class DoctorDetail extends ClientDetail {
    private static final long serialVersionUID = -8413097800977296446L;
    private int licenseId;

    /**
     * 
     */
    public DoctorDetail() {
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
     * @param o 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DoctorDetail doctorDetail = (DoctorDetail) o;

        return licenseId == doctorDetail.licenseId;
    }

    /**
     * 
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + licenseId;
        return result;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "DoctorDetail{" +
                "licenseId=" + licenseId +
                '}';
    }
}

