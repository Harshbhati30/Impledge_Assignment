import java.io.*;
import java.util.*;

public class WordCompound{

    static class Node{
        Node[] children = new Node[26];
        boolean eow = false;
    }

    static Node root = new Node();

    public static void insert(String word){
        Node curr = root;
        for(int i=0;i<word.length();i++){
            int ind =word.charAt(i) - 'a';
            if(curr.children[ind] == null){
                curr.children[ind] =new Node();
            } 
            curr=curr.children[ind];  
        }
        curr.eow=true;
    }

    public static boolean search(String word){
        Node curr = root;
        for(int i=0;i<word.length();i++){
            int ind= word.charAt(i) - 'a';
            if(curr.children[ind] ==null){
                return false;
            }
            if(i==word.length()-1 && curr.children[ind].eow ==false){
                return false;
            }
            curr= curr.children[ind];

        }
        return true;
    }

    public static boolean compound(String word , boolean realword){
        Node curr= root;
        for(int i=0;i<word.length();i++){
            int ind = word.charAt(i)-'a';
            if(curr.children[ind]== null){
                return false;
            }

            curr= curr.children[ind];

            if(curr.eow){
                String end = word.substring(i+1);
                if(end.isEmpty()){
                    if(!realword) return true;
                    continue;
                }
                if(search(end)){
                   return true;
                }

                if(compound(end,false)){
                    return true;
                }
            }
        }
        return false;

    }


    public static void file(String path) throws IOException{
        root = new Node();
        long start =System.currentTimeMillis();

        List<String> words = new ArrayList<>();

        BufferedReader br= new BufferedReader(new FileReader(path));
        String line;
        while((line =br.readLine()) != null){
            line = line.trim();
            if(!line.isEmpty()){
                words.add(line);
            }
        }
            br.close();

            for(String word :words){
                insert(word);
            }

            words.sort((a,b) -> b.length() - a.length());

            String longest ="";
            String seclongest="";

            for(String word : words){
                if(compound(word,  true)){
                    if(longest.isEmpty()){
                        longest =word;
                    }
                    else{
                        seclongest = word;
                        break;
                    }

                }
            }

            long end = System.currentTimeMillis();

            System.out.println("File   : " + path);
            System.out.println("Longest Word  : " + longest );
            System.out.println("second longest word  : " + seclongest);
            System.out.println("Time taken   : " + (end -start) + "ms");
        }

        public static void main(String[] args) throws IOException {
            file("Input_01.txt");
            file("Input_02.txt");
   
        }
    }
