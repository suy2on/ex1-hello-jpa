package hellojpa;

public class ValueMain {

    public static void main(String[] args) {
        int a = 10;
        int b = a; // 공유 되는 것 아님 : 값만 넘어감

        a = 20;

        System.out.println("a = " + a);
        System.out.println("b = " + b);;

        Integer c = new Integer(10);
        Integer d = c; // 공유 but 변경은 불가능 -> side effect x

        System.out.println("c = " + c);
        System.out.println("d = " + d);


    }
}
