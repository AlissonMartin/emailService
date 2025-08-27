package github.AlissonMartin.emailService.core.enums;

import java.time.temporal.ChronoUnit;

public enum Interval {
  DAY(ChronoUnit.DAYS),WEEK(ChronoUnit.WEEKS),MONTH(ChronoUnit.MONTHS);

  private final ChronoUnit chronoUnit;

  Interval(ChronoUnit chronoUnit) {
    this.chronoUnit = chronoUnit;
  }

  public ChronoUnit toChronoUnit() {
    return chronoUnit;
  }
}
