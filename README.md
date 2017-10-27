# 管理ss端口的微信小程序（代理端）  
## 相关  
[服务端](https://github.com/hpq86zllw/ss-port-server)  
[代理端](https://github.com/hpq86zllw/ss-port-agent)  
[客户端](https://github.com/hpq86zllw/ss-port-client)  
## 运行前  
运行maven生成jar包  
mvn clean package -Dhome=HOME -Dport=PORT -Dss.manager.port=SS_MANAGER_PORT -Dss.min-port=SS_MIN_PORT -Dss.max-port=SS_MAX_PORT -Dss.encrypt-method=SS_ENCRYPT_METHOD -Dss.host=SS_HOST -Dagent.base-url=AGENT_BASE_URL -Dagent.max-flow-bytes=AGENT_MAX_FLOW_BYTES -Dserver.base-url=SERVER_BASE_URL  
HOME 主目录  
PORT 启动端口  
SS_MANAGER_PORT ss manager端口  
SS_MIN_PORT ss最小使用端口  
SS_MAX_PORT ss最大使用端口  
SS_ENCRYPT_METHOD ss加密方法  
SS_HOST ss展示给用户的服务器地址  
AGENT_BASE_URL 代理端地址  
AGENT_MAX_FLOW_BYTES 代理端每个端口的最大流量值  
SERVER_BASE_URL 服务端地址  
## 运行方法  
cp target/ss-port-agent-0.0.1.jar HOME  
cd HOME  
mkdir logs  
mkdir run  
nohup java -jar ss-port-agent-0.0.1.jar &> logs/nohup.out &  