package edu.escuelaing.arem.ASE.app;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class MongoDatabaseOperationsTest {

    private MongoDatabaseOperations mongoOperations;

    @Before
    public void setUp() {
        mongoOperations = Mockito.spy(new MongoDatabaseOperations());
    }

    /**
     * Probar el funcionamiento del mètodo getLogs de MongoDatabaseOperations
     */
    @Test
    public void testGetLogs() {
        List<Document> logs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            logs.add(new Document("log", "Log " + i));
        }

        Mockito.doReturn(logs).when(mongoOperations).getLogs();

        List<Document> resultLogs = mongoOperations.getLogs();
        assertEquals(10, resultLogs.size());
    }


}
