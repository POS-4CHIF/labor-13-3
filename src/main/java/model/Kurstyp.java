package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Michael König
 */
@Entity
@Table(name = "kurstyp", schema = "public", catalog = "test")
public class Kurstyp {
    private String typId;
    private String typBezeichnung;

    @Id
    @NotBlank(message = "TypId may not be empty")
    @Column(name = "typ_id")
    public String getTypId() {
        return typId;
    }

    public void setTypId(String typId) {
        this.typId = typId;
    }

    @Basic
    @NotBlank(message = "Bezeichnung may not be empty")
    @Column(name = "typ_bezeichnung")
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

        Kurstyp that = (Kurstyp) o;

        if (typId != null ? !typId.equals(that.typId) : that.typId != null) return false;
        if (typBezeichnung != null ? !typBezeichnung.equals(that.typBezeichnung) : that.typBezeichnung != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typId != null ? typId.hashCode() : 0;
        result = 31 * result + (typBezeichnung != null ? typBezeichnung.hashCode() : 0);
        return result;
    }
}
