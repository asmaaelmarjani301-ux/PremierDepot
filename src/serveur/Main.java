package serveur;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import HelloWorldApp.*;

public class Main {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            HelloServant helloServant = new HelloServant();
            helloServant.setOrb(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloServant);
            Hello hello = HelloHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name("Hello");
            ncRef.rebind(path, hello);

            System.out.println("Hello object registered in NameService");
            System.out.println("HelloWorld Server ready and waiting...");

            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }
}
