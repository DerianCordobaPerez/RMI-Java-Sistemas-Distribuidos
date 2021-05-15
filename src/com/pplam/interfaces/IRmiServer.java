package com.pplam.interfaces;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que implementara la clase Remote del paquete rmi, la cual nos proporcionara los metodos solicitados
 * por la practica, si se necesitara cambiar algo sobre algun metodo se modificara desde este archivo.
 */
public interface IRmiServer extends Remote {
    /**
     * metodo invocado por los monitores, lo cual se encargara de agregar al mismo monitor al coordinador
     */
    void initMonitor(IRmiMonitor monitor) throws RemoteException, MalformedURLException;

    /**
     * metodo invocado por los monitores, sera invocado indefinida con el periodo obtenido del metodo initMonitor
     * @param result resultado obtenido del fichero /proc/loadavg
     */
    void loadMonitor(String result) throws RemoteException;

    /**
     * metodo invocado por los clientes, sera invocado de manera forzada para comprobar el numero de monitor que estan funcionando
     * @return int
     */
    int initClient() throws RemoteException;

    /**
     * metodo invocado por los clientes, obtiene el ultimo valor captura proviniente del metodo loadMonitor
     */
    void getLoadAvg() throws RemoteException;
}
