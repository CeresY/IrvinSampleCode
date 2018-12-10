package thread.threadLocal;
/**
  * @author asuka
  * @date 2018年12月10日
  */
public class Son implements Cloneable{
    public static void main(String[] args){
        Son p=new Son();
        System.out.println("1:" + p);
        Thread t = new Thread(new Runnable(){  
            public void run(){
                ThreadLocal<Son> threadLocal = new ThreadLocal<>();
                System.out.println("2:" + threadLocal);
                threadLocal.set(p);
                System.out.println("3:" + threadLocal.get());
                threadLocal.remove();
                try {
                    threadLocal.set((Son) p.clone());
                    System.out.println("4:" + threadLocal.get());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                System.out.println("5:" + threadLocal);
            }}); 
        t.start();
    }
}