package ru.vehicleutils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.vehicleutils.models.VehicleNumber;

/**
 *
 * @author ivan
 */
public class Utils {
    private static String alphaChars = "ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух";
    
    public static VehicleNumber getVehicleNumberByText(String text){
        String textNumber = textToVehicleNumber(text);
        if(textNumber != null){
            //------------ AUTO -----------
            //т312са777
            Pattern pattern = Pattern.compile("^(["+alphaChars+"])([0-9]{3})(["+alphaChars+"]{2})([0-9]{2,3})?");
            Matcher matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
            //тс312а777
            pattern = Pattern.compile("^(["+alphaChars+"]{2})([0-9]{3})(["+alphaChars+"])([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
            //тса312777
            pattern = Pattern.compile("^(["+alphaChars+"]{3})([0-9]{3})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            //312тса777
            pattern = Pattern.compile("^([0-9]{3})(["+alphaChars+"]{3})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3));
            }
            
            //------------ MOTO -----------
            //2919ас52
            pattern = Pattern.compile("^([0-9]{4})(["+alphaChars+"]{2})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3));
            }
            //ас291952
            pattern = Pattern.compile("^(["+alphaChars+"]{2})([0-9]{4})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            //а2919с52
            pattern = Pattern.compile("^(["+alphaChars+"])([0-9]{4})(["+alphaChars+"])([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
        }
        
        return null;
    }
    
    private static String[] getPartsByText(String text){
        return text.replaceAll("  ", " ").replaceAll("(?<=\\d) +(?=\\d)", "").split(" ");
    }
    
    private static boolean isShortFormat(String text){
        text = text.replaceAll("(?<=\\d) +(?=\\d)", "");
        String longText = text.replaceAll("([а-яА-Яa-zA-Z])[а-яА-Яa-zA-Z]{2,}", "$1").replaceAll(" ", "");
        if(isVehicleNumberFormat(longText)){
            return false;
        }
        if(isVehicleNumberFormat(text.replaceAll(" ", ""))){
            return true;
        }
        
        if(text.length() >= 6){
            String shortText = text.substring(0, 6);
            if(shortText.matches("^["+alphaChars+"]{3}[0-9]{3}$")){
                return true;
            }
            if(shortText.matches("^["+alphaChars+"][0-9]{3}["+alphaChars+"]{2}$")){
                return true;
            }
            if(shortText.matches("^["+alphaChars+"]{2}[0-9]{3}["+alphaChars+"]$")){
                return true;
            }
            if(shortText.matches("^[0-9]{3}["+alphaChars+"]{3}$")){
                return true;
            }
        }
        return false;
    }
    
    private static boolean isVehicleNumberFormat(String text){
        if(text.length() >= 6){
            String shortText = text.substring(0, 6);
            
            if(shortText.matches("^["+alphaChars+"]{3}[0-9]{3}$")){
                return true;
            }
            if(shortText.matches("^["+alphaChars+"][0-9]{3}["+alphaChars+"]{2}$")){
                return true;
            }
            if(shortText.matches("^["+alphaChars+"]{2}[0-9]{3}["+alphaChars+"]$")){
                return true;
            }
            if(shortText.matches("^[0-9]{3}["+alphaChars+"]{3}$")){
                return true;
            }
        }
        return false;
    }
    
    public static String textToVehicleNumber(String text){
        String textWithoutSpaces = text.replaceAll(" ", "");
        if(isShortFormat(text) == false){
            textWithoutSpaces = text.replaceAll("([а-яА-Яa-zA-Z])[а-яА-Яa-zA-Z]{2,}", "$1").replaceAll(" ", "");
        }
        
        //Выясняем указан ли регион 
        if(textWithoutSpaces.matches("^.{6}[0-9]{2,3}.*")){
            return textWithoutSpaces.replaceAll("^(.{6}[17]?[0-9]{2})[1-4]?.*", "$1");
        }else {
            return textWithoutSpaces.replaceAll("^(.{6})[1-4]?.*", "$1");
        }
    }
}
