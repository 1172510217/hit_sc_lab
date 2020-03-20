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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import circularOrbit.SocialNetworkCircle;
import physicalObject.Friend;
import track.Track;

public class SocialNetworkSystemGUI {

    private JFrame frame = new JFrame();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();

    private JComboBox<String> comboBox1 = new JComboBox<String>();
    private JButton button2 = new JButton();
    private JTextField textField2 = new JTextField();
    private JComboBox<String> comboBox3 = new JComboBox<>();
    private JTextField textField4 = new JTextField();
    private JComboBox<String> comboBox5 = new JComboBox<>();
    private JTextField textField5 = new JTextField();
    private JComboBox<String> comboBox6 = new JComboBox<>();
    private JTextField textField6 = new JTextField();
    private JButton button7 = new JButton();
    private JTextField textField7 = new JTextField();
    private JButton button8 = new JButton();
    private JTextField textField8 = new JTextField();
    private JButton button9 = new JButton();

    private SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
    File socialNetworkFile = new File("");

    private void changeAtomStructure(int flag) throws IOException {
        if (flag == 1) {
            socialNetworkFile = new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle.txt");
        } else if (flag == 2) {
            socialNetworkFile = new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle_Medium.txt");
        } else if (flag == 3) {
            socialNetworkFile = new File("src/Spring2019_HITCS_SC_Lab3-master/SocialNetworkCircle_Larger.txt");
        } else {
            System.out.println("�ļ�ѡȡ��������");
            System.exit(0);
        }
        socialNetworkCircle = new SocialNetworkCircle();
        socialNetworkCircle.readFileAndCreateSystem(socialNetworkFile);
    }

