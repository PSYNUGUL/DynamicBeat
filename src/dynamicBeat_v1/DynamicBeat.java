package dynamicBeat_v1;

// 컨트롤 + 쉬프트 + O = 필요한 요소 자동으로 import
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {

    public DynamicBeat() {

        // 제목
        setTitle("Dynamic Beat");

        // 창 사이즈 설정
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // 사용자가 임의적으로 창 사이즈를 못 줄임
        setResizable(false);

        // 창이 컴퓨터 화면의 정중앙에 뜨게 만듬
        setLocationRelativeTo(null);

        // 반드시 필요한 코드!
        // 게임창을 끄면 프로그램 전체가 종료됨
        // 이걸 안해주면 게임을 꺼도 프로그램이 내부에서 계속 돌아감
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 게임창 화면에 띄우기
        // Default = false (게임창에 화면에 안보임)
        setVisible(true);
    }
}
