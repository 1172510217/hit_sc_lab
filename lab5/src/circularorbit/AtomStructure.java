package circularorbit;

import applications.App;
import centralobject.CentralObject;
import iostrategy.IoStrategy;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import myexception.FileChooseException;
import physicalobject.PhysicalObject;
import track.Track;

public class AtomStructure
    extends ConcreteCircularOrbit<CentralObject, PhysicalObject> {

  private static final Logger logger =
      Logger.getLogger(App.class.getSimpleName());
  private File readFile = null;
  private String writeFilePath = null;

  /**
   * clone ����ʵ��.
   */
  @Override public Object clone() throws CloneNotSupportedException {
    AtomStructure atomStructure = (AtomStructure) super.clone();
    atomStructure.readFile = null;
    atomStructure.writeFilePath = null;
    return atomStructure;
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
    ioStrategy.readFileAndCreateSystem(this, readFile);
  }

  /**
   * transit an object to a track.
   * 
   * @param  physicalObject an object
   * @param  track          the new track
   * @return                true if the new track is not the same as the older
   *                        one; else false
   */
  public boolean transit(PhysicalObject physicalObject,
      Track<PhysicalObject> track) {
    assert physicalObject != null && track != null : logIn("��������null");
    assert getAlltracks().contains(track) : logIn("��������ϵͳ����track");
    if (physicalObject.getTrackRadius() == track.getRadius()) {
      assert track.contains(physicalObject)
          && physicalObject.getTrackRadius() == track.getRadius() : logIn(
              "ԾǨʧ��");
      return false;
    }
    if (deletePhysicalObject(physicalObject)) {
      physicalObject.setTrackRadius(track.getRadius());
      track.add(physicalObject);
      assert track.contains(physicalObject)
          && physicalObject.getTrackRadius() == track.getRadius() : logIn(
              "ԾǨʧ��");
      return true;
    } else {
      assert track.contains(physicalObject)
          && physicalObject.getTrackRadius() == track.getRadius() : logIn(
              "ԾǨʧ��");
      return false;
    }
  }

  /**
   * add an object to the system.
   * 
   * @param  physicalObject an object
   * @return                true if the object is not in the system; else false
   */
  public boolean addPhysicalObject(PhysicalObject physicalObject) {
    assert physicalObject != null : logIn("��������null");
    Track<PhysicalObject> track =
        getTrackByRadius(physicalObject.getTrackRadius());
    if (track == null) {
      track = new Track<>(physicalObject.getTrackRadius());
      track.add(physicalObject);
      this.addTrack(track);
      return true;
    } else {
      return track.add(physicalObject);
    }
  }

  /**
   * delete an object.
   * 
   * @param  physicalObject an object
   * @return                true if the object is in the system; else false
   */
  public boolean deletePhysicalObject(PhysicalObject physicalObject) {
    assert physicalObject != null : logIn("��������null");
    Track<PhysicalObject> track =
        getTrackByRadius(physicalObject.getTrackRadius());
    if (track != null) {
      return track.remove(physicalObject);
    } else {
      return false;
    }
  }

  private static String logIn(String message) {
    logger.severe(message);
    return "�ѽ�assert������Ϣ��������־�ļ���";
  }

}
