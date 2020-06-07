package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

/**
 * @author Michael König
 */
@Entity
@Table(name = "kurs", schema = "public", catalog = "test")
public class Kurs {
    private int kursId;
    private String kursBezeichnung;
    private Date kursBeginndatum;

    @Id
    @Column(name = "kurs_id")
    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    @Basic
    @NotBlank(message = "Bezeichnung may not be empty")
    @Column(name = "kurs_bezeichnung")
    public String getKursBezeichnung() {
        return kursBezeichnung;
    }

    public void setKursBezeichnung(String kursBezeichnung) {
        this.kursBezeichnung = kursBezeichnung;
    }

    @Basic
    @Column(name = "kurs_beginndatum")
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

        Kurs that = (Kurs) o;

        if (kursId != that.kursId) return false;
        if (kursBezeichnung != null ? !kursBezeichnung.equals(that.kursBezeichnung) : that.kursBezeichnung != null)
            return false;
        if (kursBeginndatum != null ? !kursBeginndatum.equals(that.kursBeginndatum) : that.kursBeginndatum != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = kursId;
        result = 31 * result + (kursBezeichnung != null ? kursBezeichnung.hashCode() : 0);
        result = 31 * result + (kursBeginndatum != null ? kursBeginndatum.hashCode() : 0);
        return result;
    }
}
