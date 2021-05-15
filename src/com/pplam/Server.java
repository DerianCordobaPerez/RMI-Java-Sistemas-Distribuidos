package com.pplam;

import com.pplam.configurations.Configuration;
import com.pplam.helpers.Helpers;
import com.pplam.interfaces.IRmiServer;
import com.pplam.models.Coordinator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import static com.pplam.helpers.Helpers.startRegistry;

public class Server {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        startRegistry(1099);
        System.out.println("Ingrese el tiempo que esperara");
        IRmiServer coordinator = new Coordinator(new Scanner(System.in).nextDouble());
        Naming.rebind(Configuration.setRouteCoordinator, coordinator);
        System.out.println("Contenido del fichero " + Helpers.getContentFile());
    }
}
