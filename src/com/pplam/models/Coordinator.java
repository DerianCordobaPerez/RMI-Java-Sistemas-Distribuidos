package com.pplam.models;

import com.pplam.helpers.Helpers;
import com.pplam.interfaces.IRmiMonitor;
import com.pplam.interfaces.IRmiServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class Coordinator extends UnicastRemoteObject implements IRmiServer {
    private Vector<IRmiMonitor> monitors = new Vector<>();
    private String[] listMonitors;
    public static int quantityMonitors = 0;
    private double time;

    /**
     * @param time tiempo a esperar
     * @throws RemoteException
     */
    public Coordinator(Double time) throws RemoteException {
        super();
        this.setTime(time);
    }

    /**
     * metodo sobreescrito de la interfaz IRmiServer
     * @return double
     */
    @Override
    public void initMonitor(IRmiMonitor monitor) throws RemoteException {
        System.out.println("Entra a la funcion");
        try {
            monitors.addElement(monitor);
            System.out.println("Se agrego");
        } catch (Exception exception) {
            System.out.println("No se agrego");
        }
    }

    /**
     * metodo sobreescrito de la interfaz IRmiServer
     * @param result resultado obtenido del fichero /proc/loadavg
     */
    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    @Override
    public void loadMonitor(String result) {
        do {
            System.out.println("Informacion del CPU: " + result);
            try {
                sleep((long) this.time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    @Override
    public synchronized int initClient() {
        int total = 0;
        try {
            listMonitors = Naming.list("rmi://localhost/");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        for(String remote : listMonitors) {
            if(remote.equals("Monitor"))
                try {
                    //Naming.unbind(remote);
                    continue;
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            ++total;
        }
        quantityMonitors = total;
        return quantityMonitors;
    }

    @Override
    public void getLoadAvg(){
        loadMonitor(Helpers.getContentFile());
    }

    public Vector<IRmiMonitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(Vector<IRmiMonitor> monitors) {
        this.monitors = monitors;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
