package libs;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HangPham on 12/16/2016.
 */
/**
 * DeviceConfiguration - this class contains methods to start adb server, to get connected devices and their information.
 * first 2
 */
public class DeviceConfiguration {
    CommandPrompt cmd=new CommandPrompt();
    public Map<String,String> devices=new HashMap<String, String>();

    public void startADB() throws IOException, InterruptedException {
        String output=cmd.runCommand("adb start-server");
        String[] lines = output.split("\n");
        if(lines.length==1)
            System.out.println("adb service already started");
        else if(lines[1].equalsIgnoreCase("* daemon started successfully *"))
            System.out.println("adb service started");
        else if(lines[0].contains("internal or external command")){
            System.out.println("adb path not set in system available");
            System.exit(0);
        }
    }

    /**
     * This method stop adb server
     */
    public void stopADB() throws Exception{
        String output=cmd.runCommand("adb kill-server");
    }

    /**
     * This method return connected devices
     * @return hashmap of connected devices information
     */
    public Map<String,String> getDevices() throws Exception {
        startADB();
        String checkDevices=cmd.runCommand("adb devices");
        String[] lines=checkDevices.split("\n");

        if(lines.length<=1){
            System.out.println("No Device Connected");
            stopADB();
            System.exit(0);	// exit if no connected devices found
        }

        for(int i=1;i<lines.length;i++){
            lines[i]=lines[i].replace("\\s+","");

            if (lines[i].contains("device")){
                lines[i]=lines[i].replace("device","");
                String deviceID=lines[i];
                String model = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.product.model").replaceAll("\\s+", "");
                String brand = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.product.brand").replaceAll("\\s+", "");
                String osVersion = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.build.version.release").replaceAll("\\s+", "");
                String deviceName = brand+" "+model;

                devices.put("deviceID"+i,deviceID);
                devices.put("deviceName"+i, deviceName);
                devices.put("osVersion"+i, osVersion);

                System.out.println("Following device is connected");
                System.out.println(deviceID+" "+deviceName+" "+osVersion+"\n");

                //print list devices
//                for(Map.Entry<String, String> a:devices.entrySet()){
//                    System.out.println(a);
//                }
            }else if(lines[i].contains("unauthorized")){
            lines[i]=lines[i].replaceAll("unauthorized", "");
            String deviceID = lines[i];

            System.out.println("Following device is unauthorized");
            System.out.println(deviceID+"\n");
        }else if(lines[i].contains("offline")){
            lines[i]=lines[i].replaceAll("offline", "");
            String deviceID = lines[i];

            System.out.println("Following device is offline");
            System.out.println(deviceID+"\n");
        }

        }
        return devices;
    }
    public void getIpAddress(){
        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
    }
//    public static void main(String[] args) throws Exception {
//        DeviceConfiguration configuration=new DeviceConfiguration();
////        configuration.getDevices();
////        System.out.println(configuration.getDevices());
//        configuration.getIpAddress();
//    }

}
