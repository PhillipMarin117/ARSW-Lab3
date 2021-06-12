package edu.escuelaing.arsw.app.HTML;

import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * El programa se desarrolla en clase al tiepo que el profesor
 */

public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        while (true) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out;
            BufferedReader in;

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine, res = "";
            int contador = 0;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (contador == 0) {
                    res = inputLine;
                }

                contador++;
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "";
            System.out.println("Esta es l a respuesta: " + res);
            if(!res.equals("")){
                if (res.substring(0, 3).equals("GET")) {
                    BuscarArchivo find = new BuscarArchivo();
                    System.out.println("+++ La linea de la 8 a la -9 +++"+res.substring(8, res.length() - 9));
                    File archivoEncontrado = find.buscador(res.substring(8, res.length() - 9), new File("C:\\Users\\Felipe\\Desktop\\Trabajos Universidad\\Inter ARSW\\ARWS\\ARSW-Lab3\\src\\main"));
                    //System.out.println("Archivo enconrado " + archivoEncontrado);
                    if (archivoEncontrado != null) {
                        BufferedReader reader = new BufferedReader(new FileReader(archivoEncontrado));
                        StringBuilder cadena = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            //System.out.println("Imprime el Line "+line);
                            cadena.append(line);
                        }
                        out.println("HTTP/1.1 200 OK");
                        boolean bandera = false;
                        System.out.println("LA LINEA 5 A 7: ---  "+ res.substring(5, 7));
                        if(res.substring(5, 7).equals("im")){
                            BufferedImage image = ImageIO.read(new File("C:\\Users\\Felipe\\Desktop\\Trabajos Universidad\\Inter ARSW\\ARWS\\ARSW-Lab3\\src\\main\\java\\resource\\img\\Battler.PNG"));
                            System.out.println("---- LA LINEA 12 A -9: ---  "+ res.substring(res.length() - 12,  res.length() - 9));
                            if(res.substring(res.length() - 12,  res.length() - 9).toUpperCase().equals("PNG")){
                                out.println("Content-Type: image/png");
                                out.println();
                                ImageIO.write(image, "PNG", clientSocket.getOutputStream());
                                bandera = true;
                            }
                        }

                        if(res.substring(5, 7).equals("ht")){
                            if (bandera == false){
                                out.println("Content-Type: text/html");
                                out.println();
                                out.println(cadena);
                                System.out.println("Aqui acabó de entrar"+out);
                            }
                        }
                        else if(res.substring(5, 7).equals("js")){
                            System.out.println("!!!!!!!!!!!! Aqui acabó de entrar !!!!!!!!!!!!!!!" + bandera);
                            if (bandera == false){
                                System.out.println("%%%%%%%  Papulince acabaste de mamar afa afa afaaaartex %%%%%%");
                                out.println("Content-Type: application/javascript");
                                out.println();
                                out.println(cadena);
                            }
                        }
                        else {
                            outputLine = error(outputLine,res);
                            out.println(outputLine);
                        }
                    }
                }
            }
            out.close();
            in.close();
        }

    }

    private static String error(String outputLine, String res) {

        outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>--- ERROR 40 ---<p> <div style='color:red'>" + res.substring(5, res.length() - 9).toUpperCase() + "</div>" + " MEJOR SUERTE LUEGO COMPA</p></h1>"
                + "</body>"
                + "</html>";
        return outputLine;
    }
}
