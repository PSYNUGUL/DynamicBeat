package dynamicBeat_v4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// Thread = 프로그램 안에 잇는 작은 또하나의 프로그램
public class Music extends Thread {

    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    public Music(String name, boolean isLoop) {
        // 예외처리
        try {
            // isLoop initialization
            this.isLoop = isLoop;

            // 음악 파일 가져오기
            // toURI() == 해당 파일 위치 가져오는데 쓰임
            file = new File(Main.class.getResource("../music/" + name).toURI());

            // 해당 파일을 버퍼에 담아서 읽어 올수있도록 함
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);

            // player는 해당 파일을 담음
            player = new Player(bis);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * 현재 실행되고있는 음악이 어떤위치에 잇는지 알려줌
     * 
     * EX) 3분짜리 음악에서 10초까지 재생되고있다면 10000을 return함
     *  0.001초 단위까지 알려줌
     *  
     * 노트 타이밍 맞출때 사용됨
     */
    public int getTime() {
        if (player == null) {
            return 0;
        }
        return player.getPosition();
    }

    /**
     * 음악이 어느구간에 있든 끌수잇도록 만들게 해줌 
     */
    public void close() {

        // 루프 값 거짓으로 바꾸기
        isLoop = false;

        // 플레이어 닫기
        player.close();

        // Thread 중지 (곡을 실행해주는 프로그램을 종료함)
        // 곡을 재생해주는 작은 프로그램이 따로 존재함 (extend한 파일)
        this.interrupt();
    }

    /**
     * Thread 상속받으면 무조건 사용해야되는 클래스
     */
    @Override
    public void run() {
        try {
            // 곡 실행
            do {
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop); // isLoop == true --> 무한반복

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
