package physicalobject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import physicalobject.StellarSystemObject;

class StellarSystemObjectTest {

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testToString() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals("[name=A]", planet.toString());
  }

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testGetPlanetName() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals("A", planet.getPlanetName());
  }

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testGetPlanetState() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals("B", planet.getPlanetState());
  }

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testGetPlanetColor() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals("C", planet.getPlanetColor());
  }

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testGetPlanetRadius() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals(100, planet.getPlanetRadius());
  }

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testGetRevolutionSpeed() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals(200, planet.getRevolutionSpeed());
  }

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testGetRevolutionDiretion() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals("CW", planet.getRevolutionDiretion());
  }

  /**
   * testing strategy : �������ԣ�ѡȡһ����������.
   */
  @Test void testGetAngle() {
    StellarSystemObject planet =
        new StellarSystemObject("A", "B", "C", 100, 200, "CW", 0);
    assertEquals(0, planet.getAngle());
  }

}
