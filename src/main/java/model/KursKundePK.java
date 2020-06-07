package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Michael König
 */
public class KursKundePK implements Serializable {
    private int kundeId;
    private int kursId;

    @Column(name = "kunde_id")
    @Id
    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    @Column(name = "kurs_id")
    @Id
    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KursKundePK that = (KursKundePK) o;

        if (kundeId != that.kundeId) return false;
        if (kursId != that.kursId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kundeId;
        result = 31 * result + kursId;
        return result;
    }
}
