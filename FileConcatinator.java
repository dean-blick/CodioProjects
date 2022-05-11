import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.lang.*;
public class FileConcatinator{
    public static void main(String[] args){
        Scanner reader;
        if(args.length>0){
            reader = new Scanner(args[0]);
        }else{
            reader = new Scanner(System.in);
        }
        
        try{
            String print = "print";
            String cat = "cat";
            String mv = "mv";
            //Sets the input given to an array
            String inputLine = reader.nextLine();

            String[] input = inputLine.split(" ");
            if(input.length <= 1){System.out.println("Invalid input!");
            return;}
            String path;
            //Checks for command and executes
            // Print Command
            if(input[0].equals(print)){
                if(input.length != 2){
                    System.out.println("Invalid input!");
                    return;
                }
                path = input[1];
                if(Files.isDirectory(Paths.get(path))){
                    System.out.println(path +" is a directory!");
                    
                    }else if(Files.exists(Paths.get(path))){
                        
                        Scanner reader2 = new Scanner(Paths.get(path));
                        while(reader2.hasNextLine()){
                            System.out.println(reader2.nextLine());
                        }
                        
                        
                    }else{
                        System.out.println(path +" does not exist!");
                        
                    }
                
            //Cat command
            }else if(input[0].equals(cat)){
                if(input.length == 4){
                    if(Files.isDirectory(Paths.get(input[3]))){
                            System.out.println(input[3] +" is a directory!");
                            return;
                    }
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(input[3]))){
                        
                        for(int i = 1; i < 3; i++){
                            path = input[i];
                            if(Files.isDirectory(Paths.get(path))){
                                    System.out.println(path +" is a directory!");
                                    return;
                            }else if(Files.exists(Paths.get(path))){
                                
                                Scanner reader2 = new Scanner(Paths.get(path));
                                while(reader2.hasNextLine()){
                                    writer.write(reader2.nextLine());
                                    writer.newLine();
                                }
                                
                            }else{
                                System.out.println(path +" does not exist!");
                                return;
                            }
                        }
                    }catch(Exception e){System.out.println("Invalid input!");}
                }else{
                    System.out.println("Invalid input!");
                }  
            //Mv command
            }else if(input[0].equals(mv)){
                if(input.length > 3){
                    System.out.println("Invalid input!");
                    return;
                }
                String path1 = input[1];
                String path2 = input[2];
                if(Files.isDirectory(Paths.get(path1))){
                    System.out.println(path1 +" is a directory!");
                }else if(Files.isDirectory(Paths.get(path2))){
                    System.out.println(path2 +" is a directory!");
                }else if(Files.exists(Paths.get(path2))){
                    System.out.println(path2 +" already exists!");
                }else{
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(path2))){
                    Scanner reader2 = new Scanner(Paths.get(path1));
                    while(reader2.hasNextLine()){
                        writer.write(reader2.nextLine());
                        writer.newLine();
                    }
                    }catch(Exception e){System.out.println("Invalid input!");}
                    File file1 = new File(path1);
                    file1.delete();
                }
            }else{
                System.out.println("Invalid input!");
            }
        }catch(Exception e){System.out.println("Invalid input!");}
    }
}
