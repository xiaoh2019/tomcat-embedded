package com.cyzs;

import org.apache.catalina.Context;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;

/**
 * @Description: 代码编写Tomcat
 * @Author xh
 * @create 2019-12-26 15:26
 */
public class MainTomcat {
    public static void main(String[] args) throws Exception{
        /**
         * win平台下启动Tomcat是读取配置文件，现在以编码的形式启动Tomcat
         * 所以要把以前配置文件的内容，写在代码里。
         * <Server>
         *       <Listener ></Listener>
         *       <Service >
         *           <Connector ></Connector>
         *           <Connector ></Connector>
         *           <Engine>
         *               <Realm></Realm>
         *               #虚拟主机
         *               <Host >
         *                   <context></context>
         *               </Host>
         *               <Host ></Host>
         *           </Engine>
         *       </Service>
         * </Server>
         */
        Tomcat tomcat = new Tomcat();

        Service service = tomcat.getService();
        //连接器 ,构造传入协议，或者不传默认HTTP/1.1
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(8080);
        connector.setAsyncTimeout(20000);
        //引擎
        StandardEngine standardEngine = new StandardEngine();
        //localhost这个和鞋面Host的名字一致
        standardEngine.setDefaultHost("localhost");
        standardEngine.setName("myTomcat");
        //host 加入到引擎里面
        StandardHost standardHost = new StandardHost();
        standardHost.setName("localhost");
        standardHost.setAppBase("webapps");

        standardEngine.addChild(standardHost);
        service.addConnector(connector);
        service.setContainer(standardEngine);
        //环境  contextPath是电脑目录位置  ，dir是项目名，加上之后请求路径要加上dir
        Context context = tomcat.addContext(standardHost, "", "/");
        context.addLifecycleListener(new ContextConfig());


        StandardWrapper standardWrapper = new StandardWrapper();
        standardWrapper.setServlet(new FirstServlet());
        standardWrapper.setName("myServlet");
        /**
         * StandardWrapper 加载到环境中  必须在standardWrapper.addMapping("/f");之前
         * 因为addMapping会判断 standardWrapper的父容器的情况
         */
        context.addChild(standardWrapper);
        standardWrapper.addMapping("/f");

        tomcat.start();
        tomcat.getServer().await();

    }
}
