package com.pplam.models;
import com.pplam.helpers.Helpers;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import static java.rmi.Naming.lookup;

public class Monitor implements Remote {
    private double time;
    private final Coordinator coordinator;

    /**
     * Constructor Monitor
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws NotBoundException
     */
    public Monitor() throws RemoteException, MalformedURLException, NotBoundException {
        this.coordinator = (Coordinator) lookup("rmi://localhost:1099/Coordinator");
        Helpers.startRegistry(2099);
        Naming.rebind("rmi://localhost:2099/Monitor", this);
    }

    /**
     * devuelve el time
     * @return double
     */
    public double getTime() {
        return time;
    }

    /**
     * establece el time
     * @param time
     */
    public void setTime(double time) {
        this.time = time;
    }

    private void initMonitor() {
        this.setTime(coordinator.initMonitor(this));
    }

    public void getAvgLoad() {
        this.coordinator.getLoadAvg();
    }

    public static void main(String[] args) throws RemoteException {
        try {
            new Monitor().initMonitor();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
