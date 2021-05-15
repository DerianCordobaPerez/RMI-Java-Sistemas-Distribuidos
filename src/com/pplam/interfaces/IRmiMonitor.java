package com.pplam.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmiMonitor extends Remote {
    void initClient() throws RemoteException;
    void initMonitor() throws RemoteException;
    void loadMonitor() throws RemoteException;
    void getLastStringFile() throws RemoteException;
    String getAvgLoad() throws RemoteException;
    void pingRemote() throws RemoteException;
}
