package circularOrbit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import applications.App;
import centralObject.Stellar;
import myException.CentralObjectException;
import myException.FileChooseException;
import myException.FileGrammerException;
import myException.LabelSameException;
import myException.PhysicalObjectException;
import myException.StellarRadiusException;
import physicalObject.StellarSystemObject;
import track.Track;

public class StellarSystem extends ConcreteCircularOrbit<Stellar, StellarSystemObject> {

    /* ��ϵͳ������ʹ��ʱ����readTime */
    private Calendar readTime = null;// ��ȡ�ļ�������ϵͳ��ʱ��
    private static final Logger logger = Logger.getLogger(App.class.getSimpleName());

    /**
     * @throws FileChooseException
     * @throws IOException
     */
    public void readFileAndCreateSystem(File file) throws FileChooseException, IOException {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bfReader = new BufferedReader(reader);
            String lineString = new String();
            Pattern pattern1 = Pattern.compile("Stellar ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
            Pattern pattern2 = Pattern.compile(
                    "Planet ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
            Matcher matcher;
            int lineCount = 0;
            int count = 0;
            while ((lineString = bfReader.readLine()) != null) {
                lineCount++;
                if ((matcher = pattern1.matcher(lineString)).find()) {
                    count++;
                    String group1 = matcher.group(1);
                    String group2 = matcher.group(2);
                    String group3 = matcher.group(3);
                    if (!Pattern.matches("[0-9a-zA-Z]+", group1)) {
                        bfReader.close();
                        throw new FileGrammerException("�������ִ����кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("([1-9][0-9]{1,3}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?)|[0]",
                            group2)) {
                        bfReader.close();
                        throw new FileGrammerException("���ǰ뾶�����кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("([1-9][0-9]{1,3}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?)|[0]",
                            group3)) {
                        bfReader.close();
                        throw new FileGrammerException("�������������кţ�" + lineCount, 10);
                    }
                    Stellar stellar = new Stellar();
                    stellar.setName(group1);
                    stellar.setRadius(Double.parseDouble(group2));
                    stellar.setMess(Double.parseDouble(group3));
                    addCentralPoint(stellar);
                } else if ((matcher = pattern2.matcher(lineString)).find()) {
                    String group1 = matcher.group(1), group2 = matcher.group(2), group3 = matcher.group(3),
                            group4 = matcher.group(4);
                    String group5 = matcher.group(5), group6 = matcher.group(6), group7 = matcher.group(7),
                            group8 = matcher.group(8);
                    if (!Pattern.matches("[0-9a-zA-Z]+", group1)) {
                        bfReader.close();
                        throw new FileGrammerException("�����������кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("[0-9a-zA-Z]+", group2)) {
                        bfReader.close();
                        throw new FileGrammerException("����״̬�����кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("[0-9a-zA-Z]+", group3)) {
                        bfReader.close();
                        throw new FileGrammerException("������ɫ�����кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("([1-9][0-9]{1,3}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?)|[0]",
                            group4)) {
                        bfReader.close();
                        throw new FileGrammerException("���ǰ뾶�����кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("([1-9][0-9]{1,3}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?)|[0]",
                            group5)) {
                        bfReader.close();
                        throw new FileGrammerException("����뾶�����кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("([1-9][0-9]{1,3}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?)|[0]",
                            group6)) {
                        bfReader.close();
                        throw new FileGrammerException("��ת�ٶȴ����кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("(CW)|(CCW)", group7)) {
                        bfReader.close();
                        throw new FileGrammerException("��ת��������кţ�" + lineCount, 10);
                    }
                    if (!Pattern.matches("[0-9]{1,3}([.][0-9]+)?", group8)) {
                        bfReader.close();
                        throw new FileGrammerException("��ʼ�Ƕȴ����кţ�" + lineCount, 10);
                    }
                    double angle = Double.parseDouble(group8);
                    if (angle < 0 || angle >= 360) {
                        bfReader.close();
                        throw new FileGrammerException("��ʼ�Ƕȷ�Χ�����кţ�" + lineCount, 10);
                    }
                    StellarSystemObject planet = new StellarSystemObject(group1, group2, group3,
                            Double.parseDouble(group4), Double.parseDouble(group6), group7, angle);
                    planet.setTrackRadius(Double.parseDouble(group5));
                    Track<StellarSystemObject> track = getTrackByRadius(Double.parseDouble(group5));
                    if (track == null) {
                        track = new Track<>(Double.parseDouble(matcher.group(5)));
                    }
                    track.add(planet);
                    addTrack(track);
                } else {
                    if (!lineString.equals("")) {
                        bfReader.close();
                        throw new FileGrammerException("������Ϣ�У�������" + lineCount, 10);
                    }
                }
            }
            if (count != 1) {
                bfReader.close();
                throw new CentralObjectException("����ȱʧ�����");
            }
            bfReader.close();
            sortTrack();
            checkRep();
        } catch (CentralObjectException e) {
            throw new FileChooseException("�ļ����ĺ��Ǵ�����Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (FileGrammerException e) {
            throw new FileChooseException("�ļ��﷨����������Ҫ����ѡ���ļ���" + e.getMessage());
        }
    }

    public void checkRep() throws FileChooseException {
        try {
            // �ж��������������Ψһ
            if (getCentralPoint() == null) {
                throw new CentralObjectException("���ĺ���ȱʧ");
            }
            Set<String> labelSet = new HashSet<>();
            labelSet.add(getCentralPoint().getName());
            for (int i = 0; i < getTracksNumber(); i++) {
                Track<StellarSystemObject> track = getTrack(i + 1);
                // �ж��Ƿ�һ�����������ֻ��һ������
                if (track.getNumberOfObjects() != 1) {
                    throw new PhysicalObjectException("�����������Ŀ�Ƿ�");
                }
                // �жϸ�ϵͳ���Ƿ���ڱ�ǩ��ͬ�����
                StellarSystemObject planet = track.getTrackObjects().get(0);
                if (!labelSet.add(planet.getPlanetName())) {
                    throw new LabelSameException("ϵͳ��������������ͬ���");
                }
                // �ж��Ƿ������������뾶֮��С�ڵ�����������뾶֮�͵����
                double radiusSum = planet.getPlanetRadius();
                double trackRadiusDif = planet.getTrackRadius();
                radiusSum = (i == 0) ? radiusSum + getCentralPoint().getRadius()
                        : radiusSum + getTrack(i).getTrackObjects().get(0).getPlanetRadius();
                trackRadiusDif = (i == 0) ? trackRadiusDif
                        : trackRadiusDif - getTrack(i).getTrackObjects().get(0).getTrackRadius();
                if (trackRadiusDif < radiusSum) {
                    throw new StellarRadiusException("���ǹ���뾶֮��С���������������뾶֮��:" + (i + 1));
                }
            }
        } catch (CentralObjectException e) {
            throw new FileChooseException("checkRep���յ��쳣����Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (PhysicalObjectException e) {
            throw new FileChooseException("checkRep���յ��쳣����Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (LabelSameException e) {
            throw new FileChooseException("checkRep���յ��쳣����Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (StellarRadiusException e) {
            throw new FileChooseException("checkRep���յ��쳣����Ҫ����ѡ���ļ���" + e.getMessage());
        }
    }

    /**
     * ����readTime
     * 
     * @param readTime
     */
    public void setReadTime(Calendar readTime) {
        this.readTime = readTime;
    }

    /**
     * �õ�readTime
     * 
     * @return ����readTime
     */
    public Calendar getReadTime() {
        return this.readTime;
    }

    /**
     * ��������ڶ�ȡ�ļ�ʱ�̵�ĳʱ�̵����ǽǶ�λ��
     * 
     * @param planet  �����������
     * @param newTime �µ�ʱ��
     * @return ������ʱ�̵ĽǶ�
     */
    public double calculatePosition(StellarSystemObject planet, Calendar newTime) {
        assert planet != null && newTime != null : logIn("��������null");
        long timeDif = newTime.getTimeInMillis() - readTime.getTimeInMillis();
        timeDif /= 1000;// �������
        double angleDif = planet.getRevolutionSpeed() * timeDif * 360 / (2 * Math.PI * planet.getTrackRadius());
        if (planet.getRevolutionDiretion() == "CCW") {
            angleDif += planet.getAngle();
            return angleDif % 360;
        } else {
            angleDif = planet.getAngle() - angleDif;
            angleDif %= 360;
            return angleDif < 0 ? angleDif + 360 : angleDif;
        }
    }

    /**
     * �õ���ȡ�ļ�ʱ����������֮����������
     * 
     * @param planet1 ����1
     * @param planet2 ����2
     * @return ������������֮����������
     */
    public double getPhysicalDistance(StellarSystemObject planet1, StellarSystemObject planet2) {
        assert planet1 != null && planet2 != null : logIn("��������null");
        double radius1 = planet1.getTrackRadius();
        double radius2 = planet2.getTrackRadius();
        double angleDif = planet1.getAngle() - planet2.getAngle();
        double distance = Math.pow(radius1, 2) + Math.pow(radius2, 2) - 2 * radius1 * radius2 * Math.cos(angleDif);
        return Math.pow(distance, 0.5);
    }

    /**
     * �õ�������ĳ����֮����������
     * 
     * @param planet ��������������;planet != null
     * @return ���ؾ���
     */
    public double getPhysicalDistanceStar(StellarSystemObject planet) {
        assert planet != null : logIn("��������null");
        return planet.getTrackRadius();
    }

    private static String logIn(String message) {
        logger.severe(message);
        return "�ѽ�assert������Ϣ��������־�ļ���";
    }

}
