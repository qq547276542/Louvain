import java.io.BufferedWriter;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.util.ArrayList;  
  
public class Main {  
    public static void writeOutputJson(String fileName, Louvain a) throws IOException {  
        BufferedWriter bufferedWriter;  
        bufferedWriter = new BufferedWriter(new FileWriter(fileName));  
        bufferedWriter.write("{\n\"nodes\": [\n");  
        for(int i=0;i<a.global_n;i++){  
            bufferedWriter.write("{\"id\": \""+i+"\", \"group\": "+a.global_cluster[i]+"}");  
            if(i+1!=a.global_n)  
            bufferedWriter.write(",");  
            bufferedWriter.write("\n");  
        }  
        bufferedWriter.write("],\n\"links\": [\n");  
          
        for(int i=0;i<a.global_n;i++){  
            for (int j = a.global_head[i]; j != -1; j = a.global_edge[j].next) {  
                int k=a.global_edge[j].v;  
                bufferedWriter.write("{\"source\": \""+i+"\", \"target\": \""+k+"\", \"value\": 1}");     
                if(i+1!=a.global_n||a.global_edge[j].next!=-1)  
                    bufferedWriter.write(",");  
                bufferedWriter.write("\n");  
            }  
        }  
        bufferedWriter.write("]\n}\n");  
        bufferedWriter.close();  
    }  
      
    static void writeOutputCluster(String fileName, Louvain a) throws IOException{  
        BufferedWriter bufferedWriter;  
        bufferedWriter = new BufferedWriter(new FileWriter(fileName));  
        for(int i=0;i<a.global_n;i++){  
            bufferedWriter.write(Integer.toString(a.global_cluster[i]));  
            bufferedWriter.write("\n");  
        }  
        bufferedWriter.close();  
    }  
      
    static void writeOutputCircle(String fileName, Louvain a) throws IOException{  
        BufferedWriter bufferedWriter;  
        bufferedWriter = new BufferedWriter(new FileWriter(fileName));  
        ArrayList list[] = new ArrayList[a.global_n];  
        for(int i=0;i<a.global_n;i++){  
            list[i]=new ArrayList<Integer>();  
        }  
        for(int i=0;i<a.global_n;i++){  
            list[a.global_cluster[i]].add(i);  
        }  
        for(int i=0;i<a.global_n;i++){  
            if(list[i].size()==0)  
                continue;  
            for(int j=0;j<list[i].size();j++)  
                bufferedWriter.write(list[i].get(j).toString()+" ");  
            bufferedWriter.write("\n");  
        }  
        bufferedWriter.close();  
    }       
    public static void main(String[] args) throws IOException {  
        // TODO Auto-generated method stub  
        Louvain a = new Louvain();  
        double beginTime = System.currentTimeMillis();  
        a.init("/Users/chenyuanzhe/Desktop/社团发现/Louvain/facebook_combined.txt");  
        a.louvain();  
        double endTime = System.currentTimeMillis();  
        //writeOutputJson("/var/www/html/Louvain/miserables.json", a);  //输出至d3显示  
        writeOutputCluster("/Users/chenyuanzhe/Desktop/社团发现/Louvain/cluster.txt",a);  //打印每个节点属于哪个簇  
        writeOutputCircle("/Users/chenyuanzhe/Desktop/社团发现/Louvain/circle.txt",a);   //打印每个簇有哪些节点  
        System.out.format("program running time: %f seconds%n", (endTime - beginTime) / 1000.0);  
    }  
  
}  