package code.bmsp.Bank;

import java.io.*;
import java.util.ArrayList;

public class FileHandling {
    private ArrayList<Object> allData=new ArrayList<>();

    public void w(String path, ArrayList<Object> nemp) throws IOException, ClassNotFoundException, NotSerializableException {
        File f=new File(path);
        if(f.exists()&& f.isFile()&&f.length()>0)
        {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
                allData = (ArrayList<Object>) in.readObject();
                in.close();
            }
        }
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(path));
        allData.addAll(nemp);
        out.writeObject(allData);
        out.close();
    }
    public ArrayList<Object> r(String path) throws IOException, ClassNotFoundException {
        ArrayList<Object>Alldata=new ArrayList<>();
        try
        {
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(path));

            Alldata =(ArrayList<Object>)in.readObject();
            in.close();
            return Alldata;
        } catch (Exception e) {
            System.out.println("no");
        }
        return Alldata;
    }
}
