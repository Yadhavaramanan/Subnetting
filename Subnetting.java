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
        int subnetMask = 0xFFFFFFFF << (32 - prefix);

        // Convert IP address to an integer
        String[] octets = ip.split("\\.");
        int ipAddress = (Integer.parseInt(octets[0]) << 24) |
                        (Integer.parseInt(octets[1]) << 16) |
                        (Integer.parseInt(octets[2]) << 8) |
                        Integer.parseInt(octets[3]);

        // Calculate network address
        int networkAddress = ipAddress & subnetMask;

        // Calculate broadcast address
        int broadcastAddress = networkAddress | ~subnetMask;

        // Print details
        System.out.println("Subnet Mask: " + intToIp(subnetMask));
        System.out.println("Network Address: " + intToIp(networkAddress));
        System.out.println("Broadcast Address: " + intToIp(broadcastAddress));

        // Host range
        System.out.println("Host Range: " + intToIp(networkAddress + 1) + " - " + intToIp(broadcastAddress - 1));
    }

    // Convert an integer to an IP address string
    public static String intToIp(int ip) {
        return ((ip >> 24) & 0xFF) + "." +
               ((ip >> 16) & 0xFF) + "." +
               ((ip >> 8) & 0xFF) + "." +
               (ip & 0xFF);
    }
}
