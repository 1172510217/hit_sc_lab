package applications;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import circularOrbit.StellarSystem;
import physicalObject.StellarSystemObject;
import track.Track;

public class StellarSystemGUI {

    private JFrame frame = new JFrame();
    private JPanel panel1;
    private JPanel panel2 = new JPanel();
    private Map<String, Color> colorMap = new HashMap<String, Color>();
    private double time = 0;
    private int controlSpan = 0;

    private JComboBox<String> comboBox1 = new JComboBox<>();
    private JButton button2 = new JButton();
    private JComboBox<String> comboBox3 = new JComboBox<>();
    private JTextField textField4 = new JTextField();

    private JButton button5 = new JButton();
    private JTextField textField5 = new JTextField();

    private JButton button6 = new JButton();
    private JTextField textField6 = new JTextField();

    private JButton button7 = new JButton();

    private JButton button8 = new JButton();

    private StellarSystem stellarSystem = new StellarSystem();
    File stellarSystemFile = new File("");

    private void changeStellarSystem(int flag) throws IOException {
        if (flag == 1) {
            stellarSystemFile = new File("src/Spring2019_HITCS_SC_Lab3-master/StellarSystem.txt");
        } else if (flag == 2) {
            stellarSystemFile = new File("src/Spring2019_HITCS_SC_Lab3-master/StellarSystem_Medium.txt");
        } else if (flag == 3) {
            stellarSystemFile = new File("src/Spring2019_HITCS_SC_Lab3-master/StellarSystem_Larger.txt");
        } else {
            System.out.println("�ļ�ѡȡ��������");
            System.exit(0);
        }
        stellarSystem = new StellarSystem();
        stellarSystem.readFileAndCreateSystem(stellarSystemFile);
    }

    private void initFrame() {
        frame.setTitle("StellarSystem GUI @Author ZJR");
        frame.setLayout(new GridLayout());
        frame.setVisible(true);// ��Ϊ�ɼ�
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);// ���ô���ȫ��
        frame.setDefaultCloseOperation(3);

        initPanel2();
        initPanel1();

