package apis;

import circularorbit.CircularOrbit;
import difference.Difference;

public class CircularOrbitApis<L, E> {

  public double getObjectDistributionEntropy(CircularOrbit<L, E> c) {
    return c.getSystemEntropy();
  }

  public Difference<L, E> getDifference(CircularOrbit<L, E> c1,
      CircularOrbit<L, E> c2) {
    return new Difference<>(c1, c2);
  }

}
