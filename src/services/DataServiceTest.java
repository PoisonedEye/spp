package services;

import entities.AcceptorShift;
import entities.Transfer;
import jdk.nashorn.internal.ir.LexicalContextNode;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import stubs.SessionStub;
import util.HibernateUtil;

import java.util.List;

import static org.junit.Assert.*;


public class DataServiceTest {

    @Test
    public void testGetData_nullClass_emptyList() throws Exception {
        //Arrange
        DataService ds = new DataService();

        // Act
        List result =  ds.getData(null);

        // Assert
        if (result.size() != 0)
            fail();
    }

    @Test
    public void testGetData_classOfExistingValue_NotEmptyList() throws Exception {
        //Arrange
        DataService ds = new DataService();
        HibernateUtil.testing = true;

        // Act
        List result =  ds.getData(AcceptorShift.class);

        // Assert
        if (result.size() == 0)
            fail();
    }

    @Test
    public void testGetData_classOfNotExistingValue_EmptyList() throws Exception {
        //Arrange
        DataService ds = new DataService();
        HibernateUtil.testing = true;

        // Act
        List result =  ds.getData(Transfer.class);

        // Assert
        if (result.size() != 0)
            fail();
    }

    @Test
    public void testGetById_ExistingValue_Value() throws Exception {
        //Arrange
        SessionStub stub = new SessionStub();

        // Act
        Object result =  DataService.getById(AcceptorShift.class,1,stub);

        // Assert
        if (result == null)
            fail();
    }

    @Test
    public void testGetById_NotExistingValue_Null() throws Exception {
        //Arrange
        SessionStub stub = new SessionStub();

        // Act
        Object result = DataService.getById(Transfer.class,1,stub);

        // Assert
        if (result != null)
            fail();
    }

    @Test
    public void testGetById_nullClass_Null() throws Exception {
        //Arrange
        SessionStub stub = new SessionStub();

        // Act
        Object result = DataService.getById(null,1,stub);

        // Assert
        if (result != null)
            fail();
    }

    @Test
    public void testGetById_nullSession_Null() throws Exception {
        //Arrange is skipped

        // Act
        Object result =  DataService.getById(Transfer.class,1,null);

        // Assert
        if (result != null)
            fail();
    }
}