package cn.dk.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;


public class MD5Test {

    public static void main(String[] args) {
        Md5Hash hash = new Md5Hash("111",null,3);
        System.out.println(hash);
        System.out.println(hash.toString());

    }
}
