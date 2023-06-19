package cn.com.hospital ;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HospitalApplication implements CommandLineRunner {

    @Autowired
    private SocketIOServer socketIOServer;

    public static void main(String[] args)  {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Override
    public void run(String... strings) {
        socketIOServer.start();
        System.out.println("socket.io启动成功！");
    }

}
