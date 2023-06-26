package dashboarddesign;

import com.seshare.seshare.Demo1;
import com.seshare.seshare.SeShareApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.jgroups.util.Util;

import javax.swing.*;
import java.util.Scanner;

public class HomeView {
    SeShareApp sam = new SeShareApp();

    String name=sam.email;

    @FXML
    private Button groups;
    @FXML
    private Button joinDS;

    //private AnchorPane buttonPane;
    @FXML
    private AnchorPane groupsAnchor;

    // Other code in the controller class
    @FXML
    public void handleGroupsButtonClick() throws Exception{

        groupsAnchor.setVisible(true);

        try{
            groups.setStyle("-fx-font-style: italics");
        }catch (Exception e){
            System.out.println(e.getCause());
        }


    }

    protected JChannel ch;



    public void start(String name) throws Exception {    try{
        ch=new JChannel("udp.xml").name(name)
                .setReceiver(new HomeView.MyReceiver(name))
                .connect("Distributed");



        for(;;){
            //  String line= Util.readStringFromStdin(">");
            String line = JOptionPane.showInputDialog(null, "Enter a value:", ">");

            if (line != null) {
                // User entered a value and clicked "OK"
                // Use the entered value stored in the 'line' variable

            } else {
                // User clicked "Cancel" or closed the dialog
                // Handle the case when no input is provided
            }
            System.out.println(line);
            ch.send(null, line);


        }

    } catch(Exception e){
        System.out.println(e.getCause());
    }
       // name=sam.email;



    }



    protected static class MyReceiver implements Receiver {
        protected final String name;
      //  HomeView.MyReceiver samson = new HomeView.MyReceiver("welcome");
      //  String sam =samson.name;
        




        protected MyReceiver(String name) {

            this.name=name;

         }

        public void receive(Message msg) {

            //System.out.printf("-- [%s] msg from %s: %s\n", name, msg.src(), msg.getObject());
            String message =String.format("%s:%s",name,msg.src(),msg.getObject());
            JOptionPane.showMessageDialog(null,message);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

        public void viewAccepted(View v) {
            String s= String.format("%s new view: %s\n",name,v);
            JOptionPane.showMessageDialog(null,"New Member:"+s+"Joined");

        }
    }
    @FXML
    public void joinDistributedSystems() throws Exception {
        //new Demo1().start();
      /// JOptionPane.showInputDialog("Enter Your Name",name);

        JOptionPane.showMessageDialog(null,"Welcome");
        //if(name)
        joinDS.setText("Welcome");
        try{

             // JOptionPane.showInputDialog("Enter Job Name " + i);
            new HomeView().start(name);
        }catch(Exception e){
            System.out.println(e.getCause());
        }



    }


}
