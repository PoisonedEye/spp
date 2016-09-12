package services;

import entities.CellType;
import stubs.SessionStub;

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
        catch (ClassCastException ex){}
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
    public void testGetTime_noException() throws Exception {
        ParsingService.getTime("2016-11-09 05:14:01");
    }

    @org.junit.Test
    public void testGetTime_invalidTime() throws Exception {
      try {
          ParsingService.getTime("11-09-2016 05:14:01");
          fail();
      }
      catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetTime_EmptyTime() throws Exception {
        try {
            ParsingService.getTime("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetTime_NotNumberTime() throws Exception {
        try {
            ParsingService.getTime("abcd");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetLastTime_invalidTime() throws Exception {
        try{
            ParsingService.getLastTime("2016-04-15 11:40:08", "2016-04-17 11:30:08", "");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetLastTime_noException() throws Exception {
            ParsingService.getLastTime("2016-04-15 11:40:08", "2016-04-12 11:30:08", "");
        }

    @org.junit.Test
    public void testGetLastTime_SameTime_noException() throws Exception {
        ParsingService.getLastTime("2016-04-15 11:40:08", "2016-04-15 11:40:08", "");
    }

    @org.junit.Test
    public void testGetLastTime_EmptyLastTime() throws Exception {
        try{
            ParsingService.getLastTime("", "2016-04-17 11:30:08", "");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetLastTime_EmptyFirstTime() throws Exception {
        try{
            ParsingService.getLastTime("2016-04-17 11:30:08", "", "");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetLastTime_NotNumberLastTime() throws Exception {
        try{
            ParsingService.getLastTime("abcd", "2016-04-17 11:30:08", "");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetLastTime_NotNumberFirstTime() throws Exception {
        try{
            ParsingService.getLastTime("2016-04-17 11:30:08", "abcd", "");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetForeign_noException() throws Exception {
        ParsingService.getForeign("123",new SessionStub(),"", CellType.class);
    }

    @org.junit.Test
    public void testGetForeign_EmptyForeign_Exception() throws Exception {
        try {
            ParsingService.getForeign("", new SessionStub(), "", CellType.class);
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetForeign_NotNumber_Exception() throws Exception {
        try {
            ParsingService.getForeign("abc", new SessionStub(), "", CellType.class);
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetForeign_NotPositiveNumber_Exception() throws Exception {
        try {
            ParsingService.getForeign("-12", new SessionStub(), "", CellType.class);
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetById_noException() throws Exception {
        ParsingService.getById("123",new SessionStub(), CellType.class);
    }


    @org.junit.Test
    public void testGetById_NotNumber_Exception() throws Exception {
        try {
            ParsingService.getById("abc", new SessionStub(), CellType.class);
        }
        catch (ClassCastException ex){}
    }


    @org.junit.Test
    public void testGetById_EmptyNumber_Exception() throws Exception {
        try {
            ParsingService.getById("", new SessionStub(), CellType.class);
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetById_NotPositiveNumber_Exception() throws Exception {
        try {
            ParsingService.getById("-12", new SessionStub(), CellType.class);
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeTin_noException() throws Exception {
        ParsingService.getEmployeeTin("123456789101");
    }

    @org.junit.Test
    public void testGetEmployeeTin_Empty_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTin("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeTin_NOTaNumber_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTin("abcdefghiqw");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeTin_NotPositiveNumber_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTin("-78");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeTin_SizeSmallerTin_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTin("12345");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeTin_BigSizeTin_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTin("12345678910123");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeFullName_noException() throws Exception {
        ParsingService.getEmployeeFullName("Berrington");
    }

    @org.junit.Test
    public void testGetEmployeeFullName_BigSizeFullName_Exception() throws Exception {
        try {
            ParsingService.getEmployeeFullName("BerringtonBerringtonBerringtonBerringtonBerringtonBerringtonBerringtonBerrington");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeFullName_EmptyFullName_Exception() throws Exception {
        try {
            ParsingService.getEmployeeFullName("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeTakingOffice_noException() throws Exception {
        ParsingService.getEmployeeTakingOffice("2016-09-13");
    }

    @org.junit.Test
    public void testGetEmployeeTakingOffice_InvalidEmployeeTakingOffice_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTakingOffice("2016.09013");
            fail();
        }
        catch (ClassCastException ex) {}
    }

    @org.junit.Test
    public void testGetEmployeeTakingOffice_NotNumberEmployeeTakingOffice_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTakingOffice("abcd");
            fail();
        }
        catch (ClassCastException ex) {}
    }

    @org.junit.Test
    public void testGetEmployeeTakingOffice_EmptyEmployeeTakingOffice_Exception() throws Exception {
        try {
            ParsingService.getEmployeeTakingOffice("");
            fail();
        }
        catch (ClassCastException ex) {}
    }

    @org.junit.Test
    public void testGetEmployeePhoneNumber_noException() throws Exception {
        ParsingService.getEmployeePhoneNumber("5035309");
    }

    @org.junit.Test
    public void testGetEmployeePhoneNumber_BigPhoneNumber_Exception() throws Exception {
        try {
            ParsingService.getEmployeePhoneNumber("12345678910111213141516");
            fail();
        }
        catch (ClassCastException ex){}
    }


    @org.junit.Test
    public void testGetEmployeePhoneNumber_EmptyPhoneNumber_Exception() throws Exception {
        try {
            ParsingService.getEmployeePhoneNumber("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetEmployeeLogin_noException() throws Exception {
        ParsingService.getEmployeeLogin("sdf",new SessionStub());
    }

    @org.junit.Test
    public void testGetEmployeeLogin_EmptyEmployeeLogin_Exception() throws Exception {
        try {
            ParsingService.getEmployeeLogin("", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }


    @org.junit.Test
    public void testGetEmployeeLogin_BigEmployeeLogin_Exception() throws Exception {
        try {
            ParsingService.getEmployeeLogin("sdfghjkhgdsdghjfdghhfdghjfdghjkjfdhfdjuhgfghgfdghfdfghgfdfghgfdghygfhygfg", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetPositionName_noException() throws Exception {
        ParsingService.getPositionName("Name", new SessionStub());
    }

    @org.junit.Test
    public void testGetPositionName_EmptyPositionName_Exception() throws Exception {
        try {
            ParsingService.getPositionName("", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetPositionName_BigPositionName_Exception() throws Exception {
        try {
            ParsingService.getPositionName("qwertyuiopqwertyuiopqwertyuioqwer", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetAddressFlat_noException() throws Exception {
        ParsingService.getAddressFlat("21");
    }

    @org.junit.Test
    public void testGetAddressFlat_emptyException() throws Exception {
        try {
            ParsingService.getAddressFlat("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetAddressFlat_NotPositiveNumber_Exception() throws Exception {
        try {
            ParsingService.getAddressFlat("-10");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetAddressFlat_NOTaNumber_Exception() throws Exception {
        try {
            ParsingService.getAddressFlat("cat");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellType_noException() throws Exception {
        ParsingService.getCellType("123", new SessionStub());
    }

    @org.junit.Test
    public void testGetCellType_NotPositiveCellType_Exception() throws Exception {
        try {
            ParsingService.getCellType("-13", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellType_EmptyCellType_Exception() throws Exception {
        try {
            ParsingService.getCellType("", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellNumber_noException() throws Exception {
        ParsingService.getCellNumber("1-B-6");
    }

    @org.junit.Test
    public void testGetCellNumber_EmptyCellNumber_Exception() throws Exception {
        try{
            ParsingService.getCellNumber("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellNumber_BigLength_Exception() throws Exception {
        try{
            ParsingService.getCellNumber("1-B-");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellNumber_PartsLength_Exception() throws Exception {
        try{
            ParsingService.getCellNumber("1-B-12-13-0");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellNumber_NotPositiveCellNumber_Exception() throws Exception {
        try{
            ParsingService.getCellNumber("0-B-0");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellNumber_NotOneLetter_Exception() throws Exception {
        try{
            ParsingService.getCellNumber("1-AB-13");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellNumber_NotUpperCaseLetter_Exception() throws Exception {
        try{
            ParsingService.getCellNumber("1-g-12");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCoordinate_noException() throws Exception {
        ParsingService.getCoordinate("999");
    }

    @org.junit.Test
    public void testGetCoordinate_BigCoordinate_Exception() throws Exception {
        try {
            ParsingService.getCoordinate("1999");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCoordinate_SmallCoordinate_Exception() throws Exception {
        try {
            ParsingService.getCoordinate("-1999");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCoordinate_EmptyCoordinate_Exception() throws Exception {
        try {
            ParsingService.getCoordinate("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCoordinate_NotNumberCoordinate_Exception() throws Exception {
        try {
            ParsingService.getCoordinate("asdfg");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetValue_noException() throws Exception {
        ParsingService.getValue("2");
    }

    @org.junit.Test
    public void testGetValue_SmallGetValue_Exception() throws Exception {
        try {
            ParsingService.getValue("-2");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetValue_EmptyGetValue_Exception() throws Exception {
        try {
            ParsingService.getValue("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetValue_NullGetValue_Exception() throws Exception {
        try {
            ParsingService.getValue("0");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetValue_NotNumberGetValue_Exception() throws Exception {
        try {
            ParsingService.getValue("gvjdhfg");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellVisitingProduct_noException() throws Exception {
        ParsingService.getCellVisitingProduct("12", new SessionStub());
    }

    @org.junit.Test
    public void testGetCellVisitingProduct_EmptyException() throws Exception {
        try {
            ParsingService.getCellVisitingProduct("", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }


    @org.junit.Test
    public void testGetCellVisitingProduct_NotNumber_Exception() throws Exception {
        try {
            ParsingService.getCellVisitingProduct("vdfb", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellVisitingShift_noException() throws Exception {
        ParsingService.getCellVisitingShift("12", new SessionStub());
    }

    @org.junit.Test
    public void testGetCellVisitingShift_NotNumber_Exception() throws Exception {
        try {
            ParsingService.getCellVisitingShift("abc", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetCellVisitingShift_Empty_Exception() throws Exception {
        try {
            ParsingService.getCellVisitingShift("", new SessionStub());
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetBarcode_noException() throws Exception {
        ParsingService.getBarcode("99999");
    }

    @org.junit.Test
    public void testGetBarcode_SmallBarcodeNumber_Exception() throws Exception {
        try {
            ParsingService.getBarcode("-9");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetBarcode_EmptyBarcodeNumber_Exception() throws Exception {
        try {
            ParsingService.getBarcode("");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetBarcode_NOTaNumberBarcodeNumber_Exception() throws Exception {
        try {
            ParsingService.getBarcode("abc");
            fail();
        }
        catch (ClassCastException ex){}
    }

    @org.junit.Test
    public void testGetBarcode_NullBarcodeNumber_Exception() throws Exception {
        try {
            ParsingService.getBarcode("0");
            fail();
        }
        catch (ClassCastException ex){}
    }
}