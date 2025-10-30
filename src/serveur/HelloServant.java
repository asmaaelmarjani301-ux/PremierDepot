package serveur;

import org.omg.CORBA_2_3.ORB;

import HelloWorldApp.HelloPOA;

public class HelloServant extends HelloPOA {
    private String message = "Bonjour tous Le monde !!";
    private ORB orb;

    public void setOrb(org.omg.CORBA.ORB orb2) { this.orb = (ORB) orb2; }

    @Override
    public String HelloMessage() {
        return message;
    }

    @Override
    public void HelloMessage(String newHelloMessage) {
        message = newHelloMessage;
    }
}
