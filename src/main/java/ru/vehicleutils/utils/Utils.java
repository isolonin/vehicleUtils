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
            Pattern pattern = Pattern.compile("^(["+alphaChars+"])([0-9]{3})(["+alphaChars+"]{2})([0-9]{2,3})?$");
            Matcher matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
            //тс312а777
            pattern = Pattern.compile("^(["+alphaChars+"]{2})([0-9]{3})(["+alphaChars+"])([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
            //тса312777
            pattern = Pattern.compile("^(["+alphaChars+"]{3})([0-9]{3})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            //312тса777
            pattern = Pattern.compile("^([0-9]{3})(["+alphaChars+"]{3})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3));
            }
            
            //------------ MOTO -----------
            //2919ас52
            pattern = Pattern.compile("^([0-9]{4})(["+alphaChars+"]{2})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3));
            }
            //ас291952
            pattern = Pattern.compile("^(["+alphaChars+"]{2})([0-9]{4})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            //а2919с52
            pattern = Pattern.compile("^(["+alphaChars+"])([0-9]{4})(["+alphaChars+"])([0-9]{2,3})?$");
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
        String[] parts = getPartsByText(text);
        StringBuilder alphaCharsSet = new StringBuilder();
        for(String part:parts){
            if(part.matches("^["+alphaChars+"]*$")){
                alphaCharsSet.append(part);
            }
        }
        return alphaCharsSet.toString().isEmpty() == false && alphaCharsSet.toString().length() <= 3;
    }
    
    public static String textToVehicleNumber(String text){
        String[] parts = getPartsByText(text);
        
        if(parts.length >= 3){            
            //Если номер в коротком формате "аc 2919 152" или "х 291 вт 152"
            if(isShortFormat(text)){
                return text.replaceAll(" ", "");
            }
            
            StringBuilder sb = new StringBuilder();
            for(String part:parts){
                if(part.matches("^[^0-9]*$")){
                    sb.append(part.substring(0, 1).toLowerCase());
                }else{
                    sb.append(part);
                }
            }
            return sb.toString();
        }else {
            //Если человек назвал номер в коротком формате, к примеру "2919 ас"
            if(parts.length == 2){
                return text.replaceAll(" ", "");
            }
        }
        return null;
    }
}
