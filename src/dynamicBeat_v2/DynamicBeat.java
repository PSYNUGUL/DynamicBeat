package dynamicBeat_v2;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {

    // DoubleBuffering 기법
    // 이미지를 메모리에서 출력준비 시킴
    private Image screenImage;
    private Graphics screenGraphic;

    private Image introBackground;

    public DynamicBeat() {
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();



    }

    /**
     * JFrame GUI에서 가장 처음으로 이미지를 출력하는 클래스
     * 순서는 절대적인 첫번째 (안바뀜)
     * 
     * 프로그램 크기만큼 이미지를 생성한뒤 그 이미지에 원하는 이미지를 그려줌
     */
    public void paint(Graphics g) {
        // screenImage라는 이미지 만듬
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // screenImage를 이용해 그래픽 객체를 얻어옴
        screenGraphic = screenImage.getGraphics();

        // screenGraphic에 그림을 그려줌
        screenDraw(screenGraphic);

        // screenImage를 (0,0) 에 그려줌
        g.drawImage(screenImage, 0, 0, null);

    }

    /**
     * introBackground를 이미지에 그려주고 paint() 클래스를 다시 부름
     * 
     * paint() 클래스와 상호작용하여 이미지를 프로그램에 출력
     */
    public void screenDraw(Graphics g) {

        // 이미지 그리기
        g.drawImage(introBackground, 0, 0, null);

        // paint(Graphics g) 부름
        this.repaint();

    }
}
