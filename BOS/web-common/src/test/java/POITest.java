import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class POITest {

    @Test
    public void testPOI() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("d:\\aaa.xls")));
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            String v1 = row.getCell(0).getStringCellValue();
            String v2 = row.getCell(1).getStringCellValue();
            String v3 = row.getCell(2).getStringCellValue();
            String v4 = row.getCell(3).getStringCellValue();
            String v5 = row.getCell(4).getStringCellValue();
            System.out.println(v1 + " " + v2 + " " + v3 + " " + v4 + " " + v5);
        }
    }
}
