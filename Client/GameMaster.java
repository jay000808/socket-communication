import java.io.*;
import java.net.*;
import java.util.*;

public class GameMaster {
    public Player player;

    // 指定時間ストップさせる関数
    public void parse(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ルールを説明する関数
    public void explain() {
        System.out.println(" ");
        parse(1000);
        System.out.println("--- ようこそ！ " + Player.getName() + "さん ---");
        parse(1000);
        System.out.println("--- 早稲田大学に関するクイズです ---");
        System.out.println("--- 答えだと思う番号の数字だけを入力してください ---");
        System.out.println(" ");
        parse(3000);
    }

    //ゲームをスタートさせる
    public void startGame() throws IOException {
        try {
            InetAddress addr = InetAddress.getByName("localhost"); // IP アドレスへの変換
            Socket socket = new Socket(addr, 8080);

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // データ受信用バッファの設定
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // データ送信バッファ設定
                
                int count = 10;
                while(count > 0) {
                    for(int i=0; i<2; i++) {
                        String quiz = in.readLine(); // クイズデータ受信
                        parse(1000);
                        System.out.println(quiz);
                    }
                    
                    String myanswer;
                    while(true) {
                        Scanner sc = new Scanner(System.in);
                        System.out.print("▶︎ ");
                        myanswer = sc.next();
                        if(myanswer.equals("1")||myanswer.equals("2")||myanswer.equals("3")) {
                            break;
                        } else {
                            System.out.println("1~3の数字で回答してください");                            
                        }
                    }

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

    private void evaluation(int count) {
        if(count >= 9) {
            System.out.println("--- 評価：A+ ---");
            System.out.println("--- あなたの早稲田への知識には脱帽です ---");
        } else if(count >= 8) {
            System.out.println("--- 評価：A ---");
            System.out.println("--- あなたの早稲田への知識は十分あります ---");
        } else if(count >= 7) {
            System.out.println("--- 評価：B ---");
            System.out.println("--- 早稲田の知識の更なる高みを目指しましょう ---");
        } else if(count >= 6) {
            System.out.println("--- 評価：C ---");
            System.out.println("--- 学問だけでなく早稲田のことも知ってあげましょう ---");
        } else {
            System.out.println("--- 評価：F ---");
            System.out.println("--- もう少し早稲田のことも好きになりましょう ---");
        }
    }

    public void showPoint(Player player) {
        parse(1000);
        System.out.println(" ");
        System.out.println("--- 正解数: " + player.getPoint() + " ---");
        parse(1000);
        evaluation(player.getPoint());
        System.out.println(" ");
        parse(3000);
        System.out.println("--- お疲れ様でした！ ---");
    }
}

