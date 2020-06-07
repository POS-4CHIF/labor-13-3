package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Michael König
 */
public class KursKundePK implements Serializable {
    private Integer kundeId;
    private Integer kursId;

    @Column(name = "kunde_id", nullable = false)
    @Id
    public Integer getKundeId() {
        return kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    @Column(name = "kurs_id", nullable = false)
    @Id
    public Integer getKursId() {
        return kursId;
    }

    public void setKursId(Integer kursId) {
        this.kursId = kursId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KursKundePK that = (KursKundePK) o;

        if (kundeId != null ? !kundeId.equals(that.kundeId) : that.kundeId != null) return false;
        if (kursId != null ? !kursId.equals(that.kursId) : that.kursId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kundeId != null ? kundeId.hashCode() : 0;
        result = 31 * result + (kursId != null ? kursId.hashCode() : 0);
        return result;
    }
}
