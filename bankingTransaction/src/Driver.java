
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author Kerly Titus
 */
public class Driver {

    /** 
     * main class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	 /*******************************************************************************************************************************************
    	  * TODO : implement all the operations of main class   																					*
    	  ******************************************************************************************************************************************/
        PrintStream fileOut = null;

        try {
             fileOut = new PrintStream("./src/output.txt");
            System.setOut(fileOut); // Redirects standard output to "output.txt"
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	Network objNetwork = new Network("network");      
        objNetwork.start();
        
        Server objServer = new Server();  
        objServer.start();

        Client clientThread_1 = new Client("sending");
        Client clientThread_2 = new Client("receiving");  

        clientThread_1.start();
        clientThread_2.start(); 

        try {
            // Wait for the threads to finish
            objNetwork.join();
            objServer.join();
            clientThread_1.join();
            clientThread_2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        if (fileOut != null) {
            fileOut.close();  
        }

    }
}
