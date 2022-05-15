package helloJpa;

public class ValueMain {

    public static void main(String[] args) {


       int a = 10;
       int b = a;


        System.out.println("a == b : "+ (a == b) );//true

        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");
        // == 는 참조값을 비교하는데 이건 인스턴스 자체가 다르기 때문에 false나옴
        System.out.println("(address1 == address2) = " + (address1 == address2));//false
        //equals 기본은 == 비교라서 오버라이드를 해야함
        //address에서 equals 생성하고나면 (address1 equals address2) = true
        System.out.println("(address1 equals address2) = " + (address1.equals(address2) ));//false


    }
}
