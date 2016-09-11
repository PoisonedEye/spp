package services;

import static org.junit.Assert.*;

public class ParsingServiceTest {

    @org.junit.Test
    public void testGetString_stringSizeEqualToMaxSize_noException() throws Exception {
        ParsingService.getString("validString","",11);
    }

    @org.junit.Test
    public void testGetString_stringSizeSmallerThenMaxSize_noException() throws Exception {
        ParsingService.getString("validString","",15);
    }

    @org.junit.Test
    public void testGetString_invalidString_ClassCastException() throws Exception {
        try{
            ParsingService.getString("invalidString","",11);
            fail();
        }
        catch (ClassCastException ex)
        {

        }
    }

    @org.junit.Test
    public void testGetString_emptyString_ClassCastException() throws Exception {
        try{
            ParsingService.getString("","",11);
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetTime() throws Exception {

    }

    @org.junit.Test
    public void testGetLastTime() throws Exception {

    }

    @org.junit.Test
    public void testGetForeign() throws Exception {

    }

    @org.junit.Test
    public void testGetById() throws Exception {

    }

    @org.junit.Test
    public void testGetEmployeeTin() throws Exception {

    }

    @org.junit.Test
    public void testGetEmployeeFullName() throws Exception {

    }

    @org.junit.Test
    public void testGetEmployeeTakingOffice() throws Exception {

    }

    @org.junit.Test
    public void testGetEmployeePhoneNumber() throws Exception {

    }

    @org.junit.Test
    public void testGetEmployeeLogin() throws Exception {

    }

    @org.junit.Test
    public void testGetPositionName() throws Exception {

    }

    @org.junit.Test
    public void testGetAddressFlat() throws Exception {

    }

    @org.junit.Test
    public void testGetCellType() throws Exception {

    }

    @org.junit.Test
    public void testGetCellNumber() throws Exception {

    }

    @org.junit.Test
    public void testGetCoordinate() throws Exception {

    }

    @org.junit.Test
    public void testGetValue() throws Exception {

    }

    @org.junit.Test
    public void testGetCellVisitingProduct() throws Exception {

    }

    @org.junit.Test
    public void testGetCellVisitingShift() throws Exception {

    }

    @org.junit.Test
    public void testGetBarcode() throws Exception {

    }
}