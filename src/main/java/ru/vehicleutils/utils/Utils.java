package ru.vehicleutils.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.vehicleutils.models.VehicleNumber;

/**
 *
 * @author ivan
 */
public class Utils {
    private static String alphaChars = "ABEKMHOPCTXaekmopctxАВЕКМНОРСТУХавекмнорстух";
    
    private static VehicleNumber createVehicleNumber(String transportChars, String transportId, String transportReg, String text){
        VehicleNumber vehicleNumber = new VehicleNumber(transportChars, transportId, transportReg);
        vehicleNumber.setTextTail(getMessagePart(vehicleNumber, text));
        return vehicleNumber;
    }
    
    public static VehicleNumber getVehicleNumberByText(String text){
        String textNumber = textToVehicleNumber(text);
        if(textNumber != null){
            //------------ AUTO -----------
            //т312са777
            Pattern pattern = Pattern.compile("^(["+alphaChars+"])([0-9]{3})(["+alphaChars+"]{2})([0-9]{2,3})?");
            Matcher matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return createVehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4),text);
            }
            //тс312а777
            pattern = Pattern.compile("^(["+alphaChars+"]{2})([0-9]{3})(["+alphaChars+"])([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return createVehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4),text);
            }
            //тса312777
            pattern = Pattern.compile("^(["+alphaChars+"]{3})([0-9]{3})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return createVehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3),text);
            }
            //312тса777
            pattern = Pattern.compile("^([0-9]{3})(["+alphaChars+"]{3})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return createVehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3),text);
            }
            
            //------------ MOTO -----------
            //2919ас52
            pattern = Pattern.compile("^([0-9]{4})(["+alphaChars+"]{2})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return createVehicleNumber(matcher.group(2), matcher.group(1), matcher.group(3),text);
            }
            //ас291952
            pattern = Pattern.compile("^(["+alphaChars+"]{2})([0-9]{4})([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return createVehicleNumber(matcher.group(1), matcher.group(2), matcher.group(3),text);
            }
            //а2919с52
            pattern = Pattern.compile("^(["+alphaChars+"])([0-9]{4})(["+alphaChars+"])([0-9]{2,3})?");
            matcher = pattern.matcher(textNumber);
            if(matcher.matches()){
                return createVehicleNumber(matcher.group(1)+matcher.group(3), matcher.group(2), matcher.group(4),text);
            }
        }
        
        return null;
    }
    
    private static boolean isShortFormat(String text){
        //Убираем пробелы между цифрами
        text = text.replaceAll("(?<=\\d) +(?=\\d)", "");
        
        //Берём первые буквы от слов, убираем пробелы
        String longText = text.replaceAll("([а-яА-Яa-zA-Z])[а-яА-Яa-zA-Z]{2,}", "$1").replaceAll(" ", "");
        //Если первые 6 символов изменились после преобразования
        if(longText.length() >= 6 && longText.substring(0, 6).equals(text.replaceAll(" ", "").substring(0, 6)) == false){
            //Если после полученных манипляций номер соответствует шаблону, значит имеет место длинная комбинация
            if(isVehicleNumberFormat(longText)){
                return false;
            }
        }        
        
        //Пробуем сопоставить номер короткой форме записи убрав пробелы
        if(isVehicleNumberFormat(text.replaceAll(" ", ""))){
            return true;
        }
        
        //Что ты такое?
        throw new VerifyError();
    }
    
    private static boolean isVehicleNumberFormat(String text){
        if(text.length() >= 6){
            String shortText = text.substring(0, 6);
            //------------ AUTO -----------
            //тса312
            if(shortText.matches("^["+alphaChars+"]{3}[0-9]{3}$")){
                return true;
            }
            //т312са
            if(shortText.matches("^["+alphaChars+"][0-9]{3}["+alphaChars+"]{2}$")){
                return true;
            }
            //тс312а
            if(shortText.matches("^["+alphaChars+"]{2}[0-9]{3}["+alphaChars+"]$")){
                return true;
            }
            //312тса
            if(shortText.matches("^[0-9]{3}["+alphaChars+"]{3}$")){
                return true;
            }
            //------------ MOTO -----------
            //2919ас
            if(shortText.matches("^[0-9]{4}["+alphaChars+"]{2}$")){
                return true;
            }
            //ас2919
            if(shortText.matches("^["+alphaChars+"]{2}[0-9]{4}$")){
                return true;
            }
            //а2919с
            if(shortText.matches("^["+alphaChars+"][0-9]{4}["+alphaChars+"]$")){
                return true;
            }
        }else {
            throw new VerifyError();
        }
        return false;
    }
    
    public static String textToVehicleNumber(String text){
        try{
            String textWithoutSpaces = text.replaceAll(" ", "");
            //Выявляем формат номера - длинный/короткий/хренпойми какой
            if(isShortFormat(text) == false){
                textWithoutSpaces = text.replaceAll("([а-яА-Яa-zA-Z])[а-яА-Яa-zA-Z]{2,}", "$1").replaceAll(" ", "");
            }

            //Выясняем указан ли регион 
            if(textWithoutSpaces.matches("^.{6}[0-9]{2,3}.*")){
                return textWithoutSpaces.replaceAll("^(.{6}[17]?[0-9]{2})[1-4]?.*", "$1");
            }else {
                return textWithoutSpaces.replaceAll("^(.{6})[1-4]?.*", "$1");
            }
        }catch(VerifyError ex){
            return null;
        }
    }
    
    public static String getMessagePart(VehicleNumber vehicleNumber, String text){
        String transportChars = vehicleNumber.getTransportChars();
        String transportId = vehicleNumber.getTransportId().toString();
        String transportReg = vehicleNumber.getTransportReg() != null?vehicleNumber.getTransportReg().toString():"";

        String coincidenceString = transportChars+transportId+transportReg;
        char[] coincidenceArray = coincidenceString.toCharArray();
        List<Character> coincidenceList = new ArrayList<>();
        for(int i=0; i<coincidenceArray.length; i++){
            coincidenceList.add(coincidenceArray[i]);
        }

        for(int i=0; i<text.length()-1; i++){
            char a = text.charAt(i);

            Iterator<Character> iterator = coincidenceList.iterator();
            while(iterator.hasNext()){
                Character b = iterator.next();
                if(b.equals(a)){
                    iterator.remove();
                    break;
                }
            }
            if(coincidenceList.isEmpty()){
                return text.substring(i+1).replaceFirst("^ ", "");
            }
        }
        return null;
    }
}
