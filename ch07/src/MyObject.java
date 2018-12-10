
public class MyObject {
    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768; // ldc
        MyObject myObject = new MyObject(); // new
        MyObject.staticVar = x; // putStatic
        x = MyObject.staticVar; // getStatic
        myObject.instanceVar = x; // putField
        x = myObject.instanceVar; // getField
        Object obj = myObject;
        if (obj instanceof MyObject) { // instanceof
            myObject = (MyObject) obj; // checkcast
            System.out.println(myObject.instanceVar);
        }
    }
}