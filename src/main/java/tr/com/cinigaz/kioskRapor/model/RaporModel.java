//package tr.com.cinigaz.kioskRapor.model;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//import javafx.stage.Stage;
//import tr.com.cinigaz.kioskRapor.dto.KioskKasaVerilenResponse;
//import tr.com.cinigaz.kioskRapor.entity.KasaVerilenDetay;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class RaporModel extends Stage {
//
//
//    public TableView setTableViev(List<KasaVerilenDetay> list) {
//        TableView s = new TableView();
//        s.setPrefHeight(744);
//        s.setPrefWidth(1000);
//        s.setEditable(false);
//        TableColumn<Long, KioskKasaVerilenResponse> alinanIdColumn = new TableColumn<>("Tahsil ID");
//        alinanIdColumn.setCellValueFactory(new PropertyValueFactory<>("alinanId"));
//        TableColumn<Date, KioskKasaVerilenResponse> alinmaTarihi = new TableColumn<>("Alınma Tarihi");
//        alinmaTarihi.setCellValueFactory(new PropertyValueFactory<>("alimTarihi"));
//        TableColumn<Long, KioskKasaVerilenResponse> alinanBanknot = new TableColumn<>("Alınan Tutar");
//        alinanBanknot.setCellValueFactory(new PropertyValueFactory<>("alinanBanknot"));
//        alinanBanknot.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.RIGHT));
//        TableColumn<Boolean, KioskKasaVerilenResponse> verildiMi = new TableColumn<>("");
//        TableColumn<String, KioskKasaVerilenResponse> test = new TableColumn<>("Test");
//        test.setCellValueFactory(new PropertyValueFactory<>("test"));
//        test.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.LEFT));
//        List<KioskKasaVerilenResponse> l = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            KioskKasaVerilenResponse y = new KioskKasaVerilenResponse();
//            y.setAlinanId(Long.valueOf(String.valueOf(i * 1000)));
//            y.setAlinanBanknot(Long.valueOf(String.valueOf(10 * i)));
//            y.setTest("ceylan");
//            l.add(y);
//        }
//        s.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        s.getItems().addAll(l);
//        s.getColumns().addAll(alinanIdColumn, test, alinanBanknot);
//        return s;
//
//    }
//
//    public void anaEkranCiz() {
//        // Header Box çiziliyor.
//        TableView q = setTableViev(null);
//        Label tarihLbl = new Label("Tarih : ");
//        DatePicker tarihEd = new DatePicker();
//        tarihEd.setPrefWidth(120);
//        Label kioskIdLbl = new Label("    Kiosk No : ");
//        TextField kioskIdEd = new TextField();
//        Text test = new Text();
//        test.setFill(Color.FIREBRICK);
//        Button tmmBtn = new Button(" Verileri Getir ");
//        HBox buttonHd = new HBox();
//        Label boslukLbl = new Label("                ");
//        buttonHd.setAlignment(Pos.CENTER_RIGHT);
//        buttonHd.getChildren().addAll(test, boslukLbl, tmmBtn);
//        tmmBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                q.getItems().clear();
//                List<KioskKasaVerilenResponse> l = new ArrayList<>();
//                for (int i = 1; i <= 10; i++) {
//                    KioskKasaVerilenResponse y = new KioskKasaVerilenResponse();
//                    y.setAlinanId(Long.valueOf(String.valueOf(i * 1000)));
//                    y.setAlinanBanknot(Long.valueOf(String.valueOf(10 * i)));
//                    y.setTest("İbrahim CEYLAN");
//                    l.add(y);
//                }
//                q.getItems().addAll(l);
//            }
//        });
//        HBox headerHb = new HBox();
//        headerHb.setAlignment(Pos.CENTER_LEFT);
//        headerHb.getChildren().addAll(tarihLbl, tarihEd, kioskIdLbl, kioskIdEd, buttonHd);
//
//        setWidth(1024);
//        setHeight(768);
//
//        //p.getChildren().addAll(headerHb, );
//        Label toplamLbl = new Label("Toplam : ");
//        Label toplamEd = new Label("12,500.00 TL");
//        HBox footerHb = new HBox();
//        footerHb.getChildren().addAll(toplamLbl, toplamEd);
//        VBox vbox = new VBox(10);
//        vbox.setPadding(new Insets(25, 25, 25, 25));
//        ;
//        vbox.getChildren().addAll(headerHb, q, footerHb); //, buttonHb, actionStatus);
//        Scene scn = new Scene(vbox);
//        setScene(scn);
//        show();
//    }
//}
