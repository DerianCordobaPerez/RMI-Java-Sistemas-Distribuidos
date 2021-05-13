package com.pplam.models;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import static java.rmi.Naming.lookup;
import static com.pplam.helpers.Helpers.error;

public class Monitor implements Remote {
    private double time;
    private final Coordinator coordinator;

    /**
     * Constructor Monitor
     * @param args parametros de conexion
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws NotBoundException
     */
    public Monitor(String ...args) throws RemoteException, MalformedURLException, NotBoundException {
        this.coordinator = (Coordinator) lookup("rmi://" + args[0] + ":" + Double.parseDouble(args[1]) + "/Coordinator");
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

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        if(args.length != 3) error();
        try {
            new Monitor(args[1], args[2]).initMonitor();

        } catch (Exception exception) {

        }

    }
}
