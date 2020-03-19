package circularorbit;

import applications.App;
import centralobject.Stellar;
import iostrategy.IoStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import myexception.CentralObjectException;
import myexception.FileChooseException;
import myexception.LabelSameException;
import myexception.PhysicalObjectException;
import myexception.StellarRadiusException;
import physicalobject.StellarSystemObject;
import track.Track;

public class StellarSystem
    extends ConcreteCircularOrbit<Stellar, StellarSystemObject> {

  /* ��ϵͳ������ʹ��ʱ����readTime */
  private Calendar readTime = null;// ��ȡ�ļ�������ϵͳ��ʱ��
  private static final Logger logger =
      Logger.getLogger(App.class.getSimpleName());
  private File readFile = null;
  private String writeFilePath = null;

  /**
   * clone ����ʵ��.
   */
  @Override public Object clone() throws CloneNotSupportedException {
    StellarSystem stellarSystem = (StellarSystem) super.clone();
    stellarSystem.readTime = Calendar.getInstance();
    stellarSystem.readFile = null;
    stellarSystem.writeFilePath = null;
    return stellarSystem;
  }

  /**
   * set the file to read.
   * 
   * @param file read file
   */
  public void setReadFile(File file) {
    this.readFile = file;
  }

  /**
   * set the write file path.
   * 
   * @param writeFilePath write file path.
   */
  public void setWriteFilePath(String writeFilePath) {
    this.writeFilePath = writeFilePath;
  }

  /**
   * write file strategy.
   * 
   * @param  ioStrategy  strategy.
   * @throws IOException IO�쳣
   */
  public void saveSystemInfoInFile(IoStrategy ioStrategy) throws IOException {
    ioStrategy.saveSystemInfoInFile(this, writeFilePath);
  }

  /**
   * read file.
   * 
   * @param  ioStrategy          ���ļ��Ĳ���
   * @throws IOException         IO�쳣
   * @throws FileChooseException �ļ�ѡȡ�쳣
   */
  public void readFileAndCreateSystem(IoStrategy ioStrategy)
      throws IOException, FileChooseException {
    this.initSystem();
    ioStrategy.readFileAndCreateSystem(this, readFile);
  }

  public StellarSystemObject getPlanetByNum(int num) {
    return this.getTrack(num).getIndexPhysicalObject(1);
  }

  /**
   * note:����ֵ��ʾ�ɿ�ѧ����������ʽ.
   * 
   * @param  number Ҫת����ʽ����ֵ
   * @return        ���ؿ�ѧ��������ȷ��ʽ���ַ�����ʽ
   */
  public String enotationTransform(double number) {
    if (number < 10000) {
      return String.valueOf(number);
    } else {
      int count = 0;
      StringBuilder stringBuilder = new StringBuilder();
      while (number >= 10) {
        number /= 10;
        count++;
      }
      stringBuilder.append(number);
      stringBuilder.append("e");
      stringBuilder.append(count);
      return stringBuilder.toString();
    }
  }

  /**
   * ����ʾ������.
   * 
   * @throws FileChooseException �ļ�ѡȡ�쳣
   */
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
          throw new PhysicalObjectException(
              "�����������Ŀ�Ƿ�" + track + track.getNumberOfObjects());
        }
        // �жϸ�ϵͳ���Ƿ���ڱ�ǩ��ͬ�����
        StellarSystemObject planet = track.getIndexPhysicalObject(1);
        if (!labelSet.add(planet.getPlanetName())) {
          throw new LabelSameException("ϵͳ��������������ͬ���");
        }
        // �ж��Ƿ������������뾶֮��С�ڵ�����������뾶֮�͵����
        double radiusSum = planet.getPlanetRadius();
        double trackRadiusDif = planet.getTrackRadius();
        radiusSum =
            (i == 0) ? radiusSum + getCentralPoint().getRadius() : radiusSum
                + getTrack(i).getIndexPhysicalObject(1).getPlanetRadius();
        trackRadiusDif = (i == 0) ? trackRadiusDif : trackRadiusDif
            - getTrack(i).getIndexPhysicalObject(1).getTrackRadius();
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
   * ����readTime.
   * 
   * @param readTime ��ȡʱ�䣬Ĭ�ϵĳ�ʼϵͳʱ��
   */
  public void setReadTime(Calendar readTime) {
    this.readTime = readTime;
  }

  /**
   * �õ�readTime.
   * 
   * @return ����readTime
   */
  public Calendar getReadTime() {
    return this.readTime;
  }

  /**
   * ��������ڶ�ȡ�ļ�ʱ�̵�ĳʱ�̵����ǽǶ�λ��.
   * 
   * @param  planet  �����������
   * @param  newTime �µ�ʱ��
   * @return         ������ʱ�̵ĽǶ�
   */
  public double calculatePosition(StellarSystemObject planet,
      Calendar newTime) {
    assert planet != null && newTime != null : logIn("��������null");
    long timeDif = newTime.getTimeInMillis() - readTime.getTimeInMillis();
    timeDif /= 1000;// �������
    double angleDif = planet.getRevolutionSpeed() * timeDif * 360
        / (2 * Math.PI * planet.getTrackRadius());
    if (planet.getRevolutionDiretion().equals("CCW")) {
      angleDif += planet.getAngle();
      return angleDif % 360;
    } else {
      angleDif = planet.getAngle() - angleDif;
      angleDif %= 360;
      return angleDif < 0 ? angleDif + 360 : angleDif;
    }
  }

  /**
   * �õ���ȡ�ļ�ʱ����������֮����������.
   * 
   * @param  planet1 ����1
   * @param  planet2 ����2
   * @return         ������������֮����������
   */
  public double getPhysicalDistance(StellarSystemObject planet1,
      StellarSystemObject planet2) {
    assert planet1 != null && planet2 != null : logIn("��������null");
    double radius1 = planet1.getTrackRadius();
    double radius2 = planet2.getTrackRadius();
    double angleDif = planet1.getAngle() - planet2.getAngle();
    double distance = Math.pow(radius1, 2) + Math.pow(radius2, 2)
        - 2 * radius1 * radius2 * Math.cos(angleDif);
    return Math.pow(distance, 0.5);
  }

  /**
   * �õ�������ĳ����֮����������.
   * 
   * @param  planet ��������������;planet != null
   * @return        ���ؾ���
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
