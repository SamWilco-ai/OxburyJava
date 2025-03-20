package com.wilcock.samuel.oxbury.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.wilcock.samuel.oxbury.dao.transactionsDAO;
import com.wilcock.samuel.oxbury.model.dataModel;
import com.wilcock.samuel.oxbury.model.responseModel;
import com.wilcock.samuel.oxbury.model.totalQuantModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.wilcock.samuel.oxbury.util.helper.convertToDate;

@RestController
@RequestMapping(path = "oxbury")
public class RestfulController {

    transactionsDAO dao = new transactionsDAO();

    responseModel response = null;

    @GetMapping
    public ResponseEntity<String> gettingData() throws JsonProcessingException {
        dao = new transactionsDAO();
        ArrayList<dataModel> allTransactions = dao.getAllTransactions();
        String json = new Gson().toJson(allTransactions);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<String> gettingDataBySpecifics(@RequestParam("manufacturer") String manufacturer,
                                                         @RequestParam("retailer") String retailer,
                                                         @RequestParam("productCode") String[] productCode,
                                                         @RequestParam("fromDate")  String fromDate,
                                                         @RequestParam("toDate") String toDate) throws JsonProcessingException {
        dao = new transactionsDAO();

        ArrayList<totalQuantModel> allTransactions = null;
        allTransactions = dao.getByDetails(manufacturer, retailer, productCode, fromDate, toDate);
        String json = new Gson().toJson(allTransactions);
        return new ResponseEntity<>(json, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> insertingData(@RequestParam("manufacturer") String manufacturer,
                                                @RequestParam("retailer") String retailer,
                                                @RequestParam("productCode") String productCode,
                                                @RequestParam("transactionID") String transactionID,
                                                @RequestParam("quantity") float quantity,
                                                @RequestParam("value") float value) throws SQLException {

        dataModel insertTransaction = new dataModel(manufacturer, retailer, productCode, transactionID, null, quantity, value);
        if (dao.insertTransactionDetails(insertTransaction) == 1) {
            response = new responseModel(HttpStatus.OK, "data successfully added to db");
            String json = new Gson().toJson(response);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }

        response = new responseModel(HttpStatus.INTERNAL_SERVER_ERROR, "data unsuccessfully added to db");
        String json = new Gson().toJson(response);
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public ResponseEntity<String> deletingData(@RequestParam(value = "adminToken", required = false) String adminToken,
                                               @RequestParam("manufacturer") String manufacturer,
                                               @RequestParam("retailer") String retailer,
                                               @RequestParam("productCode") String productCode,
                                               @RequestParam("transactionID") String transactionID) throws SQLException {
        if (adminToken == null){
            response = new responseModel(HttpStatus.INTERNAL_SERVER_ERROR, "no auth token");
            String json = new Gson().toJson(response);
            return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        dataModel deleteTransaction = new dataModel(manufacturer, retailer, productCode, transactionID, null, 0, 0);
        if (dao.deleteTransactionDetails(deleteTransaction) == 1) {
            response = new responseModel(HttpStatus.OK, "data successfully removed db");
            String json = new Gson().toJson(response);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }

        response = new responseModel(HttpStatus.INTERNAL_SERVER_ERROR, "data unsuccessfully removed from db");
        String json = new Gson().toJson(response);
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);    }


}
