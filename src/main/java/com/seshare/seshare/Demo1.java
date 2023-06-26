package com.seshare.seshare;

import org.jgroups.*;
import org.jgroups.util.Util;

import javax.swing.*;
import java.util.*;

import java.util.*;
public class Demo1 {





    protected JChannel ch;



    public void start(String name) throws Exception {

        ch=new JChannel("udp.xml").name(name)
                .setReceiver(new MyReceiver(name))
                .connect("DistributedSystems");



        for(;;){
            String line=Util.readStringFromStdin(">");
            ch.send(null, line);


        }

    }



    protected static class MyReceiver implements Receiver {
        protected final String name;


        protected MyReceiver(String name) {

            this.name=name;


        }

        public void receive(Message msg) {

            System.out.printf("-- [%s] msg from %s: %s\n", name, msg.src(), msg.getObject());
        }


    }


    /*Implement abstract class*/




    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        String username=input.nextLine();
        new Demo1().start(username);
    }
}