        frame.add(panel1);
        frame.add(panel2);
    }

    private void initPanel1() {
        panel1 = new JPanel() {

            @Override public void paint(Graphics g) {
                panel1.updateUI();
                super.paint(g);
                panel1.setBackground(Color.DARK_GRAY);
                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/Stellar.jpg"));
                imageIcon.setImage(
                        imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING));
                g.drawImage(imageIcon.getImage(), 0, 0, this);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) dimension.getWidth() / 4;
                int y = (int) (dimension.getHeight() - frame.getInsets().top) / 2;
                int count = stellarSystem.getTracksNumber();
                g.setColor(Color.green);
                double r = (double) (x - 10) / count;
                if (stellarSystem.getCentralPoint() != null) {
                    g.fillOval(x - 5, y - 5, 10, 10);
                }
                Graphics2D g2d = (Graphics2D) g;
                int direction = 0;
                for (int i = count - 1; i >= 0; i--) {
                    double d = r * (i + 1);
                    g2d.setColor(Color.orange);
                    g2d.draw(new Ellipse2D.Double(x - d - 5, y - d - 5, 2 * d + 10, 2 * d + 10));
                    for (int j = 0; j < stellarSystem.getTrack(i + 1).getNumberOfObjects(); j++) {
                        StellarSystemObject planet = stellarSystem.getTrack(i + 1).getTrackObjects().get(0);
                        String color = planet.getPlanetColor();
                        String directionString = planet.getRevolutionDiretion();
                        if (directionString.equals("CCW")) {
                            direction = -1;
                        } else if (directionString.equals("CW")) {
                            direction = 1;
                        } else {
                            System.out.println("������ת����");
                        }
                        double angle = planet.getAngle();
                        double angleDif = direction * planet.getRevolutionSpeed() * 360
                                / (2 * Math.PI * planet.getTrackRadius());
                        g2d.setColor(colorMap.get(color));
                        g2d.draw(new Ellipse2D.Double(
                                x + Math.cos(angle + (controlSpan % 2) * time * angleDif) * (d + 5) - 5,
                                y + Math.sin(angle + (controlSpan % 2) * angleDif * time) * (d + 5) - 5, 10, 10));
                    }
                }

            }
        };
    }

    private void initPanel2() {
        GridLayout gridLayout = new GridLayout(12, 1);
        panel2.setLayout(gridLayout);
        JTextField textField = new JTextField();
        textField.setText("����չʾ");
        textField.setFocusable(false);
        textField.setEditable(false);
        panel2.add(textField);

        // ����һ���ļ�����
        comboBox1.addItem("�ļ�����");
        panel2.add(comboBox1);
        initFunc1();

        panel2.add(button2);
        initFunc2();

        // ��������ɾ���ض���ŵĹ��
        panel2.add(comboBox3);
        initFunc3();
        comboBox3.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int num = comboBox3.getSelectedIndex();
                    if (num != 0) {
                        stellarSystem.deleteTrack(stellarSystem.getTrack(num));
                        panel1.repaint();
                        initFunc3();
                        comboBox3.repaint();
                        textField4.setText("��ֵ��" + stellarSystem.getSystemEntropy());
                    }
                }
            }
        });

        // �����ģ�����ϵͳ����ֵ
        textField4.setText("��ֵ��");
        textField4.setEditable(false);
        panel2.add(textField4);

        // �����壺����ĳʱ��ĳ����ĽǶ�λ��
        panel2.add(button5);
        panel2.add(textField5);
        initFunc5();

        // ������������ĳ��������֮��ľ���
        panel2.add(button6);
        panel2.add(textField6);
        initFunc6();

        panel2.add(button7);
        initFunc7();

        panel2.add(button8);
        initFunc8();
    }

    private void initFunc1() {
        comboBox1.addItem("С�ļ�");
        comboBox1.addItem("���ļ�");
        comboBox1.addItem("���ļ�");
        comboBox1.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                try {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (e.getItem().equals("С�ļ�")) {
                            changeStellarSystem(1);
                        } else if (e.getItem().equals("���ļ�")) {
                            changeStellarSystem(2);
                        } else if (e.getItem().equals("���ļ�")) {
                            changeStellarSystem(3);
                        }
                        initFunc3();
                        time = 0;
                        controlSpan = 0;
                        comboBox3.repaint();
                        textField4.setText("��ֵ��" + stellarSystem.getSystemEntropy());
                        stellarSystem.setReadTime(Calendar.getInstance());
                    }
                } catch (Exception e2) {
                    System.out.println("δ�ҵ��ļ���");
                }

            }
        });
    }

    private void initFunc2() {
        button2.setText("��ӹ��/����");
        button2.setFocusable(false);
        JTextField textField = new JTextField("��˳�����룺����뾶�����ơ���̬����ɫ���뾶����ת�ٶȡ���ת������ʼ�Ƕ�");
        panel2.add(textField);
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String[] strings = textField.getText().split(" ");
                    if (stellarSystem.getTrackByRadius(Double.parseDouble(strings[0])) == null) {
                        Track<StellarSystemObject> track = new Track<>(Double.parseDouble(strings[0]));
                        StellarSystemObject planet = new StellarSystemObject(strings[1], strings[2], strings[3],
                                Double.parseDouble(strings[4]), Double.parseDouble(strings[5]), strings[6],
                                Double.parseDouble(strings[7]));
                        planet.setTrackRadius(Double.parseDouble(strings[0]));
                        stellarSystem.addTrack(track);
                        stellarSystem.addTrackObject(track, planet);
                        initFunc3();
                        comboBox3.repaint();
                        textField4.setText("��ֵ��" + stellarSystem.getSystemEntropy());
                    }
                } catch (Exception e2) {
                    textField.setText("�����ʽ�Ƿ���");
                }

            }
        });

    }

    private void initFunc3() {
        comboBox3.removeAllItems();
        comboBox3.addItem("ɾ�����");
        for (int i = 0; i < stellarSystem.getTracksNumber(); i++) {
            comboBox3.addItem("" + (i + 1));
        }
    }

    private void initFunc5() {
        button5.setText("����λ��");
        button5.setFocusable(false);
        textField5.setText("��˳�����룺������� �� �� �� ʱ �� ��");
        button5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String[] strings = textField5.getText().split(" ");
                    if (Integer.parseInt(strings[0]) <= stellarSystem.getTracksNumber()) {
                        Calendar targetTime = Calendar.getInstance();
                        targetTime.set(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]),
                                Integer.parseInt(strings[3]), Integer.parseInt(strings[4]),
                                Integer.parseInt(strings[5]), Integer.parseInt(strings[6]));
                        if (targetTime.before(stellarSystem.getReadTime())) {
                            System.out.println("����ʱ��������ڶ�ȡʱ�䣡");
                        } else {
                            String targetTimeString = strings[1] + "��" + strings[2] + "��" + strings[3] + "��"
                                    + strings[4] + "ʱ" + strings[5] + "��" + strings[6] + "��";
                            textField5.setText("����" + strings[0] + "��ָ��ʱ��" + targetTimeString + "�ĽǶ�λ����"
                                    + stellarSystem.calculatePosition(stellarSystem
                                            .getTrack(Integer.parseInt(strings[0])).getTrackObjects().get(0),
                                            targetTime)
                                    + "��");
                        }
                    }
                } catch (Exception e2) {
                    textField5.setText("�����ʽ�Ƿ���");
                }
            }

        });

    }

    private void initFunc6() {
        button6.setText("�������");
        button6.setFocusable(false);
        textField6.setText("������ţ���һ������ �ڶ������壨���0��ʾ�������壩");
        button6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String[] strings = textField6.getText().split(" ");
                    int num1 = Integer.parseInt(strings[0]);
                    int num2 = Integer.parseInt(strings[1]);
                    if (num1 > stellarSystem.getTracksNumber() || num1 < 0 || num2 > stellarSystem.getTracksNumber()
                            || num2 < 0) {
                        textField6.setText("����Υ����������Χ��");
                    } else {
                        if (num1 == num2) {
                            System.out.println("����Υ������ֵһ����");
                        } else {
                            if (num1 == 0) {
                                textField6
                                        .setText(
                                                "����" + "������" + num2 + "�ľ����� "
                                                        + stellarSystem.getPhysicalDistanceStar(
                                                                stellarSystem.getTrack(num2).getTrackObjects().get(0))
                                                        + " KM");
                            } else if (num2 == 0) {
                                textField6
                                        .setText(
                                                "����" + num1 + "�ͺ��ǵľ����� "
                                                        + stellarSystem.getPhysicalDistanceStar(
                                                                stellarSystem.getTrack(num1).getTrackObjects().get(0))
                                                        + " KM");
                            } else {
                                textField6.setText("����" + num1 + "����" + num2 + "�ľ����� "
                                        + stellarSystem.getPhysicalDistance(
                                                stellarSystem.getTrack(num1).getTrackObjects().get(0),
                                                stellarSystem.getTrack(num2).getTrackObjects().get(0))
                                        + " KM");
                            }
                        }
                    }
                } catch (Exception e2) {
                    textField6.setText("�����ʽ�Ƿ���");
                }
            }
        });

    }

    private void initFunc7() {
        button7.setText("ģ�������˶�");
        button7.setFocusable(false);
        button7.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                controlSpan++;
                move();
            }
        });
    }

    private void initFunc8() {
        button8.setText("��ѡ��Ϸϵͳ");
        button8.setFocusable(false);
        button8.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                App.main(null);
            }
        });
    }

    private void move() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override public void run() {
                panel1.updateUI();
                time += 0.01;
            }
        };
        timer.schedule(task, 0, 10);
    }

    private void initColorMap() {
        colorMap.put("Pink", Color.pink);
        colorMap.put("Grey", Color.gray);
        colorMap.put("Azure", new Color(240, 255, 255));
        colorMap.put("Blue", Color.blue);
        colorMap.put("Green", Color.green);
        colorMap.put("Red", Color.red);
        colorMap.put("Yellow", Color.yellow);
        colorMap.put("White", Color.white);
        colorMap.put("Dark", Color.darkGray);
        colorMap.put("Purple", new Color(128, 0, 128));
        colorMap.put("Orange", Color.orange);
    }

    public void GUI() {
        initColorMap();// ����ɫ�ַ��������Ӧ����ɫ����һһ��Ӧ����
        initFrame();
        frame.validate();
    }
}
