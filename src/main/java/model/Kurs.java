package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Michael König
 */
@Entity
public class Kurs {
    private Integer kursId;
    private String kursBezeichnung;
    private Date kursBeginndatum;
    private Kurstyp kurstypByKursTyp;
    private Dozent dozentByKursDozId;
    private Collection<KursKunde> kursKundesByKursId;


    public Kurs() {
    }

    public Kurs(Kurstyp kursTyp, String kursBezeichnung, Date kursBeginndatum, Dozent dozent) {
        this.kurstypByKursTyp = kursTyp;
        this.kursBezeichnung = kursBezeichnung;
        this.kursBeginndatum = kursBeginndatum;
        this.dozentByKursDozId = dozent;
    }

    @Id
    @Column(name = "kurs_id", nullable = false)
    public Integer getKursId() {
        return kursId;
    }

    public void setKursId(Integer kursId) {
        this.kursId = kursId;
    }


    @Basic
    @NotBlank(message = "Bezeichnung may not be empty")
    @Column(name = "kurs_bezeichnung", nullable = false)
    public String getKursBezeichnung() {
        return kursBezeichnung;
    }

    public void setKursBezeichnung(String kursBezeichnung) {
        this.kursBezeichnung = kursBezeichnung;
    }

    @Basic
    @Column(name = "kurs_beginndatum", nullable = true)
    public Date getKursBeginndatum() {
        return kursBeginndatum;
    }

    public void setKursBeginndatum(Date kursBeginndatum) {
        this.kursBeginndatum = kursBeginndatum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kurs kurs = (Kurs) o;

        if (kursId != null ? !kursId.equals(kurs.kursId) : kurs.kursId != null) return false;
        if (kursBezeichnung != null ? !kursBezeichnung.equals(kurs.kursBezeichnung) : kurs.kursBezeichnung != null)
            return false;
        if (kursBeginndatum != null ? !kursBeginndatum.equals(kurs.kursBeginndatum) : kurs.kursBeginndatum != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kursId != null ? kursId.hashCode() : 0;
        result = 31 * result + (kursBezeichnung != null ? kursBezeichnung.hashCode() : 0);
        result = 31 * result + (kursBeginndatum != null ? kursBeginndatum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "kurs_typ", referencedColumnName = "typ_id")
    public Kurstyp getKurstypByKursTyp() {
        return kurstypByKursTyp;
    }

    public void setKurstypByKursTyp(Kurstyp kurstypByKursTyp) {
        this.kurstypByKursTyp = kurstypByKursTyp;
    }

    @ManyToOne
    @JoinColumn(name = "kurs_doz_id", referencedColumnName = "doz_id")
    public Dozent getDozentByKursDozId() {
        return dozentByKursDozId;
    }

    public void setDozentByKursDozId(Dozent dozentByKursDozId) {
        this.dozentByKursDozId = dozentByKursDozId;
    }

    @OneToMany(mappedBy = "kursByKursId")
    public Collection<KursKunde> getKursKundesByKursId() {
        return kursKundesByKursId;
    }

    public void setKursKundesByKursId(Collection<KursKunde> kursKundesByKursId) {
        this.kursKundesByKursId = kursKundesByKursId;
    }
}
