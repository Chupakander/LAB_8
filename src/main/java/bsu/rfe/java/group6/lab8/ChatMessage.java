package bsu.rfe.java.group6.lab8;

import javax.swing.*;

public class ChatMessage {
    // Текст сообщения
    private String message;
    // Автор сообщения
    private ChatUser author;

    private String[] smiles = {":)", ";)", ":(", ":D"};

    private ImageIcon imageIcon;
    // Временная метка сообщения (в микросекундах)
    private long timestamp;

    public ChatMessage(String message, ChatUser author, long timestamp, ImageIcon imageIcon) {
        this.message = message;
        this.imageIcon = imageIcon;
        this.author = author;
        this.timestamp = timestamp;
    }


    public String getMessage() {
        StringBuffer str = new StringBuffer(message);
        if (message.contains(smiles[0])){
            int pos = message.indexOf(smiles[0]);
            str = str.delete(pos, pos+2);
            message = str.toString();
            // message = message.concat(" *тут должен быть смайлик улыбки*");
            return message;
        }
        if (message.contains(smiles[1])){
            int pos = message.indexOf(smiles[1]);
            str = str.delete(pos, pos+2);
            message = str.toString();
            // message = message.concat(" *тут должен быть подмигивающий смайлик*");
            return message;
        }
        if (message.contains(smiles[2])){
            int pos = message.indexOf(smiles[2]);
            str = str.delete(pos, pos+2);
            message = str.toString();
            //message = message.concat(" *тут должен быть грустный смайлик*");
            return message;
        }
        if (message.contains(smiles[3])){
            int pos = message.indexOf(smiles[3]);
            str = str.delete(pos, pos+2);
            message = str.toString();
            //message = message.concat(" *тут должен быть смеющийся смайлик*");
            return message;
        }
        else return message;
    }


    public ChatUser getAuthor() {
        return author;
    }

    public void setAuthor(ChatUser author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ImageIcon getSmile(){
        return imageIcon;
    }
}
