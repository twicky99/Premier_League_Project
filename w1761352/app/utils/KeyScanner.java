package utils;

import java.io.IOException; 

/**
* handle user input from console
* this class was made because CLI App runs in another Thread of Play App.
* Play Console is used by Play App to log activities of Web App. 
* So, user input from another thread cannot be displayed on Play Console due to blocking
*/
public class KeyScanner{

    /**
    * capture every keypress from user input, store it in variable
    * @param breakline option to make new line or not after enter key is pressed
    * @return String
    */
	private String detectKey(boolean breakline) throws IOException{
        String input = "";
        char c;
        int charCode = 0;

        while(true){
            charCode = System.in.read();

            if(charCode == 13){
                System.out.print("\n");
                break;
            }else if((charCode == 127) | (charCode == 8)){
                System.out.print("\b");
                input = removeLast(input);
            }else{
                c = (char) charCode;
                System.out.print(c);
                input += c;
            }
            
        }

        return input;
    }

    /**
    * read input user without newline
    * @return String
    */
    public String read() throws IOException{
    	return detectKey(false);
    }

    /**
    * read input user with newline
    * @return String
    */
    public String readLine() throws IOException{
    	return detectKey(true);
    }

    /**
    * remove last character of String
    * @param str word or String input
    * @return String
    */
    private String removeLast(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
    * Input with validation and error message
    * @param command
    * @param errorMessage
    * @return String
    */
    public String inputWithValidation(String command, String errorMessage) throws IOException{
        String in = "";
        boolean err = false;
        
        while(in.equalsIgnoreCase("")){
            if (err) {
                System.out.println(errorMessage);  
                err = false;              
            }

            System.out.print(command);
            in = this.readLine();

            if (in.equalsIgnoreCase("")) {
                err = true;    
            }
        }
        
        return in;
    }

}