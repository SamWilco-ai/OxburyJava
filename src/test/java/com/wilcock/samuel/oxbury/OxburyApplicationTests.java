package com.wilcock.samuel.oxbury;

import com.wilcock.samuel.oxbury.dao.transactionsDAO;
import com.wilcock.samuel.oxbury.controller.RestfulController;

import com.wilcock.samuel.oxbury.model.dataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class OxburyApplicationTests {

    @Mock
    private transactionsDAO dao;

    @InjectMocks
    private RestfulController controller;

    @Test
    void testGettingData() throws Exception {
        // Mock data
        ArrayList<dataModel> mockTransactions = new ArrayList<>();
        mockTransactions.add(new dataModel("Coca Cola", "Tesco", "A1235", "123e4567-e89b-12d3-a456-426614174000", new java.sql.Date(System.currentTimeMillis()), 123, 4445));
        when(dao.getAllTransactions()).thenReturn(mockTransactions);

        // Call method
        ResponseEntity<String> response = controller.gettingData();

        assertEquals(200, response.getStatusCodeValue()); // HTTP 200 OK
    }

    @Test
    void testSearching() throws Exception {
        // Mock data
        ArrayList<dataModel> mockTransactions = new ArrayList<>();
        mockTransactions.add(new dataModel("Coca Cola", "Tesco", "A1235", "123e4567-e89b-12d3-a456-426614174000", new java.sql.Date(System.currentTimeMillis()), 123, 4445));
        when(dao.getAllTransactions()).thenReturn(mockTransactions);

        String[] codes = {"B2346","B2345","A1235","D1235"};
        // Call method
        ResponseEntity<String> response = controller.gettingDataBySpecifics("Coca Cola", "Tesco",codes, "2025-01-01", "2026-01-01");

        assertEquals(200, response.getStatusCodeValue()); // HTTP 200 OK
    }

    @Test
    void testDeleteFailure() throws Exception {


        ResponseEntity<String> response = controller.deletingData(null, null, null, null, null);

        assertEquals(500, response.getStatusCodeValue());
    }

    @Test
    void testInsertFailure() throws Exception {

        ResponseEntity<String> response = controller.insertingData("Coca Cola", "Tesco", "A1235", "123e4567-e89b-12d3-a456-426614174000", 123, 4445);

        assertEquals(500, response.getStatusCodeValue());
    }

}
