package com.pplam.client;

import com.pplam.configurations.Configuration;
import com.pplam.interfaces.IRmiMonitor;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static java.rmi.Naming.lookup;

public class Client {

    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    public synchronized static void main(String[] args) throws RemoteException {
        try {
            System.out.println("Ingrese el intervalo de espera");
            long time = new Scanner(System.in).nextLong();
            IRmiMonitor monitor = (IRmiMonitor) lookup(Configuration.getRouteMonitor);
            monitor.initClient();
            do {
                try {
                    System.out.println(monitor.getAvgLoad());
                    monitor.pingRemote();
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(true);
        } catch (ClassCastException | MalformedURLException | NotBoundException exception) {
            exception.printStackTrace();
        }
    }
}
