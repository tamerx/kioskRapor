package tr.com.cinigaz.kioskRapor.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import tr.com.cinigaz.kioskRapor.dto.*;
import tr.com.cinigaz.kioskRapor.entity.KioskAlinanParaKasasiEntity;
import tr.com.cinigaz.kioskRapor.service.BankService;
import tr.com.cinigaz.kioskRapor.service.KioskAlinanParaService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class KioskAlinanForm implements Initializable {


    @FXML
    ComboBox<String> bankaCombo;

    @FXML
    private TextField kioskID;

    @FXML
    private DatePicker islemTarihi;

    @FXML
    private TextField teslimAlan;

    @FXML
    private TextField teslimEden;

    @FXML
    TableView kioskTable;

    @FXML
    TableColumn siraNo;

    @FXML
    TableColumn sozlesmeNo;

    @FXML
    TableColumn tutar;

    @FXML
    Button kaydet;


    ObservableList<Record> data;


    DirectoryChooser directoryChooser = new DirectoryChooser();


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        BankService bankService = new BankService();

        List<String> bankListData = new ArrayList<>();
        for (BankResponse b : bankService.getBankList()) {
            bankListData.add(b.getBankaAdi());
        }

        ObservableList<String> bankList = FXCollections.observableArrayList(bankListData);
        bankaCombo.setItems(bankList);
        bankaCombo.setVisibleRowCount(3);
        bankaCombo.getSelectionModel().selectFirst();

        directoryChooser.setInitialDirectory(new File("src"));

    }

    @FXML
    private void listele() throws Exception {


        String kioskKodu = "";

        LocalDate islemTarihiLocal;

        if (kioskID.getText() == null || kioskID.getText().trim().isEmpty() || islemTarihi.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Kiosk Kodu Giriniz veya Tarih Giriniz");
            alert.showAndWait();
            return;

        } else {
            kioskKodu = kioskID.getText();
            islemTarihiLocal = islemTarihi.getValue();
        }


        KioskAlinanParaService kioskAlinanParaService = new KioskAlinanParaService();
        List<Record> recordList = new ArrayList<Record>();
        try {
            List<KioskAlinanParaKasasiResponse> kioskAlinanParaKasasiResponseList = kioskAlinanParaService.getData(kioskKodu, java.sql.Date.valueOf(islemTarihiLocal));
            for (KioskAlinanParaKasasiResponse k :
                    kioskAlinanParaKasasiResponseList) {
                Record r = new Record(String.valueOf(k.getId()), String.valueOf(k.getAlimSirasi()), k.getSozlesmeKod(), k.getAlinanBanknot().toString());

                recordList.add(r);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        data = FXCollections.observableArrayList(recordList);

        siraNo.setCellValueFactory(
                new PropertyValueFactory<Record, String>("siraNo")
        );
        sozlesmeNo.setCellValueFactory(
                new PropertyValueFactory<Record, String>("sozlesmeNo")
        );
        tutar.setCellValueFactory(
                new PropertyValueFactory<Record, String>("tutar")
        );

//        secim.setCellValueFactory(
//                new PropertyValueFactory<Record, String>("remark")
//        );

        kioskTable.setItems(data);
    }

    @FXML
    private void kaydet() throws Exception {

        String kioskKodu = "";
        String teslimEdenText = "";
        String alanKisiText = "";
        String kurumAdiText = "";
        LocalDate islemTarihiLocalDate;


        try {

            if (teslimEden.getText() == null || teslimEden.getText().trim().isEmpty() || teslimAlan.getText() == null || teslimAlan.getText().trim().isEmpty() || bankaCombo.getValue() == null || bankaCombo.getValue().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uyarı");
                alert.setHeaderText(null);
                alert.setContentText("Teslim Eden Kişi , Teslim Alan Kişi veya Banka  ismini giriniz");
                alert.showAndWait();
                return;

            } else {
                teslimEdenText = teslimEden.getText();
                alanKisiText = teslimAlan.getText();
                kioskKodu = kioskID.getText();
                kurumAdiText = bankaCombo.getValue();
                islemTarihiLocalDate = islemTarihi.getValue();

            }


            List<Record> recordList = new ArrayList<>();
            KioskAlinanParaService kioskAlinanParaService = new KioskAlinanParaService();
            KioskKasaOdenenDto kioskKasaOdenenDto = new KioskKasaOdenenDto();

            kioskKasaOdenenDto.setTeslimAlan(alanKisiText);
            kioskKasaOdenenDto.setTeslimEden(teslimEdenText);
            kioskKasaOdenenDto.setKurumAdi(kurumAdiText);
            kioskKasaOdenenDto.setIslemTarihi(java.sql.Date.valueOf(islemTarihiLocalDate));
            kioskKasaOdenenDto.setKioskKodu(kioskKodu);

            for (int i = 0; i < kioskTable.getItems().size(); i++) {
                Record record = (Record) kioskTable.getItems().get(i);
                recordList.add(record);
            }
            List<KioskAlinanParaKasasiEntity> kioskAlinanParaKasasiEntityList = new ArrayList<>();


            double toplamALinan = 0;

            for (Record record : recordList) {

                KioskAlinanParaKasasiEntity k = new KioskAlinanParaKasasiEntity();
                k.setId(Long.valueOf(record.getRecordID()));
                k.setAlimSirasi(Integer.parseInt(record.getSiraNo()));
                k.setSozlesmeKod(Long.valueOf(record.getSozlesmeNo()));
                k.setAlinanBanknot(Long.parseLong(record.getTutar()));
                k.setKioskKodu(kioskKodu);
                toplamALinan += Long.parseLong(record.getTutar());
                kioskAlinanParaKasasiEntityList.add(k);

            }
            kioskKasaOdenenDto.setToplamAlinan(toplamALinan); // toplam Alinan Para Set ediliyor.
            KioskKasaOdenenResponse kioskKasaOdenenResponse = kioskAlinanParaService.odenenParaKasasiKaydet(kioskKasaOdenenDto);
            Long odenenID = (long) kioskKasaOdenenResponse.getId();
            kioskAlinanParaService.alinanParaKasasiKaydet(odenenID, kioskAlinanParaKasasiEntityList);

            File selectedDirectory = directoryChooser.showDialog(new Stage());


            Font BOLD = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream(selectedDirectory.getAbsolutePath() + "/" + kioskKasaOdenenDto.getKioskKodu() + "_" + odenenID + java.sql.Date.valueOf(islemTarihiLocalDate).toString() + ".pdf"));
            my_pdf_report.open();


            PdfPTable my_report_table = new PdfPTable(3);

            PdfPHeaderCell siraNoHeaderCell = new PdfPHeaderCell();
            PdfPHeaderCell sozlesmeNoHeaderCell = new PdfPHeaderCell();
            PdfPHeaderCell tutarHeaderCell = new PdfPHeaderCell();

            Phrase siraNoPhrase = new Phrase("Sira No", BOLD);
            siraNoHeaderCell.setPhrase(siraNoPhrase);

            Phrase sozlesmeNoPhrase = new Phrase("Sözlesme No", BOLD);
            sozlesmeNoHeaderCell.setPhrase(sozlesmeNoPhrase);

            Phrase tutarPhrase = new Phrase("Tutar", BOLD);
            tutarHeaderCell.setPhrase(tutarPhrase);


            my_report_table.addCell(siraNoHeaderCell);
            my_report_table.addCell(sozlesmeNoHeaderCell);
            my_report_table.addCell(tutarHeaderCell);


            PdfPCell table_cell;

            for (Record r : recordList) {

                String siraNo = r.getSiraNo();
                String sozlesmeNo = r.getSozlesmeNo();
                String tutar = r.getTutar();

                table_cell = new PdfPCell(new Phrase(siraNo));
                my_report_table.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(sozlesmeNo));
                my_report_table.addCell(table_cell);

                table_cell = new PdfPCell(new Phrase(tutar));
                my_report_table.addCell(table_cell);

            }

            my_pdf_report.add(my_report_table);
            my_pdf_report.close();


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bilgi");
            alert.setHeaderText(null);
            alert.setContentText("İşlem Başarılı");
            alert.showAndWait();

            Stage stage = (Stage) kaydet.getScene().getWindow();
            // do what you have to do
            stage.close();


        } catch (Exception e) {
        }
    }


}
