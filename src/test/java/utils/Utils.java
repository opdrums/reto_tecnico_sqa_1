package utils;

import net.serenitybdd.core.pages.PageObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utils extends PageObject {

    /**
     * Este metodo devuelve una propiedad especifica dentro de un archivo .Properties
     *
     * @param nameFile Nombre del archivo .Properties
     * @param property Propiedad que se quiere obtener
     * @throws IOException
     */

    //metodo estatico de tipo string que lee el archivo del configurations.properties
    public static String readProperty(String nameFile, String property) throws IOException {
        Properties propiedades = new Properties();
        propiedades.load(new FileReader(nameFile + ".properties"));
        return propiedades.getProperty(property);
    }

    //metodo estatico que lee un array de tipo string lee un archivo csv
    public static ArrayList<ArrayList<String>> readCsvFile(String path) throws IOException {
        ArrayList<ArrayList<String>> data=new ArrayList<>();
        BufferedReader br = null;
        try {
            int row=0;
            br =new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (null!=line && row<6) {
                data.add(new ArrayList<>(Arrays.asList(line.split(","))));
                line = br.readLine();
                row=row+1;
            }
        } catch (Exception e) {
        } finally {
            if (null!=br) {
                br.close();
            }
        }
        return data;
    }

    /**
     *
     * @param text Hace referencia al valor con el cual se desea concatenar la fecha
     * @return El valor con la fecha concatenada
     */

    public static String autoCompleteValue(String text){
        if(text.contains("(encode)")) {
            String dateTime = DateTimeFormatter.ofPattern("ddMMyyhhmm").format(LocalDateTime.now());
            text = text.replace("(encode)",dateTime);
        }
        return text;
    }
}