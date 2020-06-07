package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kurs_kunde")
@IdClass(KursKundePK.class)
public class KursKunde {
    private Integer kundeId;
    private Integer kursId;
    private Kunde kundeByKundeId;
    private Kurs kursByKursId;

    public KursKunde() {
    }

    public KursKunde(Kunde kunde, Kurs kurs) {
        setKundeId(kunde.getKundeId());
        setKursId(kurs.getKursId());
        setKundeByKundeId(kunde);
        setKursByKursId(kurs);
    }

    @Id
    @Column(name = "kunde_id", nullable = false)
    public Integer getKundeId() {
        return kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    @Id
    @Column(name = "kurs_id", nullable = false)
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
        KursKunde that = (KursKunde) o;
        return Objects.equals(kundeId, that.kundeId) &&
                Objects.equals(kursId, that.kursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kundeId, kursId);
    }

    @ManyToOne
    @JoinColumn(name = "kunde_id", referencedColumnName = "kunde_id", nullable = false, insertable = false, updatable = false)
    public Kunde getKundeByKundeId() {
        return kundeByKundeId;
    }

    public void setKundeByKundeId(Kunde kundeByKundeId) {
        this.kundeByKundeId = kundeByKundeId;
    }

    @ManyToOne
    @JoinColumn(name = "kurs_id", referencedColumnName = "kurs_id", nullable = false, insertable = false, updatable = false)
    public Kurs getKursByKursId() {
        return kursByKursId;
    }

    public void setKursByKursId(Kurs kursByKursId) {
        this.kursByKursId = kursByKursId;
    }

    @Override
    public String toString() {
        return "KursKunde{" +
                "kundeId=" + kundeId +
                ", kursId=" + kursId +
                ", kundeByKundeId=" + kundeByKundeId +
                ", kursByKursId=" + kursByKursId +
                '}';
    }
}
