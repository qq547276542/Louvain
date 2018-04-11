public class Edge implements Cloneable{  
    int v;     //v表示连接点的编号,w表示此边的权值  
    double weight;  
    int next;    //next负责连接和此点相关的边  
    Edge(){}  
    public Object clone(){  
        Edge temp=null;  
        try{    
            temp = (Edge)super.clone();   //浅复制    
        }catch(CloneNotSupportedException e) {    
            e.printStackTrace();    
        }     
        return temp;  
    }  
}  