package tr.com.cinigaz.kioskRapor.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tr.com.cinigaz.kioskRapor.dto.KioskAlinanParaKasasiResponse;
import tr.com.cinigaz.kioskRapor.dto.KioskOdenenRecord;
import tr.com.cinigaz.kioskRapor.dto.KioskOdenenRecordDetail;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class KioskOdenenFormDetail implements Initializable {


    ObservableList<KioskOdenenRecordDetail> data;

    @FXML
    TableColumn alimSirasi;

    @FXML
    TableColumn alimZamani;

    @FXML
    TableColumn banknot;

    @FXML
    TableColumn kioskKodu;
    @FXML
    TableColumn sozlesmeKodu;

    @FXML
    TableView kioskOdenenFormDetailTable;

    public void setKioskKasaVerilenResponseList(List<KioskAlinanParaKasasiResponse> kioskAlinanParaKasasiResponseList) {

        List<KioskOdenenRecordDetail> recordList = new ArrayList<KioskOdenenRecordDetail>();

        for (KioskAlinanParaKasasiResponse kioskAlinanParaKasasiResponse : kioskAlinanParaKasasiResponseList) {


            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String alimZamaniFormatted= simpleDateFormat.format(kioskAlinanParaKasasiResponse.getAlimZamani());

            KioskOdenenRecordDetail kioskOdenenRecordDetail = new KioskOdenenRecordDetail(String.valueOf(kioskAlinanParaKasasiResponse.getAlimSirasi()), alimZamaniFormatted
                    , kioskAlinanParaKasasiResponse.getAlinanBanknot().toString(), kioskAlinanParaKasasiResponse.getKioskKodu(), kioskAlinanParaKasasiResponse.getSozlesmeKod());

            recordList.add(kioskOdenenRecordDetail);
        }


        Comparator<KioskOdenenRecordDetail> compareByAlimSirasi = (KioskOdenenRecordDetail k1, KioskOdenenRecordDetail k2) -> Integer.valueOf(k1.getAlimSirasi()).compareTo(Integer.valueOf(k2.getAlimSirasi()));

        Collections.sort(recordList, compareByAlimSirasi);


        data = FXCollections.observableArrayList(recordList);


        alimSirasi.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecordDetail, String>("alimSirasi")
        );
        alimZamani.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("alimZamani")
        );
        banknot.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("banknot")
        );

        kioskKodu.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("kioskKodu")
        );

        sozlesmeKodu.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("sozlesmeKodu")
        );


        kioskOdenenFormDetailTable.setItems(data);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
