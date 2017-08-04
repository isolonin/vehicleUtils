package ru.vehicleutils.utils.test;

import static org.junit.Assert.*;
import org.junit.Test;
import ru.vehicleutils.models.VehicleNumber;
import static ru.vehicleutils.utils.Utils.getMessagePart;
import static ru.vehicleutils.utils.Utils.getVehicleNumberByText;

/**
 *
 * @author ivan
 */
public class UnitsTest {
    
    public UnitsTest() {
        
    }

    @Test
    public void testFormats(){
        assertTrue(isFillFull(getVehicleNumberByText("к 4 3 2 о в 5 2 4 Текст FUCK тестового123 сО0бщения"), "ков", 432, 52, "4 Текст FUCK тестового123 сО0бщения"));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав алена 3 1 2 1 5 2 4 текстовое сообщение"), "тса", 312, 152, "4 текстовое сообщение"));
        
        assertTrue(getMessagePart(getVehicleNumberByText("  к 4 3 2 о в 5 2 4 Текст FUCK тестового123 сО0бщения"),
                "  к 4 3 2 о в 5 2 4 Текст FUCK тестового123 сО0бщения").equals("4 Текст FUCK тестового123 сО0бщения"));
        assertTrue(getMessagePart(getVehicleNumberByText("тараз станислав алена 3 1 2 1 5 2 4 текстовое сообщение"),
                "тараз станислав алена 3 1 2 1 5 2 4 текстовое сообщение").equals("4 текстовое сообщение"));
        assertTrue(getMessagePart(getVehicleNumberByText("к 4 3 2 о в 5 21"),"к 4 3 2 о в 5 21").equals("1"));
        assertTrue(getMessagePart(getVehicleNumberByText("  к 4 3 2 о в 5 2"),
                "  к 4 3 2 о в 5 2") == null);
        assertTrue(getMessagePart(getVehicleNumberByText("  к 4 3 2 о в 5 2 "),
                "  к 4 3 2 о в 5 2 ").equals(""));

        assertTrue(isFillWithoutReg(getVehicleNumberByText("291 хвт")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("х 291 вт")));        
        assertTrue(isFillWithoutReg(getVehicleNumberByText("хвт 291")));
        
        assertTrue(isFillFull(getVehicleNumberByText("а c 2 9 1 9 1 5 21тестовое сообщение")));
        assertTrue(isFillFull(getVehicleNumberByText("а 2919 c 15211223")));
        assertTrue(isFillFull(getVehicleNumberByText("2 9 1 9   аc 1  5 2 s s dasd asdasdasf")));

        assertTrue(isFillFull(getVehicleNumberByText("3 1 2 тараз станислав алена 1 5 2"), "тса", 312, 152));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав 3 1 2  алена 1 5 2"), "тса", 312, 152));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав алена 3 1 2 1 5 2"), "тса", 312, 152));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав алена 3 1 2 1 5 2"), "тса", 312, 152));
        
        assertTrue(isFillFull(getVehicleNumberByText("3 1 2 тараз станислав алена 1 5 2 1"), "тса", 312, 152));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав 3 1 2  алена 1 5 2 2"), "тса", 312, 152));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав алена 3 1 2 1 5 2 3"), "тса", 312, 152));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав алена 3 1 2 1 5 2 4 текстовое сообщение"), "тса", 312, 152));
        
        assertTrue(isFillWithoutReg(getVehicleNumberByText("тараз 3 1 2 станислав алена")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("тараз станислав 3 1 2  алена")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("тараз станислав алена 3 1 2")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("3 1 2 тараз станислав алена")));
        
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав 3 1 2  алена 1 5 2")));
        
        assertTrue(isFillFull(getVehicleNumberByText("к432ов52 4 Текст FUCK тестового123 сО0бщения"), "ков", 432, 52));
        assertTrue(isFillFull(getVehicleNumberByText("к 4 3 2 о в 5 2 4 Текст FUCK тестового123 сО0бщения"), "ков", 432, 52));
        
        assertTrue(isFillFull(getVehicleNumberByText("ков432 52"), "ков", 432, 52));
        assertTrue(isFillFull(getVehicleNumberByText("к432ов52"), "ков", 432, 52));        
        assertTrue(isFillFull(getVehicleNumberByText("ков43252"), "ков", 432, 52));
        assertTrue(isFillFull(getVehicleNumberByText("432ков52"), "ков", 432, 52));
        assertTrue(isFillFull(getVehicleNumberByText("ко432в52"), "ков", 432, 52));
        
        assertTrue(isFillFull(getVehicleNumberByText("к о в 432 52")));
        assertTrue(isFillFull(getVehicleNumberByText("к 4 3 2 о в 5 2")));        
        assertTrue(isFillFull(getVehicleNumberByText("к о в 4 3 2 5 2")));
        assertTrue(isFillFull(getVehicleNumberByText(" 4 3 2 к о в 5  2  ")));
        assertTrue(isFillFull(getVehicleNumberByText(" к о 4 3 2 в 5 2  ")));
        
        assertTrue(getVehicleNumberByText("анна ильина владимир 3 2 1 5 2") == null);
        
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав 312  алена 152")));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав алена 312 152")));
        assertTrue(isFillFull(getVehicleNumberByText("тараз станислав алена 312152")));
        assertTrue(isFillFull(getVehicleNumberByText("312 тараз станислав алена 152")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("тараз 312 станислав алена")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("тараз станислав 312  алена")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("тараз станислав алена 312")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("312 тараз станислав алена")));
        assertTrue(isFillFull(getVehicleNumberByText("2919 алексей стас 52")));
        assertTrue(isFillFull(getVehicleNumberByText("алексей стас 2919  52")));
        assertTrue(isFillFull(getVehicleNumberByText("алексей стас 291952")));
        assertTrue(isFillFull(getVehicleNumberByText("алексей 2919 стас 52")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("2919 алексей стас")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("алексей стас 2919")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("алексей 2919 стас")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("аc 2919")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("а 2919 c")));
        assertTrue(isFillWithoutReg(getVehicleNumberByText("2919 аc")));
        assertTrue(isFillFull(getVehicleNumberByText("аc 2919 152")));
        assertTrue(isFillFull(getVehicleNumberByText("а 2919 c 152")));
        assertTrue(isFillFull(getVehicleNumberByText("2919 аc 152")));
        
        assertTrue(isFillFull(getVehicleNumberByText("х 291 вт 152")));
        assertTrue(isFillFull(getVehicleNumberByText("291 хвт 152")));
        assertTrue(isFillFull(getVehicleNumberByText("хвт 291 152")));
        
        assertTrue(getVehicleNumberByText("Проверка 152") == null);
        assertTrue(getVehicleNumberByText("") == null);
        assertTrue(getVehicleNumberByText(" ") == null);
        assertTrue(getVehicleNumberByText("asdasdasd") == null);
        assertTrue(getVehicleNumberByText("авек1234") == null);
    }
    
    private boolean isFillWithoutReg(VehicleNumber vehicleNumberByText){
        if(vehicleNumberByText == null){
            return false;
        }
        if(vehicleNumberByText.getTransportChars() != null && 
                vehicleNumberByText.getTransportId()!= null){
            return true;
        }
        return false;
    }
    
    private boolean isFillFull(VehicleNumber vehicleNumberByText, String alpha, Integer id, Integer reg, String Text){
        if(isFillFull(vehicleNumberByText) && isFillFull(vehicleNumberByText,alpha, id, reg)){
            return vehicleNumberByText.getTextTail().equals(Text);
        }
        return false;
    }
    
    private boolean isFillFull(VehicleNumber vehicleNumberByText, String alpha, Integer id, Integer reg){
        if(isFillFull(vehicleNumberByText)){
            if(vehicleNumberByText.getTransportChars().equalsIgnoreCase(alpha) &&
                    vehicleNumberByText.getTransportId().equals(id.toString()) &&
                    vehicleNumberByText.getTransportReg().equals(reg)){
                return true;
            }
        }
        return false;
    }
    
    private boolean isFillFull(VehicleNumber vehicleNumberByText){
        if(vehicleNumberByText == null){
            return false;
        }
        if(vehicleNumberByText.getTransportChars() != null && 
                vehicleNumberByText.getTransportId()!= null &&
                vehicleNumberByText.getTransportReg() != null){
            return true;
        }
        return false;
    }
}
