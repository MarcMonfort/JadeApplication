/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myAgents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import jade.proto.ContractNetResponder;
import jade.tools.sniffer.Message;

import java.util.Random;

/**
 * @author igomez
 */
public class TermometroAgent_Responder extends Agent {

    /*public class HelloWorldTickerBehaviour extends TickerBehaviour {

        ACLMessage msg;

        public HelloWorldTickerBehaviour(Agent a, long period) {
            super(a, period);
        }

        public void onStart() {
            msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("Termostato", AID.ISLOCALNAME));
            msg.setSender(getAID());
        }

        public int onEnd() {
            System.out.println("Bye..");
            return 0;
        }

        public void onTick() {
            Random rand = new Random();
            String n = Integer.toString(rand.nextInt(40) - 10);
            //System.out.println(n);

            msg.setContent(n);
            send(msg);
        }

    }*/

    private class AchieveREResponderBehaviour extends AchieveREResponder {

        public AchieveREResponderBehaviour(Agent a, MessageTemplate mt) {super(a, mt);}

        private String getTemperatura() {
            // Simulate temperature by generating a random number
            Random rand = new Random();
            return (Integer.toString(rand.nextInt(40) - 10));
        }

        protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) {
            System.out.println("Responder has received the following message: " + request);
            ACLMessage informDone = request.createReply();
            informDone.setPerformative(ACLMessage.INFORM);
            informDone.setContent(getTemperatura());
            return informDone;
        }


    }

    protected void setup() {

        MessageTemplate mt = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST);
        AchieveREResponderBehaviour b = new AchieveREResponderBehaviour(this, mt);
        this.addBehaviour(b);
    }
}
