import java.io.*;
import java.net.*;
import java.util.*;

public class Responser {

    // 問題データ
    private String[][] quizData = {
        {"早稲田大学の創設者は？\n[1]大隈重信 [2]伊藤博文 [3]福沢諭吉", "1"},
        {"1882年 (明治15年) 10月21日に早稲田大学の前身となる学校が設立されました。その前身となった学校の名は？\n[1]東京専門学校 [2]帝国大学 [3]蘭学塾", "1"},
    };

    //クライアントの要求に応じてゲームデータを送信
    public void sendGameData() throws IOException {
        try {
            ServerSocket s = new ServerSocket(8080); // ソケットを作成する
            Socket socket = s.accept();  // コネクション設定要求を待つ

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // データ受信用バッファの設定
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // データ送信バッファ設定
            
                for(int i=0; i<2; i++) {
                    out.println(quizData[i][0]);

                    String real_answer = quizData[i][1];
                    String myanswer = in.readLine(); // データの受信
                    if(real_answer.equals(myanswer)) {
                        out.println("正解！");
                    } else {
                        out.println("不正解！正解は" + real_answer + "です");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //スコアを受け取り、ランキングに登録する
    public void updateRanking() throws IOException {
        ServerSocket s = new ServerSocket(8080); // ソケットを作成する
        Socket socket = s.accept();  // コネクション設定要求を待つ
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String p_name = in.readLine();    //名前を受け取る
        String p_score = in.readLine();   //スコアを受け取る

        s.close();
    }

}
