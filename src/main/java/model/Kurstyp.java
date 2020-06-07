package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author Michael König
 */
@Entity
public class Kurstyp {
    private String typId;
    private String typBezeichnung;
    private Collection<Kurs> kursByTypId;

    public Kurstyp() {
    }

    public Kurstyp(String typId, String typBezeichnung) {
        this.typId = typId;
        this.typBezeichnung = typBezeichnung;
    }

    @Id
    @NotBlank(message = "Id may not be empty")
    @Column(name = "typ_id", nullable = false, length = -1)
    public String getTypId() {
        return typId;
    }

    public void setTypId(String typId) {
        this.typId = typId;
    }

    @Basic
    @NotBlank(message = "Bezeichnung may not be empty")
    @Column(name = "typ_bezeichnung", nullable = true, length = 100)
    public String getTypBezeichnung() {
        return typBezeichnung;
    }

    public void setTypBezeichnung(String typBezeichnung) {
        this.typBezeichnung = typBezeichnung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kurstyp kurstyp = (Kurstyp) o;

        if (typId != null ? !typId.equals(kurstyp.typId) : kurstyp.typId != null) return false;
        if (typBezeichnung != null ? !typBezeichnung.equals(kurstyp.typBezeichnung) : kurstyp.typBezeichnung != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typId != null ? typId.hashCode() : 0;
        result = 31 * result + (typBezeichnung != null ? typBezeichnung.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "kurstypByKursTyp")
    public Collection<Kurs> getKursByTypId() {
        return kursByTypId;
    }

    public void setKursByTypId(Collection<Kurs> kursByTypId) {
        this.kursByTypId = kursByTypId;
    }
}
