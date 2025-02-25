package utils.readDate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utils.Utils;

import static utils.Utils.autoCompleteValue;


public class DataToFeature {

    public static List<Map<String, String>> excelData = null;
    public static String excelFilePathActual ="";
    /**
     * Ingresa los datos obtenidos de un excel al archivo .feature del cual se está llamando, hace que se genere la tabla en el escenario
     * Outline como Data Table
     *
     * @param featureFile Nombre del archivo .feature el cual se modificará, debe tener la ruta del archivo y la hoja ser usada
     * @return
     * @throws InvalidFormatException
     * @throws IOException
     */
    private static List<String> setExcelDataToFeature(File featureFile)

            throws InvalidFormatException, IOException {
        List<String> fileData = new ArrayList<String>();
        try (BufferedReader buffReader = new BufferedReader(
                new InputStreamReader(new BufferedInputStream(new FileInputStream(featureFile)), "UTF-8"))) {
            String data;
            boolean foundHashTag = false;
            boolean featureData = false;
            while ((data = buffReader.readLine()) != null) {
                String excelFilePath = null;
                if (data.trim().contains("#data")) {
                    if(Utils.readProperty("configurations", "DataOnly").trim().contains("xlsx")){
                        if(data.trim().contains(Utils.readProperty("configurations", "DataOnly").trim())){
                            excelFilePath = "DataEntry/" + data.substring(StringUtils.ordinalIndexOf(data, ":", 1) + 1);
                            foundHashTag = true;
                            fileData.add(data);
                        }
                    }else{
                        excelFilePath = "DataEntry/" + data.substring(StringUtils.ordinalIndexOf(data, ":", 1) + 1);
                        foundHashTag = true;
                        fileData.add(data);
                    }
                }
                if (foundHashTag) {
                    String campos = fileData.get(fileData.size() - 2).trim();
                    campos = campos.substring(1, campos.length() - 1);
                    List<String> encabezadosIni = Arrays.asList(campos.split("\\|"));
                    List<String> encabezados = new ArrayList<>();
                    for (String valor : encabezadosIni)
                        encabezados.add(valor.trim());
                    if(!excelFilePath.equals(excelFilePathActual))
                        excelData = new ReadToExcel().getData(excelFilePath, 0);
                    excelFilePathActual=excelFilePath;

//	                    String columnHeader = "";

//	                    for (Entry<String, String> map : excelData.get(0).entrySet()) {
//	                        columnHeader = columnHeader + "|" + map.getKey();
//	                    }
//
//	                    fileData.add(columnHeader + "|");

                    for (int rowNumber = 0; rowNumber < excelData.size() - 1; rowNumber++) {
                        String cellData = "|";
                        for (Entry<String, String> mapData : excelData.get(rowNumber).entrySet()) {
                            if (encabezados.contains(mapData.getKey().trim()))
                                cellData = cellData + "|" + mapData.getValue();
                        }
                        cellData = cellData.substring(1, cellData.length());
                        cellData += "|";
                        if (cellData.replace("|", "").length() > 1) {
                            if (cellData.substring(0, 4).contains(Utils.readProperty("configurations", "Ambiente").toUpperCase()))
                                fileData.add(autoCompleteValue(cellData));
                        } else {
                            break;
                        }
                    }
                    foundHashTag = false;
                    featureData = true;
                    continue;
                }
                if (data.startsWith("|") || data.endsWith("|")) {
                    if (featureData) {
                        continue;
                    } else {
                        fileData.add(data);
                        continue;
                    }
                } else {
                    featureData = false;
                }
                fileData.add(data);
            }
        }
        return fileData;
    }

    /**
     * Lista de todos los features con sus respectivos archivo de excel que se usarán en la prueba
     *
     * @param folder Carpeta donde estarán los archivo .feature
     * @return
     */
    private static List<File> listOfFeatureFiles(File folder) {
        List<File> featureFiles = new ArrayList<File>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                featureFiles.addAll(listOfFeatureFiles(fileEntry));
            } else {
                if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
                    featureFiles.add(fileEntry);
                }
            }
        }
        return featureFiles;
    }

    /**
     * Hace una lista con todos los features dependiendo de la ruta asignada
     *
     * @param featuresDirectoryPath Ruta donde se encuentran los features que tendrán las tablas
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static void overrideFeatureFiles(String featuresDirectoryPath)
//	 public void overrideFeatureFiles(String featuresDirectoryPath)
            throws IOException, InvalidFormatException {
        List<File> listOfFeatureFiles = listOfFeatureFiles(new File(featuresDirectoryPath));
        for (File featureFile : listOfFeatureFiles) {
            List<String> featureWithExcelData = setExcelDataToFeature(featureFile);
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(featureFile), "UTF-8"));) {
                for (String string : featureWithExcelData) {
                    writer.write(string);
                    writer.write("\n");
                }
            }
        }
    }
}