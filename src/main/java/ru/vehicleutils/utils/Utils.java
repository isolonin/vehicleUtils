package ru.vehicleutils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.vehicleutils.models.VehicleNumber;

/**
 *
 * @author ivan
 */
public class Utils {
    public static VehicleNumber getVehicleNumberByText(String text){
        String textNumber = textToVehicleNumber(text);
        if(textNumber != null){
            //------------ AUTO -----------
            //т312са777
            Pattern pattern = Pattern.compile("^([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух])([0-9]{3})([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух]{2})([0-9]{2,3})?$");
            Matcher matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
            //тс312а777
            pattern = Pattern.compile("^([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух]{2})([0-9]{3})([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух])([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
            //тса312777
            pattern = Pattern.compile("^([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух]{3})([0-9]{3})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            //312тса777
            pattern = Pattern.compile("^([0-9]{3})([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух]{3})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3));
            }
            
            //------------ MOTO -----------
            //2919ас52
            pattern = Pattern.compile("^([0-9]{4})([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух]{2})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3));
            }
            //ас291952
            pattern = Pattern.compile("^([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух]{2})([0-9]{4})([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            //а2919с52
            pattern = Pattern.compile("^([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух])([0-9]{4})([ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух])([0-9]{2,3})?$");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return new VehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4));
            }
        }
        
        return null;
    }
    
    public static String textToVehicleNumber(String text){
        String[] parts = text.replaceAll("  ", " ").split(" ");
        if(parts.length >= 3){
            StringBuilder sb = new StringBuilder();
            for(String part:parts){
                if(part.matches("^[^0-9]*$")){
                    sb.append(part.substring(0, 1).toLowerCase());
                }else{
                    sb.append(part);
                }
            }
            return sb.toString();
        }
        return null;
    }
}
