package com.pplam;
import com.pplam.models.Coordinator;
import java.rmi.Naming;
import java.util.Scanner;

import static com.pplam.helpers.Helpers.startRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Ingrese el tiempo que esperara");
            double time = new Scanner(System.in).nextDouble();
            startRegistry(1099);
            Coordinator coordinator = Coordinator.getInstance();
            coordinator.setTime(time);
            Naming.rebind("rmi://localhost:1099/Coordinator", coordinator);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
