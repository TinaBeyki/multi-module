package ir.ansarit.common.util.export;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtil {

    /*
    a program uses outputStream in order to write data to a destination such as excel
    apache poi is an api which helps to create, modify and display MS file with java
    xssf -> excel spreadsheet format
    */
    public <T> void writeToExcel(String sheetName, List<String> fieldNameList, List<T> data, OutputStream outputStream) {
        OutputStream opt = null;
        XSSFWorkbook workbook = null;

        try{
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("worksheet");
            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            for(String fieldName: fieldNames) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldName);
            }

            Class<?> tClass = data.get(0).getClass();
            for (T t: data) {
                Row row1 = sheet.createRow(rowCount++);
                columnCount = 0;
                for(String field: fieldNames) {
                    Cell cell = row1.createCell(columnCount++);
                    Method method = null;
                    try{
                        method = tClass.getMethod("get" + capitalize(field));
                    }catch (Exception e) {
                        method = tClass.getMethod("get" + field);
                    }

                    assert method != null;
                    Object value = method.invoke(t, (Object[]) null);
                    if(value != null) {
                        if(value instanceof String) {
                            cell.setCellValue((String) value);
                        }else if(value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        }else if(value instanceof Long) {
                            cell.setCellValue((Long) value);
                        }else if(value instanceof Double) {
                            cell.setCellValue((Double) value);
                        }
                    }
                    columnCount++;
                }
                sheet.autoSizeColumn(5);
            }
            workbook.write(outputStream);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(opt != null)
                    opt.close();

            }catch (IOException e) {
                e.printStackTrace();
            }

            try{
                if(workbook != null) {
                    workbook.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String capitalize(String s) {
        if(s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    //this will return the header of the excel
    public static List<String> getFieldNamesForClass(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    public static <T> void writeToExcelWithMultiSheetsWithMulitHeaders(List<List<String>> firstSheetHeaders, Map<String, List<T>> sheets, OutputStream outputStream) {
        Iterator<String> sheetNames = sheets.keySet().iterator();
        XSSFWorkbook workbook = new XSSFWorkbook();
        int i = 0;
        if(sheetNames.hasNext()) {
            i++;
            String sheetName = sheetNames.next();
            List<T> data = sheets.get(sheetName);
        }
    }

    public static <T> Sheet createSheet(XSSFWorkbook workbook, List<String> fieldsNameList, String sheetName, List<T> data, boolean fillObject) throws Exception {
        try{
            Sheet sheet = null;
            sheet = workbook.createSheet(sheetName);
            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            if(fieldsNameList != null) {
                for (String fieldName : fieldsNameList) {
                    Cell cell = row.createCell(columnCount++);
                    cell.setCellValue(fieldName);
                }
            }
            if(data.size() != 0) {
                for(T t: data) {
                    List<String> fieldNames = getFieldNamesForClass(t.getClass());
                    Class<? extends Object> clazz = t.getClass();
                    row = sheet.createRow(rowCount++);
                    columnCount = 0;
                    for (String field: fieldNames) {
                        Cell cell = row.createCell(columnCount++);
                        Method method = null;
                        try {
                            method = clazz.getMethod("get" + capitalize(field));
                        } catch (NoSuchMethodException e) {
                            method = clazz.getMethod("get" + field);
                        }

                        assert method != null;
                        Object value = method.invoke(t, (Object[]) null);
                        if(value != null) {
                            if(value instanceof String) {
                                cell.setCellValue((String) value);
                            }else if(value instanceof Integer) {
                                cell.setCellValue((Integer) value);
                            }else if(value instanceof Long) {
                                cell.setCellValue((Long) value);
                            }else if(value instanceof Double) {
                                cell.setCellValue((Double) value);
                            }
                            //TODo
                            columnCount++;
                        }
                        sheet.autoSizeColumn(15);
                    }
                }
            }
            return sheet;
        } catch (Exception e){
            throw e;
        }
    }


    public <T> void writeToExcelList(String sheetName, List<String> headers, List<T> data, OutputStream outputStream) {
        OutputStream opt = null;
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        Sheet sheet = xssfWorkbook.createSheet(sheetName);
        int rowIndex = 0;
        int columnIndex = 0;

        Row headerRow = sheet.createRow(rowIndex);

        //header
        for(Iterator cell = headers.iterator(); cell.hasNext();) {
            Cell headerCell = headerRow.createCell(columnIndex++);
            headerCell.setCellValue((String)cell.next());
        }

        //data
        rowIndex = 1;
        for(Iterator dataIterator = data.iterator(); dataIterator.hasNext();) {
            List row = (List) dataIterator;
            Row dataRow = sheet.createRow(rowIndex);
            columnIndex = 0;
            for(Iterator cellIterator = row.iterator(); cellIterator.hasNext(); ) {
                Cell dataCell = dataRow.createCell(columnIndex++);
                dataCell.setCellValue((String) cellIterator.next());
            }

            try{
                xssfWorkbook.write(outputStream);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
