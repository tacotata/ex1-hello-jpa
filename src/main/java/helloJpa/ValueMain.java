package helloJpa;

public class ValueMain {

    public static void main(String[] args) {

        Integer a  = new Integer(10);
       //여기는 레퍼런스 (주소값)마저 넘어감
        Integer b = a;//a = 10  b = 10


//        int a = 10;
//        int b = a;
//
//        a= 20;

        //a값이 복사가 되서 b에 가니까 공유가 안됨
        System.out.println("a = " + a);//a = 20
        System.out.println("b = " + b);//b = 10
    }
}
