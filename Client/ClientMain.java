import java.io.*;
import java.net.*;
import java.util.*;

public class ClientMain {

    public static void main(String[] args) throws IOException {
        
        Player player = new Player("", 0);
        GameMaster gameMaster = new GameMaster();
        // Requester requester = new Requester();
        
        //プレイヤーの名前を入力
        gameMaster.say("最初に名前を入力してください");
        System.out.print("▶︎ ");

        Scanner sc = new Scanner(System.in);
        String playerName = sc.nextLine(); //名前の入力
                
        player.setName(playerName);

        gameMaster.explain(); // ゲームの説明

        gameMaster.startGame();  //ゲームスタート
        sc.close();
        gameMaster.showPoint(player);
        // requester.sendPoint(player);
    }
}

