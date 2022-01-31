package hellojpa;

public class ValueMain {

    public static void main(String[] args) {
        int a = 10;
        int b = a; // 기본타입)) 공유 되는 것 아님 : 값만 넘어감

        a = 20;

        System.out.println("a = " + a);
        System.out.println("b = " + b);;

        Integer c = new Integer(10);
        Integer d = c; // 공유 but 변경은 불가능 -> side effect x

        System.out.println("c = " + c);
        System.out.println("d = " + d);

        // == : 동일성비교 , 참조값 비교 (기본값타입은 이거해도됌)
        // equals() : 동등성비교, 값 비교 but 나머지 값타입들은 각각의 요소마다 equals해줘야하기때문에 따로 equals재정의 해야함



    }
}
