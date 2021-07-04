import java.io.*;
import java.net.*;
import java.util.*;

public class Responser {

    // 問題データ
    private String[][] quizData = {
        {"早稲田大学は何年に創立された？\n[1]1890年 [2]1882年 [3]1886年", "2"},
        {"早稲田大学理工学部は何年に基幹理工,創造理工,先進理工に別れたか？\n[1]2008年 [2]2005年 [3]2007年", "3"},
        {"早稲田大学理工3学部の学科数は?\n[1]20 [2]21 [3]19", "3"},
        {"基幹理工創設時は情報理工学科であったが情報通信学科は何年にできたか？\n[1]2014年 [2]2015年 [3]2013年", "1"},
        {"西早稲田キャンパスの以前の名称は？\n[1]新大久保キャンパス [2]大久保キャンパス [3]高田馬場キャンパス", "2"},
        {"基幹理工学部の現在の学部長は？\n[1]笠原先生 [2]山名先生 [3]戸川先生", "3"},
        {"情報理工学科の現在の教員数は？\n[1]17 [2]18 [3]16", "1"},
        {"情報理工学科の定員は？\n[1]80 [2]90 [3]70", "1"},
        {"情報理工学科の2018年4月における大学院への進学率は？\n[1]85% [2]45% [3]62%", "2"},
        {"情報理工学科の卒業に必要な単位数は？\n[1]126 [2]128 [3]125", "1"},
    };

    // クライアントの要求に応じてクイズゲームデータを送信
    public void sendGameData() throws IOException {
        try {
            ServerSocket s = new ServerSocket(8080); // ソケットを作成する
            Socket socket = s.accept();  // コネクション設定要求を待つ

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // データ受信用バッファの設定
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // データ送信バッファ設定
                
                for(int i=0; i<10; i++) {
                    out.println("Q"+ (i+1) + "：" + quizData[i][0]); // クイズデータの送信

                    String real_answer = quizData[i][1];
                    String myanswer = in.readLine(); // 回答データの受信
                    if(real_answer.equals(myanswer)) {
                        out.println("正解！");
                    } else {
                        out.println("不正解！正解は" + real_answer + "です！");
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

}
