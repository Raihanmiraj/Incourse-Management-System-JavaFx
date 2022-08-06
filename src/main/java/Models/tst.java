package Models;
//package com.example.demo1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class tst {
    @FXML
    private Label temp;

    @FXML
    private TextField textF;

    @FXML
    private Label time;

    @FXML
    void onClickHanldler(ActionEvent event) throws IOException, ParseException {
String x = textF.getText();
        getLatestCurrency(x);
    }
    public String temperature;

    public String dateF;

    String date;
    double result;
    public void getLatestCurrency(String city) throws IOException, ParseException {



        try {
            String GET_URL = "http://api.weatherstack.com/forecast?access_key=6a6eab5fb486d63637b85c11178fe3a1&query="+city;

            URL url = new URL(GET_URL);
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            int responseCode = httpsURLConnection.getResponseCode();

            String inline = "";
            if(responseCode != 200)
                throw new RuntimeException("HttpResponseCode: " +responseCode);
            else
            {
                Scanner sc = new Scanner(url.openStream());

                while(sc.hasNext())
                {
                    inline += sc.nextLine();
                }
                System.out.println("\nJSON data in string format");

                sc.close();
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = new JSONObject(inline);

            result = Double.parseDouble(obj.getJSONObject("current").getString("temperature"));

            date = obj.getJSONObject("location").getString("localtime");


            temperature = String.valueOf(result);
            dateF =  date;
temp.setText(temperature);
time.setText(dateF);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }





}