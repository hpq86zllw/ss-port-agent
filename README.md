# 管理ss端口的微信小程序（代理端）  
## 相关  
[服务端](https://github.com/hpq86zllw/ss-port-server)  
[代理端](https://github.com/hpq86zllw/ss-port-agent)  
[客户端](https://github.com/hpq86zllw/ss-port-client)  
## 构建  
1. 运行maven生成jar包（需要JDK8）  
mvn clean package  
2. 把jar包放在ss服务器中，并创建如下文件夹  
logs，run，tomcat和config  
3. 在config文件夹中创建application.properties，在里面配置如下参数  
port=启动端口  
ss.manager.port=ss manager端口  
ss.min-port=ss最小使用端口  
ss.max-port=ss最大使用端口  
ss.encrypt-method=ss加密方法  
ss.host=展示给用户的ss服务器地址  
agent.base-url=代理端地址  
agent.max-flow-bytes=代理端每个端口的最大流量值  
server.base-url=服务端地址  
## 运行方法  
nohup java -jar ss-port-agent-0.0.1.jar &> logs/nohup.out &  