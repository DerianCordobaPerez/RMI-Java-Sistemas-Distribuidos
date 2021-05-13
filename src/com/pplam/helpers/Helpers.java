package com.pplam.helpers;
import org.jetbrains.annotations.Nullable;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Helpers {

    /**
     * obtiene el contenido del fichero y lo retorno en formato String
     * @return String contenido del fichero
     */
    @Nullable
    public static String getContentFile() {
        try {
            File file = new File("/proc/loadavg");
            BufferedReader bufferedReader = null;
            if(file.canRead() && file.isFile())
                bufferedReader = new BufferedReader(new FileReader(file));
            return bufferedReader != null ? bufferedReader.readLine() : null;
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * metodo que detendra la ejecucion del programa en caso de que ocurra un comportamiento no deseado
     */
    public static void error() {
        System.out.println("El numero de parametros es incorrecto");
        System.exit(0);
    }

    /**
     * verifica la conexion en caso de encontrar uno, en caso contrario la creara
     * @param port puerto donde se conectara
     * @throws RemoteException
     */
    public static void startRegistry(int port) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list();
        } catch (RemoteException e) {
            Registry registry = LocateRegistry.createRegistry(port);
        }
    }
}
