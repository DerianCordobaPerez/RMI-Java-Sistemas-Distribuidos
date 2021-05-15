package com.pplam.client;

import com.pplam.configurations.Configuration;
import com.pplam.interfaces.IRmiMonitor;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static java.rmi.Naming.lookup;

public class Client {

    public static void main(String[] args) throws RemoteException {
        try {
            IRmiMonitor monitor = (IRmiMonitor) lookup(Configuration.getRouteMonitor);
            monitor.initClient();
            System.out.println("Entro");
        } catch (ClassCastException | MalformedURLException | NotBoundException exception) {
            exception.printStackTrace();
        }
    }
}
