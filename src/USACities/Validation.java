package USACities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Validation {
    public static  boolean isDouble(String field){
    Pattern pat= Pattern.compile("\\d+(\\.\\d+)?");
    Matcher mat = pat.matcher(field);
    return mat.matches();
}
  //  public static boolean isPhone(String field){
  //      if(field.matches)
   //         return true;
  //     else 
  //          return false ;
 //   }
}
