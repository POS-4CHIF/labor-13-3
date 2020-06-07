package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author Michael König
 */
@Entity
public class Kunde {
    private Integer kundeId;
    private String kundeZuname;
    private String kundeVorname;
    private Collection<KursKunde> kursKundesByKundeId;

    public Kunde() {
    }

    public Kunde(String kundeZuname, String kundeVorname) {
        this.kundeZuname = kundeZuname;
        this.kundeVorname = kundeVorname;
    }

    @Id
    @GeneratedValue
    @Column(name = "kunde_id", nullable = false)
    public Integer getKundeId() {
        return kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    @Basic
    @NotBlank(message = "Zuname may not be empty")
    @Column(name = "kunde_zuname", nullable = true, length = 25)
    public String getKundeZuname() {
        return kundeZuname;
    }

    public void setKundeZuname(String kundeZuname) {
        this.kundeZuname = kundeZuname;
    }

    @Basic
    @NotBlank(message = "Vorname may not be empty")
    @Column(name = "kunde_vorname", nullable = true, length = 25)
    public String getKundeVorname() {
        return kundeVorname;
    }

    public void setKundeVorname(String kundeVorname) {
        this.kundeVorname = kundeVorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kunde kunde = (Kunde) o;

        if (kundeId != null ? !kundeId.equals(kunde.kundeId) : kunde.kundeId != null) return false;
        if (kundeZuname != null ? !kundeZuname.equals(kunde.kundeZuname) : kunde.kundeZuname != null) return false;
        if (kundeVorname != null ? !kundeVorname.equals(kunde.kundeVorname) : kunde.kundeVorname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kundeId != null ? kundeId.hashCode() : 0;
        result = 31 * result + (kundeZuname != null ? kundeZuname.hashCode() : 0);
        result = 31 * result + (kundeVorname != null ? kundeVorname.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "kundeByKundeId")
    public Collection<KursKunde> getKursKundesByKundeId() {
        return kursKundesByKundeId;
    }

    public void setKursKundesByKundeId(Collection<KursKunde> kursKundesByKundeId) {
        this.kursKundesByKundeId = kursKundesByKundeId;
    }
}
