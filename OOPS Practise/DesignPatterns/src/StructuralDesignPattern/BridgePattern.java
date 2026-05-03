package StructuralDesignPattern;

interface MessageTypeSystem {
    void sendMessage(String message, String recipient);
}

class EmailMessage implements MessageTypeSystem {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Sending Email to " + recipient + ": " + message);
    }
}

class SMSMessage implements MessageTypeSystem {

    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}

abstract class Message {
    protected MessageTypeSystem messageTypeSystem;

    Message(MessageTypeSystem messageTypeSystem) {
        this.messageTypeSystem = messageTypeSystem;
    }

    abstract void send(String message, String recipient);
}

class UrgentMessage extends Message {

    UrgentMessage(MessageTypeSystem messageTypeSystem) {
        super(messageTypeSystem);
    }

    @Override
    void send(String message, String recipient) {
    messageTypeSystem.sendMessage("Urgent:"+ message, recipient);
    }
}

class EmergencyMessage extends Message {

    EmergencyMessage(MessageTypeSystem messageTypeSystem) {
        super(messageTypeSystem);
    }

    @Override
    void send(String message, String recipient) {
        messageTypeSystem.sendMessage("Urgent:"+ message, recipient);
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        MessageTypeSystem emailSender = new EmailMessage();
        MessageTypeSystem smsSender = new SMSMessage();

        Message emergencyMessage= new EmergencyMessage(emailSender);
        Message urgentSMS = new UrgentMessage(smsSender);
        emergencyMessage.send("Hello mergency", "Balaji");
        urgentSMS.send("Hi Please come fast for dinner", " John Miller");
    }



}
