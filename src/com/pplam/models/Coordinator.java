package com.pplam.models;
import com.pplam.helpers.Helpers;
import com.pplam.interfaces.IRmiMonitor;
import com.pplam.interfaces.IRmiServer;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Coordinator extends UnicastRemoteObject implements IRmiServer {
    private Vector<IRmiMonitor> monitors = new Vector<>();
    private String[] listMonitors;
    public static int quantityMonitors = 0;
    public long time;

    /**
     * @param time tiempo a esperar
     * @throws RemoteException
     */
    public Coordinator(long time) throws RemoteException {
        super();
        this.setTime(time);
    }

    /**
     * metodo sobreescrito de la interfaz IRmiServer
     */
    @Override
    public void initMonitor(IRmiMonitor monitor) throws RemoteException {
        System.out.println("Entra a la funcion");
        try {
            monitors.addElement(monitor);
            System.out.println("Se agrego el monitor: " + monitor.hashCode());
        } catch (Exception exception) {
            System.out.println("No se agrego");
        }
    }

    /**
     * metodo sobreescrito de la interfaz IRmiServer
     */
    @Override
    public synchronized String loadMonitor() {
        return "Informacion del CPU: " + Helpers.getContentFile();
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
            if(!remote.equals("Monitor"))
                ++total;
        }
        quantityMonitors = total;
        return quantityMonitors;
    }

    @Override
    public String getLoadAvg(){
        return loadMonitor();
    }

    public Vector<IRmiMonitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(Vector<IRmiMonitor> monitors) {
        this.monitors = monitors;
    }

    @Override
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
