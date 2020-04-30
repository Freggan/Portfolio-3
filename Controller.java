package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;
import java.io.*;
import java.util.Stack;

public class Controller {

    @FXML
    ChoiceBox OriginPick = new ChoiceBox();

    @FXML
    ChoiceBox DestinationPick = new ChoiceBox();

    @FXML
    ChoiceBox HeuristicPick = new ChoiceBox();

    @FXML
    Button Run = new Button();

    @FXML
    TextArea Results = new TextArea();

    public void buttonPressRun(ActionEvent event) {
        Boolean Manhat;
        if(HeuristicPick.getValue() == "Manhattan") {
            Manhat = true;
        }
        else {
            Manhat = false;
        }
        AStarGraph MyMaze = new AStarGraph();

        Vertex x=new Vertex("x",0,0); Vertex y=new Vertex("y",0,0);
        Vertex A=new Vertex("A",0,4); Vertex B=new Vertex("B",1,7);
        Vertex C=new Vertex("C",4,0); Vertex D=new Vertex("D",3,7);
        Vertex E=new Vertex("E",3,3); Vertex F=new Vertex("F",6,6);
        Vertex G=new Vertex("G",7,2); Vertex H=new Vertex("H",8,7);
        Vertex I=new Vertex("I",9,2); Vertex J=new Vertex("J",11,5);

        MyMaze.addvertex(A); MyMaze.addvertex(B);
        MyMaze.addvertex(C); MyMaze.addvertex(D);
        MyMaze.addvertex(E); MyMaze.addvertex(F);
        MyMaze.addvertex(G); MyMaze.addvertex(H);
        MyMaze.addvertex(I); MyMaze.addvertex(J);

        MyMaze.newconnection(A,B,3.41); MyMaze.newconnection(A,C,6.82);
        MyMaze.newconnection(B,D,2.00); MyMaze.newconnection(C,G,4.41);
        MyMaze.newconnection(C,I,4.82); MyMaze.newconnection(D,E,4.00);
        MyMaze.newconnection(E,F,6.23); MyMaze.newconnection(F,G,4.41);
        MyMaze.newconnection(F,H,3.82); MyMaze.newconnection(G,H,5.41);
        MyMaze.newconnection(G,I,2.82); MyMaze.newconnection(H,J,4.41);
        MyMaze.newconnection(I,J,3.82);

        if(OriginPick.getValue().equals("A")) {
            x = A;
        } else if(OriginPick.getValue().equals("B")) {
            x = B;
        } else if(OriginPick.getValue().equals("C")) {
            x = C;
        } else if(OriginPick.getValue().equals("D")) {
            x = D;
        } else if(OriginPick.getValue().equals("E")) {
            x = E;
        } else if(OriginPick.getValue().equals("F")) {
            x = F;
        } else if(OriginPick.getValue().equals("G")) {
            x = G;
        } else if(OriginPick.getValue().equals("H")) {
            x = H;
        } else if(OriginPick.getValue().equals("I")) {
            x = I;
        } else {
            x = J;
        }

        if(DestinationPick.getValue().equals("A")) {
            y = A;
        } else if(DestinationPick.getValue().equals("B")) {
            y = B;
        } else if(DestinationPick.getValue().equals("C")) {
            y = C;
        } else if(DestinationPick.getValue().equals("D")) {
            y = D;
        } else if(DestinationPick.getValue().equals("E")) {
            y = E;
        } else if(DestinationPick.getValue().equals("F")) {
            y = F;
        } else if(DestinationPick.getValue().equals("G")) {
            y = G;
        } else if(DestinationPick.getValue().equals("H")) {
            y = H;
        } else if(DestinationPick.getValue().equals("I")) {
            y = I;
        } else {
            y = J;
        }

        if(MyMaze.A_Star(x,y,Manhat))
        {
            String message = "Found a path: \n" ;
            Vertex pvertex=y;
            Stack<Vertex> Path = new Stack();
            int limit=0;
            while (pvertex!=null)
            {
                Path.push(pvertex);
                pvertex=pvertex.getPrev();
            }
            if(!Path.isEmpty())
                limit =Path.size();
            for(int i=0;i<limit-1;i++)
                message = message + Path.pop().getid() +" - > ";
            if (limit>0)
                message = message + Path.pop().getid();
                Results.setText(message);

        }
        else
            Results.setText("DID NOT FIND A PATH!!");



    }

}
