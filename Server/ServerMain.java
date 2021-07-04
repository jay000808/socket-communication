import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Responser responser = new Responser();

        //クライアントの要求に応じてゲームデータを送信
        responser.sendGameData();

        //クライアントはゲームをプレイ中・・・
    
    }

}