    private void initFrame() {
        frame.setTitle("SocialNetwork System GUI @Author ZJR");
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
                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/Social Network.jpg"));
                imageIcon.setImage(
                        imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING));
                g.drawImage(imageIcon.getImage(), 0, 0, this);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) dimension.getWidth() / 4;
                int y = (int) (dimension.getHeight() - frame.getInsets().top) / 2;
                int count = socialNetworkCircle.getTracksNumber();
                g.setColor(Color.green);
                double r = (double) (x - 10) / count;
                if (socialNetworkCircle.getCentralPoint() != null) {
                    g.fillOval(x - 5, y - 5, 10, 10);
                }
                Graphics2D g2d = (Graphics2D) g;
                for (int i = count - 1; i >= 0; i--) {
                    double d = r * (i + 1);
                    g2d.setColor(Color.orange);
                    g2d.draw(new Ellipse2D.Double(x - d - 5, y - d - 5, 2 * d + 10, 2 * d + 10));
                    for (int j = 0; j < socialNetworkCircle.getTrack(i + 1).getNumberOfObjects(); j++) {
                        g2d.setColor(Color.green);
                        g2d.draw(new Ellipse2D.Double(x + Math.cos(j * 90) * (d + 5) - 5,
                                y + Math.sin(j * 90) * (d + 5) - 5, 10, 10));
                    }
                }
                g2d.setColor(Color.red);
                for (int i = 0; i < socialNetworkCircle.getTracksNumber(); i++) {
                    Track<Friend> track = socialNetworkCircle.getTrack(i + 1);
                    double d = r * (i + 1);
                    if (i == 0) {
                        for (int j = 0; j < track.getNumberOfObjects(); j++) {
                            g2d.drawLine(x, y, x + (int) (Math.cos(j * 90) * (r + 5)),
                                    y + (int) (Math.sin(j * 90) * (r + 5)));
                        }
                    }
                    for (int j = 0; j < track.getNumberOfObjects(); j++) {
                        Friend friend1 = track.getTrackObjects().get(j);
                        for (int k = 0; k < friend1.getAllFriends().size(); k++) {
                            Friend friend2 = friend1.getAllFriends().get(k);
                            if (!friend2.equals(socialNetworkCircle.getCentralPoint())) {
                                int fr2Radius = (int) friend2.getTrackRadius();
                                int numOfFr2 = socialNetworkCircle.getTrackByRadius(fr2Radius)
                                        .getPhysicalObjectIndex(friend2);
                                g2d.drawLine(x + (int) (Math.cos(numOfFr2 * 90) * (fr2Radius * r + 5)),
                                        y + (int) (Math.sin(numOfFr2 * 90) * (fr2Radius * r + 5)),
                                        x + (int) (Math.cos(j * 90) * (d + 5)), y + (int) (Math.sin(j * 90) * (d + 5)));
                            }
                        }
                    }
                }

            }
        };
    }

    private void initPanel2() {
        GridLayout gridLayout = new GridLayout(15, 1);
        panel2.setLayout(gridLayout);
        JLabel label = new JLabel("����չʾ");
        panel2.add(label);

        // ����һ���ļ�����
        comboBox1.addItem("�ļ�����");
        panel2.add(comboBox1);
        initFunc1();

        // �������ѣ������������еĲ���
        panel2.add(button2);
        panel2.add(textField2);
        initFunc2();

        // ɾ�����ѣ�Ҫɾ�����еĹ�ϵ
        panel2.add(comboBox3);
        initFunc3();
        comboBox3.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (comboBox3.getSelectedIndex() != 0) {
                        String[] strings = comboBox3.getSelectedItem().toString().split(":");
                        Friend friend = socialNetworkCircle.getFriendByName(strings[1]);
                        socialNetworkCircle.removeFriend(friend);
                        initFunc3();
                        textField4.setText("��ֵ��" + socialNetworkCircle.getSystemEntropy());
                        initFunc5();
                        comboBox5.repaint();
                        textField5.setText("��ѯ�����ڵڼ�������");
                        initFunc6();
                        comboBox6.repaint();
                        textField7.setText("������Ҫ���ӹ�ϵ���������������ܶȣ�friend1 friend2 intimacy");
                        textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
                    }
                }
            }
        });

        panel2.add(textField4);
        textField4.setEditable(false);
        textField4.setText("��ֵ��0");

        panel2.add(comboBox5);
        panel2.add(textField5);
        textField5.setEditable(false);
        textField5.setText("��ѯ�����ڵڼ�������");
        initFunc5();
        comboBox5.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (comboBox5.getSelectedIndex() != 0) {
                        String[] strings = comboBox5.getSelectedItem().toString().split(":");
                        Friend friend = socialNetworkCircle.getFriendByName(strings[1]);
                        if ((int) friend.getTrackRadius() == -1) {
                            textField5.setText(friend + "���ɱ���ʶ");
                        } else {
                            textField5.setText(friend + "�ڵ�" + (int) friend.getTrackRadius() + "������");
                        }
                        textField7.setText("������Ҫ���ӹ�ϵ���������������ܶȣ�friend1 friend2 intimacy");
                        textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
                    }
                }
            }
        });

        panel2.add(comboBox6);
        panel2.add(textField6);
        textField6.setEditable(false);
        textField6.setText("��ѯ�����û�ĳֱ�Ӻ��ѵ���ɢ�̶�");
        initFunc6();
        comboBox6.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (comboBox6.getSelectedIndex() != 0) {
                        String[] strings = comboBox6.getSelectedItem().toString().split(":");
                        Friend friend = socialNetworkCircle.getFriendByName(strings[1]);
                        textField6.setText(friend + "����Ϣ��ɢ�̶�Ϊ��" + socialNetworkCircle.Informationdiffusivity(friend));
                        textField7.setText("������Ҫ���ӹ�ϵ���������������ܶȣ�friend1 friend2 intimacy");
                        textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
                    }
                }
            }
        });

        panel2.add(button7);
        panel2.add(textField7);
        initFunc7();

        panel2.add(button8);
        panel2.add(textField8);
        initFunc8();

        panel2.add(button9);
        initFunc9();
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
                            changeAtomStructure(1);
                        } else if (e.getItem().equals("���ļ�")) {
                            changeAtomStructure(2);
                        } else if (e.getItem().equals("���ļ�")) {
                            changeAtomStructure(3);
                        }
                        initFunc3();
                        textField2.setText("������һ�����ѵ���Ϣ������ ���� �Ա�M|F��");
                        comboBox3.repaint();
                        textField4.setText("��ֵ��" + socialNetworkCircle.getSystemEntropy());
                        initFunc5();
                        comboBox5.repaint();
                        textField5.setText("��ѯ�����ڵڼ�������");
                        initFunc6();
                        comboBox6.repaint();
                        textField6.setText("��ѯ�����û�ĳֱ�Ӻ��ѵ���ɢ�̶�");
                        textField7.setText("������Ҫ���ӹ�ϵ���������������ܶȣ�friend1 friend2 intimacy");
                        textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
                    }
                } catch (Exception e2) {
                    System.out.println("δ�ҵ��ļ���");
                    e2.printStackTrace();
                }

            }
        });
    }

    private void initFunc2() {
        button2.setText("����һ������");
        button2.setFocusable(false);
        textField2.setText("������һ�����ѵ���Ϣ������ ���� �Ա�M|F��");
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String[] strings = textField2.getText().split(" ");
                    if (socialNetworkCircle.getFriendByName(strings[0]) != null) {
                        textField2.setText("�������ѱ�ռ�ã�����������");
                    } else {
                        Friend friend = new Friend();
                        friend.setFriendName(strings[0]);
                        friend.setAge(Integer.parseInt(strings[1]));
                        friend.setSex(strings[2]);
                        socialNetworkCircle.addFriend(friend);
                        textField2.setText(friend + "��ӳɹ�");
                        initFunc3();
                        textField4.setText("��ֵ��" + socialNetworkCircle.getSystemEntropy());
                        initFunc5();
                        comboBox5.repaint();
                        textField5.setText("��ѯ�����ڵڼ�������");
                        initFunc6();
                        comboBox6.repaint();
                        textField6.setText("��ѯ�����û�ĳֱ�Ӻ��ѵ���ɢ�̶�");
                        textField7.setText("������Ҫ���ӹ�ϵ���������������ܶȣ�friend1 friend2 intimacy");
                        textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
                    }
                } catch (Exception e2) {
                    textField2.setText("�����ʽ�Ƿ���");
                }
            }
        });
    }

    private void initFunc3() {
        comboBox3.removeAllItems();
        comboBox3.addItem("ɾ������");
        for (int i = 0, j = 0; i < socialNetworkCircle.getFriendNum(); i++) {
            if (!socialNetworkCircle.getFriend(i + 1).getFriendName()
                    .equals(socialNetworkCircle.getCentralPoint().getFriendName())) {
                comboBox3.addItem((++j) + ":" + socialNetworkCircle.getFriend(i + 1).getFriendName());
            }
        }
        textField4.setText("��ֵ��" + socialNetworkCircle.getSystemEntropy());
        textField6.setText("��ѯ�����û�ĳֱ�Ӻ��ѵ���ɢ�̶�");
        textField7.setText("������Ҫ���ӹ�ϵ���������������ܶȣ�friend1 friend2 intimacy");
        textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
    }

    private void initFunc5() {
        comboBox5.removeAllItems();
        comboBox5.addItem("�жϹ��");
        for (int i = 0, j = 0; i < socialNetworkCircle.getFriendNum(); i++) {
            if (!socialNetworkCircle.getFriend(i + 1).equals(socialNetworkCircle.getCentralPoint())) {
                comboBox5.addItem((++j) + ":" + socialNetworkCircle.getFriend(i + 1).getFriendName());
            }
        }
    }

    private void initFunc6() {
        comboBox6.removeAllItems();
        comboBox6.addItem("��ϵ��ɢ");
        if (socialNetworkCircle.getTracksNumber() == 0) {
            return;
        }
        Track<Friend> track = socialNetworkCircle.getTrack(1);
        for (int i = 0; i < track.getNumberOfObjects(); i++) {
            comboBox6.addItem((i + 1) + ":" + track.getTrackObjects().get(i).getFriendName());
        }
    }

    private void initFunc7() {
        button7.setText("���ӹ�ϵ");
        button7.setFocusable(false);
        textField7.setText("������Ҫ���ӹ�ϵ���������������ܶȣ�friend1 friend2 intimacy");
        button7.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String[] strings = textField7.getText().split(" ");
                if (strings.length == 3) {
                    Friend friend1 = socialNetworkCircle.getFriendByName(strings[0]);
                    Friend friend2 = socialNetworkCircle.getFriendByName(strings[1]);
                    if (friend1 != null && friend2 != null) {
                        socialNetworkCircle.addRelationAndRefactor(friend1, friend2, Double.parseDouble(strings[2]));
                        textField7.setText(friend1.toString() + friend2 + "�ѳɹ�����ӹ�ϵ��");
                        initFunc6();
                        comboBox6.repaint();
                    } else {
                        textField7.setText("�����û�������");
                    }
                } else {
                    textField7.setText("��������������������룡");
                }
                textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
            }
        });
    }

    private void initFunc8() {
        button8.setText("ɾ����ϵ");
        button8.setFocusable(false);
        textField8.setText("������Ҫɾ����ϵ������������friend1 friend2");
        button8.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String[] strings = textField8.getText().split(" ");
                if (strings.length == 2) {
                    Friend friend1 = socialNetworkCircle.getFriendByName(strings[0]);
                    Friend friend2 = socialNetworkCircle.getFriendByName(strings[1]);
                    if (friend1 != null && friend2 != null) {
                        if (friend1.getAllFriends().contains(friend2)) {
                            socialNetworkCircle.deleteRelationAndRefactor(friend1, friend2);
                            textField8.setText(friend1.toString() + friend2 + "�ѳɹ���ɾ����ϵ��");
                            initFunc6();
                            comboBox6.repaint();
                        } else {
                            textField8.setText("����������������޹�ϵ��");
                        }
                    } else {
                        textField8.setText("�����û�������");
                    }
                } else {
                    textField8.setText("��������������������룡");
                }
            }
        });
    }

    private void initFunc9() {
        button9.setText("��ѡ��Ϸϵͳ");
        button9.setFocusable(false);
        button9.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                App.main(null);
            }
        });
    }

    public void GUI() {
        initFrame();
        frame.validate();
    }

    @SuppressWarnings("unused") private static void showSocialNetworkSystem() {
        System.out.println("11��ɾ����ϵ\t12����ѡϵͳ");
    }
}
