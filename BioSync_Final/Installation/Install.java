import java.io.*; 
public class Install { 
	public static void main(String args[]) { 
		try { 
			//finding current directory
			String directory_Path="cmd /c cd";
			Process process_Path=Runtime.getRuntime().exec(directory_Path); 
			process_Path.waitFor(); 
			BufferedReader reader_Path=new BufferedReader(
				new InputStreamReader(process_Path.getInputStream())
			); 
			String line_Path=reader_Path.readLine();

			//Copying BioSync.jar file to auto startup location
			String installCommand="cmd /c COPY "+line_Path+"\\BioSync.jar \"C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\StartUp\"";
			Process process_Install=Runtime.getRuntime().exec(installCommand); 
			process_Install.waitFor();
			BufferedReader reader_Install=new BufferedReader(
				new InputStreamReader(process_Install.getInputStream())
			); 

			//checking whether file has been copied correctly or not 
			String line_Install,output_Install=""; 
			while((line_Install = reader_Install.readLine()) != null){
				output_Install+=line_Install;
			}

			//copying DarwinboxLogo.jpg file to ProgramFiles Darwinbox directory
			String copyLogo="cmd /c mkdir \"C:\\Program Files\\Darwinbox\" && copy DarwinboxLogo.jpg \"C:\\Program Files\\Darwinbox\"";
			Process logoCopy=Runtime.getRuntime().exec(copyLogo); 
			logoCopy.waitFor();
			
			if(output_Install.indexOf("0 file(s) copied.")!=-1){
				System.out.println("Error Occured\n"+output_Install);
			}
			else{
				System.out.println("Successfully installed\nconfigure the details\n");
				String openJarCommand="cmd /c start /B "+line_Path+"\\BioSync.jar";
				Process process_jar=Runtime.getRuntime().exec(openJarCommand);
			}
		}
		catch(IOException e1) {} 
		catch(InterruptedException e2) {} 
	} 
}