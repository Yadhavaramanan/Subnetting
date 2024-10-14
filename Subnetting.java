package server;

import java.util.Scanner;

public class Subnetting {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter IP address (e.g., 192.168.10.0): ");
        String ip = sc.nextLine();

        System.out.print("Enter CIDR notation (e.g., 24 for /24): ");
        int prefix = sc.nextInt();

        // Calculate the subnet mask
        int subnetMask = (int) (0xFFFFFFFFL << (32 - prefix));

        // Convert IP address to an integer
        int ipAddress = ipToInt(ip);

        // Calculate network and broadcast addresses
        int networkAddress = ipAddress & subnetMask;
        int broadcastAddress = networkAddress | ~subnetMask;

        // Display details
        System.out.println("Subnet Mask: " + intToIp(subnetMask));
        System.out.println("Network Address: " + intToIp(networkAddress));
        System.out.println("Broadcast Address: " + intToIp(broadcastAddress));

        // Host range
        System.out.println("Host Range: " + intToIp(networkAddress + 1) + " - " + intToIp(broadcastAddress - 1));
    }

    // Convert an IP string to an integer
    public static int ipToInt(String ip) {
        String[] octets = ip.split("\\.");
        return (Integer.parseInt(octets[0]) << 24) |
               (Integer.parseInt(octets[1]) << 16) |
               (Integer.parseInt(octets[2]) << 8) |
               Integer.parseInt(octets[3]);
    }

    // Convert an integer to an IP string
    public static String intToIp(int ip) {
        return ((ip >> 24) & 0xFF) + "." +
               ((ip >> 16) & 0xFF) + "." +
               ((ip >> 8) & 0xFF) + "." +
               (ip & 0xFF);
    }
}
