package GUI;

import Algo.AllPairs;
import Algo.Dijkstra;
import Algo.Edge;
import Algo.TargetFixed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 鬼才 on 2015/12/6 0006.
 */
public class Map {
    private static JFrame jf = new JFrame("Navigation System");
    /** 1
     * all components' declaration
     */
    private static MapPanel jPanel;
    //left
    private static JLabel jLabel;   //map
    private static ImageIcon image;
    //right
    private static JPanel jPanel1;
    //right up
    private static JLabel jLabel1;  //"let's go travelling"-->Logo
    //right medium1
    private static JPanel jPanel1_1;
    private static JPanel jPanel1_2;
    private static JPanel jPanel1_3;
    private static JLabel jLabel1_1;
    private static JLabel jLabel1_2;
    private static TextField textField;
    private static TextField textField1;
    //right medium2
    private static JPanel jPanel2;
    private static JRadioButton jrbtWalk;
    private static JRadioButton jrbtBus;
    private static JRadioButton jrbtDrive;
    private static ButtonGroup buttonGroup;
    private static JPanel jPanel2_1;
    private static JButton jButton;
    private static JButton jButton1;
    //right bottom
    private static TextArea textArea;

    private static Edge[][] G;
//    private static Graphics g;
    static int[][] pointXY = {
            {70, 125, 152, 240, 262, 270, 274, 298, 410, 421, 427, 433, 435, 543, 585, 604, 661, 595, 717, 673/*T*/, 771/*U*/, 873, 952, 876, 903, 770},
            {218, 474, 596, 161, 239, 331, 423, 565, 76, 223, 300, 398, 477, 49, 166, 245, 312, 395, 375, 195/*T*/, 114, 24, 102, 108, 311, 357}};
    static JLabel[] points;
    static boolean lines = false;
    static int startPoint;
    static int endPoint;
    static int[] pi;

