package com.pplam.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmiMonitor extends Remote {
    void initClient() throws RemoteException;
    void initMonitor() throws RemoteException;
}
