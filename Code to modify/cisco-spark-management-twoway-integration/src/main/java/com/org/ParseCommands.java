package com.org;

import java.util.HashMap;
import java.util.Map;

import org.jcodings.util.Hash;


public class ParseCommands {

    public static final String SPACE = " ";
    public static final String DOUBLE_QUOTE ="\"";
    public static final String AT = "@";
    public static final String COLON =":";
     HashMap<String,String> values = null;
    public  HashMap<String,String> parsingCommand(String jiraCommand) {


        /*System.out.println("Enter your input: ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();*/
        String[] splitData = jiraCommand.split(SPACE);
        String command = getCommand(splitData);
        String action = getAction(splitData);
        values = new HashMap<String,String>();
         values = getValues(splitData);
        System.out.println("Your input values  are " );
        System.out.println(jiraCommand);
        System.out.println("Your command is " );
        System.out.println(command);
        System.out.println("Your action is " );
        System.out.println(action);
        System.out.println("Your values are " );
        System.out.println(values);
       return values;


    }
     
    
    public static String getCommand(String inputMessage){
        String[] splitData = inputMessage.split(SPACE);
        return getCommand(splitData);
    }

    public static String getAction(String inputMessage){
        String[] splitData = inputMessage.split(SPACE);
        return getAction(splitData);
    }

    public static Map<String, String> getParameters(String inputMessage){
        String[] splitData = inputMessage.split(SPACE);
        return getValues(splitData);
    }

    private static HashMap<String, String> getValues(String[] splitData) {
         System.out.println("aaaa_______________________");
        HashMap<String,String> returnMap = new HashMap<String,String>();
        System.out.println("bbbbb****************");
        String key =null;
        String value =null;
        boolean isKey =true;

        for (int i = 0; i < splitData.length; i++) {
            String data = splitData[i];

            if( data.equals(COLON)){
                continue;
            }
            if(data.startsWith(AT) && isKey){
                continue;
            }
            if(isKey){
                if(data.startsWith(DOUBLE_QUOTE)){
                    data = data.substring(1);
                    if(data.indexOf(DOUBLE_QUOTE) > -1){

                        key = data.substring(0, data.indexOf(DOUBLE_QUOTE));
                        if(!data.endsWith(DOUBLE_QUOTE)){
 splitData[i]=data.substring(data.indexOf(DOUBLE_QUOTE)+1);
                        i = i-1;
                        }
                    }else {
                        key = data;


                    for(int k=i+1;k<splitData.length;k++){
                        i=i+1;
                        String repeatKey = splitData[k];
                        if(repeatKey.indexOf(DOUBLE_QUOTE)> -1){
                            if(repeatKey.endsWith(DOUBLE_QUOTE)){
                            key = key+SPACE+ repeatKey.substring(0, repeatKey.length()-1);
                            } else {
                                key = key+SPACE+repeatKey.substring(0, repeatKey.indexOf("\""));
 splitData[i]=data.substring(data.indexOf("\"")+1);
                                i = i-1;
                            }

                            break;
                        }else {
                        key =key+repeatKey;
                        }
                    }
                    }
                }
                else{
                    if(data.indexOf(COLON)>0){
                        if(data.endsWith(COLON)){
                            key = data.substring(0,data.length()-1);
                        }else{
                            key = data.substring(0,data.indexOf(COLON));
 splitData[i]=data.substring(data.indexOf(COLON)+1);
                            i = i-1;
                        }


                    }else{
                        key = data;
                    }

                }
                isKey =false;
            }
            else {//values
                    if(data.startsWith(DOUBLE_QUOTE)){

                        data =data.substring(1);
                        if(data.indexOf(DOUBLE_QUOTE) > -1){
                            value = data.substring(1, data.indexOf(DOUBLE_QUOTE) );
                            if(!value.endsWith(DOUBLE_QUOTE)){
 splitData[i]=data.substring(data.indexOf(DOUBLE_QUOTE)+1);
                            i = i-1;
                            }
                        }else {
                            value = data;


                        for(int k=i+1;k<splitData.length;k++){
                            i=i+1;
                            String repeatValue = splitData[k];
                            if(repeatValue.indexOf(DOUBLE_QUOTE)> -1){
 if(repeatValue.endsWith(DOUBLE_QUOTE)){
                                value = value+SPACE+repeatValue.substring(0, repeatValue.length() -1);
                                } else {
                                    value = value+SPACE+repeatValue.substring(0, repeatValue.indexOf(DOUBLE_QUOTE));
 splitData[i]=data.substring(data.indexOf(DOUBLE_QUOTE)+1);
                                    i = i-1;
                                }

                                break;
                            }else {
                            value =value+SPACE+repeatValue;
                            }
                        }
                        }


                    }
                    else{
                        if(data.startsWith(COLON)){
                            value = data.substring(1);

                        }else{
                            value = data;
                        }

                    }
                isKey = true;
                returnMap.put(key.toLowerCase(), value);
            }
        }

     System.out.println("Return Map::::"+returnMap);
        return returnMap;
    }



    private static String getCommand(String[] splitData) {
        String returnString = null;
        for (int i = 0; i < splitData.length; i++) {
            String value = splitData[i];
            if(value.startsWith("@")){
                returnString = value.substring(1,value.length());
                break;
            }
        }
        return returnString;
    }

    private static String getAction(String[] splitData) {
        String returnString = null;
        int j=0;
        for (int i = 0; i < splitData.length; i++) {
            String value = splitData[i];
            if(value.startsWith("@")){
                if(j==1){
                returnString = value.substring(1,value.length());
                break;
                }
                j=1;
            }
        }
        return returnString;
    }
   
}
