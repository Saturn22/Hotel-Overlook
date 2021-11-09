import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileIO {

	// Writes the given object to a file with the given file name
	public void writeToFile(String fileName, Object obj) throws FileNotFoundException, IOException{
		ObjectOutputStream write = null;
		try{
			FileOutputStream fileOutput = new FileOutputStream(fileName);
			write = new ObjectOutputStream(fileOutput);
			write.writeObject(obj);
		}finally{
			if(write != null){
				try{
				write.close();
				}catch(IOException e){
					System.out.println("IO Error closing file - " + fileName);
				}
			}
		}
	}
	
	// Writes the objects in the given array to a file with the given file name
	public void writeToFile(String fileName, Object[] objs) throws FileNotFoundException, IOException{
		ObjectOutputStream write = null;
		try{
			FileOutputStream fileOutput = new FileOutputStream(fileName);
			write = new ObjectOutputStream(fileOutput);
			for(int i=0; i<objs.length; i++){
				write.writeObject(objs[i]);
			}
		}finally{
			if(write != null){
				try{
					write.close();
				}catch(IOException e){
					System.out.println("IO Error closing file - " + fileName);
				}
			}
		}
		
	}
	
	// Reads the first object from the file with the given file name and returns it.
	// Whoever calls the method will need to cast it from type Object to its real type
	public Object readObjectFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		Object obj = null;
		ObjectInputStream read = null;
		try{
			FileInputStream fileInput = new FileInputStream(fileName);
			read = new ObjectInputStream(fileInput);
			try{
			obj = read.readObject();
			}catch(EOFException eof){
				//Done reading
			}
		}finally{
			if(read != null){
				try{
					read.close();
				}catch(IOException e){
					System.out.println("IO Error closing file - " + fileName);
				}
			}
		}
		return obj;
	}
	
	// Reads all objects from the file with the given file name and returns it as an Object[].
	// Whoever calls the method will need to cast the Objects to their real type
	public Object[] readArrayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
		ArrayList<Object> objs = new ArrayList<Object>();
		ObjectInputStream read = null;
		try{
			FileInputStream fileInput = new FileInputStream(fileName);
			read = new ObjectInputStream(fileInput);
			while(true){
				try{
					objs.add(read.readObject());
				}catch(EOFException eof){
					//Done reading
					break;
				}
			}
		}finally{
			if(read != null){
				try{
					read.close();
				}catch(IOException e){
					System.out.println("IO Error closing file - " + fileName);
				}
			}
		}
		String[] strArray = new String[objs.size()];
		return objs.toArray(strArray);
	}
}
