package edu.escuelaing.arsw.app.SquareService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SquareServiceServer {

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
        while ((inputLine = in.readLine())!=null){
            System.out.println("Mensaje: " + inputLine);
            try {Double numero =Double.parseDouble(inputLine);
                numero = Math.pow(numero, 2);
                outputLine = "Respuesta " + inputLine + "numero : " + numero;
            }catch (NumberFormatException e){
                outputLine = "Respuesta: Entrada no valida. Error" + e;
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

}
