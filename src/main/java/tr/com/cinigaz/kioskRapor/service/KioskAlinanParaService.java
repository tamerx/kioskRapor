package tr.com.cinigaz.kioskRapor.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.kioskRapor.dto.KioskAlinanParaKasasiResponse;
import tr.com.cinigaz.kioskRapor.dto.KioskKasaOdenenDto;
import tr.com.cinigaz.kioskRapor.dto.KioskKasaOdenenResponse;
import tr.com.cinigaz.kioskRapor.entity.KioskAlinanParaKasasiEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
public class KioskAlinanParaService {

    private final String URL = "http://localhost:9510/kiosk/kasa/value/";


    public List<KioskAlinanParaKasasiResponse> getData(String kioskID, Date kasaTarihi) throws IOException {
        URL url = new URL("http://localhost:9510/kiosk/kasa/value/" + kioskID + "/" + kasaTarihi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        int responseCode = con.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            String response = new String();
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();

            final ObjectMapper objectMapper = new ObjectMapper();

            try {
                List<KioskAlinanParaKasasiResponse> kioskAlinanParaKasasiResponseList = objectMapper.readValue(response, new TypeReference<List<KioskAlinanParaKasasiResponse>>() {
                });
                return kioskAlinanParaKasasiResponseList;
            } catch (JsonMappingException e) {

                e.printStackTrace();

            } catch (JsonProcessingException e) {

                e.printStackTrace();
            }
        } else {
        }

        return null;
    }

    public KioskKasaOdenenResponse odenenParaKasasiKaydet(KioskKasaOdenenDto request) {


        String response = "";
        String output = null;


        try {
            URL url = new URL("http://localhost:9510/kiosk/verilen/master/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoOutput(true);
            String jSonString = JSON.toJSONString(request);
            OutputStream os = conn.getOutputStream();
            os.write(jSonString.getBytes());
            os.flush();
            Integer responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + responseCode);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            while ((output = br.readLine()) != null) {
                response = response + output;
            }

            final ObjectMapper objectMapper = new ObjectMapper();

            try {

                KioskKasaOdenenResponse kioskKasaOdenenResponse = objectMapper.readValue(response, KioskKasaOdenenResponse.class);
                return kioskKasaOdenenResponse;
            } catch (JsonMappingException e) {

                e.printStackTrace();

            } catch (JsonProcessingException e) {

                e.printStackTrace();
            }


            return null;
        } catch (
                ProtocolException e) {

            e.printStackTrace();
            return null;
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }


    public void alinanParaKasasiKaydet(Long odenenId, List<KioskAlinanParaKasasiEntity> lst) throws IOException {


        String response = "";
        String output = null;
        try {

            URL url = new URL("http://localhost:9510/kiosk/verilen/detail/" + odenenId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoOutput(true);
            String jSonString = JSON.toJSONString(lst);
            OutputStream os = conn.getOutputStream();
            os.write(jSonString.getBytes());
            os.flush();
            Integer responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + responseCode);
            }


        } catch (
                ProtocolException e) {

            e.printStackTrace();

        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();

        }


    }


    public List<KioskAlinanParaKasasiResponse> getDataByOdenenId(String odenenId) throws IOException {
        URL url = new URL("http://localhost:9510/kiosk/kasa/getDataByOdenenId/" + odenenId);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        int responseCode = con.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            String response = new String();
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();

            final ObjectMapper objectMapper = new ObjectMapper();

            try {

                List<KioskAlinanParaKasasiResponse> kioskAlinanParaKasasiResponseList = objectMapper.readValue(response, new TypeReference<List<KioskAlinanParaKasasiResponse>>() {
                });
                return kioskAlinanParaKasasiResponseList;
            } catch (JsonMappingException e) {

                e.printStackTrace();

            } catch (JsonProcessingException e) {

                e.printStackTrace();
            }
        } else {
        }
        return null;
    }


}