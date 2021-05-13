package com.pplam.models;
import com.pplam.helpers.Helpers;
import com.pplam.interfaces.IRmiServer;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import static java.lang.Thread.sleep;

public class Coordinator extends UnicastRemoteObject implements IRmiServer {
    private ArrayList<Monitor> monitors = new ArrayList<>();
    private static Coordinator coordinator;
    private String[] listMonitors;
    public static int quantityMonitors = 0;
    private double time;
    /**
     * Constructor Coordinator
     */
    private Coordinator() throws RemoteException {
        super();
    }

    /**
     * retorna una unica instancia de la clase Coordinator usando el patron singleton
     * @return Coordinator
     * @throws RemoteException
     */
    public static Coordinator getInstance() throws RemoteException {
        if(coordinator == null)
            coordinator = new Coordinator();
        return coordinator;
    }

    /**
     * metodo sobreescrito de la interfaz IRmiServer
     * @return double
     */
    @Override
    public double initMonitor(Monitor monitor) {
        this.monitors.add(monitor);
        return this.getTime();
    }

    /**
     * metodo sobreescrito de la interfaz IRmiServer
     * @param result resultado obtenido del fichero /proc/loadavg
     */
    @Override
    public void loadMonitor(String result) {
        do {
            System.out.println("Valor contenido: " + result);
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
            if(remote.equals("Coordinator"))
                try {
                    Naming.unbind(remote);
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

    public ArrayList<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(ArrayList<Monitor> monitors) {
        this.monitors = monitors;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
