package com.pplam.client;
import com.pplam.models.Monitor;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static java.rmi.Naming.lookup;

public class Client {

    public static void main(String[] args) {
        try {
            Monitor monitor = (Monitor) lookup("rmi://localhost:2099/Monitor");
            monitor.getAvgLoad();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
