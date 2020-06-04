package dynamicBeat_v4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

    // DoubleBuffering 기법
    // 이미지를 메모리에서 출력준비 시킴
    private Image screenImage;
    private Graphics screenGraphic;

    // introBackGround initialization
    private Image introBackground =
        new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();

    // menuBar initialization
    private JLabel menuBar =
        new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

    // 나가기 버튼 클릭(O)
    private ImageIcon exitButtonEnteredImage =
        new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));

    // 나가기 버튼 클릭(X)
    private ImageIcon exitButtonBasicImage =
        new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

    // exitButton Initialization
    private JButton exitButton = new JButton(exitButtonBasicImage);

    // 마우스의 x,y 좌표
    private int mouseX, mouseY;

    public DynamicBeat() {
        // 메뉴바가 실행시에 안보임
        setUndecorated(true);
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // paintComponent() 실행시 배경이 회색이 아닌 하얀색으로 바꿈
        setBackground(new Color(0, 0, 0, 0));

        // 버튼이나 JLabel을 넣었을때 위치 고정
        setLayout(null);

        // 나가는 버튼 위치와 크기를 정해줌
        exitButton.setBounds(1245, 0, 30, 30);

        // 버튼 효과 수정
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);

        exitButton.addMouseListener(new MouseAdapter() {
            // 마우스가 나가는 버튼 위에 올라와있을때 눌러져있는 이미지로 바꿈
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage);

                // 마우스가 올라가있을때 손가락 모양으로 커서가 바뀜
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // 버튼이 올라가있을때 "한번"(false) 소리가남
                Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
                buttonEnteredMusic.start();
            }

            // 마우스가 나가는버튼 위에 없을때 안눌러져있는 이미지로 다시 바꿔줌
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasicImage);

                // 마우스가 나가는 버튼을 지나왔을때는 다시 원래 커서로 바뀜
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            // 마우스가 나가는버튼을 클릭하면 프로그램이 나가짐
            @Override
            public void mousePressed(MouseEvent e) {
                // 버튼이 눌렸을때 "한번"(false) 소리가남
                Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
                buttonPressedMusic.start();

                // 바로 밑에 exit 을 넣게 되면 음악이 나오자마자 종료되기 때문에 안들림
                try {
                    // 소리가 나온후에 1초정도있다가 꺼짐
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });

        // JFrame에 나가는버튼이 추가 (paintComponent에 의해서)
        add(exitButton);

        // 메뉴바의 위치와 크기를 정해줌
        menuBar.setBounds(0, 0, 1280, 30);

        // 마우스 클릭 인식
        menuBar.addMouseListener(new MouseAdapter() {
            // 마우스 클릭할때 좌표 구하기
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        // 마우스 드래그 인식
        menuBar.addMouseMotionListener(new MouseAdapter() {
            // 메뉴바를 잡고 이동시킬수있게 만듬
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                // 드래그 할때마다 순간 좌표 계산
                setLocation(x - mouseX, y - mouseY);
            }
        });

        // JFrame에 메뉴바가 추가 (paintComponent에 의해서)
        add(menuBar);

        Music introMusic = new Music("이밤.mp3", true);
        introMusic.start();
    }

    /**
     * JFrame GUI에서 가장 처음으로 이미지를 출력하는 클래스
     * 순서는 절대적인 첫번째 (안바뀜)
     * 
     * 프로그램 크기만큼 이미지를 생성한뒤 그 이미지에 원하는 이미지를 그려줌
     */
    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(screenImage, 0, 0, null);
    }

    /**
     * introBackground를 이미지에 그려주고 paint() 클래스를 다시 부름
     * 
     * paint() 클래스와 상호작용하여 이미지를 프로그램에 출력
     */
    public void screenDraw(Graphics g) {
        g.drawImage(introBackground, 0, 0, null);

        // JFrame 안에 추가된 함수들을 그려줌
        // 이미지 그리는 다른방법
        // 메뉴바는 항상 있고 고정되있기 때문에 paintComponents()를 씀
        paintComponents(g);
        this.repaint();
    }
}
