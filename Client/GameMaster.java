import java.io.*;
import java.net.*;
import java.util.*;

public class GameMaster {
    public Player player;

    //ターミナル上でユーザー入力と区別できるように
    public void say(String s) {
        System.out.println("--- " + s + " ---");
    }

    // ルールを説明する関数
    public void explain() {
        System.out.println(" ");
        System.out.println("--- ようこそ！ " + Player.getName() + "さん ---");
        System.out.println("--- 早稲田大学に関するクイズです ---");
        System.out.println("--- 答えだと思う番号の数字だけを入力してください ---");
    }

    //ゲームをスタートさせる
    public void startGame() throws IOException {
        try {
            InetAddress addr = InetAddress.getByName("localhost"); // IP アドレスへの変換
            Socket socket = new Socket(addr, 8080);

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // データ受信用バッファの設定
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // データ送信バッファ設定
                
                int count = 2;
                while(count > 0) {
                    System.out.println(" ");

                    for(int i=0; i<2; i++) {
                        String quiz = in.readLine(); // クイズデータ受信
                        System.out.println(quiz);
                    }
                    
                    Scanner sc = new Scanner(System.in);
                    String myanswer = sc.next();

                    out.println(myanswer); // 回答データ送信

                    String judge = in.readLine(); // 判定データ受信
                    System.out.println(judge);

                    // スコアの更新
                    if(judge.equals("正解！")) {
                        player.incrementPoint();
                    }
                    count--;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPoint(Player player) {
        System.out.println(" ");
        System.out.println("--- 正解数: " + player.getPoint() + " ---");
    }

}

