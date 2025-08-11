
 package stopwatch;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StopWatchTest {
    @Test
    void ShouldRecordMinutesWhenPositiveValue(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.record(10);
        assertThat(stopWatch.getMinutes()).isEqualTo(10);
    }
    @Test
    void ShouldIgnoreMinutesWhenNegativeValue(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.record(-5);
        assertThat(stopWatch.getMinutes()).isEqualTo(0);
    }

    @Test
    void ShouldIncreaseHourWhenMinutesReach60(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.record(60);
        assertThat(stopWatch.getHours()).isEqualTo(1);
        assertThat(stopWatch.getMinutes()).isEqualTo(0);

    }

    @Test
    void ShouldIncreaseDayWhenHoursReach24(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.record(24*60);
        assertThat(stopWatch.getDays()).isEqualTo(1);
        assertThat(stopWatch.getMinutes()).isEqualTo(0);
        assertThat(stopWatch.getHours()).isEqualTo(0);

    }

    @Test
    void givenDailyWorkingHoursEight_whenRecordMinutes_thenIncreaseDaysAfter8Hours(){
        StopWatch stopWatch = new StopWatch(8);
        stopWatch.record(8*60);
        assertThat(stopWatch.getDays()).isEqualTo(1);
        assertThat(stopWatch.getMinutes()).isEqualTo(0);
        assertThat(stopWatch.getHours()).isEqualTo(0);

    }

    @Test
    void  shouldThrowIllegalArgumentExceptionWhenGivenInValidDailyWorkingHours(){
        assertThatThrownBy(() -> new StopWatch(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("greater than zero");

        assertThatThrownBy(() -> new StopWatch(-5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("greater than zero");
    }

}
