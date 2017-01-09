package libs;

import java.io.*;

/**
 * Created by HangPham on 12/14/2016.
 */
/**
 * Command Prompt - this class contains method to run windows and mac commands
 * first 1
 */

public class CommandPrompt {
    Process process;
    ProcessBuilder builder;

    /**
     * this method run command on windows and mac
     *
     * @param command
     */
    public String runCommand(String command) throws InterruptedException, IOException {
        //get os window and mac
        String os = System.getProperty("os.name");
        //first 1
        System.out.println(os);

        //build cmd progress according to os
        if (os.contains("Windows")) {
            // create the process builder
            builder = new ProcessBuilder("cmd.exe", "/c", command);//c: get from c disk
            //first 2
//            System.out.println("output: "+builder.redirectOutput());
            // Redirect the errorstream = true, cmd will run
            builder.redirectErrorStream(true);
//            System.out.println("builder return: " + builder.redirectErrorStream());
            Thread.sleep(1000);
            // create a new process
            process = builder.start();

        } else process = Runtime.getRuntime().exec(command);

        // get std output on cmd
        InputStreamReader reader=new InputStreamReader(process.getInputStream());
        BufferedReader r = new BufferedReader(reader);

        String line = "";
        String allLine = "";
        int i = 1;
        while ((line = r.readLine()) != null) {
            System.out.println(i+". "+line);
            allLine = allLine + "" + line + "\n";
            if (line.contains("Console LogLevel: debug"))
                break;
            i++;
        }
        return allLine;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CommandPrompt cp = new CommandPrompt();
        cp.runCommand("dir");
    }
}
