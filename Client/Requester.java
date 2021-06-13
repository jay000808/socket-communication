import java.io.*;
import java.net.*;
import java.util.*;

public class Requester {

    public void requestGameData(GameData gameData){
        try {       
            ServerSocket s = new ServerSocket(8080);   // ポート番号を設定する
            
            s.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    //サーバーにスコアを送信し、ランキングを表示
    public void sendPoint(Player player){
        try {            
            String name = player.getName();
            String score = String.valueOf(player.getPoint());
            
            ServerSocket s = new ServerSocket(8080); 
            Socket socket = s.accept();
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            out.println(name);     //プレイヤーの名前を送信
            out.println(score);    //プレイヤーのスコアを送信

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //ランキングを表示
            for(int i=0; i<6; i++){
                String str = in.readLine();
                System.out.println(str);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
