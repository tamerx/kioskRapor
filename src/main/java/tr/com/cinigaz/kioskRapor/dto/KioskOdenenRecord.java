package tr.com.cinigaz.kioskRapor.dto;

import javafx.beans.property.SimpleStringProperty;

public class KioskOdenenRecord  {

    private Integer id;


    private SimpleStringProperty islemTarihi;
    private SimpleStringProperty kioskKodu;
    private SimpleStringProperty kurumAdi;
    private SimpleStringProperty toplamTutar;

    public KioskOdenenRecord(String islemTarihi, String kioskKodu, String kurumAdi, String toplamTutar) {
        this.islemTarihi = new SimpleStringProperty(islemTarihi);
        this.kioskKodu = new SimpleStringProperty(kioskKodu);
        this.kurumAdi = new SimpleStringProperty(kurumAdi);
        this.toplamTutar = new SimpleStringProperty(toplamTutar);
    }


    public String getIslemTarihi() {
        return islemTarihi.get();
    }

    public SimpleStringProperty islemTarihiProperty() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi.set(islemTarihi);
    }

    public String getKioskKodu() {
        return kioskKodu.get();
    }

    public SimpleStringProperty kioskKoduProperty() {
        return kioskKodu;
    }

    public void setKioskKodu(String kioskKodu) {
        this.kioskKodu.set(kioskKodu);
    }

    public String getKurumAdi() {
        return kurumAdi.get();
    }

    public SimpleStringProperty kurumAdiProperty() {
        return kurumAdi;
    }

    public void setKurumAdi(String kurumAdi) {
        this.kurumAdi.set(kurumAdi);
    }

    public String getToplamTutar() {
        return toplamTutar.get();
    }

    public SimpleStringProperty toplamTutarProperty() {
        return toplamTutar;
    }

    public void setToplamTutar(String toplamTutar) {
        this.toplamTutar.set(toplamTutar);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
