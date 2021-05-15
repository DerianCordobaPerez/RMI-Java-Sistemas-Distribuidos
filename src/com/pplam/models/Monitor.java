package com.pplam.models;
import com.pplam.interfaces.IRmiMonitor;
import com.pplam.interfaces.IRmiServer;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Monitor extends UnicastRemoteObject implements IRmiMonitor {
    private double time;
    private IRmiServer coordinator;

    /**
     * Constructor Monitor
     */
    public Monitor(IRmiServer coordinator) throws RemoteException {
        super();
        this.coordinator = coordinator;
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
     * @param time tiempo a establecer
     */
    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public void initClient() {
        try {
            System.out.println("Total de monitores funcionando: " + this.coordinator.initClient());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initMonitor() {
        try {
            this.coordinator.initMonitor(this);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
