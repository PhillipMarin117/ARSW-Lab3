package edu.escuelaing.arsw.app.URLService;

import java.io.*;
import java.net.*;

/** Ejercicio realizado de la mano con el profesor siguiendo los pasos de la guia
 * @author Daniel Benavides
 * @author Felipe Marin
 */

public class URLReader {
    public static void main(String[] args) throws Exception {
        URL google = new URL("http://www.google.com");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
