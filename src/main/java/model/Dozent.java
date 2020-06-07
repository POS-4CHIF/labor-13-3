package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Michael König
 */
@Entity
@Table(name = "dozent", schema = "public", catalog = "test")
public class Dozent {
    private int dozId;
    private String dozZuname;
    private String dozVorname;

    public Dozent() {
    }

    public Dozent(int dozId, String dozZuname, String dozVorname) {
        this.dozId = dozId;
        this.dozZuname = dozZuname;
        this.dozVorname = dozVorname;
    }

    @Id
    @Column(name = "doz_id")
    public int getDozId() {
        return dozId;
    }

    public void setDozId(int dozId) {
        this.dozId = dozId;
    }

    @Basic
    @NotBlank(message = "Zuname may not be empty")
    @Column(name = "doz_zuname")
    public String getDozZuname() {
        return dozZuname;
    }

    public void setDozZuname(String dozZuname) {
        this.dozZuname = dozZuname;
    }

    @Basic
    @NotBlank(message = "Vorname may not be empty")
    @Column(name = "doz_vorname")
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

        Dozent that = (Dozent) o;

        if (dozId != that.dozId) return false;
        if (dozZuname != null ? !dozZuname.equals(that.dozZuname) : that.dozZuname != null) return false;
        if (dozVorname != null ? !dozVorname.equals(that.dozVorname) : that.dozVorname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dozId;
        result = 31 * result + (dozZuname != null ? dozZuname.hashCode() : 0);
        result = 31 * result + (dozVorname != null ? dozVorname.hashCode() : 0);
        return result;
    }
}
