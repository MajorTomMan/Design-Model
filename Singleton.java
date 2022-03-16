



/**
 * 单例模式
 * 1.保证一个类只有一个实例。
 * 2.为该实例提供一个全局访问节点
 * 3.仅在首次请求单例对象时对其进行初始化。
 */
public class Singleton {
    private static Singleton instance;
    public String value;
    private Singleton(String value) {
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        this.value=value;
    }
    public static Singleton getInstance(String value){
        if(instance == null){
            instance=new Singleton(value);
        }
        return instance;
    }
}



class DemoSingleThread {
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Singleton singleton = Singleton.getInstance("FOO");
        Singleton anotherSingleton = Singleton.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);
    }
}