    public static void main(String[] args) throws Exception {
        /* 1
         * all components' declaration
         * */
        jPanel = new MapPanel();
        //left
        jLabel = new JLabel();   //map
        image = new ImageIcon("./data/map.png");
        //right
        jPanel1 = new JPanel();
        //right up
        jLabel1 = new JLabel();  //"let's go travelling"-->Logo
        //right medium1
        jPanel1_1 = new JPanel();
        jPanel1_2 = new JPanel();
        jPanel1_3 = new JPanel();
        jLabel1_1 = new JLabel("Start：");
        jLabel1_2 = new JLabel("End：");
        textField = new TextField(4);    //start
        textField1 = new TextField(4);   //end
        //right medium2
        jPanel2 = new JPanel();
        jrbtWalk = new JRadioButton("Walk");
        jrbtBus = new JRadioButton("Bus");
        jrbtDrive = new JRadioButton("Drive");
        buttonGroup = new ButtonGroup();
        jPanel2_1 = new JPanel();
        jButton = new JButton("Consult");
        jButton1 = new JButton("Resume");
        //right bottom
        textArea = new TextArea();

        G = Algo.Map.makeMap();

        /** 2
         * Layout the GUI
         */
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setSize(1550,650);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.add(jPanel);

        jPanel.setLayout(null);
        jPanel.setVisible(true);
        jPanel.setLocation(0,0);
        jPanel.setSize(1030,620);
//        jLabel.setSize(1024,607);
//        jLabel.setLocation(0,0);
//        image.setImage(image.getImage().getScaledInstance(1024,607,Image.SCALE_DEFAULT));
//        jLabel.setIcon(image);
//        jPanel.add(jLabel);
        instancializeLabels();

//        jPanel1.setLayout(new BoxLayout(jPanel1,BoxLayout.Y_AXIS));
        jPanel1.setLayout(null);
        jPanel1.setSize(500,650);
        jPanel1.setLocation(1035,0);    //
        jf.add(jPanel1);
//        jPanel1.setBackground(new Color(255,0,0));
        jLabel1.setSize(500,100);
        jPanel1.add(jLabel1);
        jLabel1.setText("Let's go travelling");
        jLabel1.setFont(new Font("TimesRoman", Font.BOLD,50));
        jPanel1.add(jPanel1_1);
        jPanel1_1.setLayout(new BoxLayout(jPanel1_1,BoxLayout.Y_AXIS));//垂直布局
        jPanel1_1.setFont(new Font("TimesRoman", Font.ITALIC,30));
        jPanel1_1.setSize(500,150);
        jPanel1_1.setLocation(0,100);
        jPanel1_1.add(jPanel1_2);
//        jPanel1_2.setSize(500,70);
        jPanel1_1.add(jPanel1_3);
        jPanel1_2.add(jLabel1_1);
        jPanel1_2.add(textField);
        jPanel1_3.add(jLabel1_2);
        jPanel1_3.add(textField1);
//        textField.setSize(100,50);
//        textField1.setSize(100,50);

        jPanel2.setSize(500,110);
        jPanel2.setLocation(0,250);
        jPanel2.setLayout(new BoxLayout(jPanel2,BoxLayout.Y_AXIS));
        buttonGroup.add(jrbtWalk);
        jPanel2.add(jrbtWalk);
        buttonGroup.add(jrbtBus);
        jPanel2.add(jrbtBus);
        buttonGroup.add(jrbtDrive);
        jPanel2.add(jrbtDrive);
        jPanel2.add(jPanel2_1);
        jPanel2_1.add(jButton);
        jPanel2_1.add(jButton1);
        jrbtWalk.setSelected(true);     //default: walk
        jPanel1.add(jPanel2);

        jPanel1.add(textArea);
        textArea.setSize(500,250);
        textArea.setLocation(0,360);
        textArea.setEditable(false);
        textArea.setText("Please enter the place you want to look up.");
        textArea.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        // add listener
        resumeListener(jButton1);
        consultListener(jButton);
        jPanel.repaint();
    }
    public static void instancializeLabels() {
        points = new JLabel[26];
        for(int i = 0; i < 26; i++) {
            points[i] = new JLabel("\u2022 "+(char)(i+'A'));   //for •
            jPanel.add(points[i]);
            points[i].setForeground(Color.RED);
            points[i].setSize(50,50);
            points[i].setLocation(pointXY[0][i],pointXY[1][i]-15);
//            points[i].setText("\u2022");
//            points[i].setBounds(pointXY[0][i], pointXY[1][i], 10, 10);
        }
    }
    public static void resumeListener(JButton resume) {
        resume.addActionListener((event) -> {
//                String set = " ";
                /**
                 * setText in TextComponent.java skip the setting if the string is empty or null
                 * thus I have to use a new TextField component
                 */
                jPanel1_2.remove(textField);
                textField = new TextField(4);
                jPanel1_2.add(textField);
                jPanel1_2.updateUI();
                jPanel1_3.remove(textField1);
                textField1 = new TextField(4);
                jPanel1_3.add(textField1);
                jPanel1_3.updateUI();
                //resume the radio button group
                jrbtWalk.setSelected(true);
                /**TODO
                 * resume the graph if the point has been
                 */

        });
    }
    public static void consultListener(JButton consult) throws Exception{
        consult.addActionListener((event)-> {
                String start = textField.getText();
                String end = textField1.getText();
                if(start != null && end != null) {
                    if(start.length() == 1 && end.length() == 1) {
                        char st = start.charAt(0);
                        char ed = end.charAt(0);
                        if('A' <= st && st <= 'Z' && 'A' <= ed && ed <= 'Z') {
                            //handle in this case
                            int mode;
                            if(jrbtWalk.isSelected()) {
                                mode = 0;
                            } else if(jrbtBus.isSelected()) {
                                mode = 1;
                            } else {
                                mode = 2;
                            }
                            pi = Dijkstra.dijAlgo(G, st-'A', mode);
                            String result;
                            if(pi[ed-'A'] == -1) {
                                result = st + "to" + ed + ": " +"Can't reach.";
                                textArea.setText(result);
                                try {
                                    TargetFixed.targetFixed(G, mode, ed-'A');
                                    AllPairs.allPairDij(G, mode);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                            result = Dijkstra.parsePi(G, pi, mode, st-'A', ed-'A', false);
                            result = st + "to" + ed + ": " + result;
                            textArea.setText(result);
                            lines = true;
                            startPoint = st - 'A';
                            endPoint = ed - 'A';
                            jPanel.repaint();
                            try {
                                TargetFixed.targetFixed(G, mode, ed-'A');
                                System.out.println("TargetFixed Over");
                                AllPairs.allPairDij(G, mode);
                                System.out.println("Allpairs Over");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                    }
                }
                textArea.setText("Input Error.");
        });
    }
}
class MapPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        ImageIcon img = new ImageIcon("./data/map.png");
        g.drawImage(img.getImage(), 4, 12, 1024, 607, this);
        if(Map.lines) {
            //TODO

            int[] p = Map.pi;
            int tmp = Map.endPoint;
            int s = Map.startPoint;
            int a ;
            while( tmp != s) {
                a = p[tmp];
                g.setColor(Color.GREEN);
                for(int i = 0; i < 5; i++) {
                    for(int j = 0; j < 5; j++) {
                        g.drawLine(Map.pointXY[0][a]+1+j, Map.pointXY[1][a]+10+i, Map.pointXY[0][tmp]+1+j, Map.pointXY[1][tmp]+10+i);
                    }
                }
                tmp = a;
            }
            Map.lines = false;
        }
    }
}
