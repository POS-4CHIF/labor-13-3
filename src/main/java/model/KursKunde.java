package model;

import javax.persistence.*;

/**
 * @author Michael König
 */
@Entity
@Table(name = "kurs_kunde", schema = "public", catalog = "test")
@IdClass(KursKundePK.class)
public class KursKunde {
    private int kundeId;
    private int kursId;

    public KursKunde() {
    }

    public KursKunde(int kundeId, int kursId) {
        this.kundeId = kundeId;
        this.kursId = kursId;
    }

    @Id
    @Column(name = "kunde_id")
    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    @Id
    @Column(name = "kurs_id")
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

        KursKunde that = (KursKunde) o;

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
