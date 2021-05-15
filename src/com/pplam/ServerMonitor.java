package com.pplam;
import com.pplam.configurations.Configuration;
import com.pplam.interfaces.IRmiMonitor;
import com.pplam.interfaces.IRmiServer;
import com.pplam.models.Monitor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import static com.pplam.helpers.Helpers.startRegistry;

public class ServerMonitor {

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        startRegistry(1099);
        IRmiServer coordinator = (IRmiServer) Naming.lookup(Configuration.getRouteCoordinator);
        IRmiMonitor monitor = new Monitor(coordinator, coordinator.getTime());
        monitor.initMonitor();
        Naming.rebind(Configuration.setRouteMonitor, monitor);
        monitor.loadMonitor();
    }
}
