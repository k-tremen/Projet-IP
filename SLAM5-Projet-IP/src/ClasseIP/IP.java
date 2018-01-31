/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasseIP;
/**
 *
 * @author k-tremen
 */
public class IP {

    private int octet1, octet2, octet3, octet4;

    public IP(int octet1, int octet2, int octet3, int octet4) {
                this.octet1 = octet1;
                this.octet2 = octet2;
                this.octet3 = octet3;
                this.octet4 = octet4;
    }
    
    public static IP getInstance(int octet1, int octet2, int octet3, int octet4) throws Exception {
        if (octet1 < 0 || octet1 > 255 || octet2 < 0 || octet2 > 255 || octet3 < 0 || octet3 >255 || octet4 < 0 || octet4 > 255) {
            throw new Exception("Adresse IP invalide");
        }
        else {
            IP ip = new IP(octet1, octet2, octet3, octet4);
            return ip;
        }
    }

    public char getClasse() {
        char classe = ' ';
        if (this.getOctet1() >= 1 && this.getOctet1() <= 127) {
            classe = 'A';
        }
        if (this.getOctet1() >= 128 && this.getOctet1() <= 191) {
            classe = 'B';
        }
        if (this.getOctet1() >= 192 && this.getOctet1() <= 223) {
            classe = 'C';
        }

        return classe;
    }

    public IP adresseReseau() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        
        if (this.getClasse() == 'A') {
            a = this.octet1;
        }
        if (this.getClasse() == 'B') {
            a = this.octet1;
            b = this.octet2;
        }
        if (this.getClasse() == 'C') {
            a = this.octet1;
            b = this.octet2;
            c = this.octet3;
        }

        return new IP(a,b,c,d);
    }

    public boolean estMemeReseau(IP ip) {
        return (this.adresseReseau().octet1 == ip.adresseReseau().octet1 && this.adresseReseau().octet2 == ip.adresseReseau().octet2 && this.adresseReseau().octet3 == ip.adresseReseau().octet3);
    }
    
    public IP getIPsuivant() {
        int a = this.getOctet1();
        int b = this.getOctet2();
        int c = this.getOctet3();
        int d = this.getOctet4();
        
        if(d < 255) {
            return new IP(a,b,c,d+1);
        }
        if(d == 255 && c < 255) {
            return new IP(a,b,c+1,0);
        }
        if(d == 255 && c == 255 && b < 255) {
            return new IP(a,b+1,0,0);
        }
        if(d == 255 && c == 255 && b == 255 && a < 255) {
            return new IP(a+1,0,0,0);
        }
        else {
            return this;
        }
    }

    public int getOctet1() {
        return octet1;
    }

    public int getOctet2() {
        return octet2;
    }

    public int getOctet3() {
        return octet3;
    }

    public int getOctet4() {
        return octet4;
    }

    @Override
    public String toString() {
        return this.getOctet1() + "." + this.getOctet2() + "." + this.getOctet3() + "." + this.getOctet4();
    }
    
    public String toStringBinaire() {
        return Integer.toBinaryString(getOctet1()) + "." + Integer.toBinaryString(getOctet2()) + "." + Integer.toBinaryString(getOctet3()) + "." + Integer.toBinaryString(getOctet4());
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        IP ip1 = new IP(192,10,14,17);
        
        System.out.println(ip1.getOctet1());
        System.out.println(ip1.getClasse());
        System.out.println(ip1.adresseReseau());
        
        System.out.println(ip1.estMemeReseau(new IP(192,10,14,40)));
        System.out.println(ip1.toStringBinaire());
        
        System.out.println(ip1.getIPsuivant());
        
        System.out.println(getInstance(255,255,255,1));
    }

}
