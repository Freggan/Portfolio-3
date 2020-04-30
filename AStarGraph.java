package sample;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class AStarGraph {
    private ArrayList<Vertex> vertices;
    public AStarGraph() {
        vertices=new ArrayList<Vertex>();
    }
    public void addvertex(Vertex v) {
        vertices.add(v);
    }
    public void newconnection(Vertex v1, Vertex v2, Double dist) {
        v1.addOutEdge(v2,dist);
        v2.addOutEdge(v1,dist);
    }
    public boolean A_Star(Vertex start, Vertex destination,Boolean Manhat)
    {   if (start==null || destination==null)
        return false;
        PriorityQueue<Vertex> Openlist = new PriorityQueue<Vertex>();
        ArrayList<Vertex> Closedlist = new ArrayList<Vertex>();
        Openlist.offer(start);
        Vertex Current;
        ArrayList<Vertex> CurrentNeighbors;
        ArrayList<Double> CurrentNeighborsDistance;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        if(Manhat) {
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).seth(Manhattan(vertices.get(i), destination));
            }
        }
        else {
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).seth(Euclidean(vertices.get(i), destination));
            }
        }
        start.setg(0.0);
        start.calculatef();
        //Start algorithm
        System.out.println("Start Algorithm");

        Current=start;
        while (Current.getid()!=destination.getid())
        {
            CurrentNeighbors=Current.getNeighbours();
            CurrentNeighborsDistance=Current.getNeighbourDistance();

            for (int i=0;i<CurrentNeighbors.size();i++)
            {
                Neighbor=CurrentNeighbors.get(i);

                if ((!Closedlist.contains(Neighbor)) && (!Openlist.contains(Neighbor)))
                {
                    Neighbor.setg(Current.getg() + CurrentNeighborsDistance.get(i));
                    Openlist.offer(Neighbor);

                }

                if((Current.getg()+CurrentNeighborsDistance.get(i)+Neighbor.geth()) < Neighbor.getf())
                {
                    Neighbor.setg(Current.getg() + CurrentNeighborsDistance.get(i));
                    Neighbor.calculatef();
                    Neighbor.setPrev(Current);
                }

            }
            if (!Openlist.isEmpty())
            {
                Closedlist.add(Current);
                Current = Openlist.poll();
            }
            else{return false;}

        }
        return true;
    }
    public Double Manhattan(Vertex from,Vertex goal){
        return Double.valueOf(abs(from.getx() - goal.getx()) + abs(from.gety() - goal.gety()));

    }
    public Double Euclidean(Vertex from,Vertex to){
        return (sqrt ( pow(from.getx()-to.getx(),2) + pow(from.gety()-to.gety(),2)));
    }
}

class Vertex implements Comparable<Vertex>{
    private String id;
    private ArrayList<Vertex> Neighbours=new ArrayList<Vertex>();
    private ArrayList<Double> NeighbourDistance =new ArrayList<Double>();
    private Double f;
    private Double g;
    private Double h;
    private Integer x;
    private Integer y;
    private Vertex prev =null;
    public Vertex(String id, int x_cor,int y_cor){
        this.id=id;
        this.x=x_cor;
        this.y = y_cor;
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        h=0.0;
    }
    public void addOutEdge(Vertex toV, Double dist){
        Neighbours.add(toV);
        NeighbourDistance.add(dist);
    }
    public ArrayList<Vertex> getNeighbours(){
        return Neighbours;
    }
    public ArrayList<Double> getNeighbourDistance(){
        return NeighbourDistance;
    }
    public String getid(){ return id;};
    public Integer getx(){ return x; }
    public Integer gety(){return x; }
    public Double getf() { return f; }
    public void calculatef(){ f=g+h; }

    public Double getg() { return g; }

    public void setg(Double newg){ g=newg; }
    public Double geth(){ return h; }
    public void seth(Double estimate){ h=estimate; }
    public Vertex getPrev() { return prev; }
    public void setPrev(Vertex v){prev=v;}
    public void printVertex(){
        System.out.println(id + " g: "+g+ ", h: "+h+", f: "+f);
    }
    @Override
    public int compareTo(Vertex o) {
        if (o.getf()==f){return 0;}
        if (o.getf()<f){return 1;}
        else{return -1;}
    }
}
