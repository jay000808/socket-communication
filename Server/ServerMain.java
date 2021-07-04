import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Responser responser = new Responser();

        // クライアントの要求に応じてクイズゲームデータを送信
        responser.sendGameData();

        // クライアントはクイズゲームをプレイ中
    
    }

}

