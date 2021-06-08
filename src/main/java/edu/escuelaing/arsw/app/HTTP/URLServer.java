package edu.escuelaing.arsw.app.HTTP;

import java.net.MalformedURLException;
import java.net.URL;

/** Ejercicio realizado de la mano con el profesor siguiendo los pasos de la guia
 * @author Daniel Benavides
 * @author Felipe Marin
*/

public class URLServer {
        public static void main (String ... args) throws MalformedURLException {
                URL personalSite = new URL("http://ldbn.escuelaing.edu.co:80/publicaciones/publicaciones.pdf?val=45&r?78#publicaciones");

                System.out.println("Autorithy: "+ personalSite.getAuthority());
                System.out.println("Host: " + personalSite.getHost());
                System.out.println("Port: " + personalSite.getPort());
                System.out.println("Path: " + personalSite.getPath());
                System.out.println("Querty: " + personalSite.getQuery());
                System.out.println("File: " + personalSite.getFile());
                System.out.println("Ref: " + personalSite.getRef());
        }
}
