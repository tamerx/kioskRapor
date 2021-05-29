package tr.com.cinigaz.kioskRapor.dto;

import javafx.beans.property.SimpleStringProperty;

public class KioskOdenenRecordDetail implements Comparable<KioskOdenenRecordDetail> {


    private SimpleStringProperty alimSirasi;
    private SimpleStringProperty alimZamani;
    private SimpleStringProperty banknot;
    private SimpleStringProperty kioskKodu;
    private SimpleStringProperty sozlesmeKodu;

    public KioskOdenenRecordDetail(String alimSirasi, String alimZamani, String alinanBanknot, String kioskKodu, String sozlesmeKodu) {
        this.alimSirasi = new SimpleStringProperty(alimSirasi);
        this.alimZamani = new SimpleStringProperty(alimZamani);
        this.banknot = new SimpleStringProperty(alinanBanknot);
        this.kioskKodu = new SimpleStringProperty(kioskKodu);
        this.sozlesmeKodu = new SimpleStringProperty(sozlesmeKodu);
    }


    public String getAlimSirasi() {
        return alimSirasi.get();
    }

    public SimpleStringProperty alimSirasiProperty() {
        return alimSirasi;
    }

    public void setAlimSirasi(String alimSirasi) {
        this.alimSirasi.set(alimSirasi);
    }

    public String getAlimZamani() {
        return alimZamani.get();
    }

    public SimpleStringProperty alimZamaniProperty() {
        return alimZamani;
    }

    public void setAlimZamani(String alimZamani) {
        this.alimZamani.set(alimZamani);
    }

    public String getBanknot() {
        return banknot.get();
    }

    public SimpleStringProperty banknotProperty() {
        return banknot;
    }

    public void setBanknot(String banknot) {
        this.banknot.set(banknot);
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

    public String getSozlesmeKodu() {
        return sozlesmeKodu.get();
    }

    public SimpleStringProperty sozlesmeKoduProperty() {
        return sozlesmeKodu;
    }

    public void setSozlesmeKodu(String sozlesmeKodu) {
        this.sozlesmeKodu.set(sozlesmeKodu);
    }

    @Override
    public int compareTo(KioskOdenenRecordDetail o) {
        return 0;
    }
}
