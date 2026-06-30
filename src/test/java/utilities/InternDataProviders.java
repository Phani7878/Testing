package utilities;

import lombok.experimental.StandardException;
import org.testng.annotations.DataProvider;
import utilities.ExcelUtility;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InternDataProviders {

    @DataProvider(name = "validInternLoginData")
    public static Object[][] validInternLoginData() throws IOException {
        String sheetname = "ValidCredentials";
        ExcelUtility util = new ExcelUtility("testData/intern-login-credentials-data.xlsx");
        int rows = util.getRowCount(sheetname);

        String[][] data = new String[rows][2];
        for (int i = 1; i <= rows; i++) {
            String username = util.getCellData(sheetname, i, 0);
            String password = util.getCellData(sheetname, i, 1);
            data[i - 1][0] = username;
            data[i - 1][1] = password;
        }
        return data;
//        Object[][] data = {
//                {"2494588", "123456"},
//                {"2494393", "123456"},
//                {"2494399", "123456"},
//                {"2494401", "123456"},
//                {"2494430", "Hello1"}
//
//        };
//        return data;
    }

    @DataProvider(name = "invalidInternLoginData")
    public static Object[][] invalidInternLoginData() throws IOException {
        String sheetname = "InvalidCredentials";
        ExcelUtility util = new ExcelUtility("testData/intern-login-credentials-data.xlsx");
        int rows = util.getRowCount(sheetname);

        String[][] data = new String[rows][2];
        for (int i = 1; i <= rows; i++) {
            String username = util.getCellData(sheetname, i, 0);
            String password = util.getCellData(sheetname, i, 1);
            data[i - 1][0] = username;
            data[i - 1][1] = password;
        }
        return data;
    }

    @DataProvider(name = "firstTimePunchInValidTokenData")
    public static Object[][] firstTimePunchInValidTokenData() throws IOException {
        String sheetname = "FirstTimeValidPunchIn";
        ExcelUtility util = new ExcelUtility("testData/intern-login-credentials-data.xlsx");
        int rows = util.getRowCount(sheetname);

        String[][] data = new String[rows][2];
        for (int i = 1; i <= rows; i++) {
            String username = util.getCellData(sheetname, i, 0);
            String password = util.getCellData(sheetname, i, 1);
            data[i - 1][0] = username;
            data[i - 1][1] = password;
        }
        return data;
    }
    @DataProvider(name = "firstTimePunchInInValidTokenData")
    public static Object[][] firstTimePunchInInValidTokenData() throws IOException {
        String sheetname = "FirstTimeInValidPunchIn";
        ExcelUtility util = new ExcelUtility("testData/intern-login-credentials-data.xlsx");
        int rows = util.getRowCount(sheetname);

        String[][] data = new String[rows][2];
        for (int i = 1; i <= rows; i++) {
            String username = util.getCellData(sheetname, i, 0);
            String password = util.getCellData(sheetname, i, 1);
            data[i - 1][0] = username;
            data[i - 1][1] = password;
        }
        return data;
    }

    @DataProvider(name = "validLeaveDateProvider")
    public static Object[][] validLeaveDateProvider() {
        int count = 5;
        int maxDaysAhead = 90;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        Random rnd = new Random();

        Set<LocalDate> set = new HashSet<>();
        while (set.size() < count) {
            int addDays = 1 + rnd.nextInt(maxDaysAhead);
            set.add(today.plusDays(addDays));
        }

        // sort dates (optional, makes results stable ascending)
        List<LocalDate> dates = new ArrayList<>(set);
        Collections.sort(dates);

        Object[][] data = new Object[dates.size()][3];
        for (int i = 0; i < dates.size(); i++) {
            String dateStr = dates.get(i).format(fmt);
            data[i][0] = dateStr;
            data[i][1] = "TestReason";
            data[i][2] = "TestDescription";
        }
        return data;
    }

    @DataProvider(name = "randomPastLeaveDateProvider")
    public static Object[][] randomPastLeaveDateProvider() {
        int count = 5;            // number of past dates to generate
        int maxDaysBack = 365;    // how many days back from today to allow
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        Random rnd = new Random();

        Set<LocalDate> set = new HashSet<>();
        while (set.size() < count) {
            int backDays = 1 + rnd.nextInt(maxDaysBack); // 1..maxDaysBack
            set.add(today.minusDays(backDays));
        }

         List<LocalDate> dates = new ArrayList<>(set);
        Collections.sort(dates);

        Object[][] data = new Object[dates.size()][3];
        for (int i = 0; i < dates.size(); i++) {
            String dateStr = dates.get(i).format(fmt);
            data[i][0] = dateStr;
            data[i][1] = "Invalid Leave Request";
            data[i][2] = "This should not be created";
        }
        return data;
    }
}

