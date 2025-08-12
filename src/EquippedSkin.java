import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EquippedSkin {
    private Image birdSkin;
    private int[] list;
    private int skinIndex;
   public EquippedSkin(){
       try{
           String link = new String("resources/data/equippedSkin.txt");
           File inputFile=new File(link);
           Scanner sc=new Scanner(inputFile);
           if(sc.hasNextLine()){
               String line=sc.nextLine();
               String[] values=line.split(",");
               int[] data=new int[values.length];
               this.list =data;
               for(int i=0;i<values.length;i++){
                   int j=Integer.parseInt(values[i].trim());
                   list[i]=j;
                   {
                       if(j==1){
                           skinIndex=i;
                       }
                   }
               }
           }
           String birdSkinLink=new String("resources/birdSkins/"+"bird"+skinIndex+".png");
birdSkin=new ImageIcon(birdSkinLink).getImage();
sc.close();
       }catch(IOException e){
           e.printStackTrace();
       }
   }
   public void equipSkin(int skinNum){
       for(int i=0;i<list.length;i++){
           if(i==skinNum){
               list[i]=1;
           }else{
               list[i]=0;
           }
       }
       skinIndex=skinNum;
       saveEquippedSkin();
   }
   private void saveEquippedSkin(){
       try {
           String link = "resources/data/equippedSkin.txt";
           PrintWriter writer = new PrintWriter(link);
           for(int i=0;i<list.length;i++){
           writer.println(list[i] + ",");
           }


           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   public int getSkinIndex(){
       return skinIndex;
   }
   public Image getCurrentSkin(){
       return birdSkin;
   }

   }



