package edu.escuelaing.arsw.app.OperationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

public class OperationServiceServer {

    private static String actualFunction = "fun:cos";



    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Servidor escuchando por el puerto 35000...");
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje: " + inputLine);
            try {
                Double number = validateInput(inputLine);
                outputLine = "Su numero es: " + inputLine + " El resultado de la operacion es: " + number;
            } catch (NumberFormatException | OperationServiceException e) {
                outputLine = "Repsuesta: Entrada no valida mi pana, Error" + e;
            }
            out.println(outputLine);
            if (outputLine.equals("Respuestas: Bye."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }


    private static Double validateInput(String inputLine) throws OperationServiceException {
        Double number;
        String[] nuevo = inputLine.split(" ");
        String function = actualFunction;
        if (nuevo.length > 1){
            function = nuevo[0];
            number = Double.parseDouble(nuevo[1]);
        }
        else{
            number = Double.parseDouble(nuevo[0]);
        }
        return validateFunction(function, number);
    }

    private static Double validateFunction(String function, Double number) throws OperationServiceException{
        Double ans;
        if (function.equals("fun:sin")){
            ans = Math.sin(number);
        }else if (function.equals("fun:cos")){
            ans = Math.cos(number);
        }else if (function.equals("fun:tan")){
            ans = Math.tan(number);
        }
        else{
            throw new OperationServiceException(OperationServiceException.MAL_FORMED_FUNCTION);
        }
        actualFunction = function;
        return ans;
    }
}
