/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myAgents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREInitiator;
import jade.proto.ContractNetInitiator;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author igomez
 */
public class TermostatoAgent_Initator extends Agent {

    /*public class HelloWorldCyclicBehaviour extends CyclicBehaviour {

        MessageTemplate tmpl;

        public HelloWorldCyclicBehaviour() {
        }

        public void onStart() {
            tmpl = MessageTemplate.MatchSender((new AID("Termometro", AID.ISLOCALNAME)));
        }

        public int onEnd() {
            System.out.println("Bye..");
            return 0;
        }

        public void action() {
            ACLMessage msg = receive(tmpl);
            if (msg != null) {
                int n = Integer.parseInt(msg.getContent());

                if (n < 15) System.out.println("calefacción encendida (" + n + "°C)");
                else if (n > 25) System.out.println("refrigeración encendida (" + n + "°C)");
                else System.out.println("temperatura adecuada (" + n + "°C)");
            }
            else {
                block();
            }
        }

    }*/

    private class AchieveREInitatorBehaviour extends AchieveREInitiator {
        public AchieveREInitatorBehaviour(Agent a, ACLMessage msg) {
            super(a, msg);
        }

        protected void handleInform(ACLMessage inform) {
            //System.out.println("Protocol finished. Rational Effect achieved. Received the following message: "+inform);
            int n = Integer.parseInt(inform.getContent());

            if (n < 15) System.out.println("calefacción encendida (" + n + "°C)");
            else if (n > 25) System.out.println("refrigeración encendida (" + n + "°C)");
            else System.out.println("temperatura adecuada (" + n + "°C)");

        }


    }

    protected void setup() {



        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        request.addReceiver(new AID("Termometro_Responder", AID.ISLOCALNAME));

        AchieveREInitatorBehaviour b = new AchieveREInitatorBehaviour(this, request);
        this.addBehaviour(b);

    }
}
