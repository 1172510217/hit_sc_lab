package myexception;

public class FileGrammerException extends Exception {

  private int exceptionNum = -1;// �쳣�ţ�1x,2x,3x
  private String info = "";

  /**
   * get the exceptionNum.
   * 
   * @return the exceptionNum
   */
  public int getExceptionNum() {
    return exceptionNum;
  }

  /**
   * set the exceptionNum.
   * 
   * @param exceptionNum the exceptionNum to set
   */
  public void setExceptionNum(int exceptionNum) {
    this.exceptionNum = exceptionNum;
  }

  /**
   * get the info.
   * 
   * @return the info
   */
  public String getInfo() {
    return info;
  }

  /**
   * set the info.
   * 
   * @param info the info to set
   */
  public void setInfo(String info) {
    this.info = info;
  }

  public FileGrammerException() {
  }

  public FileGrammerException(String message) {
    super(message);
  }

  public FileGrammerException(String message, int exceptionNum) {
    super(message);
    this.exceptionNum = exceptionNum;
  }

  /**
   * Constructor.
   * 
   * @param message      Ҫ�������Ϣ
   * @param exceptionNum �쳣��
   * @param info         �������õ���Ϣ
   */
  public FileGrammerException(String message, int exceptionNum, String info) {
    super(message);
    this.info = info;
    this.exceptionNum = exceptionNum;
  }

  @Override public String toString() {
    return "FileGrammerException:" + getMessage();
  }
}
