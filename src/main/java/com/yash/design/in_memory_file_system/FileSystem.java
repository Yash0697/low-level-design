package com.yash.design.in_memory_file_system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {

    DirectoryNode root;


    public FileSystem() {
        root = new DirectoryNode();
        
    }

    public List<String> ls(String path) {
        DirectoryNode temp = root;
        if(!path.equals("/")) {
            
            String [] subDirs = path.split("/");
            for(int i = 1; i < subDirs.length; i++) {
                String curr = subDirs[i];
                temp = temp.childPaths.get(curr);
                
                // last word after a / is a file
                if(i == subDirs.length - 1
//                        && temp.name != null
                ) {
                    return Arrays.asList(curr); // only one file
                }
            }
        }
        List<String> filesAndDirs = new ArrayList<>(root.childPaths.keySet());
        Collections.sort(filesAndDirs); 
        return filesAndDirs;
    }

    public void mkdir(String path) {
        DirectoryNode temp = root;
        String [] subDirs = path.split("/");

        for(int i = 1; i < subDirs.length; i++) {
            String curr = subDirs[i];
            if(!temp.childPaths.containsKey(curr)) 
                temp.childPaths.put(curr, new DirectoryNode());
            temp = temp.childPaths.get(curr); 
        }
    }

    public void addContentToFile(String filePath, String content) {
        DirectoryNode temp = root;
        String [] subDirs = filePath.split("/");

        for(int i = 1; i < subDirs.length; i++) {
            String curr = subDirs[i];
            if(!temp.childPaths.containsKey(curr)) 
                temp.childPaths.put(curr, new DirectoryNode());
            temp = temp.childPaths.get(curr); 
        }
        if( temp.fileContent == null) 
            temp.fileContent = content;
        else
            temp.fileContent += content;
    }

    public String readContentFromFile(String filePath) {
        DirectoryNode temp = root;
        String [] subDirs = filePath.split("/");

        for(int i = 1; i < subDirs.length; i++) {
            String curr = subDirs[i];
            if(!temp.childPaths.containsKey(curr)) 
                temp.childPaths.put(curr, new DirectoryNode());
            temp = temp.childPaths.get(curr); 
        }
        return temp.fileContent;
    }
    
}
 

class DirectoryNode {
    String fileContent;
    Map<String, DirectoryNode> childPaths;

    boolean isFile;

    public DirectoryNode() {
        this.fileContent = null;
        childPaths = new HashMap<>();
    }
}