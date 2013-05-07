/**
 * @date Apr 30, 2013
 * @author Ben Cochrane
 */
package cc.ngon.io;

/**
 * A logging class with a short name for ease of use.
 */
public final class L {
    
    private L() {}
    
    public static void p(int m) { System.out.println(m); }
    public static void p(double m) { System.out.println(m); }
    public static void p(float m) { System.out.println(m); }
    public static void p(byte m) { System.out.println(m); }
    public static void p(char m) { System.out.println(m); }
    public static void p(Object m) { System.out.println(m); }
    
    public static void ex(Exception e) { e.printStackTrace(); }
    
    /*public static void v(int m) { System.out.println(m); }
    public static void v(double m) { System.out.println(m); }
    public static void v(float m) { System.out.println(m); }
    public static void v(byte m) { System.out.println(m); }
    public static void v(char m) { System.out.println(m); }
    public static void v(Object m) { System.out.println(m); }
    
    public static void d(int m) { System.out.println(m); }
    public static void d(double m) { System.out.println(m); }
    public static void d(float m) { System.out.println(m); }
    public static void d(byte m) { System.out.println(m); }
    public static void d(char m) { System.out.println(m); }
    public static void d(Object m) { System.out.println(m); }
    
    public static void e(int m) { System.out.println(m); }
    public static void e(double m) { System.out.println(m); }
    public static void e(float m) { System.out.println(m); }
    public static void e(byte m) { System.out.println(m); }
    public static void e(char m) { System.out.println(m); }
    public static void e(Object m) { System.out.println(m); }*/
    
}
