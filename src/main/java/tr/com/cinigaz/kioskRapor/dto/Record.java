package tr.com.cinigaz.kioskRapor.dto;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Record {


    private SimpleStringProperty recordID;
    private SimpleStringProperty siraNo;
    private SimpleStringProperty sozlesmeNo;
    private SimpleStringProperty tutar;
    private CheckBox remark;



    public Record(String recordID, String siraNo, String sozlesmeNo, String tutar) {
        this.recordID = new SimpleStringProperty(recordID);
        this.siraNo = new SimpleStringProperty(siraNo);
        this.sozlesmeNo = new SimpleStringProperty(sozlesmeNo);
        this.tutar = new SimpleStringProperty(tutar);
        this.remark = new CheckBox();
    }

    public Record() {
    }


    public String getRecordID() {
        return recordID.get();
    }

    public SimpleStringProperty recordIDProperty() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID.set(recordID);
    }

    public String getSiraNo() {
        return siraNo.get();
    }

    public SimpleStringProperty siraNoProperty() {
        return siraNo;
    }

    public void setSiraNo(String siraNo) {
        this.siraNo.set(siraNo);
    }

    public String getSozlesmeNo() {
        return sozlesmeNo.get();
    }

    public SimpleStringProperty sozlesmeNoProperty() {
        return sozlesmeNo;
    }

    public void setSozlesmeNo(String sozlesmeNo) {
        this.sozlesmeNo.set(sozlesmeNo);
    }

    public String getTutar() {
        return tutar.get();
    }

    public SimpleStringProperty tutarProperty() {
        return tutar;
    }

    public void setTutar(String tutar) {
        this.tutar.set(tutar);
    }

    public CheckBox getRemark() {
        return remark;
    }

    public void setRemark(CheckBox remark) {
        this.remark = remark;
    }


}
