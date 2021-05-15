package com.pplam.models;
import com.pplam.interfaces.IRmiMonitor;
import com.pplam.interfaces.IRmiServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Monitor extends UnicastRemoteObject implements IRmiMonitor {
    private long time;
    private String lastString;
    private final IRmiServer coordinator;

    /**
     * Constructor Monitor
     */
    public Monitor(IRmiServer coordinator, long time) throws RemoteException {
        super();
        this.coordinator = coordinator;
        setTime(time);
    }

    /**
     * devuelve el time
     * @return double
     */
    public long getTime() {
        return time;
    }

    /**
     * establece el time
     * @param time tiempo a establecer
     */
    public void setTime(long time) {
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

    @SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
    @Override
    public synchronized void loadMonitor() throws RemoteException {
        do {
            try {
                System.out.println(this.coordinator.loadMonitor());
                Thread.sleep(this.time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(true);
    }

    @Override
    public void getLastStringFile() throws RemoteException {
        this.lastString = this.coordinator.loadMonitor();
    }

    @Override
    public String getAvgLoad() throws RemoteException {
        getLastStringFile();
        return this.lastString;
    }

    @Override
    public void pingRemote() throws RemoteException {
        InetAddress ip, ping;
        try {
            ip = InetAddress.getLocalHost();
            ping = InetAddress.getByName(ip.getHostAddress());
            if(ping.isReachable(5000)) {
                System.out.println("Monitor hizo ping");
                this.coordinator.initClient();
            }
            else System.out.println("Monitor no hizo ping");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
