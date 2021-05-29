package tr.com.cinigaz.kioskRapor.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import tr.com.cinigaz.kioskRapor.dto.KioskAlinanParaKasasiResponse;
import tr.com.cinigaz.kioskRapor.dto.KioskKasaOdenenResponse;
import tr.com.cinigaz.kioskRapor.dto.KioskOdenenRecord;
import tr.com.cinigaz.kioskRapor.dto.KioskOdenenRecordDetail;
import tr.com.cinigaz.kioskRapor.service.KioskAlinanParaService;
import tr.com.cinigaz.kioskRapor.service.KioskOdenenParaService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class KioskOdenenForm implements Initializable {

    @FXML
    private TextField kioskID;

    @FXML
    private DatePicker islemTarihi;

    @FXML
    TableView kioskTable;

    @FXML
    TableColumn islemTarihiColumn;

    @FXML
    TableColumn kioskKoduColumn;

    @FXML
    TableColumn kurumAdi;

    @FXML
    TableColumn toplamTutar;


    ObservableList<KioskOdenenRecord> data;

    KioskOdenenParaService kioskOdenenParaService;
    KioskAlinanParaService kioskAlinanParaService;
    DirectoryChooser directoryChooser = new DirectoryChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void listele() throws Exception {
        String kioskKodu = "";
        LocalDate islemTarihiLocal;
        if (kioskID.getText() == null || kioskID.getText().trim().isEmpty() || islemTarihi.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Kiosk Kodu  veya Tarih Giriniz");
            alert.showAndWait();
            return;

        } else {
            kioskKodu = kioskID.getText().trim();
            islemTarihiLocal = islemTarihi.getValue();

        }

        List<KioskOdenenRecord> recordList = new ArrayList<KioskOdenenRecord>();
        kioskOdenenParaService = new KioskOdenenParaService();
        try {
            List<KioskKasaOdenenResponse> kioskAlinanParaKasasiResponseList = kioskOdenenParaService.getData(kioskKodu, java.sql.Date.valueOf(islemTarihiLocal));
            if (kioskAlinanParaKasasiResponseList != null) {
                for (KioskKasaOdenenResponse k : kioskAlinanParaKasasiResponseList) {

                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String responseIslemTarihi = simpleDateFormat.format(k.getIslemTarihi());
                    String responseKioskKodu = k.getKioskKodu();
                    String responseKurumAdi = k.getKurumAdi();
                    double responseToplamTutar = k.getToplamAlinan();
                    KioskOdenenRecord kioskOdenenRecord = new KioskOdenenRecord(responseIslemTarihi, responseKioskKodu, responseKurumAdi, Double.toString(responseToplamTutar));
                    kioskOdenenRecord.setId(k.getId());
                    recordList.add(kioskOdenenRecord);
                }
            } else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        data = FXCollections.observableArrayList(recordList);

        islemTarihiColumn.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("islemTarihi")
        );
        kioskKoduColumn.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("kioskKodu")
        );
        kurumAdi.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("kurumAdi")
        );

        toplamTutar.setCellValueFactory(
                new PropertyValueFactory<KioskOdenenRecord, String>("toplamTutar")
        );
        kioskTable.setItems(data);
    }

    @FXML
    private void handleClickTableView(MouseEvent click) throws IOException {

        kioskOdenenParaService = new KioskOdenenParaService();
        kioskAlinanParaService = new KioskAlinanParaService();

        if (click.getClickCount() == 2) {
            KioskOdenenRecord kioskOdenenRecord = (KioskOdenenRecord) kioskTable.getSelectionModel().getSelectedItem();
            if (kioskOdenenRecord != null) {
                String id = String.valueOf(kioskOdenenRecord.getId());
                List<KioskAlinanParaKasasiResponse> kioskAlinanParaKasasiResponseList = kioskAlinanParaService.getDataByOdenenId(id);
                try {
                    //Load second scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/KioskOdenenFormDetail.fxml"));
                    Parent root = loader.load();
                    KioskOdenenFormDetail kioskOdenenFormDetail = loader.getController();
                    Stage kioskOdenenFormDetailStage = new Stage();
                    kioskOdenenFormDetailStage.setScene(new Scene(root));
                    kioskOdenenFormDetailStage.setFullScreen(false);
                    kioskOdenenFormDetailStage.setMaximized(false);
                    kioskOdenenFormDetailStage.setTitle("Kiosk Rapor Uygulaması");
                    kioskOdenenFormDetailStage.setResizable(false);
                    kioskOdenenFormDetail.setKioskKasaVerilenResponseList(kioskAlinanParaKasasiResponseList);
                    kioskOdenenFormDetailStage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }


    @FXML
    public void exportAndSave() throws IOException, DocumentException {

        kioskOdenenParaService = new KioskOdenenParaService();
        kioskAlinanParaService = new KioskAlinanParaService();
        KioskOdenenRecord kioskOdenenRecord = (KioskOdenenRecord) kioskTable.getSelectionModel().getSelectedItem();
        String odenenID = "";
        String islemTarihi = "";

        List<KioskOdenenRecordDetail> kioskOdenenRecordDetails = new ArrayList<KioskOdenenRecordDetail>();
        if (kioskOdenenRecord != null) {

            odenenID = String.valueOf(kioskOdenenRecord.getId());
            islemTarihi = kioskOdenenRecord.getIslemTarihi();
            List<KioskAlinanParaKasasiResponse> kioskAlinanParaKasasiResponseList = kioskAlinanParaService.getDataByOdenenId(odenenID);
            for (KioskAlinanParaKasasiResponse kioskAlinanParaKasasiResponse : kioskAlinanParaKasasiResponseList) {

                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String alimZamaniFormatted = simpleDateFormat.format(kioskAlinanParaKasasiResponse.getAlimZamani());
                KioskOdenenRecordDetail kioskOdenenRecordDetail = new KioskOdenenRecordDetail(String.valueOf(kioskAlinanParaKasasiResponse.getAlimSirasi()), alimZamaniFormatted
                        , kioskAlinanParaKasasiResponse.getAlinanBanknot().toString(), kioskAlinanParaKasasiResponse.getKioskKodu(), kioskAlinanParaKasasiResponse.getSozlesmeKod());
                kioskOdenenRecordDetails.add(kioskOdenenRecordDetail);
            }
        }

        File selectedDirectory = directoryChooser.showDialog(new Stage());
        Font BOLD = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Document my_pdf_report = new Document();
        PdfWriter.getInstance(my_pdf_report, new FileOutputStream(selectedDirectory.getAbsolutePath() + "/" + odenenID + islemTarihi + ".pdf"));
        my_pdf_report.open();
        PdfPTable odenenDataDetail = new PdfPTable(4);

        PdfPHeaderCell islemTarihiHeaderCell = new PdfPHeaderCell();
        PdfPHeaderCell kioskKoduColumnHeaderCell = new PdfPHeaderCell();
        PdfPHeaderCell kurumAdiHeaderCell = new PdfPHeaderCell();
        PdfPHeaderCell toplamTutarHeaderCell = new PdfPHeaderCell();

        Phrase islemTarihiPhrase = new Phrase("Islem Tarihi", BOLD);
        islemTarihiHeaderCell.setPhrase(islemTarihiPhrase);

        Phrase kioskKoduPhrase = new Phrase("Kiosk Kodu", BOLD);
        kioskKoduColumnHeaderCell.setPhrase(kioskKoduPhrase);

        Phrase kurumAdiPhrase = new Phrase("Kurum Adi", BOLD);
        kurumAdiHeaderCell.setPhrase(kurumAdiPhrase);

        Phrase toplamTutarPhrase = new Phrase("Toplam Tutar", BOLD);
        toplamTutarHeaderCell.setPhrase(toplamTutarPhrase);

        odenenDataDetail.addCell(islemTarihiHeaderCell);
        odenenDataDetail.addCell(kioskKoduColumnHeaderCell);
        odenenDataDetail.addCell(kurumAdiHeaderCell);
        odenenDataDetail.addCell(toplamTutarHeaderCell);

        PdfPCell table_cell;

        String islemTarihiMaster = kioskOdenenRecord.getIslemTarihi();
        String kioskKoduMaster = kioskOdenenRecord.getKioskKodu();
        String kurumAdiMaster = kioskOdenenRecord.getKurumAdi();
        String toplamTutar = kioskOdenenRecord.getToplamTutar();

        table_cell = new PdfPCell(new Phrase(islemTarihiMaster));
        odenenDataDetail.addCell(table_cell);

        table_cell = new PdfPCell(new Phrase(kioskKoduMaster));
        odenenDataDetail.addCell(table_cell);

        table_cell = new PdfPCell(new Phrase(kurumAdiMaster));
        odenenDataDetail.addCell(table_cell);

        table_cell = new PdfPCell(new Phrase(toplamTutar));
        odenenDataDetail.addCell(table_cell);


        PdfPTable my_report_table_detail = new PdfPTable(4);

        PdfPHeaderCell alimSirasiHeaderCell = new PdfPHeaderCell();
        PdfPHeaderCell alimZamaniHeaderCell = new PdfPHeaderCell();
        PdfPHeaderCell banknotHeaderCell = new PdfPHeaderCell();
        PdfPHeaderCell sozlesmeKoduHeaderCell = new PdfPHeaderCell();

        Phrase alimSirasiPhrase = new Phrase("Alim Sirasi", BOLD);
        alimSirasiHeaderCell.setPhrase(alimSirasiPhrase);

        Phrase alimZamaniPhrase = new Phrase("Alim Zamani", BOLD);
        alimZamaniHeaderCell.setPhrase(alimZamaniPhrase);

        Phrase bankNotPhrase = new Phrase("Bank Not", BOLD);
        banknotHeaderCell.setPhrase(bankNotPhrase);


        Phrase sozlesmeKoduPhrase = new Phrase("Sozlesme Kodu", BOLD);
        sozlesmeKoduHeaderCell.setPhrase(sozlesmeKoduPhrase);

        my_report_table_detail.addCell(alimSirasiHeaderCell);
        my_report_table_detail.addCell(alimZamaniHeaderCell);
        my_report_table_detail.addCell(banknotHeaderCell);
        my_report_table_detail.addCell(sozlesmeKoduHeaderCell);


        Comparator<KioskOdenenRecordDetail> compareByAlimSirasi = (KioskOdenenRecordDetail k1, KioskOdenenRecordDetail k2) -> Integer.valueOf(k1.getAlimSirasi()).compareTo(Integer.valueOf(k2.getAlimSirasi()));

        Collections.sort(kioskOdenenRecordDetails, compareByAlimSirasi);

        PdfPCell table_cell_detail;
        for (KioskOdenenRecordDetail kioskOdenenRecordDetail : kioskOdenenRecordDetails) {

            String alimSirasi = kioskOdenenRecordDetail.getAlimSirasi();
            String alimZamani = kioskOdenenRecordDetail.getAlimZamani();
            String bankNot = kioskOdenenRecordDetail.getBanknot();
            String sozlesmeKodu = kioskOdenenRecordDetail.getSozlesmeKodu();

            table_cell_detail = new PdfPCell(new Phrase(alimSirasi));
            my_report_table_detail.addCell(table_cell_detail);

            table_cell_detail = new PdfPCell(new Phrase(alimZamani));
            my_report_table_detail.addCell(table_cell_detail);

            table_cell_detail = new PdfPCell(new Phrase(bankNot));
            my_report_table_detail.addCell(table_cell_detail);

            table_cell_detail = new PdfPCell(new Phrase(sozlesmeKodu));
            my_report_table_detail.addCell(table_cell_detail);
        }


        my_pdf_report.add(odenenDataDetail);
        my_pdf_report.add(my_report_table_detail);
        my_pdf_report.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bilgi");
        alert.setHeaderText(null);
        alert.setContentText("İşlem Başarılı");
        alert.showAndWait();
    }
}
