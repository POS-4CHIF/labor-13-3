package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author Michael König
 */
@Entity
public class Dozent {
    private Integer dozId;
    private String dozZuname;
    private String dozVorname;
    private Collection<Kurs> kursByDozId;

    public Dozent() {
    }

    public Dozent(String dozZuname, String dozVorname) {
        this.dozZuname = dozZuname;
        this.dozVorname = dozVorname;
    }

    @Id
    @Column(name = "doz_id", nullable = false)
    public Integer getDozId() {
        return dozId;
    }

    public void setDozId(Integer dozId) {
        this.dozId = dozId;
    }

    @Basic
    @NotBlank(message = "Zuname may not be empty")
    @Column(name = "doz_zuname", nullable = true, length = 25)
    public String getDozZuname() {
        return dozZuname;
    }

    public void setDozZuname(String dozZuname) {
        this.dozZuname = dozZuname;
    }

    @Basic
    @NotBlank(message = "Vorname may not be empty")
    @Column(name = "doz_vorname", nullable = true, length = 25)
    public String getDozVorname() {
        return dozVorname;
    }

    public void setDozVorname(String dozVorname) {
        this.dozVorname = dozVorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dozent dozent = (Dozent) o;

        if (dozId != null ? !dozId.equals(dozent.dozId) : dozent.dozId != null) return false;
        if (dozZuname != null ? !dozZuname.equals(dozent.dozZuname) : dozent.dozZuname != null) return false;
        if (dozVorname != null ? !dozVorname.equals(dozent.dozVorname) : dozent.dozVorname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dozId != null ? dozId.hashCode() : 0;
        result = 31 * result + (dozZuname != null ? dozZuname.hashCode() : 0);
        result = 31 * result + (dozVorname != null ? dozVorname.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "dozentByKursDozId")
    public Collection<Kurs> getKursByDozId() {
        return kursByDozId;
    }

    public void setKursByDozId(Collection<Kurs> kursByDozId) {
        this.kursByDozId = kursByDozId;
    }
}
