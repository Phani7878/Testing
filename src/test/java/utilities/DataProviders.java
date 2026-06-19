package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public String [][] getData() throws IOException{
        String path = "";
        ExcelUtility xlutil = new ExcelUtility(path);
        int tr = xlutil.getRowCount("sheet1");
        int tc = xlutil.getCellCount("sheet1",2);
        String [][] data = new String [tr][tc];
        for(int i=1;i<tr;i++){
            for(int j=0;j<tc;j++){
                data[i-1][j] = xlutil.getCellData("sheet1",i,j);
            }
        }
        return data;

    }
}
