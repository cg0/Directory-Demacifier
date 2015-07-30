package pe.nn.connor.directorydemacifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDemacifier {
	private static String applicationVersion = "v1.0.2";
	
	public static void main(String[] args) {
		if(args.length == 0){
			System.err.println("directorydemacifier PATH [--nosubfolders]");
			System.exit(0);
		}
		
		List<String> arguments = new ArrayList<String>();
		
		for(String argument : args){
			arguments.add(argument);
		}
		
		File path = new File(args[0]);
		if(path.exists()){
			if(path.isDirectory()){
				doStuff(path, arguments);
			}else{
				System.err.println("PATH must be directory");
			}
			
			System.out.println(String.format("Thanks for using DirectoryDemacifier V%s by Connor Graham (thecg.me)", applicationVersion));
		}else{
			System.err.println("PATH must exist");
		}
	}
	
	private static void doStuff(File directory, List<String> arguments){
		for(File file : directory.listFiles()){
			if(file.getName().startsWith("._") || file.getName().startsWith(".Spotlight") || file.getName().equals(".Trashes")){
				System.out.println("Deleted " + file.getAbsolutePath());
				file.delete();
			}
			if(file.isDirectory() && file.exists()){
				if(!arguments.contains("--nosubfolders")){
					doStuff(file, arguments);
				}
			}
		}
	}
}
