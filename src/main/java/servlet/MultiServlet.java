package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-2
 * @Vesion 1.0
 **/
public class MultiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的URI地址信息
        String url = request.getRequestURI();
        // 截取其中的方法名
        String methodName = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
        System.out.println("methodName = " + methodName + ", url = " + url);
        Method method = null;
        try {
            // 使用反射机制获取在本类中声明了的方法
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("调用方法出错！");
        }
    }

    private void queryEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行查询员工的方法...");
        response.setContentType("text/html;charset=utf8");
        PrintWriter pw = response.getWriter();
        pw.println("<h1>查询员工的方法</h1>");
        pw.flush();
        pw.close();
    }

    private void addEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行新增员工的方法...");
        response.setContentType("text/html;charset=utf8");
        PrintWriter pw = response.getWriter();
        pw.println("<h1>新增员工的方法</h1>");
        pw.flush();
        pw.close();
    }

    private void deleteEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行删除员工的方法...");
        response.setContentType("text/html;charset=utf8");
        PrintWriter pw = response.getWriter();
        pw.println("<h1>删除员工的方法</h1>");
        pw.flush();
        pw.close();
    }

    private void queryEmpList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行查询所有员工的方法...");
        response.setContentType("text/html;charset=utf8");
        PrintWriter pw = response.getWriter();
        pw.println("<h1>查询所有员工的方法</h1>");
        pw.flush();
        pw.close();
    }
}
